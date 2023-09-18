//package com.chirkov.restApiRestaurantBussines.models;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "foodreview")
//@Getter
//@Setter
//public class FoodReview {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "foodreview_id")
//    private int idFoodReview;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "foodreview_person_id", referencedColumnName = "person_id")
//    @NotNull
//    private Person author;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "foodreview_dishes_id", referencedColumnName = "dishes_id")
//    private Dishes dishes;
//
//    @Column(name = "foodreview_datecreate")
//    private String dateCreate;
//
//    @Column(name = "foodreview_grade")
//    private int grade;
//
//    @Column(name = "foodreview_comment")
//    private String comment;
//
//    public FoodReview() {
//    }
//}
