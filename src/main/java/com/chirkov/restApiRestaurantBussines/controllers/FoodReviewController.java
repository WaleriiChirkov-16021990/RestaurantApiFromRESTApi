package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.FoodReviewDto;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.FoodReviewsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.FoodReviewErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.FoodReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/food-reviews")
public class FoodReviewController {
    private final FoodReviewsServiceByRepository<FoodReview> service;
    private final PeopleServiceByRepository<Person> peopleService;
    private final DishesServiceByRepository<Dishes> dishesService;
    private final FoodReviewValidator validator;

    @Autowired
    public FoodReviewController(FoodReviewsServiceByRepository<FoodReview> service,
                                PeopleServiceByRepository<Person> peopleService,
                                DishesServiceByRepository<Dishes> dishesService,
                                FoodReviewValidator validator) {
        this.service = service;
        this.peopleService = peopleService;
        this.dishesService = dishesService;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<FoodReview>> getFoodReviews()
            throws FoodReviewNotFoundException, FoodReviewEmptyListException {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FoodReview> saveFoodReview(@RequestBody @Valid FoodReviewDto reviewDto, BindingResult bindingResult)
            throws FoodReviewNotCreatedException, PersonNotFoundException, DishesNotFoundException {
        FoodReview review = reviewDto.mappingReview(peopleService, dishesService);
        validator.validate(review, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new FoodReviewNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        service.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodReview> getFoodReviewById(@PathVariable("id") long id) throws FoodReviewNotFoundException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FoodReview> deleteFoodReviewById(@PathVariable("id") long id) throws FoodReviewNotDeletedException {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
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
