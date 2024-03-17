package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.RestaurantReviewsDto;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.RestaurantReviewsService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.RestaurantReviewsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.RestaurantReviewsErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotCreateException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.RestaurantReviewsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restReviews")
public class RestaurantReviewsController {
    private final RestaurantReviewsServiceByRepository<RestaurantReviews> service;
    private final RestaurantReviewsValidator validator;
    private final PeopleServiceByRepository<Person> peopleService;

    @Autowired
    public RestaurantReviewsController(RestaurantReviewsServiceByRepository<RestaurantReviews> service,
                                       RestaurantReviewsValidator validator,
                                       PeopleServiceByRepository<Person> peopleService) {
        this.service = service;
        this.validator = validator;
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<RestaurantReviews> getRestaurantsReviews() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantReviews getRestaurantReview(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<RestaurantReviewsErrorResponse> handlerException(RestaurantReviewsNotFoundException exception) {
        RestaurantReviewsErrorResponse errorResponse = new RestaurantReviewsErrorResponse(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addRestaurantReview(@RequestBody @Valid RestaurantReviewsDto reviewDto,
                                                          BindingResult bindingResult) {
        RestaurantReviews review = reviewDto.mapReview(this.peopleService);
        this.validator.validate(review, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new RestaurantReviewsNotCreateException(
                    AddErrorMessageFromMyException.getErrorMessage(bindingResult)
            );
        }
        this.service.save(review);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler({RestaurantReviewsNotCreateException.class, RestaurantReviewsNotDeletedException.class})
    private ResponseEntity<RestaurantReviewsErrorResponse> handlerException(Exception exception) {
        RestaurantReviewsErrorResponse errorResponse = new RestaurantReviewsErrorResponse(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

