package org.itstep.msk.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders_dishes")
public class OrderDish {

    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(targetEntity = Dish.class)
    @JoinColumn(name = "dishes_id", referencedColumnName = "id")
    private Dish dish;

    @Column
    private Integer quantity = 1;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
