package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.OrderElementDto;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.OrderElementsService;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.OrderElementsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.OrderServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderElementErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderElementsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order-element")
public class OrderElementController {
    private final OrderElementsServiceByRepository<OrderElements> service;
    private final DishesServiceByRepository<Dishes> dishesService;
    private final OrderServiceByRepository<Order> orderService;
    private final OrderElementsValidator validator;

    @Autowired
    public OrderElementController(OrderElementsServiceByRepository<OrderElements> service,
                                  DishesServiceByRepository<Dishes> dishesService,
                                  OrderServiceByRepository<Order> orderService,
                                  OrderElementsValidator validator) {
        this.service = service;
        this.dishesService = dishesService;
        this.orderService = orderService;
        this.validator = validator;
    }

    @GetMapping
    public List<OrderElements> getOrderElements() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OrderElements getOrderElementsById(@PathVariable("id") long id) throws OrderElementNotFoundException {
        return service.findById(id);
    }

    @GetMapping("/order/{order}")
    public List<OrderElements> getOrderElementByOrder(@PathVariable("order") Order order) throws OrderElementNotFoundException {
        return service.getOrderElementsByOrder(order);
    }

    @PostMapping
    public ResponseEntity<OrderElements> addOrder(@RequestBody @Valid OrderElementDto orderElementsDto, BindingResult bindingResult) throws OrderElementNotCreatedException {
        OrderElements orderElements = orderElementsDto.mappingTransferObject(orderService, dishesService);
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

    @ExceptionHandler({OrderElementNotCreatedException.class, OrderElementNotDeletedException.class})
    public ResponseEntity<OrderElementErrorResponse> handlerException(Exception exception) {
        OrderElementErrorResponse errorResponse = new OrderElementErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getClass().getSimpleName()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
