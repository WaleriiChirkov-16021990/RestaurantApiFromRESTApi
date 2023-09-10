package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "discount")
@Getter
@Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "discount")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Person> personList;

}
