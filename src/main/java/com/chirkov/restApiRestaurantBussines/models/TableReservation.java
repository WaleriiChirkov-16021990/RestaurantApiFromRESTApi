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
    @Column(name = "id")
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private ReserveTable table;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person owner;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "time")
    private Time time;

    @NotNull
    @Column(name = "number_of_guests")
    @Range(min = 1, max = 100, message = "Number of seats should between 1 to 100")
    private int numberOfGuests;

    @NotNull
    @Column(name = "create_at")
    private LocalDateTime create_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_from_record", referencedColumnName = "id")
    @NotNull
    private Person authorThisRecodrs;

    @NotNull
    @Column(name = "update_at")
    private LocalDateTime update_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_from_update", referencedColumnName = "id")
    @NotNull
    private Person authorOfUpdate;

    public TableReservation() {

    }

}
