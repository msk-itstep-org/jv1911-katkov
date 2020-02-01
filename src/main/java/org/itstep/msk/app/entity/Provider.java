//package org.itstep.msk.app.entity;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "providers")
//public class Provider {
//
//    @Column(columnDefinition = "int unsigned")
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "delivery_cost")
//    private Double deliveryCost;
//
//    @OneToMany(targetEntity = Storage.class, mappedBy = "id")
//    private Set<Storage> items;
//
//}
