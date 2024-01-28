package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.repositories.RestaurantReviewsRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = RestaurantReviewsNotFoundException.class)
public class RestaurantReviewsService {
    private final RestaurantReviewsRepository repository;
    private final PeopleService peopleService;


    @Autowired
    public RestaurantReviewsService(RestaurantReviewsRepository repository, PeopleService peopleService) {
        this.repository = repository;
        this.peopleService = peopleService;
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
        try {
            enrichReview(review);
            this.repository.save(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enrichReview(RestaurantReviews review) {
//        review.setOwner(this.peopleService.findOne(review.getGradle()));
        review.setCreateAt(LocalDateTime.now());
        review.setUpdateAt(LocalDateTime.now());
    }
}
