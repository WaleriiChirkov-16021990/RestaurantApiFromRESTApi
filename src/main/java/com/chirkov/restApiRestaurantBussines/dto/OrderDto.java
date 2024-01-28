package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class OrderDto {
    @NotNull(message = "OrderDto/owner must not be null")
    @NotEmpty(message = "OrderDto/owner must not be empty")
    @Min(value = 1, message = "OrderDto/owner must no less 1")
    @Max(value = Long.MAX_VALUE, message = "OrderDto/owner must no greater than Long.MAX value")
    private Long owner;


    @NotNull(message = "OrderDto/price must not be null")
    @NotEmpty(message = "OrderDto/price must not be empty")
    @Min(value = 0, message = "The orderDto/price is no less 0. ")
    @Max(value = 1_999_999_999, message = "The orderDto/price is no greater than 1_999_999_999 value.")
    private Double price;


    @NotNull(message = "OrderDto/statusFromOrder must not be null")
    @NotEmpty(message = "OrderDto/statusFromOrder must not be empty")
    @Size(max = 100, message = "OrderDto/statusFromOrder must not exceed 100.")
    private String statusFromOrder;


    public Order mappingbyOrder(PeopleService peopleService) throws PersonNotFoundException {
        Order order = new Order();
        Person person;
        try {
            person = peopleService.findOne(owner);
        } catch (Exception e) {
            throw new PersonNotFoundException(e.getMessage(), e);
        }
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
