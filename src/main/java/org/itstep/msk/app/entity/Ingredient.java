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
            inverseJoinColumns = @JoinColumn(name = "weight_id", referencedColumnName = "weight")
    )
    private Set<Dish> ingredientWeight;


}
