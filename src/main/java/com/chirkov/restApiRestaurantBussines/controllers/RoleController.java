package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.RoleErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.validators.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final RoleValidator roleValidator;

    @Autowired
    public RoleController(RoleService roleService, RoleValidator roleValidator) {
        this.roleService = roleService;
        this.roleValidator = roleValidator;
    }

    @GetMapping("/all")
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role getRoleByName(@PathVariable("id") int id) throws RoleNotFoundException {
        return roleService.getRoleById(id);
    }

    @GetMapping("/name/{name}")
    public Role getRoleByName(@PathVariable("name") String name) throws RoleNotFoundException {
        return roleService.getRoleByName(name).orElseThrow(RoleNotFoundException::new);
    }

    @ExceptionHandler
    private ResponseEntity<RoleErrorResponse> handlerException(RoleNotFoundException exception) {
        RoleErrorResponse response = new RoleErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addRole(@RequestBody @Valid Role role, BindingResult bindingResult) throws RoleNotCreatedException {
//        this.roleValidator.validate(role, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new RoleNotCreatedException(AddErrorMessageFromMyException
                    .getErrorMessage(bindingResult));
        }
        this.roleService.save(role);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<RoleErrorResponse> handlerException(RoleNotCreatedException exception) {
        RoleErrorResponse roleErrorResponse = new RoleErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(roleErrorResponse, HttpStatus.BAD_REQUEST);
    }


}
