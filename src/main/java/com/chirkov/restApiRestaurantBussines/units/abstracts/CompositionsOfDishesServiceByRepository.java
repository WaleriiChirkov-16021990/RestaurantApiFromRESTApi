package com.chirkov.restApiRestaurantBussines.units.abstracts;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompositionsOfDishesServiceByRepository <M> {
    M findById(Long id);
    List<M> findAll();
//    <M> M save(M data);
    M deleteById(Long id);
    M findByName (String name);

    M save(M compositions);
}
