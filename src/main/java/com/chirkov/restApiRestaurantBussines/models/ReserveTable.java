package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Reserve_table",schema = "public")
@Getter
@Setter
public class ReserveTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reverse_table_id")
    private Long id;

    @NotNull
    @Column(name = "reverse_table_accommodation_number")
    private int accommodationNumber;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserve_table_state",referencedColumnName = "state_from_table_id")
    private StateFromTable stateFromTable;

    @NotNull
    @Column(name = "reverse_table_number_of_seats")
    @Range(min = 0, max = 100, message = "Number of seats should between 0 to 100")
    private int numberOfSeats;

    @OneToMany(mappedBy = "table",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<TableReservation> reservationList;

    public ReserveTable() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReserveTable that)) return false;
        return getId() == that.getId() && getAccommodationNumber() == that.getAccommodationNumber() && getNumberOfSeats() == that.getNumberOfSeats() && Objects.equals(getStateFromTable(), that.getStateFromTable());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccommodationNumber(), getStateFromTable(), getNumberOfSeats());
    }

    @Override
    public String toString() {
        return "ReserveTable{" +
                "id=" + id +
                ", accommodationNumber=" + accommodationNumber +
                ", stateFromTable=" + stateFromTable +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
