package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReserveTableRepository extends JpaRepository<ReserveTable,Integer> {
    Optional<ReserveTable> findReserveTableByAccommodationNumber(int tableNumber);
    Optional<List<ReserveTable>> findReserveTableByStateFromTable(StateFromTable value);
    Optional<List<ReserveTable>> findReserveTableByNumberOfSeats(int numberOfSeats);
}
