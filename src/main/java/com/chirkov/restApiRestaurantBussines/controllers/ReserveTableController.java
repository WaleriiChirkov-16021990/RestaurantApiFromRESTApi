package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.ReserveTableDto;
import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.ReserveTableErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotCreatedException;
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
    private final ReserveTableService service;
    private final ReserveTableValidator validator;
    private final StateFromTablesService stateFromTablesService;

    @Autowired
    public ReserveTableController(ReserveTableService service, ReserveTableValidator validator, StateFromTablesService stateFromTablesService) {
        this.service = service;
        this.validator = validator;
        this.stateFromTablesService = stateFromTablesService;
    }

    @GetMapping("/all")
    public List<ReserveTable> findAllTables() {
        return this.service.findAll();
    }
    /*
    The error message you provided indicates that there was an issue resolving the template "reserveatable/all" in your application. The template resolver was unable to find or access the specified template.

Here are some possible causes and solutions for this error:

1. Missing template: Make sure that the template "reserveatable/all" exists in the correct location within your project's template directory. Double-check the file name, extension, and any subdirectories.

2. Incorrect configuration: Verify that the template resolver configuration is set up correctly in your application. Ensure that the template resolver is looking in the correct directories and has the necessary permissions to access the templates.

3. Template resolver order: If you have multiple template resolvers configured, check the order in which they are defined. The resolver responsible for resolving the "reserveatable/all" template should be configured before any other resolvers that could interfere with its resolution.

4. Permissions and file accessibility: Ensure that the user running your application has the necessary permissions to access the template file. Check the file permissions and the ownership of the template file.

By addressing these potential issues, you should be able to resolve the "TemplateInputException" and successfully access the "reserveatable/all" template in your application.
     */

    @GetMapping("/{id}")
    public ReserveTable findById(@PathVariable("id") int id) throws ReserveTableNotFoundException {
        return this.service.findReserveById(id);
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

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addReserveTable(@RequestBody @Valid ReserveTableDto tableDto, BindingResult bindingResult) {
        ReserveTable table = tableDto.mappingTable(stateFromTablesService);
        this.validator.validate(table, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ReserveTableNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }

        this.service.save(table);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ReserveTableErrorResponse> handlerException(ReserveTableNotCreatedException exception) {
        ReserveTableErrorResponse stateFromTable = new ReserveTableErrorResponse(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(stateFromTable, HttpStatus.BAD_REQUEST);
    }
}
