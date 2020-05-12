package org.itstep.msk.app.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "dishes")
public class Dish {
    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Название блюда не должно быть меньше 2 знаков и больше 30")
    @Pattern(regexp = "[\\D]+", message = "Название блюда не должно содержать цифр")
    @Column(length = 100)
    private String name;

    @Max(value = 100000, message = "Стоимость блюда превышает 100000!!!")
    @Column
    private Integer cost;

    @NotEmpty
    @Size(max = 255, message = "Слишком длинное описание блюда")
    @Column
    private String description;

    @Column(name = "active", nullable = false, columnDefinition = "BIT")
    private Boolean active = true;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @OneToMany(targetEntity = DishIngredient.class, mappedBy = "dish")
    private List<DishIngredient> dishesIngredients;

    @OneToMany(targetEntity = OrderDish.class, mappedBy = "dish")
    private List<OrderDish> ordersDishes;

    @OneToOne(targetEntity = Upload.class)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Upload dishPhoto;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<DishIngredient> getDishesIngredients() {
        return dishesIngredients;
    }

    public void setDishesIngredients(List<DishIngredient> dishesIngredients) {
        this.dishesIngredients = dishesIngredients;
    }

    public List<OrderDish> getOrdersDishes() {
        return ordersDishes;
    }

    public void setOrdersDishes(List<OrderDish> ordersDishes) {
        this.ordersDishes = ordersDishes;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Upload getDishPhoto() {
        return dishPhoto;
    }

    public void setDishPhoto(Upload dishPhoto) {
        this.dishPhoto = dishPhoto;
    }
}
