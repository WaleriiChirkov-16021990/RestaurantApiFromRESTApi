package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import java.util.List;

public interface CompositionsOfDishesServiceByRepository <M> {
    M findById(Long id);
    List<M> findAll();
//    <M> M save(M data);
    M deleteById(Long id);
    M findByName (String name);

    M save(M compositions);
}
