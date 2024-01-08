package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class DishesValidator implements Validator {
    private final DishesService service;

    @Autowired
    public DishesValidator(DishesService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Dishes.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        Dishes dishes = (Dishes) target;
        // TODO Create validation for Dishes entity
    }
}
