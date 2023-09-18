//package com.chirkov.restApiRestaurantBussines.models;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "restaurant_reviews")
//@Getter
//@Setter
//public class RestaurantReviews {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "restaurant_reviews_id")
//    private int id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "restaurant_reviews_owner", referencedColumnName = "person_id")
//    @NotNull
//    private Person owner;
//
//    @Column(name = "restaurant_reviews_gradle")
//    @NotNull
//    private int gradle;
//
//    @Column(name = "restaurant_reviews_comment")
//    private String comment;
//
//    @Column(name = "restaurant_reviews_create_at")
//    @NotNull
//    private LocalDateTime createAt;
//
//    @Column(name = "restaurant_reviews_update_at")
//    @NotNull
//    private LocalDateTime updateAt;
//
//    public RestaurantReviews() {
//    }
//}
