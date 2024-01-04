package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class TableReservationDto {
    @NotNull
    @Range(min = 1, max = 100, message = "Id of table is required and between 1 to 100")
    private int table;

    @NotNull
    private int owner;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Range(min = 1, max = 100, message = "Number of seats should between 1 to 100")
    private int numberOfGuests;

    @NotNull
    private int authorThisRecords;

    public TableReservation mappingTableReservationDto(PeopleService peopleService, ReserveTableService reserveTableService) {
        TableReservation tableReservation = new TableReservation();
        tableReservation.setTable(reserveTableService.findReserveById(table));
        tableReservation.setOwner(peopleService.findOne(owner));
        tableReservation.setDate(date);
        tableReservation.setNumberOfGuests(numberOfGuests);
        Person person = peopleService.findOne(authorThisRecords);
        tableReservation.setAuthorThisRecords(person);
        tableReservation.setAuthorOfUpdate(person);
        return tableReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableReservationDto that)) return false;
        return getTable() == that.getTable() && getOwner() == that.getOwner() && getNumberOfGuests() == that.getNumberOfGuests() && getAuthorThisRecords() == that.getAuthorThisRecords() && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTable(), getOwner(), getDate(), getNumberOfGuests(), getAuthorThisRecords());
    }

    @Override
    public String toString() {
        return "TableReservationDto{" +
                "table=" + table +
                ", owner=" + owner +
                ", date=" + date +
                ", numberOfGuests=" + numberOfGuests +
                ", authorThisRecords=" + authorThisRecords +
                '}';
    }
}
