package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.DishesErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.DishesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishesController {
    private final DishesService service;
    private final DishesValidator validator;

    @Autowired
    public DishesController(DishesService service, DishesValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/all")
    public List<Dishes> getAllDishes() {
        return service.findAllDishes();
    }

    @GetMapping("/{id}")
    public Dishes getDishesById(@PathVariable("id") long id) throws DiscountNotFoundException {
        return service.getDishesById(id);
    }

    @GetMapping("/name/{start}")
    public List<Dishes> getDishesFromStartingWithName(@PathVariable("start") String name) throws DiscountNotFoundException {
        return service.getDishesByStartingWith(name);
    }

    @GetMapping("/{name}")
    public Dishes getDishesByName(@PathVariable("name") String name) throws DiscountNotFoundException {
        return service.getDishesByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> saveDishes(@RequestBody @Valid Dishes dishes, BindingResult bindingResult) throws DishesNotCreatedException {
        validator.validate(dishes, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new DishesNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        service.save(dishes);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<DishesErrorResponse> handleOrder(DishesNotFoundException foundException) {
        DishesErrorResponse orderErrorResponse = new DishesErrorResponse(
                foundException.getMessage(),
                System.currentTimeMillis(),
                foundException.getClass().getSimpleName()
//                ); TODO find right info for Exception
        );
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DishesErrorResponse> handleOrder(DishesNotCreatedException foundException) {
        DishesErrorResponse orderErrorResponse = new DishesErrorResponse(
                foundException.getMessage(),
                System.currentTimeMillis(),
                foundException.getClass().getSimpleName());
//        TODO find right info for Exception
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.BAD_REQUEST);
    }


}