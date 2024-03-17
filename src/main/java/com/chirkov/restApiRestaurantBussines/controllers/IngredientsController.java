package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.IngredientsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.IngredientsErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.IngredientsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientsServiceByRepository<Ingredients> service;
    private final IngredientsValidator validator;

    @Autowired
    public IngredientsController(IngredientsServiceByRepository<Ingredients> service, IngredientsValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<Ingredients> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Ingredients> addIngredient(@RequestBody @Valid Ingredients ingredient, BindingResult bindingResult) throws IngredientsNotCreatedException {
        validator.validate(ingredient, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new IngredientsNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        return new ResponseEntity<>(service.save(ingredient), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Ingredients getIngredientsById(@PathVariable long id) throws IngredientsNotFoundException {
        return service.findById(id);
    }


    @ExceptionHandler({IngredientsNotDeletedException.class, IngredientsNotCreatedException.class})
    private ResponseEntity<IngredientsErrorResponse> getIngredientsErrorResponse(Exception e) {
        IngredientsErrorResponse response = new IngredientsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IngredientsEmptyListException.class)
    private ResponseEntity<IngredientsErrorResponse> getIngredientErrorResponse(Exception e) {
        IngredientsErrorResponse response = new IngredientsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IngredientsNotFoundException.class)
    private ResponseEntity<IngredientsErrorResponse> getErrorResponse(Exception e) {
        IngredientsErrorResponse response = new IngredientsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
