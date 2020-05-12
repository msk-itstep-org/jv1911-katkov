package org.itstep.msk.app.service.impl;

import org.itstep.msk.app.entity.*;
import org.itstep.msk.app.model.IngrAndQuantity;
import org.itstep.msk.app.repository.DishIngredientRepository;
import org.itstep.msk.app.repository.IngredientStorageRepository;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
//
//    // Сколько всего ингредиентов (на будующее для менеджера)
//    @Override
//    public List<IngrAndQuantity> countAllIngredients() {
//        List<IngredientStorage> ingredientStorages = ingredientStorageRepository.findAll();
//        Set<IngrAndQuantity> ingrAndQuantityHashSet = new HashSet<>();
//
//        for (IngredientStorage ingredientStorage : ingredientStorages) {
//            IngrAndQuantity ingrAndQuantity = countRestIngrInStorage(ingredientStorage.getIngredient());
//            ingrAndQuantityHashSet.add(ingrAndQuantity);
//        }
//        ArrayList<IngrAndQuantity> result = new ArrayList<>(ingrAndQuantityHashSet);
//        result.sort(Comparator.comparing(x -> x.getIngredient().toString()));
//
//        return result;
//    }
    // Сколько блюд еще можно приготовить
    @Override
    public Integer countDishesCanCook (Dish dish) {
        List<DishIngredient> dishesIngredients = getIngredientsByDish(dish);
        double result = Integer.MAX_VALUE;
        for(DishIngredient oneDI : dishesIngredients) {
            //сколько ингредиента есть на складе
            IngrAndQuantity oneIngredient = countRestIngrInStorage(oneDI.getIngredient());
            int count = (int) (oneIngredient.getQuantity() / oneDI.getWeight());
            if (count < result) {
                result = count;
            }
        }
        return (int) result;
    }

    // вычитаем ВСЕ элементы из имеющихся на складе для конкретного блюда и количества блюд
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

    @Override
    public void returnIngredientsToStorage(Dish dish, Integer dishQuantity) {
        // ингредиенты в блюде и их количество
        List<DishIngredient> ingredientsInDishes = getIngredientsByDish(dish);

        for (DishIngredient ingredientsInDish : ingredientsInDishes) {
            returnOneIngredient(
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
        List<IngredientStorage> storagesIngr = ingredientStorageRepository
                .findAllByIngredientOrderByReceiptDate(ingredient);
        //TODO Фильтр по просроченным
        int sumOfQuantity = 0;
        for (IngredientStorage storage : storagesIngr) {
            sumOfQuantity += storage.getQuantity();
        }
        return new IngrAndQuantity(ingredient, sumOfQuantity);
    }

    // вычитаем ОДИН ингредиент из имеющиехся на складе
    private void removeOneIngredient(Ingredient ingredient, Integer quantityOfOrder) {
        List<IngredientStorage> storagesIngrs = ingredientStorageRepository
                .findAllByIngredientOrderByReceiptDate(ingredient);

        // Проверка сколько всего ингредиентов есть на складе
        if (quantityOfOrder > countRestIngrInStorage(ingredient).getQuantity()) {
            throw new RuntimeException("WTF");
        }

        int ingrWeight = quantityOfOrder;

        for (IngredientStorage storagesIngr : storagesIngrs) {
            int currentIngrQuantity = storagesIngr.getQuantity();

            if (currentIngrQuantity >= ingrWeight) {
                int usedIngredient = storagesIngr.getQuantityUsed();
                storagesIngr.setQuantityUsed(usedIngredient + ingrWeight);
                storagesIngr.setQuantity(currentIngrQuantity - ingrWeight);
                ingredientStorageRepository.save(storagesIngr);
                break;
            } else {
                storagesIngr.setQuantityUsed(storagesIngr.getQuantityUsed() + currentIngrQuantity);
                storagesIngr.setQuantity(0);
                ingrWeight -= currentIngrQuantity;
                ingredientStorageRepository.save(storagesIngr);
            }
        }
        ingredientStorageRepository.flush();
    }

    // возвращаеи ОДИН ингредиент на склад
    private void returnOneIngredient(Ingredient ingredient, Integer quantityOfOrder) {
        // нашли все оставшиеся игнгедиенты на складе и отсортировале по дате
        List<IngredientStorage> storagesIngrs = ingredientStorageRepository
                .findAllByIngredientOrderByReceiptDate(ingredient);

        // TODO нужно добавить в условие запроса
        storagesIngrs = storagesIngrs
                .stream()
                .filter(x -> x.getQuantityUsed() > 0)
                .collect(Collectors.toList());

        int ingrWeight = quantityOfOrder;
        for (IngredientStorage storagesIngr : storagesIngrs) {
            int currentUsedQuantity = storagesIngr.getQuantityUsed();

            if (currentUsedQuantity >= ingrWeight) {
                storagesIngr.setQuantityUsed(currentUsedQuantity - ingrWeight);
                storagesIngr.setQuantity(storagesIngr.getQuantity() + ingrWeight);
                ingredientStorageRepository.save(storagesIngr);
                System.out.println(storagesIngr.getIngredient().getName());
                break;
            } else {
                storagesIngr.setQuantity(storagesIngr.getQuantity() + currentUsedQuantity);
                storagesIngr.setQuantityUsed(0);
                ingrWeight -= currentUsedQuantity;
                ingredientStorageRepository.save(storagesIngr);
            }
        }
        ingredientStorageRepository.flush();
    }
}
