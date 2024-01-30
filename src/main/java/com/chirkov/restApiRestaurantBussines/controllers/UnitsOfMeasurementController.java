package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.UnitsOfMeasurementDto;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.UnitsOfMeasurementServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.UnitsOfMeasurementErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.UnitsOfMeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/unit-of-measurements")
public class UnitsOfMeasurementController {
    private final UnitsOfMeasurementServiceByRepository<UnitsOfMeasurement> service;
    private final UnitsOfMeasurementValidator validator;

    @Autowired
    public UnitsOfMeasurementController(UnitsOfMeasurementServiceByRepository<UnitsOfMeasurement> service, UnitsOfMeasurementValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public List<UnitsOfMeasurement> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<UnitsOfMeasurement> addUnitOfMeasurement(
            @RequestBody @Valid UnitsOfMeasurementDto unitsOfMeasurementdto, BindingResult bindingResult)
            throws UnitsOfMeasurementNotCreatedException, UnitsOfMeasurementNotFoundException {
        UnitsOfMeasurement unitsOfMeasurement = unitsOfMeasurementdto.mappingUnitObject();
        validator.validate(unitsOfMeasurement, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new UnitsOfMeasurementNotCreatedException(AddErrorMessageFromMyException
                    .getErrorMessage(bindingResult));
        }
        return new ResponseEntity<>(service.save(unitsOfMeasurement), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UnitsOfMeasurement getById(@PathVariable long id) throws UnitsOfMeasurementNotFoundException {
        return service.findById(id);
    }


    @ExceptionHandler({UnitsOfMeasurementNotDeletedException.class,
            UnitsOfMeasurementNotCreatedException.class})
    private ResponseEntity<UnitsOfMeasurementErrorResponse> getUnitsOfMeasurementErrorResponse(Exception e) {
        UnitsOfMeasurementErrorResponse response = new UnitsOfMeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnitsOfMeasurementEmptyListException.class)
    private ResponseEntity<UnitsOfMeasurementErrorResponse> getUnitsErrorResponse(Exception e) {
        UnitsOfMeasurementErrorResponse response = new UnitsOfMeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UnitsOfMeasurementNotFoundException.class)
    private ResponseEntity<UnitsOfMeasurementErrorResponse> getErrorResponse(Exception e) {
        UnitsOfMeasurementErrorResponse response = new UnitsOfMeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis(),
                e.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
