package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dishes_ingredients")
public class DishIngredient {

    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Dish.class)
    @JoinColumn(name = "dishes_id", referencedColumnName = "id")
    private Dish dish;

    @OrderBy("name")
    @ManyToOne(targetEntity = Ingredient.class)
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    private Ingredient ingredient;

    @Column
    private Integer weight;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
