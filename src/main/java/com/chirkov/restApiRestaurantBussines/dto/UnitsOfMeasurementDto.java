package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitsOfMeasurementDto {
    @NotNull
    private String name;
    @NotNull
    private String unitOfMeasurement;
    @Size(min = 4, max = 300, message = "Commentary should between 4 to 300 symbol")
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
