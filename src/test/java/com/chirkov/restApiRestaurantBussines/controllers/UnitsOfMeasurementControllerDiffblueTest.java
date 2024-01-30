package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.UnitsOfMeasurementDto;
import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import com.chirkov.restApiRestaurantBussines.units.validators.UnitsOfMeasurementValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ContextConfiguration(classes = {UnitsOfMeasurementController.class})
@ExtendWith(SpringExtension.class)
class UnitsOfMeasurementControllerDiffblueTest {
    @Autowired
    private UnitsOfMeasurementController unitsOfMeasurementController;

    @MockBean
    private UnitsOfMeasurementService unitsOfMeasurementService;

    @MockBean
    private UnitsOfMeasurementValidator unitsOfMeasurementValidator;

    /**
     * Method under test:
     * {@link UnitsOfMeasurementController#addUnitOfMeasurement(UnitsOfMeasurementDto, BindingResult)}
     */
    @Test
    void testAddUnitOfMeasurement() throws Exception {
        when(unitsOfMeasurementService.findAll()).thenReturn(new ArrayList<>());

        UnitsOfMeasurementDto unitsOfMeasurementDto = new UnitsOfMeasurementDto();
        unitsOfMeasurementDto.setCommentary("Commentary");
        unitsOfMeasurementDto.setName("Name");
        unitsOfMeasurementDto.setUnitOfMeasurement("Unit Of Measurement");
        String content = (new ObjectMapper()).writeValueAsString(unitsOfMeasurementDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/unit-of-measurements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(unitsOfMeasurementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UnitsOfMeasurementController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(unitsOfMeasurementService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/unit-of-measurements");
        MockMvcBuilders.standaloneSetup(unitsOfMeasurementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UnitsOfMeasurementController#getById(long)}
     */
    @Test
    void testGetById() throws Exception {
        UnitsOfMeasurement unitsOfMeasurement = new UnitsOfMeasurement();
        unitsOfMeasurement.setCommentary("Commentary");
        unitsOfMeasurement.setCompositionsOfDishesList(new ArrayList<>());
        unitsOfMeasurement.setId(1L);
        unitsOfMeasurement.setName("Name");
        unitsOfMeasurement.setUnitOfMeasurement(EnumUnitsOfMeasurement.GRAMMES);
        when(unitsOfMeasurementService.findById(anyLong())).thenReturn(unitsOfMeasurement);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/unit-of-measurements/{id}", 1L);
        MockMvcBuilders.standaloneSetup(unitsOfMeasurementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"name\":\"Name\",\"unitOfMeasurement\":\"GRAMMES\",\"commentary\":\"Commentary\"}"));
    }
}
