package org.itstep.msk.app.model;

import org.itstep.msk.app.entity.Ingredient;

public class IngrAndQuantity {
    private Ingredient ingredient;
    private Double quantity;

    public IngrAndQuantity(Ingredient ingredient, Double quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
