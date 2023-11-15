package com.chirkov.restApiRestaurantBussines.units.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DishesValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Dishes.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Dishes dishes = (Dishes) target;
        if (dishes.getName().equals("")) {
            errors.rejectValue("name","10001","Name must be a valid name");
        }
        if (dishes.getPrice() < 0) {
            errors.rejectValue("price","10002","Price must be greater than zero");
        }
        if (dishes.getWeight() < 0) {
            errors.rejectValue("weight","10003","Weight must be greater than zero");
        }
        if (dishes.getCalories() < 0) {
            errors.rejectValue("calories","10004","Calories must be greater than zero");
        }
        if (dishes.getProteins() < 0) {
            errors.rejectValue("proteins","10005", "Proteins must be greater than zero");
        }
        if (dishes.getFats() < 0) {
            errors.rejectValue("fats","10006","Fats must be greater than zero");
        }
        if (dishes.getCarbohydrates() < 0) {
            errors.rejectValue("carbohydrates","10007","Carbohydrates must be greater than zero");
        }
        if (dishes.getImageURL() < 0) {
            errors.rejectValue("imageURL","10008","Image URL must be greater than zero");
        }

    }
}
