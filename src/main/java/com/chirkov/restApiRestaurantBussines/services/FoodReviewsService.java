package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.repositories.FoodReviewRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewEmptyListException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewNotFoundException;
import org.aspectj.apache.bcel.classfile.Module;
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
        rollbackFor = FoodReviewNotFoundException.class)
public class FoodReviewsService {
    private final FoodReviewRepository foodReviewRepository;

    @Autowired
    public FoodReviewsService(FoodReviewRepository foodReviewRepository) {
        this.foodReviewRepository = foodReviewRepository;
    }

    public List<FoodReview> getFoodReviews() {
        List<FoodReview> list;
        try {
            list = foodReviewRepository.findAll();
        } catch (Exception e) {
            throw new FoodReviewNotFoundException(e.getMessage(), e);
        }
        if (list == null) {
            throw new FoodReviewEmptyListException("Food-review empty list");
        }
        return foodReviewRepository.findAll();
    }

    public FoodReview getFoodReviewById(long id) {
        return foodReviewRepository.getReferenceById(id);
    }

    public FoodReview getById(long id) {
        return foodReviewRepository.findById(id).orElseThrow(() ->
                new FoodReviewNotFoundException("Food-Review by id = " + id + ", not found."));
    }

    public List<FoodReview> getFoodReviewsByDate(LocalDateTime dateTime) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByDateCreate(dateTime).orElseThrow(
                () -> new FoodReviewNotFoundException("FoodReview for date: {" + dateTime + "}, not found."));
    }

    public Optional<List<FoodReview>> getOptionalFoodReviewsByDate(LocalDateTime dateTime) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByDateCreate(dateTime);
    }

    public List<FoodReview> getFoodReviewsByAuthor(Person author) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByAuthor(author).orElseThrow(
                () -> new FoodReviewNotFoundException("FoodReview for author: {" + author.getName() + "}, not found."));
    }


    public Optional<List<FoodReview>> getOptionalFoodReviewsByAuthor(Person author) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByAuthor(author);
    }

    public List<FoodReview> getFoodReviewsByDishes(Dishes dishes) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByDishes(dishes).orElseThrow(
                () -> new FoodReviewNotFoundException("FoodReview for dishes: {" + dishes.getName() + "}, not found."));
    }

    public List<FoodReview> getFoodReviewsByGrade(int grade) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByGrade(grade).orElseThrow(
                () -> new FoodReviewNotFoundException("FoodReview for grade: {" + grade + "}, not found"));
    }

    public List<FoodReview> getFoodReviewsByComment(String comment) throws FoodReviewNotFoundException {
        return foodReviewRepository.getFoodReviewsByCommentStartsWith(comment).orElseThrow(
                () -> new FoodReviewNotFoundException("FoodReviews starts with for comment: {" + comment + "}, not found"));
    }

    @Transactional
    public FoodReview saveFoodReview(FoodReview review) throws FoodReviewNotCreatedException {
        try {
            enrichFoodReview(review);
            return this.foodReviewRepository.save(review);
        } catch (Exception e) {
            throw new FoodReviewNotCreatedException(
                    "Failed to save review for " + review.getAuthor().getName(), e);
        }
    }

    @Transactional
    public FoodReview deleteFoodReview(long reviewId) throws FoodReviewNotDeletedException {
        FoodReview reviewForDelete;
        try {
            reviewForDelete = this.foodReviewRepository.getReferenceById(reviewId);
            foodReviewRepository.deleteById(reviewId);
        } catch (Exception e) {
            throw new FoodReviewNotDeletedException(
                    "Failed to delete review by id = {" + reviewId + "} .");
        }
        return reviewForDelete;
    }

    private void enrichFoodReview(FoodReview review) {
        review.setDateCreate(LocalDateTime.now());
    }
}
