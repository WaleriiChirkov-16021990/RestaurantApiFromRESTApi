package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.ErrorResponceStateFromTable;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotCreateException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.StateFromTableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/state_from_tables")
public class StateFromTableController {

    private final StateFromTablesService stateFromTablesService;
    private final StateFromTableValidator stateFromTablesvalidator;

    @Autowired
    public StateFromTableController(StateFromTablesService stateFromTablesService, StateFromTableValidator stateFromTablesvalidator) {
        this.stateFromTablesService = stateFromTablesService;
        this.stateFromTablesvalidator = stateFromTablesvalidator;
    }

    @GetMapping("/all")
    public List<StateFromTable> findAll() {
        return this.stateFromTablesService.findAll();
    }

    @GetMapping("/{id}")
    public StateFromTable getStateById(@PathVariable("id") int id) throws StateFromTableNotFoundException {
        return this.stateFromTablesService.getStateById(id);
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

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addState(@RequestBody @Valid StateFromTable state, BindingResult bindingResult) {
//        this.stateFromTablesvalidator.validate(state, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new StateFromTableNotCreateException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        this.stateFromTablesService.save(state);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponceStateFromTable> handlerException(StateFromTableNotCreateException exception) {
        ErrorResponceStateFromTable stateFromTable = new ErrorResponceStateFromTable(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(stateFromTable,HttpStatus.BAD_REQUEST);
    }

}
