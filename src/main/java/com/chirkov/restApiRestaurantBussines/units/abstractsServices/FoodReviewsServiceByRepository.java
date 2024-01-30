package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.FoodReview;

import java.util.List;
import java.util.Optional;

public interface FoodReviewsServiceByRepository<M> {
    M findById(Long id);
    Optional<FoodReview> findByIdOptional(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M save(M discount);
}
