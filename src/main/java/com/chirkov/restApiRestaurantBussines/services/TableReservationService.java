package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.repositories.TableReservationRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.TableReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {TableReservationNotFoundException.class})
public class TableReservationService {
    private final TableReservationRepository repository;

    @Autowired
    public TableReservationService(TableReservationRepository repository) {
        this.repository = repository;
    }

    public List<TableReservation> findAll() {
        return repository.findAll();
    }

    public TableReservation getTableReservationById(Long id) {
        Optional<TableReservation> reservation = repository.getTableReservationById(id);
        return reservation.orElseThrow(() -> new TableReservationNotFoundException("Table reservation not found for id : " + id));
    }

    public List<TableReservation> getTableReservationByTable(ReserveTable table) {
        Optional<List<TableReservation>> reservation = repository.getTableReservationByTable(table);
        return reservation.orElseThrow(() -> new TableReservationNotFoundException("This table reservation does not exist"));
    }

    @Transactional
    public void save(TableReservation tableReservation) {
        enrichTableReservation(tableReservation);
        this.repository.save(tableReservation);
    }

    private void enrichTableReservation(TableReservation tableReservation) {
        tableReservation.setCreate_at(LocalDateTime.now());
        tableReservation.setUpdate_at(LocalDateTime.now());
    }
}
