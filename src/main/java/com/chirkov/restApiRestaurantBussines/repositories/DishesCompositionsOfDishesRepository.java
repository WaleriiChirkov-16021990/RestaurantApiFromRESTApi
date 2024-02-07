package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.DishesCompositionsOfDishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesCompositionsOfDishesRepository extends JpaRepository<DishesCompositionsOfDishes,Long> {
    Optional<List<DishesCompositionsOfDishes>> findByCompositionsOfDishes(CompositionsOfDishes compositions);
    Optional<List<DishesCompositionsOfDishes>> findByDishes(Dishes dishes);
}
