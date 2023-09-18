//package com.chirkov.restApiRestaurantBussines.models;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.validator.constraints.Range;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//@Entity
//@Table(name = "reverse_table")
//@Getter
//@Setter
//public class ReserveTable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "reverse_table_id")
//    private int id;
//
//    @NotNull
//    @Column(name = "reverse_table_accomodation_number")
//    private int accommodationNumber;
//
//    @NotNull
//    @Column(name = "reverse_table_state")
//    private StateFromTable state;
//
//    @NotNull
//    @Column(name = "reverse_table_number_of_seats")
//    @Range(min = 0, max = 100, message = "Number of seats should between 0 to 100")
//    private int numberOfSeats;
//
//    @OneToMany(mappedBy = "table")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<TableReservation> reservationList;
//
//    public ReserveTable() {
//    }
//}
