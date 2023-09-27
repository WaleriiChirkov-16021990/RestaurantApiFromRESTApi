package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.PersonErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.PersonDtoValidator;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
@Getter
@Setter
public class PeopleController {

    private final ModelMapper modelMapper;
    private final PeopleService peopleService;
    private final PersonDtoValidator personDtoValidator;

    @Autowired
    public PeopleController(ModelMapper modelMapper, PeopleService peopleService, PersonDtoValidator personDtoValidator) {
        this.modelMapper = modelMapper;
        this.peopleService = peopleService;
        this.personDtoValidator = personDtoValidator;
    }

    @GetMapping
    public List<PersonDto> getPeople() {
        return peopleService.findAll().stream().map(this::convertToPersonDto).collect(Collectors.toList());
    }

    private PersonDto convertToPersonDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") int id) {
        return convertToPersonDto(peopleService.findOne(id));
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException exception) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(
                "Person with this id wasn't found",
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND); // status 404
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDto personDto, BindingResult bindingResult) {
        personDtoValidator.validate(personDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new PersonNotCreatedException(
                    AddErrorMessageFromMyException
                            .getErrorMessage(bindingResult));
        }
        peopleService.save(convertToPerson(personDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handlerException(PersonNotCreatedException exception) {
        PersonErrorResponse response = new PersonErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //status = 400
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        if (peopleService.findOne(id) == null) {
            AtomicReference<StringBuilder> error = new AtomicReference<>(new StringBuilder());
            error.get().append("This user not found");
            throw new PersonNotDelete(error.toString());
        }
        peopleService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handlerException(PersonNotDelete notDelete) {
        PersonErrorResponse response = new PersonErrorResponse(
                notDelete.getMessage(),
                System.currentTimeMillis(),
                notDelete.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<HttpStatus> edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid PersonDto person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new PersonNotUpdatedException(
                    AddErrorMessageFromMyException
                            .getErrorMessage(bindingResult));
        }
        peopleService.update(id, convertToPerson(person));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handlerException(PersonNotUpdatedException exception) {
        PersonErrorResponse response = new PersonErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
