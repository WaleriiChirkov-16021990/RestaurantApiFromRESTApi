package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "restaurant_reviews")
@Getter
@Setter
public class RestaurantReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @NotNull
    private Person owner;

    @Column(name = "gradle")
    @NotNull
    private int gradle;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_at")
    @NotNull
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @NotNull
    private LocalDateTime updateAt;

    public RestaurantReviews() {
    }
}
