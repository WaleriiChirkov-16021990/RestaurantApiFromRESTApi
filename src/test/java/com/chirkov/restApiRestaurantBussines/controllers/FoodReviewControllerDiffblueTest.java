package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.FoodReviewDto;
import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.FoodReviewsService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.validators.FoodReviewValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@ContextConfiguration(classes = {FoodReviewController.class})
@ExtendWith(SpringExtension.class)
class FoodReviewControllerDiffblueTest {
    @MockBean
    private DishesService dishesService;

    @Autowired
    private FoodReviewController foodReviewController;

    @MockBean
    private FoodReviewValidator foodReviewValidator;

    @MockBean
    private FoodReviewsService foodReviewsService;

    @MockBean
    private PeopleService peopleService;

    /**
     * Method under test: {@link FoodReviewController#deleteFoodReviewById(long)}
     */
    @Test
    void testDeleteFoodReviewById() throws Exception {
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

        Person author = new Person();
        author.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setCreatedReserveRecords(new ArrayList<>());
        author.setDiscount(discount);
        author.setEmail("jane.doe@example.org");
        author.setId(1L);
        author.setLastName("Doe");
        author.setName("Name");
        author.setOrderList(new ArrayList<>());
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setReservationList(new ArrayList<>());
        author.setRestaurantReviews(new ArrayList<>());
        author.setRole(role);
        author.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setUpdatedReserveRecords(new ArrayList<>());
        author.setUpdatedWho("2020-03-01");
        author.setUsername("janedoe");
        author.setYearOfBirth(1);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setFats(1);
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        FoodReview foodReview = new FoodReview();
        foodReview.setAuthor(author);
        foodReview.setComment("Comment");
        foodReview.setDateCreate(LocalDate.of(1970, 1, 1).atStartOfDay());
        foodReview.setDishes(dishes);
        foodReview.setGrade(1);
        foodReview.setIdFoodReview(1L);
        when(foodReviewsService.deleteById(anyLong())).thenReturn(foodReview);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/food-reviews/{id}", 1L);
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idFoodReview\":1,\"author\":{\"id\":1,\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":"
                                        + "\"6625550144\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"role\":{\"id\":1"
                                        + ",\"name\":\"Name\",\"roleValue\":\"ROLE_ADMIN\",\"personList\":[]},\"discount\":{\"id\":1,\"name\":\"Name\",\"sale\":\"ZERO"
                                        + "\",\"personList\":[]},\"restaurantReviews\":[],\"reservationList\":[],\"createdReserveRecords\":[],\"orderList"
                                        + "\":[],\"updatedReserveRecords\":[],\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0],\"updatedWho\":"
                                        + "\"2020-03-01\"},\"dishes\":{\"id\":1,\"name\":\"Name\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,"
                                        + "\"fats\":1,\"carbohydrates\":1,\"imageURL\":\"https://example.org/example\"},\"dateCreate\":[1970,1,1,0,0],\"grade"
                                        + "\":1,\"comment\":\"Comment\"}"));
    }

    /**
     * Method under test: {@link FoodReviewController#deleteFoodReviewById(long)}
     */
    @Test
    void testDeleteFoodReviewById2() throws Exception {
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

        Person author = new Person();
        author.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setCreatedReserveRecords(new ArrayList<>());
        author.setDiscount(discount);
        author.setEmail("jane.doe@example.org");
        author.setId(1L);
        author.setLastName("Doe");
        author.setName("Name");
        author.setOrderList(new ArrayList<>());
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setReservationList(new ArrayList<>());
        author.setRestaurantReviews(new ArrayList<>());
        author.setRole(role);
        author.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setUpdatedReserveRecords(new ArrayList<>());
        author.setUpdatedWho("2020-03-01");
        author.setUsername("janedoe");
        author.setYearOfBirth(1);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        FoodReview foodReview = new FoodReview();
        foodReview.setAuthor(author);
        foodReview.setComment("Comment");
        foodReview.setDateCreate(LocalDate.of(1970, 1, 1).atStartOfDay());
        foodReview.setDishes(dishes);
        foodReview.setGrade(1);
        foodReview.setIdFoodReview(1L);
        when(foodReviewsService.deleteById(anyLong())).thenReturn(foodReview);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/food-reviews/{id}", 1L);
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idFoodReview\":1,\"dishes\":{\"id\":1,\"name\":\"Name\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1"
                                        + ",\"fats\":1,\"carbohydrates\":1,\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositio"
                                        + "nsOfDishesList\":[]},\"dateCreate\":[1970,1,1,0,0],\"grade\":1,\"comment\":\"Comment\"}"));
    }

    /**
     * Method under test: {@link FoodReviewController#getFoodReviewById(long)}
     */
    @Test
    void testGetFoodReviewById() throws Exception {
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

        Person author = new Person();
        author.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setCreatedReserveRecords(new ArrayList<>());
        author.setDiscount(discount);
        author.setEmail("jane.doe@example.org");
        author.setId(1L);
        author.setLastName("Doe");
        author.setName("Name");
        author.setOrderList(new ArrayList<>());
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setReservationList(new ArrayList<>());
        author.setRestaurantReviews(new ArrayList<>());
        author.setRole(role);
        author.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setUpdatedReserveRecords(new ArrayList<>());
        author.setUpdatedWho("2020-03-01");
        author.setUsername("janedoe");
        author.setYearOfBirth(1);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setFats(1);
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        FoodReview foodReview = new FoodReview();
        foodReview.setAuthor(author);
        foodReview.setComment("Comment");
        foodReview.setDateCreate(LocalDate.of(1970, 1, 1).atStartOfDay());
        foodReview.setDishes(dishes);
        foodReview.setGrade(1);
        foodReview.setIdFoodReview(1L);
        when(foodReviewsService.deleteById(anyLong())).thenReturn(foodReview);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food-reviews/{id}", 1L);
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idFoodReview\":1,\"author\":{\"id\":1,\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":"
                                        + "\"6625550144\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"role\":{\"id\":1"
                                        + ",\"name\":\"Name\",\"roleValue\":\"ROLE_ADMIN\",\"personList\":[]},\"discount\":{\"id\":1,\"name\":\"Name\",\"sale\":\"ZERO"
                                        + "\",\"personList\":[]},\"restaurantReviews\":[],\"reservationList\":[],\"createdReserveRecords\":[],\"orderList"
                                        + "\":[],\"updatedReserveRecords\":[],\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0],\"updatedWho\":"
                                        + "\"2020-03-01\"},\"dishes\":{\"id\":1,\"name\":\"Name\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,"
                                        + "\"fats\":1,\"carbohydrates\":1,\"imageURL\":\"https://example.org/example\"},\"dateCreate\":[1970,1,1,0,0],\"grade"
                                        + "\":1,\"comment\":\"Comment\"}"));
    }

    /**
     * Method under test: {@link FoodReviewController#getFoodReviewById(long)}
     */
    @Test
    void testGetFoodReviewById2() throws Exception {
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

        Person author = new Person();
        author.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setCreatedReserveRecords(new ArrayList<>());
        author.setDiscount(discount);
        author.setEmail("jane.doe@example.org");
        author.setId(1L);
        author.setLastName("Doe");
        author.setName("Name");
        author.setOrderList(new ArrayList<>());
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setReservationList(new ArrayList<>());
        author.setRestaurantReviews(new ArrayList<>());
        author.setRole(role);
        author.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setUpdatedReserveRecords(new ArrayList<>());
        author.setUpdatedWho("2020-03-01");
        author.setUsername("janedoe");
        author.setYearOfBirth(1);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        FoodReview foodReview = new FoodReview();
        foodReview.setAuthor(author);
        foodReview.setComment("Comment");
        foodReview.setDateCreate(LocalDate.of(1970, 1, 1).atStartOfDay());
        foodReview.setDishes(dishes);
        foodReview.setGrade(1);
        foodReview.setIdFoodReview(1L);
        when(foodReviewsService.findById(anyLong())).thenReturn(foodReview);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food-reviews/{id}", 1L);
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idFoodReview\":1,\"dishes\":{\"id\":1,\"name\":\"Name\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1"
                                        + ",\"fats\":1,\"carbohydrates\":1,\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositio"
                                        + "nsOfDishesList\":[]},\"dateCreate\":[1970,1,1,0,0],\"grade\":1,\"comment\":\"Comment\"}"));
    }

    /**
     * Method under test: {@link FoodReviewController#getFoodReviews()}
     */
    @Test
    void testGetFoodReviews() throws Exception {
        when(foodReviewsService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food-reviews/all");
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FoodReviewController#getFoodReviews()}
     */
    @Test
    void testGetFoodReviews2() throws Exception {
        when(foodReviewsService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food-reviews");
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link FoodReviewController#saveFoodReview(FoodReviewDto, BindingResult)}
     */
    @Test
    void testSaveFoodReview() throws Exception {
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

        Person author = new Person();
        author.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setCreatedReserveRecords(new ArrayList<>());
        author.setDiscount(discount);
        author.setEmail("jane.doe@example.org");
        author.setId(1L);
        author.setLastName("Doe");
        author.setName("Name");
        author.setOrderList(new ArrayList<>());
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setReservationList(new ArrayList<>());
        author.setRestaurantReviews(new ArrayList<>());
        author.setRole(role);
        author.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        author.setUpdatedReserveRecords(new ArrayList<>());
        author.setUpdatedWho("2020-03-01");
        author.setUsername("janedoe");
        author.setYearOfBirth(1);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setFats(1);
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        FoodReview foodReview = new FoodReview();
        foodReview.setAuthor(author);
        foodReview.setComment("Comment");
        foodReview.setDateCreate(LocalDate.of(1970, 1, 1).atStartOfDay());
        foodReview.setDishes(dishes);
        foodReview.setGrade(1);
        foodReview.setIdFoodReview(1L);
        when(foodReviewsService.save(Mockito.<FoodReview>any())).thenReturn(foodReview);

        Discount discount2 = new Discount();
        discount2.setId(1L);
        discount2.setName("Name");
        discount2.setPersonList(new ArrayList<>());
        discount2.setSale(DiscountEnum.ZERO);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        role2.setPersonList(new ArrayList<>());
        role2.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount2);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("Name");
        person.setOrderList(new ArrayList<>());
        person.setPassword("iloveyou");
        person.setPhoneNumber("6625550144");
        person.setReservationList(new ArrayList<>());
        person.setRestaurantReviews(new ArrayList<>());
        person.setRole(role2);
        person.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setUpdatedReserveRecords(new ArrayList<>());
        person.setUpdatedWho("2020-03-01");
        person.setUsername("janedoe");
        person.setYearOfBirth(1);
        when(peopleService.findById(anyLong())).thenReturn(person);

        Dishes dishes2 = new Dishes();
        dishes2.setCalories(1);
        dishes2.setCarbohydrates(1);
        dishes2.setFats(1);
        dishes2.setId(1L);
        dishes2.setImageURL("https://example.org/example");
        dishes2.setName("Name");
        dishes2.setOrderElementsIntegerMap(new ArrayList<>());
        dishes2.setPrice(10.0d);
        dishes2.setProteins(1);
        dishes2.setWeight(10.0d);
        when(dishesService.findById(anyLong())).thenReturn(dishes2);
        doNothing().when(foodReviewValidator).validate(Mockito.<Object>any(), Mockito.<Errors>any());

        FoodReviewDto foodReviewDto = new FoodReviewDto();
        foodReviewDto.setAuthor(1L);
        foodReviewDto.setComment("Comment");
        foodReviewDto.setDishes(1L);
        foodReviewDto.setGrade(1);
        String content = (new ObjectMapper()).writeValueAsString(foodReviewDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/food-reviews/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idFoodReview\":0,\"author\":{\"id\":1,\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":"
                                        + "\"6625550144\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"password\":\"iloveyou\",\"role\":{\"id\":1"
                                        + ",\"name\":\"Name\",\"roleValue\":\"ROLE_ADMIN\",\"personList\":[]},\"discount\":{\"id\":1,\"name\":\"Name\",\"sale\":\"ZERO"
                                        + "\",\"personList\":[]},\"restaurantReviews\":[],\"reservationList\":[],\"createdReserveRecords\":[],\"orderList"
                                        + "\":[],\"updatedReserveRecords\":[],\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0],\"updatedWho\":"
                                        + "\"2020-03-01\"},\"dishes\":{\"id\":1,\"name\":\"Name\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,"
                                        + "\"fats\":1,\"carbohydrates\":1,\"imageURL\":\"https://example.org/example\"},\"dateCreate\":null,\"grade\":1,"
                                        + "\"comment\":\"Comment\"}"));
    }

    /**
     * Method under test:
     * {@link FoodReviewController#saveFoodReview(FoodReviewDto, BindingResult)}
     */
    @Test
    void testSaveFoodReview2() throws Exception {
        when(foodReviewsService.findAll()).thenReturn(new ArrayList<>());

        FoodReviewDto foodReviewDto = new FoodReviewDto();
        foodReviewDto.setAuthor(1L);
        foodReviewDto.setComment("Comment");
        foodReviewDto.setDishes(1L);
        foodReviewDto.setGrade(1);
        String content = (new ObjectMapper()).writeValueAsString(foodReviewDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/food-reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(foodReviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
