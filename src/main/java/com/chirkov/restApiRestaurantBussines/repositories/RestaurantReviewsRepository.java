package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RestaurantReviewsRepository extends JpaRepository<RestaurantReviews,Long> {
    @NotNull Optional<RestaurantReviews> findById(@NotNull Long id);
    Optional<List<RestaurantReviews>> findAllByOwner(Person owner);
}
