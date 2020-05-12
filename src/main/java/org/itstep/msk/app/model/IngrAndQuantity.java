package org.itstep.msk.app.model;

import org.itstep.msk.app.entity.Ingredient;

public class IngrAndQuantity {
    private Ingredient ingredient;
    private Integer quantity;

    public IngrAndQuantity(Ingredient ingredient, Integer quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
