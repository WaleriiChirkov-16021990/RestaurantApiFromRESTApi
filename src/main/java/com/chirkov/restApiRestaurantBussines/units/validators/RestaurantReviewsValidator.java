package com.chirkov.restApiRestaurantBussines.units.validators;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.services.RestaurantReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RestaurantReviewsValidator implements Validator {
    private final RestaurantReviewsService service;
@Autowired
    public RestaurantReviewsValidator(RestaurantReviewsService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return RestaurantReviews.class.equals(clazz);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
            RestaurantReviews valid = (RestaurantReviews) target;
        if (valid.getComment().contains("fuck")) {
            errors.rejectValue("comment","99999","Not a valid comment? deleted a 'fuck'");
        }
    }
}
