package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import com.chirkov.restApiRestaurantBussines.services.StateFromTablesService;
import com.chirkov.restApiRestaurantBussines.units.validators.StateFromTableValidator;
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

@ContextConfiguration(classes = {StateFromTableController.class})
@ExtendWith(SpringExtension.class)
class StateFromTableControllerDiffblueTest {
    @Autowired
    private StateFromTableController stateFromTableController;

    @MockBean
    private StateFromTableValidator stateFromTableValidator;

    @MockBean
    private StateFromTablesService stateFromTablesService;

    /**
     * Method under test:
     * {@link StateFromTableController#addState(StateFromTable, BindingResult)}
     */
    @Test
    void testAddState() throws Exception {
        when(stateFromTablesService.findAll()).thenReturn(new ArrayList<>());

        StateFromTable stateFromTable = new StateFromTable();
        stateFromTable.setId(1L);
        stateFromTable.setName("Name");
        stateFromTable.setValue(StateFromTableEnum.READY_TO_BOARD);
        String content = (new ObjectMapper()).writeValueAsString(stateFromTable);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/state_from_tables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(stateFromTableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link StateFromTableController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(stateFromTablesService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/state_from_tables");
        MockMvcBuilders.standaloneSetup(stateFromTableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link StateFromTableController#getStateById(Long)}
     */
    @Test
    void testGetStateById() throws Exception {
        StateFromTable stateFromTable = new StateFromTable();
        stateFromTable.setId(1L);
        stateFromTable.setName("Name");
        stateFromTable.setValue(StateFromTableEnum.READY_TO_BOARD);
        when(stateFromTablesService.findById(Mockito.<Long>any())).thenReturn(stateFromTable);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/state_from_tables/{id}", 1L);
        MockMvcBuilders.standaloneSetup(stateFromTableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"value\":\"READY_TO_BOARD\"}"));
    }
}
