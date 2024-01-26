package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.repositories.ReserveTableRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.ReserveTableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReserveTableService {
    private final ReserveTableRepository repository;

    @Autowired
    public ReserveTableService(ReserveTableRepository repository) {
        this.repository = repository;
    }

    public List<ReserveTable> findAll() {
        return repository.findAll();
    }

    public ReserveTable findReserveById(Long id) {
        Optional<ReserveTable> reserveTable = repository.findById(id);
        return reserveTable.orElseThrow(() -> new ReserveTableNotFoundException("ReserveTable by Id= "+ id + ", not found"));
    }

    @Transactional
    public void save(ReserveTable reserveTable) {
        this.repository.save(reserveTable);
    }

    public Optional<ReserveTable> getReserveTableByAccommodationNumber(int accommodationNumber) {
        return this.repository.findReserveTableByAccommodationNumber(accommodationNumber);
    }
}
