package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "foodreview")
@Getter
@Setter
public class FoodReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idFoodReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @NotNull
    private Person author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dishes_id", referencedColumnName = "id")
    private Dishes dishes;

    @Column(name = "datecreate")
    private String dateCreate;

    @Column(name = "grade")
    private int grade;

    @Column(name = "comment")
    private String comment;

    public FoodReview() {
    }
}
