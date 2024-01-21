package com.chirkov.restApiRestaurantBussines.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Food_review", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_review_id")
    private int idFoodReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_review_person_id", referencedColumnName = "person_id")
    @NotNull
    private Person author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_review_dishes_id", referencedColumnName = "dishes_id")
    private Dishes dishes;

    @Column(name = "food_review_date_create")
    private LocalDateTime dateCreate;

    @Column(name = "food_review_grade")
    private int grade;

    @Column(name = "food_review_comment")
    private String comment;
}
