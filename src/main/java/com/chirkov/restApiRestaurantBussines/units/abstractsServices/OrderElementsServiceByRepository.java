package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;

import java.util.List;
import java.util.Optional;

public interface OrderElementsServiceByRepository<M> {
    M findById(Long id);
    Optional<M> getOrderElementById(Long id);
    List<M> getOrderElementsByOrder(Order order);

    List<M> findAll();

    M deleteById(Long id);

    M findByName(String name);

    M save(M discount);
}
