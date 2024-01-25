package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompositionsOfDishesRepository extends JpaRepository<CompositionsOfDishes,Long> {
        Optional<CompositionsOfDishes> findCompositionsOfDishesByName(String name);
}
