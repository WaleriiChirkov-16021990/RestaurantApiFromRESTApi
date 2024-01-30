package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import java.util.List;

public interface UnitsOfMeasurementServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M findByName(String name);

    M save(M discount);
}
