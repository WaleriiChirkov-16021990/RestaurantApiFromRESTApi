package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.ReserveTableServiceByRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class TableReservationDto {
    @NotNull
    @Min(value = 1, message = "TableReservationDto/table must no less 1")
    @Max(value = Long.MAX_VALUE, message = "TableReservationDto/table must no greater than Long.MAX value")
    private Long table;


    @NotNull(message = "TableReservationDto/owner must not be null")
    @NotEmpty(message = "TableReservationDto/owner must not be empty")
    @Min(value = 1, message = "TableReservationDto/owner must no less 1")
    @Max(value = Long.MAX_VALUE, message = "TableReservationDto/owner must no greater than Long.MAX value")
    private Long owner;


    @NotNull(message = "TableReservationDto/date must not be null")
    @NotEmpty(message = "TableReservationDto/date must not be empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{6}$", message = "TableReservationDto/_LocalDateTime is not a valid date")
    private LocalDateTime date;


    @NotNull(message = "TableReservationDto/numberOfGuests must not be null")
    @NotEmpty(message = "TableReservationDto/numberOfGuests must not be empty")
    @Min(value = 0, message = "TableReservationDto/numberOfGuests must no less 0")
    @Max(value = Integer.MAX_VALUE, message = "TableReservationDto/numberOfGuests must no greater than Integer.MAX value")
    private int numberOfGuests;


    @NotNull(message = "TableReservationDto/authorThisRecords must not be null")
    @NotEmpty(message = "TableReservationDto/authorThisRecords must not be empty")
    @Min(value = 1, message = "TableReservationDto/authorThisRecords must no less 1")
    @Max(value = Long.MAX_VALUE, message = "TableReservationDto/authorThisRecords must no greater than Long.MAX value")
    private Long authorThisRecords;

    public TableReservation mappingTableReservationDto(PeopleServiceByRepository<Person> peopleService,
                                                       ReserveTableServiceByRepository<ReserveTable> reserveTableService) {
        TableReservation tableReservation = new TableReservation();
        tableReservation.setTable(reserveTableService.findById(table));
        tableReservation.setOwner(peopleService.findById(owner));
        tableReservation.setDate(date);
        tableReservation.setNumberOfGuests(numberOfGuests);
        Person person = peopleService.findById(authorThisRecords);
        tableReservation.setAuthorThisRecords(person);
        tableReservation.setAuthorOfUpdate(person);
        return tableReservation;
    }
}
