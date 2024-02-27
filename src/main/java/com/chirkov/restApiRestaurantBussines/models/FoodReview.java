package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Food_review", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodReview implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_review_id")
    private Long idFoodReview;

    @JsonIgnore
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
