package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DiscountServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.DiscountErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.DiscountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountServiceByRepository<Discount> discountService;
    private final DiscountValidator discountValidator;

    @Autowired
    public DiscountController(DiscountServiceByRepository<Discount> discountService, DiscountValidator discountValidator) {
        this.discountService = discountService;
        this.discountValidator = discountValidator;
    }

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return this.discountService.findAll();
    }

    @GetMapping("/{id}")
    public Discount getDiscount(@PathVariable("id") Long id) throws DiscountNotFoundException {
        return this.discountService.findById(id);
    }

    @GetMapping("/name/{name}")
    public Discount getDiscount(@PathVariable("name") String name) throws DiscountNotFoundException {
        return this.discountService.findByName(name);
    }

    @ExceptionHandler
    private ResponseEntity<DiscountErrorResponse> handlerException(DiscountNotFoundException exception) {
        DiscountErrorResponse response = new DiscountErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addDiscount(@RequestBody @Valid Discount discount, BindingResult bindingResult) {
        this.discountValidator.validate(discount, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new DiscountNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        this.discountService.save(discount);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler({DiscountNotCreatedException.class, DiscountNotDeletedException.class})
    private ResponseEntity<DiscountErrorResponse> handlerException(Exception exception) {
        DiscountErrorResponse exceptionHandler = new DiscountErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(exceptionHandler, HttpStatus.BAD_REQUEST);
    }
}
