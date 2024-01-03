package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "table_reservation")
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_reservation_id")
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_table_id", referencedColumnName = "reserve_table_id")
    private ReserveTable table;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_user_id", referencedColumnName = "person_id")
    private Person owner;

    @NotNull
    @Column(name = "table_reservation_date")
    private LocalDate date;

    @NotNull
    @Column(name = "table_reservation_time")
    private Time time;

    @NotNull
    @Column(name = "table_reservation_number_of_guests")
    @Range(min = 1, max = 100, message = "Number of seats should between 1 to 100")
    private int numberOfGuests;

    @NotNull
    @Column(name = "table_reservation_create_at")
    private LocalDateTime create_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_author_from_record", referencedColumnName = "person_id")
    @NotNull
    private Person authorThisRecords;

    @NotNull
    @Column(name = "table_reservation_update_at")
    private LocalDateTime update_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_author_from_update", referencedColumnName = "person_id")
    @NotNull
    private Person authorOfUpdate;

    public TableReservation() {

    }

}
