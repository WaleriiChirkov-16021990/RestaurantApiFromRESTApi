package com.chirkov.restApiRestaurantBussines.controllers;

import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.services.OrderElementsService;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderElementsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-element")
public class OrderElementController {
    private final OrderElementsService service;
    private final OrderElementsValidator validator;

    @Autowired
    public OrderElementController(OrderElementsService service, OrderElementsValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/all")
    public List<OrderElements> getOrderElements() {
        return service.getAllOrderElements();
    }
}
