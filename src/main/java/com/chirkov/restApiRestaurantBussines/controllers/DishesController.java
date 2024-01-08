package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.units.validators.DishesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;

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
}
