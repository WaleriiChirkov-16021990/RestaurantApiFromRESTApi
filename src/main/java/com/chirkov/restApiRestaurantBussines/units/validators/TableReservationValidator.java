package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.services.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Component
public class TableReservationValidator implements Validator {

    private final TableReservationService service;

    @Autowired
    public TableReservationValidator(TableReservationService service) {
        this.service = service;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return TableReservation.class.isAssignableFrom(clazz);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        TableReservation validator = (TableReservation) target;
        List<TableReservation> tableReservations = this.service.getTableReservationByTable(validator.getTable());
        if (!tableReservations.isEmpty()) {
            tableReservations.forEach((reservations)-> {
                if (reservations.getDate() == validator.getDate() && (validator.getTime() == reservations.getTime())) {
                    errors.rejectValue("TableReservation","1111222233","Invalid date and time for reservation,the specified date is already booked");
                }
//                if (reservations.getDate() == validator.getDate() && ((validator.getTime()-reservations.getTime())< 2))
            } );
        }

    }
}
