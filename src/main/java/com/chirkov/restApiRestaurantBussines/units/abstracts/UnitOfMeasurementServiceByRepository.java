package com.chirkov.restApiRestaurantBussines.units.abstracts;

import java.util.List;

public interface UnitOfMeasurementServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M findByName(String name);

    M save(M discount);
}
