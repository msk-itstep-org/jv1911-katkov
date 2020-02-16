package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long id;

    @Column(name = "waiter_name")
    private String waiterName;

    @OneToMany(targetEntity = OrdersDishes.class, mappedBy = "id")
    private List<OrdersDishes> ordersDishes;

    public Long getId() {
        return id;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public List<OrdersDishes> getOrdersDishes() {
        return ordersDishes;
    }

    public void setOrdersDishes(List<OrdersDishes> ordersDishes) {
        this.ordersDishes = ordersDishes;
    }
}

