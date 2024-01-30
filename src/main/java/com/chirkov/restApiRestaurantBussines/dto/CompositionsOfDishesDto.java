package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.IngredientsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.UnitsOfMeasurementServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesNotCreatedException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompositionsOfDishesDto {
    @NotNull(message = "compositionsOfDishesDto/name must not be null")
    @NotEmpty(message = "compositionsOfDishesDto/name must not be empty")
    @Size(max = 300, message = "compositionsOfDishesDto/name must not exceed 200.")
    private String name;

    @NotNull(message = "compositionsOfDishesDto/ingredient must not be null")
    @NotEmpty(message = "compositionsOfDishesDto/ingredient must not be empty")
    @Min(value = 0, message = "compositionsOfDishes/ingredient must no less 0")
    @Max(value = Long.MAX_VALUE, message = "compositionsOfDishes/ingredient must no greater than Long.MAX value")
    private long ingredient;

    @NotNull(message = "compositionsOfDishesDto/count must not be null")
    @NotEmpty(message = "compositionsOfDishesDto/count must not be empty")
    @Min(value = 0, message = "compositionsOfDishesDto/count must no less 0")
    @Max(value = Integer.MAX_VALUE, message = "compositionsOfDishes/count must not exceed Integer.MAX_VALUE")
    private int count;

    @NotNull(message = "compositionsOfDishesDto/units must not be null")
    @NotEmpty(message = "compositionsOfDishesDto/units must not be empty")
    @Min(value = 0, message = "compositionsOfDishes/units must no less 0")
    @Max(value = Long.MAX_VALUE, message = "compositionsOfDishes/units must no greater than Long.MAX value")
    private long units;

    public CompositionsOfDishes mappingObject(IngredientsServiceByRepository<Ingredients> serviceIngredient,
                                              UnitsOfMeasurementServiceByRepository<UnitsOfMeasurement> service)
            throws CompositionsOfDishesNotCreatedException {
        try {
            CompositionsOfDishes compositions = new CompositionsOfDishes();
            Ingredients ingredients;
            ingredients = serviceIngredient.findById(ingredient);
            UnitsOfMeasurement unitsOfMeasurement = service.findById(units);
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
