package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StateFromTableValidator implements Validator {
    private final StateFromTablesService stateFromTablesService;

    @Autowired
    public StateFromTableValidator(StateFromTablesService stateFromTablesService) {
        this.stateFromTablesService = stateFromTablesService;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(StateFromTablesService.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        StateFromTable state = (StateFromTable) target;
        if (this.stateFromTablesService.getStateByName(state.getName()).isPresent()) {
            errors.rejectValue("state","0000099","This state does already exists");
        }


    }
}
