package com.chirkov.restApiRestaurantBussines.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.repositories.DiscountsRepository;
import com.chirkov.restApiRestaurantBussines.repositories.DishesRepository;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.repositories.RoleRepository;
import com.chirkov.restApiRestaurantBussines.services.DiscountService;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FoodReviewDto.class, PeopleService.class, DishesService.class, RoleService.class,
        DiscountService.class})
@ExtendWith(SpringExtension.class)
class FoodReviewDtoDiffblueTest {
    @MockBean
    private DiscountsRepository discountsRepository;

    @MockBean
    private DishesRepository dishesRepository;

    @Autowired
    private DishesService dishesService;

    @Autowired
    private FoodReviewDto foodReviewDto;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    @MockBean
    private RoleRepository roleRepository;

    /**
     * Method under test:
     * {@link FoodReviewDto#mappingReview(PeopleService, DishesService)}
     */
    @Test
    void testMappingReview() throws DishesNotFoundException, FoodReviewNotCreatedException, PersonNotFoundException {
        when(peopleRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(FoodReviewNotCreatedException.class, () -> foodReviewDto.mappingReview(peopleService, dishesService));
        verify(peopleRepository).findById(Mockito.<Long>any());
    }
}
