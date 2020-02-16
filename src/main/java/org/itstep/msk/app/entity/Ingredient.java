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

    @OneToMany(targetEntity = DishesIngredients.class, mappedBy = "ingredient")
    private List<DishesIngredients> dishesIngredients;

    @ManyToMany(targetEntity = Storage.class)
    @JoinTable(
            name = "ingredients_storage",
            joinColumns = @JoinColumn(name = "ingredients_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "storage_id", referencedColumnName = "id")
    )
    private List<Storage> storages;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishesIngredients> getDishesIngredients() {
        return dishesIngredients;
    }

    public void setDishesIngredients(List<DishesIngredients> dishesIngredients) {
        this.dishesIngredients = dishesIngredients;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }
}
