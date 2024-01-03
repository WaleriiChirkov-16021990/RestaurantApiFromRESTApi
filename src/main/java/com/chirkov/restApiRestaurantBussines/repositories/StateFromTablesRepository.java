package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateFromTablesRepository extends JpaRepository<StateFromTable,Integer> {
    Optional<StateFromTable> findStateFromTablesByName(String tableName);
    Optional<StateFromTable> findStateFromTablesByValue(StateFromTableEnum value);
}
