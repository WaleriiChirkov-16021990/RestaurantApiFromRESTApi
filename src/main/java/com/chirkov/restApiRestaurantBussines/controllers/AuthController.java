package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.JwtAuthenticationResponse;
import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.dto.SignInRequest;
import com.chirkov.restApiRestaurantBussines.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid PersonDto request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}

//
//import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
//import com.chirkov.restApiRestaurantBussines.models.Person;
//import com.chirkov.restApiRestaurantBussines.services.JwtService;
//import com.chirkov.restApiRestaurantBussines.services.PeopleService;
//import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
//import com.chirkov.restApiRestaurantBussines.units.validators.PersonDtoValidator;
//import lombok.Getter;
////import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//
//@Controller
//@RequestMapping("/auth")
//@Getter
//public class AuthController {
//
//    private final PeopleServiceByRepository<Person> peopleService;
//    private final ModelMapper modelMapper;
//    private final PersonDtoValidator personDtoValidator;
//    private final JwtService jwtService;
//
//
//    @Autowired
//    public AuthController(
//            PeopleService peopleService,
//            ModelMapper modelMapper,
//            PersonDtoValidator personDtoValidator,
//            JwtService jwtService) {
//        this.peopleService = peopleService;
//        this.modelMapper = modelMapper;
//        this.personDtoValidator = personDtoValidator;
//        this.jwtService = jwtService;
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "auth/login";
//    }
//
//
//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person") PersonDto person) {
//        return "auth/registration";
//    }
//
//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("person") @Valid PersonDto personDto, BindingResult bindingResult) {
//        personDtoValidator.validate(personDto, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "auth/registration";
//        }
//        peopleService.save(convertToPerson(personDto));
//        return "auth/people";
//    }
//
//    private Person convertToPerson(PersonDto personDto) {
//        return modelMapper.map(personDto, Person.class);
//
//    }
//
//    @GetMapping("/test")
//    public String getLogoutPage() {
//        return "auth/people";
//    }
//}
