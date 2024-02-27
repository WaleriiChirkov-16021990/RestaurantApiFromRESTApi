package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.OrderServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderElementDto {

    @NotNull(message = "OrderElementDto/orderId must not be null")
    @NotEmpty(message = "OrderElementDto/orderId must not be empty")
    @Min(value = 1, message = "OrderElementDto/orderId must no less 1")
    @Max(value = Long.MAX_VALUE, message = "OrderElementDto/orderId must no greater than Long.MAX value")
    private Long orderId;

    @NotNull(message = "OrderElementDto/dishesId must not be null")
    @NotEmpty(message = "OrderElementDto/dishesId must not be empty")
    @Min(value = 1, message = "OrderElementDto/dishesId must no less 1")
    @Max(value = Long.MAX_VALUE, message = "OrderElementDto/dishesId must no greater than Long.MAX value")
    private Long dishesId;


    @NotNull(message = "OrderElementDto/quantity must not be null")
    @NotEmpty(message = "OrderElementDto/quantity must not be empty")
    @Min(value = 0, message = "OrderElementDto/quantity must no less 0")
    @Max(value = Integer.MAX_VALUE, message = "OrderElementDto/quantity must no greater than Integer.MAX value")
    private int quantity;


    public OrderElements mappingTransferObject(OrderServiceByRepository<Order> orderService, DishesServiceByRepository<Dishes> dishesService) throws OrderElementNotCreatedException {
        OrderElements orderElements = new OrderElements();
        try {
            orderElements.setOrder(orderService.findById(orderId));
            orderElements.setDishes(dishesService.findById(dishesId));
            orderElements.setCount(quantity);
        } catch (Exception e) {
            throw new OrderElementNotCreatedException(e.getMessage(), e);
        }
        return orderElements;
    }
}
