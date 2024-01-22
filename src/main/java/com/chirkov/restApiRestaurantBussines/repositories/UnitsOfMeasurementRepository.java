package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitsOfMeasurementRepository extends JpaRepository<UnitsOfMeasurement,Long> {
    Optional<Long> getIdByName(String name);
    Optional<EnumUnitsOfMeasurement> getEnumByName(String name);
    Optional<UnitsOfMeasurement> getByUnitOfMeasurement(EnumUnitsOfMeasurement unitOfMeasurement);
}
