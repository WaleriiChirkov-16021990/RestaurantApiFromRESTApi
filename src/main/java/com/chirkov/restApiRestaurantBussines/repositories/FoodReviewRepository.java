package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodReviewRepository extends JpaRepository<FoodReview, Long> {
    Optional<List<FoodReview>> getFoodReviewsByAuthor(Person author);

    Optional<List<FoodReview>> getFoodReviewsByDishes(Dishes dishes);

    Optional<List<FoodReview>> getFoodReviewsByDateCreate(LocalDateTime dishes);

    Optional<List<FoodReview>> getFoodReviewsByGrade(int grade);

    Optional<List<FoodReview>> getFoodReviewsByCommentStartsWith(String start);
}
