package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {
    private final OrderService orderService;
    @Autowired
    public OrderValidator(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Order.class);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;
        if (order.getPrice() < 0) {
            errors.rejectValue("price","23455555","Price cannot be negative");
        }
        // TODO Create a proper error message
//        if (orderService.findById(order.getId()) != null) {
//            errors.rejectValue("Order","23455555","This order is already exist");
//        }
    }
}
