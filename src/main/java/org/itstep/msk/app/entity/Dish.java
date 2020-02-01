package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dish {
    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String photo;

    @Column
    private Integer cost;

    @Column
    private String description;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @Column
    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
            name = "weight_ingredients",
            joinColumns = @JoinColumn(name = "weight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    )
    private List<Ingredient> weight;

    @Column
    @ManyToMany(targetEntity = Order.class)
    @JoinTable(
            name = "orders_dishes",
            joinColumns = @JoinColumn(name = "dishes_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id", referencedColumnName = "id")
    )
    private List<Order> orders;

    public Long getId() {
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

    public List<Ingredient> getWeight() {
        return weight;
    }

    public void setWeight(List<Ingredient> weight) {
        this.weight = weight;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
