package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateFromTablesRepository extends JpaRepository<StateFromTable,Integer> {
    Optional<StateFromTable> findStateFromTablesByName(String tableName);
    Optional<StateFromTable> findStateFromTablesByValue(StateFromTableEnum value);
}
