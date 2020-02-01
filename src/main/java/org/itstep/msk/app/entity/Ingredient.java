package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToMany(targetEntity = Dish.class , mappedBy = "weight")
    private List<Dish> ingredientWeight;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(List<Dish> ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }
}
