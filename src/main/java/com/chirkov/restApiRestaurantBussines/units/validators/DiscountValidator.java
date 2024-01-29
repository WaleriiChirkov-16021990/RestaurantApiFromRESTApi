package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.services.DiscountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DiscountValidator implements Validator {
    private final DiscountService discountService;

    @Autowired
    public DiscountValidator(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Discount.class.equals(clazz);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        Discount discount = (Discount) target;
        if (this.discountService.findByNameOptional(discount.getName()).isPresent()) {
            errors.rejectValue("discount_name","88888","This discount is already exists");
        }
        if (this.discountService.findByDiscountValue(discount.getSale()).isPresent()) {
            errors.rejectValue("sale", "8889","This discount is already");
        }
    }
}
