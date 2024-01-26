package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.FoodReviewDto;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.FoodReviewsService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.FoodReviewErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.FoodReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-reviews")
public class FoodReviewController {
    private final FoodReviewsService service;
    private final PeopleService peopleService;
    private final DishesService dishesService;
    private final FoodReviewValidator validator;

    @Autowired
    public FoodReviewController(FoodReviewsService service, PeopleService peopleService, DishesService dishesService, FoodReviewValidator validator) {
        this.service = service;
        this.peopleService = peopleService;
        this.dishesService = dishesService;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<FoodReview>> getFoodReviews()
            throws FoodReviewNotFoundException, FoodReviewEmptyListException {
        return new ResponseEntity<>(service.getFoodReviews(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FoodReview> saveFoodReview(@RequestBody FoodReviewDto reviewDto, BindingResult bindingResult)
            throws FoodReviewNotCreatedException, PersonNotFoundException, DishesNotFoundException {
        FoodReview review = reviewDto.mappingReview(peopleService, dishesService);
        validator.validate(review, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new FoodReviewNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        service.saveFoodReview(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodReview> getFoodReviewById(@PathVariable("id") long id) throws FoodReviewNotFoundException {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FoodReview> deleteFoodReviewById(@PathVariable("id") long id) throws FoodReviewNotDeletedException {
        return new ResponseEntity<>(service.deleteFoodReview(id), HttpStatus.OK);
    }

    @ExceptionHandler({FoodReviewNotDeletedException.class, FoodReviewNotCreatedException.class})
    private ResponseEntity<FoodReviewErrorResponse> getFoodReviewErrorResponse(Exception e) {
        FoodReviewErrorResponse response = new FoodReviewErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FoodReviewEmptyListException.class)
    private ResponseEntity<FoodReviewErrorResponse> getFoodErrorResponse(Exception e) {
        FoodReviewErrorResponse response = new FoodReviewErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(FoodReviewNotFoundException.class)
    private ResponseEntity<FoodReviewErrorResponse> getErrorResponse(Exception e) {
        FoodReviewErrorResponse response = new FoodReviewErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
