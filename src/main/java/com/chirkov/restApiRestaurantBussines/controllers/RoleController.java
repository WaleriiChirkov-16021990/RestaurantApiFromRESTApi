package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.RoleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.RoleErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleServiceByRepository<Role> roleService;
    private final RoleValidator roleValidator;

    @Autowired
    public RoleController(RoleServiceByRepository<Role> roleService, RoleValidator roleValidator) {
        this.roleService = roleService;
        this.roleValidator = roleValidator;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') OR hasAuthority('ADMIN')")
    public Role getRoleByName(@PathVariable("id") Long id) throws RoleNotFoundException{
        return roleService.findById(id);
    }

    @PreAuthorize("hasAuthority('USER') OR hasAuthority('ADMIN')")
    @GetMapping("/name/{name}")
    public Role getRoleByName(@PathVariable("name") String name) throws RoleNotFoundException{
        return roleService.findByName(name);
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

    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> addRole(@RequestBody @Valid Role role, BindingResult bindingResult) throws RoleNotCreatedException {
        this.roleValidator.validate(role, bindingResult);
        // TODO Auto-generated method stub
        if (bindingResult.hasErrors()) {
            throw new RoleNotCreatedException(AddErrorMessageFromMyException
                    .getErrorMessage(bindingResult));
        }
        this.roleService.save(role);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler({RoleNotCreatedException.class, RoleNotDeletedException.class})
    private ResponseEntity<RoleErrorResponse> handlerException(Exception exception) {
        RoleErrorResponse roleErrorResponse = new RoleErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(roleErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
