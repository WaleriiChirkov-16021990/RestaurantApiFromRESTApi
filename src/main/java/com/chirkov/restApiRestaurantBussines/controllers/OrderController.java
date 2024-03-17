package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.dto.OrderDto;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.AddErrorMessageFromMyException;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.OrderServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.*;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderValidator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

    @RestController
    @RequestMapping("/orders")
    @Getter
    public class OrderController {
        private final OrderServiceByRepository<Order> orderService;
        private final OrderValidator orderValidator;
        private final PeopleServiceByRepository<Person> peopleService;

        @Autowired
        public OrderController(OrderServiceByRepository<Order> orderService, OrderValidator orderValidator, PeopleServiceByRepository<Person> peopleService) {
            this.orderService = orderService;
            this.orderValidator = orderValidator;
            this.peopleService = peopleService;
        }

        @GetMapping
        public List<Order> findAllOrders() {
            return orderService.findAll();
        }

        @GetMapping("/{id}")
        public Order findById(@PathVariable("id") Long id) throws OrderNotFoundException {
            return orderService.findById(id);
        }

        @PostMapping
        public ResponseEntity<HttpStatus> save(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult) throws OrderNotCreatedException {
            Order order = orderDto.mappingbyOrder(this.peopleService);
            orderValidator.validate(order, bindingResult);
            if (bindingResult.hasErrors()) {
                throw new OrderNotCreatedException(
                        AddErrorMessageFromMyException.getErrorMessage(bindingResult));
            }
            this.orderService.save(order);
            return ResponseEntity.ok(HttpStatus.CREATED);
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

        @ExceptionHandler({OrderNotDeletedException.class, OrderNotCreatedException.class})
        public ResponseEntity<OrderErrorResponse> handleOrder(Exception foundException) {
            OrderErrorResponse orderErrorResponse = new OrderErrorResponse(
                    foundException.getMessage(),
                    System.currentTimeMillis(),
                    foundException.getClass().getSimpleName());
    //        TODO find right info for Exception
            return new ResponseEntity<>(orderErrorResponse, HttpStatus.BAD_REQUEST);
        }
    }
