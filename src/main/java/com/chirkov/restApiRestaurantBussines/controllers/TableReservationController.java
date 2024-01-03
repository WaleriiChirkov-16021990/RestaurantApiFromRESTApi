package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.services.TableReservationService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.TableReservationErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.TableReservationNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.TableReservationNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.TableReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/table-reservations")
public class TableReservationController {
    private final TableReservationService service;
    private final TableReservationValidator validator;

    @Autowired
    public TableReservationController(TableReservationService service, TableReservationValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/all")
    public List<TableReservation> getAllTableReservations() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TableReservation getTableReservationById(@PathVariable("id") int id) throws TableReservationNotFoundException {
        return service.getTableReservationById(id);
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid TableReservation reservation, BindingResult bindingResult) throws TableReservationNotCreatedException {
        validator.validate(reservation, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new TableReservationNotCreatedException(
                    AddErrorMessageFromMyException.getErrorMessage(bindingResult)
            );
        }
        service.save(reservation);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<TableReservationErrorResponse> handlerException(TableReservationNotFoundException exception) {
        TableReservationErrorResponse stateFromTable = new TableReservationErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    private ResponseEntity<TableReservationErrorResponse> handlerException(TableReservationNotCreatedException exception) {
        TableReservationErrorResponse stateFromTable = new TableReservationErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.NOT_FOUND);
    }
}
