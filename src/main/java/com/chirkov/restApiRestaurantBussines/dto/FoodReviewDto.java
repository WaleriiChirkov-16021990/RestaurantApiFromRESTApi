package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodReviewDto {
    @NotNull(message = "FoodReviewDto/author must not be null")
    @NotEmpty(message = "FoodReviewDto/author must not be empty")
    @Min(value = 0, message = "FoodReviewDto/author must no less 0")
    @Max(value = Long.MAX_VALUE, message = "FoodReviewDto/author must no greater than Long.MAX value")
    private Long author;

    @NotNull(message = "FoodReviewDto/dishes must not be null")
    @NotEmpty(message = "FoodReviewDto/dishes must not be empty")
    @Min(value = 0, message = "FoodReviewDto/dishes must no less 0")
    @Max(value = Long.MAX_VALUE, message = "FoodReviewDto/dishes must no greater than Long.MAX value")
    private Long dishes;

    @NotNull(message = "FoodReviewDto/grade must not be null")
    @NotEmpty(message = "FoodReviewDto/grade must not be empty")
    @Min(value = 0, message = "FoodReviewDto/grade must no less 0")
    @Max(value = Integer.MAX_VALUE, message = "FoodReviewDto/grade must no greater than Integer.MAX value")
    private int grade;

    @NotNull(message = "FoodReviewDto/comment must not be null")
    @NotEmpty(message = "FoodReviewDto/comment must not be empty")
    @Size(max = 600, message = "FoodReviewDto/comment must not exceed 600.")
    private String comment;

    public FoodReview mappingReview(PeopleServiceByRepository<Person> peopleService, DishesServiceByRepository<Dishes> dishesService)
            throws PersonNotFoundException, DishesNotFoundException, FoodReviewNotCreatedException {
        FoodReview foodReview = new FoodReview();
        try {
            Person person = peopleService.findById(author);
            if (person == null) {
                throw new PersonNotFoundException("Person by id: {" + author + "}, not found");
            }
            foodReview.setAuthor(person);

            Dishes findDishes = dishesService.findById(dishes);
            if (findDishes == null) {
                throw new DishesNotFoundException("Dishes by id: {" + dishes + "}, not found.");
            }
            foodReview.setDishes(findDishes);
            foodReview.setGrade(grade);
            foodReview.setComment(comment);
            return foodReview;
        } catch (IllegalArgumentException e) {
            throw new FoodReviewNotCreatedException("FoodReview not created", e);
        }
    }
}
