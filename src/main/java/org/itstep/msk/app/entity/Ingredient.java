package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @ManyToMany(targetEntity = Dish.class)
    @JoinTable(
            name = "weight_ingredients",
            joinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "weight_id", referencedColumnName = "id")
    )
    private Set<Dish> ingredientWeight;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dish> getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(Set<Dish> ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }
}
