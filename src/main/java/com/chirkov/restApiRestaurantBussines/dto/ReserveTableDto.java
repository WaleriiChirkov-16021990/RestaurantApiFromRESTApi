package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.StateFromTablesServiceByRepository;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

//import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReserveTableDto {

    @NotNull(message = "ReserveTable/accommodationNumber must be not null")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "Accommodation must be between 0 and Integer.MAX_VALUE")
    private int accommodationNumber;

    @NotNull(message = "ReserveTable/stateFromTable must be not null")
    @Range(min = 1, max = 5, message = "The status number must be between 1 and 5 inclusive")
    private Long stateFromTable;

    @NotNull(message = "ReserveTable/numberOfSeats must be not null")
    @Range(min = 0, max = 100, message = "Number of seats should between 0 to 100")
    private int numberOfSeats;

    public ReserveTable mappingTable(StateFromTablesServiceByRepository<StateFromTable> service) {
        ReserveTable reserveTable = new ReserveTable();
        reserveTable.setStateFromTable(service.findById(stateFromTable));
        reserveTable.setAccommodationNumber(accommodationNumber);
        reserveTable.setNumberOfSeats(numberOfSeats);
        return reserveTable;
    }
}
