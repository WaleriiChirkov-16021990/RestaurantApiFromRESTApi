//package com.chirkov.restApiRestaurantBussines.models;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//@Entity
//@Table(name = "discount")
//@Getter
//@Setter
//public class Discount {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "discount_id")
//    private int id;
//
//    @Column(name = "discount_name")
//    @NotNull
//    private String name;
//
//    @Column(name = "discount_sale")
//    @NotNull
//    private int sale;
//
//    @OneToMany(mappedBy = "discount")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<Person> personList;
//
//}
