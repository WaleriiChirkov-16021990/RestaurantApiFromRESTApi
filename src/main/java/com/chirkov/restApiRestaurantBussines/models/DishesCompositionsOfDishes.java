package com.chirkov.restApiRestaurantBussines.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Dishes_Compositions_of_dishes")
@Data
public class DishesCompositionsOfDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dishes_id")
    private Dishes dishes;

    @ManyToOne
    @JoinColumn(name = "compositions_of_dishes_id")
    private CompositionsOfDishes compositionsOfDishes;
}
