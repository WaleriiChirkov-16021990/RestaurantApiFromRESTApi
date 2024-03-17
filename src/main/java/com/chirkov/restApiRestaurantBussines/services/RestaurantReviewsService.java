package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.repositories.RestaurantReviewsRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.RestaurantReviewsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotCreateException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotDeletedException;
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
        rollbackFor = {RestaurantReviewsNotCreateException.class,RestaurantReviewsNotDeletedException.class})
public class RestaurantReviewsService implements RestaurantReviewsServiceByRepository<RestaurantReviews> {
    private final RestaurantReviewsRepository repository;
    private final PeopleServiceByRepository<Person> peopleService;


    @Autowired
    public RestaurantReviewsService(RestaurantReviewsRepository repository, PeopleService peopleService) {
        this.repository = repository;
        this.peopleService = peopleService;
    }

    public Optional<RestaurantReviews> findById(int id) {
        return this.repository.findById((long) id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public RestaurantReviews findById(Long id) {
        return this.repository.findById(id).orElseThrow(() ->
                new ReserveTableNotFoundException("Not found restaurant review for id = " + id));
    }

    public List<RestaurantReviews> findAll() {
        return this.repository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public RestaurantReviews deleteById(Long id) {
        RestaurantReviews res = findById(id);
        try {
            this.repository.deleteById(id);
            return res;
        } catch (Exception e) {
            throw new RestaurantReviewsNotDeletedException(
                    "Error deleting restaurant review for id = " + id + "_\n" + e.getMessage(), e);
        }
    }

    public Optional<List<RestaurantReviews>> findAllByPerson(Person person) {
        return this.repository.findAllByOwner(person);
    }

    @Transactional
    public RestaurantReviews save(RestaurantReviews review) {
        try {
            enrichReview(review);
            this.repository.save(review);
            return review;
        } catch (Exception e) {
            throw new RestaurantReviewsNotCreateException(
                    "Could not save review for " + review + ": " + e.getMessage(), e);
        }
    }

    private void enrichReview(RestaurantReviews review) {
//        review.setOwner(this.peopleService.findOne(review.getGradle()));
        review.setCreateAt(LocalDateTime.now());
        review.setUpdateAt(LocalDateTime.now());
    }
}
