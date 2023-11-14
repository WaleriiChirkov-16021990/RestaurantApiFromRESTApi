package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.RestaurantReviewsService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.RestaurantReviewsErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotCreateException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RestaurantReviewsNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.RestaurantReviewsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restReviews")
public class RestaurantReviewsController {
    private final RestaurantReviewsService service;
    private final RestaurantReviewsValidator validator;

    @Autowired
    public RestaurantReviewsController(RestaurantReviewsService service, RestaurantReviewsValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/all")
    public List<RestaurantReviews> getRestaurantsReviews() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RestaurantReviews> getRestaurantReview(@PathVariable("id") int id) {
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

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addRestaurantReview(@RequestBody @Valid RestaurantReviews review, BindingResult bindingResult) {
        this.validator.validate(review,bindingResult);
        if (bindingResult.hasErrors()) {
            throw new RestaurantReviewsNotCreateException(
                    AddErrorMessageFromMyException.getErrorMessage(bindingResult)
            );
        }
        this.service.save(review);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<RestaurantReviewsErrorResponse> handlerException(RestaurantReviewsNotCreateException exception) {
        RestaurantReviewsErrorResponse errorResponse = new RestaurantReviewsErrorResponse(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                System.currentTimeMillis()
        );
        return new  ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}

