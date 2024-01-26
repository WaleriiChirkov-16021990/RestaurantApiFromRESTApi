package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.OrderElementDto;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.OrderElementsService;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderElementErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderElementsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order-element")
public class OrderElementController {
    private final OrderElementsService service;
    private final DishesService dishesService;
    private final OrderService orderService;
    private final OrderElementsValidator validator;

    @Autowired
    public OrderElementController(OrderElementsService service, DishesService dishesService, OrderService orderService, OrderElementsValidator validator) {
        this.service = service;
        this.dishesService = dishesService;
        this.orderService = orderService;
        this.validator = validator;
    }

    @GetMapping
    public List<OrderElements> getOrderElements() {
        return service.getAllOrderElements();
    }

    @GetMapping("/{id}")
    public OrderElements getOrderElementsById(@PathVariable("id") long id) throws OrderElementNotFoundException {
        return service.getOrderElementsById(id);
    }

    @GetMapping("/order/{order}")
    public List<OrderElements> getOrderElementByOrder(@PathVariable("order") Order order) throws OrderElementNotFoundException {
        return service.getOrderElementsByOrder(order);
    }

    @PostMapping
    public ResponseEntity<OrderElements> addOrder(@RequestBody @Valid OrderElementDto orderElementsdto, BindingResult bindingResult) throws OrderElementNotCreatedException {
        OrderElements orderElements = orderElementsdto.mappingTransferObject(orderService,dishesService);
        validator.validate(orderElements, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new OrderElementNotCreatedException(AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        service.save(orderElements);
        return new ResponseEntity<>(orderElements, HttpStatus.CREATED);
    }

    @ExceptionHandler
    public ResponseEntity<OrderElementErrorResponse> handlerException(OrderElementNotFoundException exception) {
        OrderElementErrorResponse errorResponse = new OrderElementErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OrderElementErrorResponse> handlerException(OrderElementNotCreatedException exception) {
        OrderElementErrorResponse errorResponse = new OrderElementErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
