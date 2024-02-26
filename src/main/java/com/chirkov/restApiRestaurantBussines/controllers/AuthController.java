package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.validators.PersonDtoValidator;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@Getter
public class AuthController {

    private final PeopleServiceByRepository<Person> peopleService;
    private final ModelMapper modelMapper;
    private final PersonDtoValidator personDtoValidator;


    @Autowired
    public AuthController(PeopleService peopleService, ModelMapper modelMapper, PersonDtoValidator personDtoValidator) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
        this.personDtoValidator = personDtoValidator;
    }

    /**
     * Retrieves the login page.
     *
     * @return         	the login page template
     */
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    /**
     * A description of the entire Java function.
     *
     * @param  person	description of parameter
     * @return         	description of return value
     */
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") PersonDto person) {
        return "auth/registration";
    }

    /**
     * Perform registration for a person.
     *
     * @param  personDto        the data transfer object for the person
     * @param  bindingResult    the result of the validation binding
     * @return                  the destination view name
     */
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid PersonDto personDto, BindingResult bindingResult) {
        personDtoValidator.validate(personDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        peopleService.save(convertToPerson(personDto));
        return "auth/people";
    }

    /**
     * Converts a PersonDto object to a Person object using modelMapper.
     *
     * @param  personDto  the PersonDto object to be converted
     * @return            the converted Person object
     */
    private Person convertToPerson(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);

    }

    /**
     * A logout user.
     *
     * @return destination view name page for logout user
     */
    @GetMapping("/logout")
    public String getLogoutPage() {
        return "auth/people";
    }
}
