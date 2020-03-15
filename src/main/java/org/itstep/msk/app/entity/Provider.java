package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider {

    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "delivery_cost")
    private Integer deliveryCost;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}