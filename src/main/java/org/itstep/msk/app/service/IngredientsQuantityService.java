package org.itstep.msk.app.service;

import org.itstep.msk.app.entity.Ingredient;

import java.util.List;
import java.util.Map;

public interface IngredientsQuantityService {
    Map<Ingredient, Integer> countIngredientsQuantityRest(Long dishId);

    void minusIngredients(List<Ingredient> ingredients);
}
