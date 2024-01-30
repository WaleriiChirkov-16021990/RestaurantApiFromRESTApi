package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReserveTableService.class})
@ExtendWith(SpringExtension.class)
class ReserveTableServiceDiffblueTest {
    @Autowired
    private ReserveTableService reserveTableService;

    /**
     * Method under test:
     * {@link ReserveTableService#findByStateFromTable(StateFromTable)}
     */
    @Test
    void testFindByStateFromTable() {
        // Arrange
        // TODO: Populate arranged inputs
        StateFromTable state = null;

        // Act
        List<ReserveTable> actualFindByStateFromTableResult = this.reserveTableService.findByStateFromTable(state);

        // Assert
        // TODO: Add assertions on result
    }
}
