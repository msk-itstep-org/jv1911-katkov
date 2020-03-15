package org.itstep.msk.app.service.impl;

import org.itstep.msk.app.entity.DishesIngredients;
import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.IngredientsStorage;
import org.itstep.msk.app.model.IngrAndQuantity;
import org.itstep.msk.app.repository.DishesIngredientsRepository;
import org.itstep.msk.app.repository.IngredientsStorageRepository;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientsQuantityServiceImpl implements IngredientsQuantityService {

    private DishesIngredientsRepository dishesIngredientsRepository;
    private IngredientsStorageRepository ingredientsStorageRepository;

    @Autowired
    public IngredientsQuantityServiceImpl(
            DishesIngredientsRepository dishesIngredientsRepository,
            IngredientsStorageRepository ingredientsStorageRepository
    ) {
        this.dishesIngredientsRepository = dishesIngredientsRepository;
        this.ingredientsStorageRepository = ingredientsStorageRepository;
    }

    // Находим сколько ингредиентов осталось на складе для КОНКРЕТНОГО блюда (нужно для как отдельный запрос)
    @Override
    public List<IngrAndQuantity> countIngredientsQuantityRest(Long dishId) {
        // нашел ингредиенты в блюде и их количество
        List<DishesIngredients> ingredientsInDish = getIngredientsByDish(dishId);
        // узнал количество выбранных элементов на складе
        List<IngrAndQuantity> ingredientsInStorage = new ArrayList<>();

        for (DishesIngredients dishesIngredients : ingredientsInDish) {
            ingredientsInStorage.add(countRestIngrInStorage(dishesIngredients.getIngredient()));
        }

        return ingredientsInStorage;
    }

    // вычитаем элементы из имеющихся на складе
    private void removeIngredientsFromStorage (Long dishId, List<IngrAndQuantity> ingrAndQuantities) {
        List<DishesIngredients> ingredietsInDish = getIngredientsByDish(dishId); // задвоение

        for (DishesIngredients ingredietsInDish1 : ingredietsInDish) {
            Double dishIngrWeight = ingredietsInDish1.getWeight();
            IngrAndQuantity ingrInStorage = ingrAndQuantities.get(ingrAndQuantities.indexOf(ingredietsInDish1));

            if (dishIngrWeight > ingrInStorage.getQuantity()) {
                throw new RuntimeException("Маловато будет");
            } else {
                // получил ингредиенты на складе
                List<IngredientsStorage> tempIngrs = ingredientsStorageRepository
                        .findAllByIngredient(ingrInStorage.getIngredient());

                double restWeight = dishIngrWeight;
                for (IngredientsStorage tempIngr : tempIngrs) {

                    if (restWeight <= tempIngr.getQuantity()) {
                        tempIngr.setQuantity(tempIngr.getQuantity() - dishIngrWeight);
                        break;
                    } else {
                        // вычли и обнулили
                        restWeight -= tempIngr.getQuantity();
                        tempIngr.setQuantity(0.0);
                    }
                }
            }
        }
    }

    // узнать какие ингредиенты есть в блюде (мы знаем ингредиенты и их вес)
    private List<DishesIngredients> getIngredientsByDish(Long dishId) {
        return dishesIngredientsRepository.ingredientsForDish(dishId);
    }

    // берем ОДИН ингредиент и считаем сколько его есть на складе
    private IngrAndQuantity countRestIngrInStorage(Ingredient ingredient) {
        List<IngredientsStorage> storagesIngr = ingredientsStorageRepository.findAllByIngredient(ingredient);
        double sumOfQuantity = 0.0;
        for (IngredientsStorage storage : storagesIngr) {
            sumOfQuantity += storage.getQuantity();
        }
        return new IngrAndQuantity(ingredient, sumOfQuantity);
    }

}
