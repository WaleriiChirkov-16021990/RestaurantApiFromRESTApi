package com.chirkov.restApiRestaurantBussines.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "Person", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @NotNull(message = "Name is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    @Column(name = "person_name")
    private String name;

    @NotNull(message = "Surname is not empty")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    @Column(name = "person_lastname")
    private String lastName;

    @Min(value = 1900, message = "Year of birth > 1900")
    @NotNull(message = "Year of birth is not empty")
    @Column(name = "person_year_of_birth")
    private int yearOfBirth;

    @Column(name = "person_phone_number")
    @NotNull(message = "PhoneNumber is not null")
    @Size(min = 11, max = 11, message = "Mobile knife must contains strictly 11 digits")
    private String phoneNumber;

    @Column(name = "person_email")
    @NotNull(message = "Email is not null")
    @Email(message = "Email should be valid")
    private String email;


    @Column(name = "person_username")
    @NotNull(message = "Username should be not empty")
    private String username;

    @Column(name = "person_password")
    @NotNull(message = "Password is not empty")
    @Size(min = 6, message = "Password contains min 6 symbol")
    private String password;

    @JsonBackReference
    @OneToMany(mappedBy = "author")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<FoodReview> reviewList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "person_discount", referencedColumnName = "discount_id")
    private Discount discount;

    @JsonBackReference
    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<RestaurantReviews> restaurantReviews;

    @JsonBackReference
    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<TableReservation> reservationList;

    @JsonBackReference
    @OneToMany(mappedBy = "authorThisRecords")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<TableReservation> createdReserveRecords;

    @JsonBackReference
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Order> orderList;

    @JsonBackReference
    @OneToMany(mappedBy = "authorOfUpdate")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<TableReservation> updatedReserveRecords;

    @Column(name = "person_created_at")
    private LocalDateTime createdAt;

    @Column(name = "person_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "person_updated_who")
    private String updatedWho;


    public Person(String name, String lastName, int yearOfBirth, String phoneNumber, String email, String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

}
