package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {
    Optional<List<TableReservation>> getTableReservationByTable(ReserveTable table);

    List<TableReservation> getTableReservationByAuthorOfUpdate(Person AuthorOfUpdate);

    List<TableReservation> getTableReservationByDate(LocalDateTime date);

    List<TableReservation> getTableReservationByOwner(Person owner);

    List<TableReservation> getTableReservationByAuthorThisRecords(Person owner);

    Optional<TableReservation> getTableReservationById(Long id);

}
