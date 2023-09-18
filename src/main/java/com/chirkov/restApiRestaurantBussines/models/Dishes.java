//package com.chirkov.restApiRestaurantBussines.models;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "Dishes")
//public class Dishes {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "dishes_id")
//    private int id;
//
//    @Column(name = "dishes_name")
//    private String name;
//
//    @Column(name = "dishes_price")
//    private float price;
//
//    @Column(name = "dishes_weight")
//    private float weight;
//
//    @Column(name = "dishes_calories")
//    private int calories;
//
//    @Column(name = "dishes_proteins")
//    private int proteins;
//
//    @Column(name = "dishes_fats")
//    private int fats;
//
//    @Column(name = "dishes_carbohydrates")
//    private int carbohydrates;
//
//    @Column(name = "dishes_image_url")
//    private String imageURL;
//
//    @OneToMany(mappedBy = "dishes")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<FoodReview> foodReviewList;
//
//
//    @OneToMany(mappedBy = "dishes")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<OrderElements> orderElementsIntegerMap;
//
//    @ManyToMany
//    @JoinTable(
//            name = "Dishes_Compositions_of_dishes",
//            joinColumns = @JoinColumn(name = "dishes_id"),
//            inverseJoinColumns = @JoinColumn(name = "compositions_of_dishes_id")
//    )
//    private List<CompositionsOfDishes> compositionsOfDishesList;
//
//
//    public Dishes() {
//    }
//
//}
//
