package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
public class ReserveTableDto {

    @NotNull
    @Range(min = 1, max = 1000, message = "Accommodation must be between 0 and 1000")
    private int accommodationNumber;

    @NotNull
    @Range(min = 1, max = 5, message = "The status number must be between 1 and 5 inclusive")
    private Long stateFromTable;

    @NotNull
    @Range(min = 0, max = 100, message = "Number of seats should between 0 to 100")
    private int numberOfSeats;

    public ReserveTableDto() {
    }

    public ReserveTableDto(int accommodationNumber, Long stateFromTable, int numberOfSeats) {
        this.accommodationNumber = accommodationNumber;
        this.stateFromTable = stateFromTable;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "ReserveTableDto{" +
                "accommodationNumber=" + accommodationNumber +
                ", stateFromTable=" + stateFromTable +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    public ReserveTable mappingTable(StateFromTablesService service) {
        ReserveTable reserveTable = new ReserveTable();
        reserveTable.setStateFromTable(service.getStateById(stateFromTable));
        reserveTable.setAccommodationNumber(accommodationNumber);
        reserveTable.setNumberOfSeats(numberOfSeats);
        return reserveTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReserveTableDto that)) return false;
        return getAccommodationNumber() == that.getAccommodationNumber() && getStateFromTable() == that.getStateFromTable() && getNumberOfSeats() == that.getNumberOfSeats();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccommodationNumber(), getStateFromTable(), getNumberOfSeats());
    }
}
