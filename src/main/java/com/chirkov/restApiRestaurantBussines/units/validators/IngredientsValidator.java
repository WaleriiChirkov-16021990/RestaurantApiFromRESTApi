package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IngredientsValidator implements Validator {
    private final IngredientsService service;

    @Autowired
    public IngredientsValidator(IngredientsService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Ingredients.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        Ingredients ingredients = (Ingredients) target;
        if (service.getOptionalByName(ingredients.getIngredientName()).isPresent()) {
            errors.rejectValue("Ingredient " + ingredients.getIngredientName(), "409", "This Ingredient is already exist.");
            // TODO придумать более подходящую валидацию для ингредиентов
            // come up with a validation for a ingredients
        }

    }
}
