package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.services.CompositionsOfDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompositionsOfDishesValidator implements Validator {

    private final CompositionsOfDishesService service;

    @Autowired
    public CompositionsOfDishesValidator(CompositionsOfDishesService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CompositionsOfDishes.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        CompositionsOfDishes compositions = (CompositionsOfDishes) target;
        if (service.getByNameOptional(compositions.getName()).isPresent()) {
            errors.rejectValue("name", "9999999", "This Composition of dishes already exist.");
        }
    }
}
