package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.RestaurantReviewsDto;
import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.RestaurantReviewsService;
import com.chirkov.restApiRestaurantBussines.units.validators.RestaurantReviewsValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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

@ContextConfiguration(classes = {RestaurantReviewsController.class})
@ExtendWith(SpringExtension.class)
class RestaurantReviewsControllerDiffblueTest {
    @MockBean
    private PeopleService peopleService;

    @Autowired
    private RestaurantReviewsController restaurantReviewsController;

    @MockBean
    private RestaurantReviewsService restaurantReviewsService;

    @MockBean
    private RestaurantReviewsValidator restaurantReviewsValidator;

    /**
     * Method under test:
     * {@link RestaurantReviewsController#addRestaurantReview(RestaurantReviewsDto, BindingResult)}
     */
    @Test
    void testAddRestaurantReview() throws Exception {
        when(restaurantReviewsService.findAll()).thenReturn(new ArrayList<>());

        RestaurantReviewsDto restaurantReviewsDto = new RestaurantReviewsDto();
        restaurantReviewsDto.setComment("Comment");
        restaurantReviewsDto.setGradle(1);
        restaurantReviewsDto.setOwner(1L);
        String content = (new ObjectMapper()).writeValueAsString(restaurantReviewsDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restReviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(restaurantReviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link RestaurantReviewsController#getRestaurantReview(int)}
     */
    @Test
    void testGetRestaurantReview() throws Exception {
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Name");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        RestaurantReviews restaurantReviews = new RestaurantReviews();
        restaurantReviews.setComment("Comment");
        restaurantReviews.setCreateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        restaurantReviews.setGradle(1);
        restaurantReviews.setId(1L);
        restaurantReviews.setOwner(owner);
        restaurantReviews.setUpdateAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<RestaurantReviews> ofResult = Optional.of(restaurantReviews);
        when(restaurantReviewsService.findById(anyInt())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restReviews/{id}", 1);
        MockMvcBuilders.standaloneSetup(restaurantReviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"owner\":{\"id\":1,\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":\"6625550144\","
                                        + "\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"role\":{\"id\":1,\"name\":\"Name"
                                        + "\",\"roleValue\":\"ROLE_ADMIN\",\"personList\":[]},\"discount\":{\"id\":1,\"name\":\"Name\",\"sale\":\"ZERO\"},"
                                        + "\"restaurantReviews\":[],\"reservationList\":[],\"createdReserveRecords\":[],\"orderList\":[],\"updatedReserveRecords"
                                        + "\":[],\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0],\"updatedWho\":\"2020-03-01\"},\"gradle\":1,"
                                        + "\"comment\":\"Comment\",\"createAt\":[1970,1,1,0,0],\"updateAt\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test:
     * {@link RestaurantReviewsController#getRestaurantsReviews()}
     */
    @Test
    void testGetRestaurantsReviews() throws Exception {
        when(restaurantReviewsService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restReviews");
        MockMvcBuilders.standaloneSetup(restaurantReviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
