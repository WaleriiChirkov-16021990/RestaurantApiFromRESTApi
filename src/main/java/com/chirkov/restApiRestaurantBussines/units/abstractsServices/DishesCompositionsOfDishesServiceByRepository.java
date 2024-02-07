package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.DishesCompositionsOfDishes;

import java.util.List;
import java.util.Optional;

public interface DishesCompositionsOfDishesServiceByRepository<M> {
    Optional<List<M>> findByCompositionsOfDishes(CompositionsOfDishes compositions);

    Optional<List<M>> findByDishes(Dishes dishes);

    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M save(M discount);
}
