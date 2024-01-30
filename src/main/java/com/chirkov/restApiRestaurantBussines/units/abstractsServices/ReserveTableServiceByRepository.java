package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;

import java.util.List;

public interface ReserveTableServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    List<M> findByStateFromTable(StateFromTable status);

    M save(M discount);
}
