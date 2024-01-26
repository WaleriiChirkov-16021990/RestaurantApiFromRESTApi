package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.FoodReview;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.FoodReviewNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodReviewDto {
    private Long author;
    private Long dishes;
    private int grade;
    private String comment;

    public FoodReview mappingReview(PeopleService peopleService, DishesService dishesService)
            throws PersonNotFoundException, DishesNotFoundException, FoodReviewNotCreatedException {
        FoodReview foodReview = new FoodReview();
        try {
            Person person = peopleService.findOne(author);
            if (person == null) {
                throw new PersonNotFoundException("Person by id: {" + author + "}, not found");
            }
            foodReview.setAuthor(person);

            Dishes findDishes = dishesService.getDishesById(dishes);
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
