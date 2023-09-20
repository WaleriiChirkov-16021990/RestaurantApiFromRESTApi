package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.PersonDtoValidator;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@Getter
public class AuthController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;
    private final PersonDtoValidator personDtoValidator;


    @Autowired
    public AuthController(PeopleService peopleService, ModelMapper modelMapper, PersonDtoValidator personDtoValidator) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
        this.personDtoValidator = personDtoValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") PersonDto person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid PersonDto personDto, BindingResult bindingResult) {
        personDtoValidator.validate(personDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        peopleService.save(convertToPerson(personDto));
        return "auth/people";
    }

    private Person convertToPerson(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);

    }

    @GetMapping("/test")
    public String getLogoutPage() {
        return "auth/people";
    }
}
