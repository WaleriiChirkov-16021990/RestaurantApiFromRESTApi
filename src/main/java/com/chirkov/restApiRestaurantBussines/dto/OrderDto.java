package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long owner;
    private double price;
    private String statusFromOrder;

    public Order mappingbyOrder(PeopleService peopleService) throws PersonNotFoundException {
        Order order = new Order();
        Person person = peopleService.findOne(owner);
        order.setOwner(person);
        order.setPrice(price);
        for (StatusFromOrder status : StatusFromOrder.values()) {
            if (status.toString().equals(statusFromOrder)) {
                order.setStatusFromOrder(status);
            }
        }
        return order;
    }
}
