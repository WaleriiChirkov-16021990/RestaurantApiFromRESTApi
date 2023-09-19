package com.chirkov.restApiRestaurantBussines.units;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Getter
@Setter
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleService.findPersonByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "This email is already taken");
        } else if (peopleService.findPersonByPhoneNumber(person.getPhoneNumber()).isPresent()) {
            errors.rejectValue("phoneNumber", "This phoneNumber is already taken");
        }
    }
}
