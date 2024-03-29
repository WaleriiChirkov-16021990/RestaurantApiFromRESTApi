package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Restaurant_reviews",schema = "public")
@Getter
@Setter
public class RestaurantReviews implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_reviews_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_reviews_owner", referencedColumnName = "person_id")
    private Person owner;

    @Column(name = "restaurant_reviews_gradle")
    @NotNull
    private int gradle;

    @Column(name = "restaurant_reviews_comment")
    private String comment;

    @Column(name = "restaurant_reviews_create_at")
    private LocalDateTime createAt;

    @Column(name = "restaurant_reviews_update_at")
    private LocalDateTime updateAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantReviews that)) return false;
        return getGradle() == that.getGradle() && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getComment(), that.getComment()) && Objects.equals(getCreateAt(), that.getCreateAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getGradle(), getComment(), getCreateAt());
    }
}
