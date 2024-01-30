package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.CompositionsOfDishesDto;
import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.services.CompositionsOfDishesService;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.CompositionsOfDishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.IngredientsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.UnitsOfMeasurementServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.CompositionsOfDishesErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.CompositionsOfDishesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/composition_of_dishes")
public class CompositionsOfDishesController {

    private final CompositionsOfDishesServiceByRepository<CompositionsOfDishes> service;
    private final CompositionsOfDishesValidator validator;
    private final IngredientsServiceByRepository<Ingredients> ingredientsService;
    private final UnitsOfMeasurementServiceByRepository<UnitsOfMeasurement> unitsOfMeasurementService;

    @Autowired
    public CompositionsOfDishesController(CompositionsOfDishesService service, CompositionsOfDishesValidator validator, IngredientsService injectionService, UnitsOfMeasurementService unitsOfMeasurementService) {
        this.service = service;
        this.validator = validator;
        this.ingredientsService = injectionService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompositionsOfDishes> findComById(@PathVariable Long id) throws CompositionsOfDishesNotFoundException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompositionsOfDishes>> findComList() throws CompositionsOfDishesEmptyListException {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompositionsOfDishes> addCom(@RequestBody @Valid CompositionsOfDishesDto compositionsDto, BindingResult bindingResult) throws CompositionsOfDishesNotCreatedException {
        CompositionsOfDishes compositions = compositionsDto.mappingObject(ingredientsService, unitsOfMeasurementService);
        validator.validate(compositions, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new CompositionsOfDishesNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }

        return new ResponseEntity<>(service.save(compositions), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompositionsOfDishes> deleteCom(@PathVariable Long id) throws CompositionsOfDishesNotDeletedException {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

    @ExceptionHandler({CompositionsOfDishesNotDeletedException.class, CompositionsOfDishesNotCreatedException.class})
    private ResponseEntity<CompositionsOfDishesErrorResponse> getCompositionsOfDishesErrorResponse(Exception e) {
        CompositionsOfDishesErrorResponse response = new CompositionsOfDishesErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompositionsOfDishesEmptyListException.class)
    private ResponseEntity<CompositionsOfDishesErrorResponse> getCompositionsErrorResponse(Exception e) {
        CompositionsOfDishesErrorResponse response = new CompositionsOfDishesErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CompositionsOfDishesNotFoundException.class)
    private ResponseEntity<CompositionsOfDishesErrorResponse> getErrorResponse(Exception e) {
        CompositionsOfDishesErrorResponse response = new CompositionsOfDishesErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
