package org.itstep.msk.app.entity;

import org.hibernate.annotations.Parent;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Название ингредиента не должно быть пустым")
    @Size(min = 3, max = 30, message = "Название ингредиента не должно быть меньше 3 знаков и больше 30")
    @Pattern(regexp = "[\\D]+", message = "Название ингредиента не должно содержать цифр")
    @Column(length = 100)
    private String name;

    @Column(name = "active", nullable = false, columnDefinition = "BIT")
    private Boolean active = true;

    @OneToMany(targetEntity = DishIngredient.class, mappedBy = "ingredient")
    private List<DishIngredient> dishesIngredients;

    @OneToMany(targetEntity = IngredientStorage.class, mappedBy = "ingredient")
    private List<IngredientStorage> ingredientStorages;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishIngredient> getDishesIngredients() {
        return dishesIngredients;
    }

    public void setDishesIngredients(List<DishIngredient> dishesIngredients) {
        this.dishesIngredients = dishesIngredients;
    }

    public List<IngredientStorage> getIngredientStorages() {
        return ingredientStorages;
    }

    public void setIngredientStorages(List<IngredientStorage> ingredientStorages) {
        this.ingredientStorages = ingredientStorages;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
