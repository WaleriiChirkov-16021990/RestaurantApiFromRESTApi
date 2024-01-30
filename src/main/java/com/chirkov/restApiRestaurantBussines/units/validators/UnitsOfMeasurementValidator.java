package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UnitsOfMeasurementValidator implements Validator {
    private final UnitsOfMeasurementService service;

    @Autowired
    public UnitsOfMeasurementValidator(UnitsOfMeasurementService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UnitsOfMeasurement.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        UnitsOfMeasurement units = (UnitsOfMeasurement) target;
        if (service.getUnitsOfMeasurementByEnum(units.getUnitOfMeasurement()).isPresent()) {
            errors.rejectValue("EnumUnitsOfMeasurement", "409", "This UnitsOfMeasurement is already exist.");
            // TODO придумать более подходящую валидацию для единиц измерения
            // come up with a validation for a UnitsOfMeasurement
        }

    }
}
