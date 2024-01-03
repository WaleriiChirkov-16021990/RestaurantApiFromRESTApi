package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReserveTableValidator implements Validator {
    private final ReserveTableService reserveTableService;

    @Autowired
    public ReserveTableValidator(ReserveTableService reserveTableService) {
        this.reserveTableService = reserveTableService;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ReserveTable.class) ;
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        ReserveTable reserveTable = (ReserveTable) target;
        if (this.reserveTableService.getReserveTableByAccommodationNumber(reserveTable.getAccommodationNumber()).isPresent()) {
            errors.rejectValue("AccommodationNumber","12233445","This Reserve a table already exists");
        }
    }
}
