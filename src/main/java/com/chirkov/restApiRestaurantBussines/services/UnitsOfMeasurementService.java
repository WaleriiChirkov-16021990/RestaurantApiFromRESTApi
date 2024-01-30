package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.repositories.UnitsOfMeasurementRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.UnitsOfMeasurementServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementEmptyListException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.UnitsOfMeasurementNotDeletedException;
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
        rollbackFor = {UnitsOfMeasurementNotCreatedException.class, UnitsOfMeasurementNotDeletedException.class}
)
public class UnitsOfMeasurementService implements UnitsOfMeasurementServiceByRepository<UnitsOfMeasurement> {
    private final UnitsOfMeasurementRepository repositories;

    @Autowired
    public UnitsOfMeasurementService(UnitsOfMeasurementRepository repositories) {
        this.repositories = repositories;
    }

    public List<UnitsOfMeasurement> findAll() throws UnitsOfMeasurementEmptyListException {
        List<UnitsOfMeasurement> unitsOfMeasure;
        try {
            unitsOfMeasure = repositories.findAll();
        } catch (Exception e) {
            throw new UnitsOfMeasurementEmptyListException(e.getMessage(), e);
        }
        if (unitsOfMeasure.isEmpty()) {
            throw new UnitsOfMeasurementEmptyListException("UnitsOfMeasurement could not be found. Empty list.");
        }
        return unitsOfMeasure;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UnitsOfMeasurement deleteById(Long id) throws UnitsOfMeasurementNotDeletedException {
        UnitsOfMeasurement unitsOfMeasurement = findById(id);
        try {
            this.repositories.deleteById(id);
            return unitsOfMeasurement;
        } catch (Exception e) {
            throw new UnitsOfMeasurementNotDeletedException(
                    "Error deleting units of measurement id =" + id + "_\n" + e.getMessage(), e);
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public UnitsOfMeasurement findByName(String name) {
        return this.repositories.getByName(name).orElseThrow(() ->
                new UnitsOfMeasurementNotFoundException("UnitsOfMeasurement by name = " + name + ", not found."));
    }

    public Long getIdByName(String name) {
        return repositories.getIdByName(name)
                .orElseThrow(() ->
                        new UnitsOfMeasurementNotFoundException(
                                "UnitsOfMeasurement could not be found for name '" + name + "'."));
    }

    public EnumUnitsOfMeasurement findEnumUnitsOfMeasurementByName(String name) {
        return repositories.getEnumByName(name).orElseThrow(() ->
                new UnitsOfMeasurementNotFoundException(
                        "Enumerated UnitsOfMeasurement could not be found for name '" + name + "'."));
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
