package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;

import java.util.List;
import java.util.Optional;

public interface RestaurantReviewsServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M save(M discount);

    Optional<List<M>> findAllByPerson(Person person);
}
