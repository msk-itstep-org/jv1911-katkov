package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "active", nullable = false, columnDefinition = "BIT")
    private Boolean active = true;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(targetEntity = OrderDish.class, mappedBy = "order")
    private List<OrderDish> ordersDishes;

    public Integer getDishesCount(){
        Integer count = 0;
        for (OrderDish orderDish : ordersDishes) {
            count += orderDish.getQuantity();
        }

        return count;
    }

    public Long getId() {
        return id;
    }

    public List<OrderDish> getOrdersDishes() {
        return ordersDishes;
    }

    public void setOrdersDishes(List<OrderDish> ordersDishes) {
        this.ordersDishes = ordersDishes;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

