package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Dishes")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "calories")
    private int calories;

    @Column(name = "proteins")
    private int proteins;

    @Column(name = "fats")
    private int fats;

    @Column(name = "carbohydrates")
    private int carbohydrates;

    @Column(name = "image_url")
    private String imageURL;

    @OneToMany(mappedBy = "dishes")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<FoodReview> foodReviewList;

    public Dishes() {
    }
}

