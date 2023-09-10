package com.chirkov.restApiRestaurantBussines.models;

import java.sql.Time;
import java.time.LocalDate;

public class TableReservation {
    private int id;
    private ReserveTable table;
    private Person owner;
    private LocalDate date;
    private Time time;

}
