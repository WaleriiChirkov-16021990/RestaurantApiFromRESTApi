package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.doNothing;

import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.validators.PersonDtoValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerDiffblueTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PeopleService peopleService;

    @MockBean
    private PersonDtoValidator personDtoValidator;

    /**
     * Method under test: {@link AuthController#getLogoutPage()}
     */
    @Test
    void testGetLogoutPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/test");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("auth/people"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/people"));
    }

    /**
     * Method under test: {@link AuthController#getLogoutPage()}
     */
    @Test
    void testGetLogoutPage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/test", "Uri Variables");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("auth/people"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/people"));
    }

    /**
     * Method under test: {@link AuthController#loginPage()}
     */
    @Test
    void testLoginPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/login");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("auth/login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/login"));
    }

    /**
     * Method under test: {@link AuthController#loginPage()}
     */
    @Test
    void testLoginPage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/login");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("auth/login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/login"));
    }

    /**
     * Method under test:
     * {@link AuthController#performRegistration(PersonDto, BindingResult)}
     */
    @Test
    void testPerformRegistration() throws Exception {
        doNothing().when(personDtoValidator).validate(Mockito.<Object>any(), Mockito.<Errors>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/registration");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.view().name("auth/registration"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/registration"));
    }

    /**
     * Method under test:
     * {@link AuthController#performRegistration(PersonDto, BindingResult)}
     */
    @Test
    void testPerformRegistration2() throws Exception {
        doNothing().when(personDtoValidator).validate(Mockito.<Object>any(), Mockito.<Errors>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/registration");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.view().name("auth/registration"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/registration"));
    }

    /**
     * Method under test: {@link AuthController#registrationPage(PersonDto)}
     */
    @Test
    void testRegistrationPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/registration");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.view().name("auth/registration"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/registration"));
    }

    /**
     * Method under test: {@link AuthController#registrationPage(PersonDto)}
     */
    @Test
    void testRegistrationPage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/registration");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.view().name("auth/registration"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("auth/registration"));
    }
}
