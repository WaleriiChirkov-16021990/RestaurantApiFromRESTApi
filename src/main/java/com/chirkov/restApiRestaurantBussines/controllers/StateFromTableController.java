package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.StateFromTablesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.ErrorResponceStateFromTable;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotCreateException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.StateFromTableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/state_from_tables")
public class StateFromTableController {

    private final StateFromTablesServiceByRepository<StateFromTable> stateFromTablesService;
    private final StateFromTableValidator stateFromTablesValidator;

    @Autowired
    public StateFromTableController(StateFromTablesServiceByRepository<StateFromTable> stateFromTablesService, StateFromTableValidator stateFromTablesValidator) {
        this.stateFromTablesService = stateFromTablesService;
        this.stateFromTablesValidator = stateFromTablesValidator;
    }

    @GetMapping
    public List<StateFromTable> findAll() {
        return this.stateFromTablesService.findAll();
    }

    @GetMapping("/{id}")
    public StateFromTable getStateById(@PathVariable("id") Long id) throws StateFromTableNotFoundException {
        return this.stateFromTablesService.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponceStateFromTable> handlerException(StateFromTableNotFoundException exception) {
        ErrorResponceStateFromTable stateFromTable = new ErrorResponceStateFromTable(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addState(@RequestBody @Valid StateFromTable state, BindingResult bindingResult) throws StateFromTableNotCreateException {
        this.stateFromTablesValidator.validate(state, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new StateFromTableNotCreateException("Not unique state " + AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        this.stateFromTablesService.save(state);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler({StateFromTableNotCreateException.class, StateFromTableNotDeletedException.class})
    private ResponseEntity<ErrorResponceStateFromTable> handlerException(Exception exception) {
        ErrorResponceStateFromTable stateFromTable = new ErrorResponceStateFromTable(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.BAD_REQUEST);
    }
}
