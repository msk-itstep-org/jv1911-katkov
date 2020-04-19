package org.itstep.msk.app.service.impl;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.DishIngredient;
import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.IngredientStorage;
import org.itstep.msk.app.model.IngrAndQuantity;
import org.itstep.msk.app.repository.DishIngredientRepository;
import org.itstep.msk.app.repository.IngredientStorageRepository;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientsQuantityServiceImpl implements IngredientsQuantityService {

    private DishIngredientRepository dishIngredientRepository;
    private IngredientStorageRepository ingredientStorageRepository;

    @Autowired
    public IngredientsQuantityServiceImpl(
            DishIngredientRepository dishIngredientRepository,
            IngredientStorageRepository ingredientStorageRepository
    ) {
        this.dishIngredientRepository = dishIngredientRepository;
        this.ingredientStorageRepository = ingredientStorageRepository;
    }

    // Находим сколько ингредиентов осталось на складе для КОНКРЕТНОГО блюда (нужно для отдельного запроса)
    @Override
    public List<IngrAndQuantity> countIngredientsQuantityRest(Dish dish) {
        // нашел ингредиенты в блюде и их количество
        List<DishIngredient> ingredientsInDish = getIngredientsByDish(dish);
        // узнал количество выбранных элементов на складе
        List<IngrAndQuantity> ingredientsInStorage = new ArrayList<>();

        for (DishIngredient dishIngredient : ingredientsInDish) {
            ingredientsInStorage.add(countRestIngrInStorage(dishIngredient.getIngredient()));
        }

        return ingredientsInStorage;
    }

    // Сколько всего ингредиентов (на будующее для менеджера)
    @Override
    public List<IngrAndQuantity> countAllIngredients() {
        List<IngredientStorage> ingredientStorages = ingredientStorageRepository.findAll();
        Set<IngrAndQuantity> ingrAndQuantityHashSet = new HashSet<>();

        for (IngredientStorage ingredientStorage : ingredientStorages) {
            IngrAndQuantity ingrAndQuantity = countRestIngrInStorage(ingredientStorage.getIngredient());
            ingrAndQuantityHashSet.add(ingrAndQuantity);
        }
        ArrayList<IngrAndQuantity> result = new ArrayList<>(ingrAndQuantityHashSet);
        result.sort(Comparator.comparing(x -> x.getIngredient().toString()));

        return result;
    }
    // Сколько блюд еще можно приготовить
    @Override
    public Integer countDishesCanCook (Dish dish) {
        List<DishIngredient> dishesIngredients = getIngredientsByDish(dish);
        double result = Integer.MAX_VALUE;
        for(DishIngredient oneDI : dishesIngredients) {
            //сколько ингредиента есть на складе
            IngrAndQuantity oneIngredient = countRestIngrInStorage(oneDI.getIngredient());
            double count = Math.floor(oneIngredient.getQuantity() / oneDI.getWeight());
            if (count < result) {
                result = count;
            }
        }
        return (int) result;
    }

    // вычитаем ВСЕ элементы из имеющихся на складе
    @Override
    public void removeIngredientsFromStorage (Dish dish, Integer dishQuantity) {

        // ингредиенты в блюде и их количество
        List<DishIngredient> ingredientsInDishes = getIngredientsByDish(dish);

        for (DishIngredient ingredientsInDish : ingredientsInDishes) {
            removeOneIngredient(
                    ingredientsInDish.getIngredient(),
                    ingredientsInDish.getWeight() * dishQuantity
            );
        }
    }

    // узнать какие ингредиенты есть в блюде (мы знаем ингредиенты и их вес)
    private List<DishIngredient> getIngredientsByDish(Dish dish) {
        return dishIngredientRepository.findAllByDish(dish);
    }

    // берем ОДИН ингредиент и считаем сколько его есть на складе
    private IngrAndQuantity countRestIngrInStorage(Ingredient ingredient) {
        List<IngredientStorage> storagesIngr = ingredientStorageRepository.findAllByIngredient(ingredient);
        double sumOfQuantity = 0.0;
        for (IngredientStorage storage : storagesIngr) {
            sumOfQuantity += storage.getQuantity();
        }
        return new IngrAndQuantity(ingredient, sumOfQuantity);
    }

    // вычитаем ОДИН ингредиент из имеющиехся на складе
    private void removeOneIngredient(Ingredient ingredient, Double quantityOfOrder) {
        List<IngredientStorage> storagesIngrs = ingredientStorageRepository.findAllByIngredient(ingredient);
        storagesIngrs.sort((x, y) -> {
            if (x.getReceiptDate().before(y.getReceiptDate())) {
                return 1;
            } else if (x.getReceiptDate().after(y.getReceiptDate())) {
                return -1;
            } else {
                return 0;
            }
        });

        // Проверка сколько всего ингредиентов есть на складе
        if (quantityOfOrder > countRestIngrInStorage(ingredient).getQuantity()) {
            throw new RuntimeException("WTF");
        }

        double ingrWeight = quantityOfOrder;
        IngredientStorage result = new IngredientStorage();

        for (IngredientStorage storagesIngr : storagesIngrs) {

            double currentIngrQuantity = storagesIngr.getQuantity();
            if (currentIngrQuantity >= ingrWeight) {
                result.setQuantity(currentIngrQuantity - ingrWeight);
                result.setIngredient(ingredient);
                result.setPriceForKilo(storagesIngr.getPriceForKilo());
                result.setReceiptDate(storagesIngr.getReceiptDate());
                ingredientStorageRepository.delete(storagesIngr);
                ingredientStorageRepository.save(result);
                break;
            } else {
                ingredientStorageRepository.delete(storagesIngr);
                ingrWeight -= currentIngrQuantity;
            }
        }
        ingredientStorageRepository.flush();
    }
}
