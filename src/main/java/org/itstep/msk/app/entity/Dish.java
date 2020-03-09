package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(targetEntity = DishesIngredients.class, mappedBy = "dish")
    private List<DishesIngredients> dishesIngredients;

    @OneToMany(targetEntity = OrdersDishes.class, mappedBy = "dish")
    private List<OrdersDishes> ordersDishes;

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

    public List<DishesIngredients> getDishesIngredients() {
        return dishesIngredients;
    }

    public void setDishesIngredients(List<DishesIngredients> dishesIngredients) {
        this.dishesIngredients = dishesIngredients;
    }

    public List<OrdersDishes> getOrdersDishes() {
        return ordersDishes;
    }

    public void setOrdersDishes(List<OrdersDishes> ordersDishes) {
        this.ordersDishes = ordersDishes;
    }
}
