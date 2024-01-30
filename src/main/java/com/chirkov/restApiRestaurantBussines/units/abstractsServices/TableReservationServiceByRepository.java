package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;

import java.util.List;

public interface TableReservationServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);


    M save(M discount);

    List<M> getTableReservationByTable(ReserveTable table);
}
