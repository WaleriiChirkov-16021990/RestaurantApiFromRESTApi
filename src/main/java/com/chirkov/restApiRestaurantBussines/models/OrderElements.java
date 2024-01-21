package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;

@Entity
@Table(name = "order_element",schema = "public")
@Getter
@Setter
public class OrderElements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_element_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "order_element_order_elements", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_element_dishes", referencedColumnName = "dishes_id")
    private Dishes dishes;

    @Column(name = "order_element_count")
    @Range(min = 1, max = 1000, message = "Count should between 1 - 1000")
    private int count;

}
