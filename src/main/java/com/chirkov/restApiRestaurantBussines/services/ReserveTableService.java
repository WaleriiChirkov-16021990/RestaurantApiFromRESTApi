package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.repositories.ReserveTableRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.ReserveTableServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {ReserveTableNotCreatedException.class, ReserveTableNotDeletedException.class})
public class ReserveTableService implements ReserveTableServiceByRepository<ReserveTable> {
    private final ReserveTableRepository repository;

    @Autowired
    public ReserveTableService(ReserveTableRepository repository) {
        this.repository = repository;
    }

    public List<ReserveTable> findAll() {
        return repository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ReserveTable deleteById(Long id) throws ReserveTableNotDeletedException {
        ReserveTable reserveTable = findById(id);
        try {
            this.repository.deleteById(id);
            return reserveTable;
        } catch (Exception e) {
            throw new ReserveTableNotDeletedException("Error deleting reserve table " + id, e);
        }
    }

    /**
     * @param state
     * @return
     */
    @Override
    public List<ReserveTable> findByStateFromTable(StateFromTable state) {
        return repository.findReserveTableByStateFromTable(state).orElseThrow(() ->
                new ReserveTableNotFoundException(""));
    }

    public ReserveTable findById(Long id) {
        Optional<ReserveTable> reserveTable = repository.findById(id);
        return reserveTable.orElseThrow(() -> new ReserveTableNotFoundException("ReserveTable by Id= " + id + ", not found"));
    }

    @Transactional
    public ReserveTable save(ReserveTable reserveTable) {
        return this.repository.save(reserveTable);
    }

    public Optional<ReserveTable> getReserveTableByAccommodationNumber(int accommodationNumber) {
        return this.repository.findReserveTableByAccommodationNumber(accommodationNumber);
    }
}
