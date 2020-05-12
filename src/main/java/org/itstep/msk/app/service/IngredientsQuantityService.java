package org.itstep.msk.app.service;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.entity.OrderDish;
import org.itstep.msk.app.model.IngrAndQuantity;

import java.util.List;

public interface IngredientsQuantityService {
    List<IngrAndQuantity> countIngredientsQuantityRest(Dish dish);

//    List<IngrAndQuantity> countAllIngredients();

    Integer countDishesCanCook (Dish dish);

    void removeIngredientsFromStorage (Dish dish, Integer dishQuantity);

    void returnIngredientsToStorage(Dish dish, Integer dishQuantity);
}
