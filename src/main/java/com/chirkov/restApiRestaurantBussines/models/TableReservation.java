package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "table_reservation",schema = "public")
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_reservation_id")
    private int id;

    @NotNull
    @JsonIgnore // TODO узнать почему fetch не работает без явного исключения из response
//    @Fetch(FetchMode.SELECT)
    @ManyToOne
    @JoinColumn(name = "table_reservation_table_id", referencedColumnName = "reverse_table_id")
    private ReserveTable table;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_user_id", referencedColumnName = "person_id")
    private Person owner;

    @NotNull
    @Column(name = "table_reservation_date")
    private LocalDateTime date;

    @NotNull
    @Column(name = "table_reservation_number_of_guests")
    @Range(min = 1, max = 100, message = "Number of seats should between 1 to 100")
    private int numberOfGuests;

    @Column(name = "table_reservation_create_at")
    private LocalDateTime create_at;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_author_from_record", referencedColumnName = "person_id")
    @NotNull
    private Person authorThisRecords;

    @Column(name = "table_reservation_update_at")
    private LocalDateTime update_at;

//    @ManyToOne(fetch = FetchType.LAZY)

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_reservation_author_from_update", referencedColumnName = "person_id")
    @NotNull
    private Person authorOfUpdate;

    public TableReservation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableReservation that)) return false;
        return getNumberOfGuests() == that.getNumberOfGuests() && Objects.equals(getTable(), that.getTable()) && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTable(), getOwner(), getDate(), getNumberOfGuests());
    }

    @Override
    public String toString() {
        return "TableReservation{" +
                "id=" + id +
                ", table=" + table +
                ", owner=" + owner +
                ", date=" + date +
                ", numberOfGuests=" + numberOfGuests +
                ", create_at=" + create_at +
                ", authorThisRecords=" + authorThisRecords +
                ", update_at=" + update_at +
                ", authorOfUpdate=" + authorOfUpdate +
                '}';
    }
}
