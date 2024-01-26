package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderElementDto {
    private Long orderId;
    private Long dishesId;
    private int quantity;


    public OrderElements mappingTransferObject(OrderService orderService, DishesService dishesService) throws OrderElementNotCreatedException {
        OrderElements orderElements = new OrderElements();
        try {
            orderElements.setOrder(orderService.findById(orderId));
            orderElements.setDishes(dishesService.getDishesById(dishesId));
            orderElements.setCount(quantity);
        } catch (Exception e) {
            throw new OrderElementNotCreatedException(e.getMessage(), e);
        }
        return orderElements;
    }
}
