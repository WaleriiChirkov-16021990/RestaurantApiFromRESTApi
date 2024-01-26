package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesNotCreatedException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompositionsOfDishesDto {
    private String name;
    private long ingredient;
    private int count;
    private long units;

    public CompositionsOfDishes mappingObject(IngredientsService serviceIngredient, UnitsOfMeasurementService service)
            throws CompositionsOfDishesNotCreatedException {
        try {
            CompositionsOfDishes compositions = new CompositionsOfDishes();
            Ingredients ingredients;
            ingredients = serviceIngredient.getById(ingredient);
            UnitsOfMeasurement unitsOfMeasurement = service.getUnitsOfMeasurementById(units);
            compositions.setName(name);
            compositions.setCount(count);
            compositions.setIngredient(ingredients);
            compositions.setUnits(unitsOfMeasurement);
            return compositions;
        } catch (Exception e) {
            throw new CompositionsOfDishesNotCreatedException("Could not create composition? Dto\n" + e.getMessage(), e);
        }


    }
}
