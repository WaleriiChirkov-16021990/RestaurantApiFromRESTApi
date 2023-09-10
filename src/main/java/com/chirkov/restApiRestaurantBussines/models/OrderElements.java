package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Entity
@Table(name = "order_element")
@Getter
@Setter
public class OrderElements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "orderElements", referencedColumnName = "id")
    private Order order;

    @ManyToMany()
    @JoinTable(
        name = "OrderElements_Dishes",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Dishes> listDishes;

}
