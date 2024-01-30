package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.repositories.TableReservationRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.TableReservationServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.TableReservationNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.TableReservationNotDeletedException;
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
        rollbackFor = {TableReservationNotCreatedException.class, TableReservationNotDeletedException.class})
public class TableReservationService implements TableReservationServiceByRepository<TableReservation> {
    private final TableReservationRepository repository;

    @Autowired
    public TableReservationService(TableReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TableReservation> findAll() {
        return repository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TableReservation deleteById(Long id) {
        TableReservation deletingTableReservation = findById(id);
        try {
            this.repository.deleteById(id);
            return deletingTableReservation;
        } catch (Exception e) {
            throw new TableReservationNotDeletedException(
                    "Table reservation error deleting by id = " + id + "_\n" + e.getMessage(), e);
        }
    }

    @Override
    public TableReservation findById(Long id) {
        Optional<TableReservation> reservation = repository.getTableReservationById(id);
        return reservation.orElseThrow(() -> new TableReservationNotFoundException("Table reservation not found for id : " + id));
    }

    @Override
    public List<TableReservation> getTableReservationByTable(ReserveTable table) {
        Optional<List<TableReservation>> reservation = repository.getTableReservationByTable(table);
        return reservation.orElseThrow(() -> new TableReservationNotFoundException("This table reservation does not exist"));
    }

    @Override
    @Transactional
    public TableReservation save(TableReservation tableReservation) throws TableReservationNotCreatedException {
        enrichTableReservation(tableReservation);
        try {
            return this.repository.save(tableReservation);
        } catch (Exception e) {
            throw new TableReservationNotCreatedException(
                    "Error create table reservation: " + tableReservation + "_\n" + e.getMessage(), e);
        }
    }

    private void enrichTableReservation(TableReservation tableReservation) {
        tableReservation.setCreate_at(LocalDateTime.now());
        tableReservation.setUpdate_at(LocalDateTime.now());
    }
}
