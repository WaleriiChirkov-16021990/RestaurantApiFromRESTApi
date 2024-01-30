package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.ReserveTableDto;
import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.validators.ReserveTableValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {ReserveTableController.class})
@ExtendWith(SpringExtension.class)
class ReserveTableControllerDiffblueTest {
    @Autowired
    private ReserveTableController reserveTableController;

    @MockBean
    private ReserveTableService reserveTableService;

    @MockBean
    private ReserveTableValidator reserveTableValidator;

    @MockBean
    private StateFromTablesService stateFromTablesService;

    /**
     * Method under test:
     * {@link ReserveTableController#addReserveTable(ReserveTableDto, BindingResult)}
     */
    @Test
    void testAddReserveTable() throws Exception {
        when(reserveTableService.findAll()).thenReturn(new ArrayList<>());

        ReserveTableDto reserveTableDto = new ReserveTableDto();
        reserveTableDto.setAccommodationNumber(10);
        reserveTableDto.setNumberOfSeats(10);
        reserveTableDto.setStateFromTable(1L);
        String content = (new ObjectMapper()).writeValueAsString(reserveTableDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reserve_a_table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(reserveTableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReserveTableController#findAllTables()}
     */
    @Test
    void testFindAllTables() throws Exception {
        when(reserveTableService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reserve_a_table");
        MockMvcBuilders.standaloneSetup(reserveTableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReserveTableController#findById(Long)}
     */
    @Test
    void testFindById() throws Exception {
        StateFromTable stateFromTable = new StateFromTable();
        stateFromTable.setId(1L);
        stateFromTable.setName("Name");
        stateFromTable.setValue(StateFromTableEnum.READY_TO_BOARD);

        ReserveTable reserveTable = new ReserveTable();
        reserveTable.setAccommodationNumber(10);
        reserveTable.setId(1L);
        reserveTable.setNumberOfSeats(10);
        reserveTable.setReservationList(new ArrayList<>());
        reserveTable.setStateFromTable(stateFromTable);
        when(reserveTableService.findById(Mockito.<Long>any())).thenReturn(reserveTable);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reserve_a_table/{id}", 1L);
        MockMvcBuilders.standaloneSetup(reserveTableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"accommodationNumber\":10,\"numberOfSeats\":10,\"reservationList\":[]}"));
    }
}
