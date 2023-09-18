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
//@Table(name = "role")
//@Getter
//@Setter
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id")
//    private int id;
//
//    @Column(name = "role_name")
//    @NotNull
//    private String name;
//
//    @Column(name = "role_value")
//    @NotNull
//    private RoleEnum roleValue;
//
//    @OneToMany(mappedBy = "role")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<Person> personList;
//
//    public Role() {
//    }
//
//
//}
