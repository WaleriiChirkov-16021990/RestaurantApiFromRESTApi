package com.chirkov.restApiRestaurantBussines.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Name is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Surname is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    @Column(name = "lastname")
    private String lastName;

    @Min(value = 1900, message = "Year of birth > 1900")
    @NotNull(message = "Year of birth is not null")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "phone_number")
    @NotNull(message = "PhoneNumber is not null")
    @Size(min = 11, max = 11, message = "Mobile knife must contains strictly 11 digits")
    private String phoneNumber;

    @Column(name = "email")
    @NotNull(message = "Email is not null")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password is not null")
    @Size(min = 6, message = "Password contains min 6 symbol")
    private String password;

    @OneToMany(mappedBy = "author")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<FoodReview> reviewList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_discount", referencedColumnName = "id")
    private Discount discount;

    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<RestaurantReviews> restaurantReviews;

    public Person() {
    }

    public Person(String name, String lastName, int yearOfBirth, String phoneNumber, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
