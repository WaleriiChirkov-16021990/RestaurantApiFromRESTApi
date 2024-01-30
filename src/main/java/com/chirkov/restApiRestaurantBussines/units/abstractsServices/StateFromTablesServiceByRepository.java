package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;

import java.util.List;
import java.util.Optional;

public interface StateFromTablesServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M findByName(String name);

    M save(M discount);

    Optional<M> getStateByValue(String stateValue);
}
