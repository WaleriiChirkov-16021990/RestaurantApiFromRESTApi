package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RestaurantReviewsRepository extends JpaRepository<RestaurantReviews,Integer> {
    Optional<RestaurantReviews> findById(int id);
    Optional<List<RestaurantReviews>> findAllByOwner(Person owner);
}
