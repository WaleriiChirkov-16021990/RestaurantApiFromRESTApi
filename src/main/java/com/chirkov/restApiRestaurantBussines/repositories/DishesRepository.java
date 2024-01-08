package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {
    Optional<List<Dishes>> findByPrice(double price);

    Optional<List<Dishes>> findByCarbohydrates(int carbohydrates);

    Optional<List<Dishes>> findByFats(int fats);

    Optional<List<Dishes>> findByProteins(int proteins);

    Optional<List<Dishes>> findByCalories(int calories);

    Optional<List<Dishes>> findByWeight(double weight);
    Optional<Dishes> findByName(String name);

    Optional<List<Dishes>> findByNameStartingWith(String name);

}
