package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;

import java.util.List;
import java.util.Optional;

public interface OrderServiceByRepository<M> {
    List<M> findByOwner(Person owner);

    M findById(Long id);

    Optional<List<M>> findByPrice(double price);

    Optional<List<M>> findByStatusFromOrder(StatusFromOrder status);

    List<M> findAll();

    M deleteById(Long id);


    M save(M discount);
}
