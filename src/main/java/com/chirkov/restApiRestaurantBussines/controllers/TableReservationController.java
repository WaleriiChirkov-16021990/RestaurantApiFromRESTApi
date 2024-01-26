package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.TableReservationDto;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
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
    private final PeopleService peopleService;
    private final ReserveTableService reserveTableService;

    @Autowired
    public TableReservationController(TableReservationService service, TableReservationValidator validator, PeopleService peopleService, ReserveTableService reserveTableService) {
        this.service = service;
        this.validator = validator;
        this.peopleService = peopleService;
        this.reserveTableService = reserveTableService;
    }

    @GetMapping
    public List<TableReservation> getAllTableReservations() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public TableReservation getTableReservationById(@PathVariable("id") Long id) throws TableReservationNotFoundException {
        return this.service.getTableReservationById(id);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid TableReservationDto reservationDto, BindingResult bindingResult) throws TableReservationNotCreatedException {
        TableReservation reservation = reservationDto.mappingTableReservationDto(peopleService,reserveTableService);
        // TODO Auto-generated method Update reservations
        this.validator.validate(reservation, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new TableReservationNotCreatedException(
                    AddErrorMessageFromMyException.getErrorMessage(bindingResult)
            );
        }
        this.service.save(reservation);
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
