package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

//import javax.validation.constraints.*;

@Getter
@Setter
public class RestaurantReviewsDto {

    @NotNull(message = "RestaurntReviewsDto/_Owner = null is not available for this application.")
    @Pattern(regexp = "^\\d", message = "Id of person is numeric and  required")
    @Min(value = 1, message = "RestaurantReviewsDto/_Owner must be between 1 and Long.MAX_VALUE.")
    @Max(value = Long.MAX_VALUE, message = "RestaurantReviewsDto/_Owner must be between 1 and Long.MAX_VALUE.")
    private Long owner;

    @Pattern(regexp = "^\\d", message = "RestaurntReviewsDto/_Gradle. Rating for this restaurant is numeric")
    @Range(min = 1, max = 5, message = "RestaurntReviewsDto/_Gradle. Rating for this restaurant with a between 1 and 5 rating")
    private int gradle;

    @NotNull(message = "RestaurntReviewsDto/_Comment = null is not available for this application.")
    @Size(max = 550, message = "RestaurntReviewsDto/_Comment must be between 1 and 550 symbol")
    private String comment;


    public RestaurantReviews mapReview(PeopleServiceByRepository<Person> peopleService) {
        RestaurantReviews dto = new RestaurantReviews();
        dto.setOwner(peopleService.findById(this.owner));
        dto.setGradle(this.gradle);
        dto.setComment(this.comment);
        return dto;
    }
}
