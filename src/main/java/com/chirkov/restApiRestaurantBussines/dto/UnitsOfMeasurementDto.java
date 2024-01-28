package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitsOfMeasurementDto {


    @NotNull(message = "UnitsOfMeasurementDto/_name must not be null")
    @NotEmpty(message = "UnitsOfMeasurementDto/_name must not be empty")
    @Size(max = 100, message = "UnitsOfMeasurementDto/_name must not exceed 100.")
    private String name;


    @NotNull(message = "UnitsOfMeasurementDto/_unitOfMeasurement must not be null")
    @NotEmpty(message = "UnitsOfMeasurementDto/_unitOfMeasurement must not be empty")
    @Size(max = 50, message = "UnitsOfMeasurementDto/_unitOfMeasurement must not exceed 50.")
    private String unitOfMeasurement;

    @NotNull(message = "UnitsOfMeasurementDto/_commentary must not be null")
    @NotEmpty(message = "UnitsOfMeasurementDto/_commentary must not be empty")
    @Size(max = 300, message = "UnitsOfMeasurementDto/_Commentary should be not exceed 300 symbol")
    private String commentary;

    public UnitsOfMeasurement mappingUnitObject() throws UnitsOfMeasurementNotFoundException {
        UnitsOfMeasurement units = new UnitsOfMeasurement();
        units.setName(name);
        EnumUnitsOfMeasurement enumUnitsOfMeasurement = Arrays.stream(EnumUnitsOfMeasurement.values())
                .filter(unitOfMeasurement -> unitOfMeasurement.name().equalsIgnoreCase(name.trim())).findFirst().orElseThrow(
                        () -> new UnitsOfMeasurementNotFoundException("Not found units of measurement = " + unitOfMeasurement));
        units.setUnitOfMeasurement(enumUnitsOfMeasurement);
        units.setCommentary(commentary);
        return units;
    }
}
