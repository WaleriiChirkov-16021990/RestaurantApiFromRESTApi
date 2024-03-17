package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients,Long> {
    Optional<List<Ingredients>> getByIngredientNameContaining(String ingredientName);
    Optional<Ingredients> getByIngredientName(String ingredientName);
    Optional<List<Ingredients>> getByIngredientNameIsStartingWith(String ingredientName);

    Optional<List<Ingredients>> getByRemnant(int remnant);
//    Optional<List<Ingredients>> getByVeganIs(boolean veganIs);
//    Optional<List<Ingredients>> getBySpicyIs(boolean spicyIs);
}
