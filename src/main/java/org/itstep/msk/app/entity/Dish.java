package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dish {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String photo;

    @Column
    private Integer cost;

    @Column
    private String description;

    @Column
    private Integer weight;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @Column
    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
            name = "weight_ingredients",
            joinColumns = @JoinColumn(name = "weight_id", referencedColumnName = "weight"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    )
    private Set<Ingredient> weightSet;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Set<Ingredient> getWeightSet() {
        return weightSet;
    }

    public void setWeightSet(Set<Ingredient> weightSet) {
        this.weightSet = weightSet;
    }
}
