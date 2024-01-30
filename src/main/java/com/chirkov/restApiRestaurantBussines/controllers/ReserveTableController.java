package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.ReserveTableDto;
import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.ReserveTableServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.StateFromTablesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.ReserveTableErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.ReserveTableValidator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reserve_a_table")
public class ReserveTableController {
    private final ReserveTableServiceByRepository<ReserveTable> service;
    private final ReserveTableValidator validator;
    private final StateFromTablesServiceByRepository<StateFromTable> stateFromTablesService;

    @Autowired
    public ReserveTableController(ReserveTableServiceByRepository<ReserveTable> service,
                                  ReserveTableValidator validator,
                                  StateFromTablesServiceByRepository<StateFromTable> stateFromTablesService) {
        this.service = service;
        this.validator = validator;
        this.stateFromTablesService = stateFromTablesService;
    }

    @GetMapping
    public List<ReserveTable> findAllTables() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ReserveTable findById(@PathVariable("id") Long id) throws ReserveTableNotFoundException {
        return this.service.findById(id);
    }

    @ExceptionHandler
    private ResponseEntity<ReserveTableErrorResponse> handlerException(ReserveTableNotFoundException exception) {
        ReserveTableErrorResponse stateFromTable = new ReserveTableErrorResponse(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addReserveTable(@RequestBody @Valid ReserveTableDto tableDto, BindingResult bindingResult) {
        ReserveTable table = tableDto.mappingTable(stateFromTablesService);
        this.validator.validate(table, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ReserveTableNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }

        this.service.save(table);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler({ReserveTableNotCreatedException.class, ReserveTableNotDeletedException.class})
    private ResponseEntity<ReserveTableErrorResponse> handlerException(Exception exception) {
        ReserveTableErrorResponse stateFromTable = new ReserveTableErrorResponse(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.BAD_REQUEST);
    }
}
