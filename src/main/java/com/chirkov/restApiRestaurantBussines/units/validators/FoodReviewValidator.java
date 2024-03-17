package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.services.FoodReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FoodReviewValidator implements Validator {
    private final FoodReviewsService service;

    @Autowired
    public FoodReviewValidator(FoodReviewsService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FoodReview.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        FoodReview foodReview = (FoodReview) target;
//        if (service.getOptionalFoodReviewsByAuthor(foodReview.getAuthor()).isPresent()) {
//            // TODO придумать валидацию для отзыва о еде
                // come up with a validation for a food review
//        }
    }
}
