package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.repositories.RestaurantReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RestaurantReviewsService {

    private final RestaurantReviewsRepository repository;

    @Autowired
    public RestaurantReviewsService(RestaurantReviewsRepository repository) {
        this.repository = repository;
    }

    public Optional<RestaurantReviews> findById(int id) {
        return this.repository.findById(id);
    }

    public List<RestaurantReviews> findAll() {
        return this.repository.findAll();
    }

    public Optional<List<RestaurantReviews>> findAllByPerson(Person person) {
        return this.repository.findAllByOwner(person);
    }

    @Transactional
    public void save(RestaurantReviews review) {
        this.repository.save(review);
    }
}
