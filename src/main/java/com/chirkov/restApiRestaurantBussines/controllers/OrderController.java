package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.OrderDto;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderValidator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Getter
public class OrderController {
    private final OrderService orderService;
    private final OrderValidator orderValidator;
    private final PeopleService peopleService;

    @Autowired
    public OrderController(OrderService orderService, OrderValidator orderValidator, PeopleService peopleService) {
        this.orderService = orderService;
        this.orderValidator = orderValidator;
        this.peopleService = peopleService;
    }

    @GetMapping("/all")
    public List<Order> findAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") int id) throws OrderNotFoundException {
        return orderService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult) throws OrderNotCreatedException {
        Order order = orderDto.mappingbyOrder(this.peopleService);
        orderValidator.validate(order, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new OrderNotCreatedException(
                    AddErrorMessageFromMyException.getErrorMessage(bindingResult));
        }
        this.orderService.save(order);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<OrderErrorResponse> handleOrder(OrderNotCreatedException createdException) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse(
                createdException.getMessage(),
                System.currentTimeMillis(),
                createdException.getClass().getSimpleName());
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<OrderErrorResponse> handleOrder(OrderNotFoundException foundException) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse(
                foundException.getMessage(),
                System.currentTimeMillis(),
                foundException.getClass().getSimpleName()
//                ); TODO find right info for Exception
        );
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OrderErrorResponse> handleOrder(PersonNotFoundException foundException) {
        OrderErrorResponse orderErrorResponse = new OrderErrorResponse(
                foundException.getMessage(),
                System.currentTimeMillis(),
                foundException.getClass().getSimpleName());
//        TODO find right info for Exception
        return new ResponseEntity<>(orderErrorResponse, HttpStatus.NOT_FOUND);
    }
}
