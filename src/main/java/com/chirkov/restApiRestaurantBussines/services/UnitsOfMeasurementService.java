package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.repositories.UnitsOfMeasurementRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementEmptyListException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(
        readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = UnitsOfMeasurementNotFoundException.class
)
public class UnitsOfMeasurementService {
    private final UnitsOfMeasurementRepository repositories;

    @Autowired
    public UnitsOfMeasurementService(UnitsOfMeasurementRepository repositories) {
        this.repositories = repositories;
    }

    public List<UnitsOfMeasurement> findAll() {
        List<UnitsOfMeasurement> unitsOfMeasure;
        try {
            unitsOfMeasure = repositories.findAll();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        if (unitsOfMeasure.isEmpty()) {
            throw new UnitsOfMeasurementEmptyListException("UnitsOfMeasurement could not be found. Empty list.");
        }
        return unitsOfMeasure;
    }

    public Long getIdByName(String name) {
        return repositories.getIdByName(name)
                .orElseThrow(() ->
                        new UnitsOfMeasurementNotFoundException("UnitsOfMeasurement could not be found for name '" + name + "'."));
    }

    public EnumUnitsOfMeasurement findEnumUnitsOfMeasurementByName(String name) {
        return repositories.getEnumByName(name).orElseThrow(() ->
                new UnitsOfMeasurementNotFoundException("Enumerated UnitsOfMeasurement could not be found for name '" + name + "'."));
    }

    public UnitsOfMeasurement getUnitsOfMeasurementByEnumerated(EnumUnitsOfMeasurement enumUnitsOfMeasurement) {
        return repositories.getByUnitOfMeasurement(enumUnitsOfMeasurement)
                .orElseThrow(() ->
                        new UnitsOfMeasurementNotFoundException("UnitsOfMeasurement could not be found for enum."));
    }

    public Optional<UnitsOfMeasurement> getUnitsOfMeasurementByEnum(EnumUnitsOfMeasurement enumUnitsOfMeasurement) {
        return repositories.getByUnitOfMeasurement(enumUnitsOfMeasurement);
    }

    public UnitsOfMeasurement findById(Long id) {
        return repositories.findById(id).orElseThrow(() ->
                new UnitsOfMeasurementNotFoundException("UnitsOfMeasurement by id = " + id + ", not found."));
    }

    @Transactional
    public UnitsOfMeasurement save(UnitsOfMeasurement unitOfMeasure) throws UnitsOfMeasurementNotCreatedException {
        if (unitOfMeasure == null) {
            throw new UnitsOfMeasurementNotCreatedException("UnitsOfMeasurement cannot be null");
        }
        try {
            return repositories.save(unitOfMeasure);
        } catch (Exception e) {
            throw new UnitsOfMeasurementNotCreatedException(e.getMessage(), e);
        }
    }
}
