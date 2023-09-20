package com.chirkov.restApiRestaurantBussines.units;

//import javax.validation.Validator;

import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonDtoValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonDtoValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * @param clazz - object for method equals .class
     * @return true or false
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDto.class.equals(clazz);
    }

    /**
     * @param target - is return object
     * @param errors - is object for errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        PersonDto personDto = (PersonDto) target;
        if (peopleService.findPersonByPhoneNumber(personDto.getPhoneNumber()).isPresent()) {
            errors.rejectValue("phoneNumber", "This phoneNumber is already");
        } else if (peopleService.findPersonByEmail(personDto.getEmail()).isPresent()) {
            errors.rejectValue("email", "This email is already");
        }
    }
}
