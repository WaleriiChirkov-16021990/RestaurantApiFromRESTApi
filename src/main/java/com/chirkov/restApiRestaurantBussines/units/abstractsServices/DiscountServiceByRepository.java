package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;

import java.util.List;
import java.util.Optional;

public interface DiscountServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M findByName(String name);

    M save(M discount);
    Optional<M> findByNameOptional(String name);

    Optional<M> findByDiscountValue(DiscountEnum discount);
}
