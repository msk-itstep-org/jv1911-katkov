package org.itstep.msk.app.service.impl;

import org.itstep.msk.app.entity.DishesIngredients;
import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.Storage;
import org.itstep.msk.app.repository.DishesIngredientsRepository;
import org.itstep.msk.app.repository.StorageRepository;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IngredientsQuantityServiceImpl implements IngredientsQuantityService {

    private DishesIngredientsRepository dishesIngredientsRepository;

    private StorageRepository storageRepository;

    @Autowired
    public IngredientsQuantityServiceImpl(
            DishesIngredientsRepository dishesIngredientsRepository,
            StorageRepository storageRepository
    ) {
        this.dishesIngredientsRepository = dishesIngredientsRepository;
        this.storageRepository = storageRepository;
    }

    @Override
    public Map<Ingredient, Integer> countIngredientsQuantityRest(Long dishId) {

        // узнать сколько нужно каждого ингредиента для блюда (узнали)

        // узнать сколько каждого ингредиента осталось на складе

        // вычесть из ингредиентов если что-то заказали


     return null;
    }

    @Override
    public void minusIngredients(List<Ingredient> ingredients) {
        List<Storage> storagesIngr = storageRepository.findIngrQuantityInStorage(ingredients);
    }

    private List<DishesIngredients> getIngredientsByDish(Long dishId) {
        // узнать какие ингредиенты есть в блюде (мы знаем ингредиенты и их вес)
        return dishesIngredientsRepository.ingredientsForDish(dishId);
    }
}
