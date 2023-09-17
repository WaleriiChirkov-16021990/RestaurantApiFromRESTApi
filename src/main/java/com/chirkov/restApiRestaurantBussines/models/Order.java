package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @OneToOne(mappedBy = "order")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private OrderElements orderElements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_owner", referencedColumnName = "person_id")
    private Person owner;

    @NotNull
    @Column(name = "order_price")
    private float price;

    @NotNull
    @Column(name = "order_status")
    private StatusFromOrder statusFromOrder;
}
