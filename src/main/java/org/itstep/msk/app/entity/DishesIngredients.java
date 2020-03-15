package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dishes_ingredients")
public class DishesIngredients {

    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Dish.class)
    @JoinColumn(name = "dishes_id", referencedColumnName = "id")
    private Dish dish;

    @ManyToOne(targetEntity = Ingredient.class)
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    private Ingredient ingredient;

    @Column
    private Double weight;


    public Long getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
