package com.chirkov.restApiRestaurantBussines.dto;

import com.chirkov.restApiRestaurantBussines.models.RestaurantReviews;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
public class RestaurantReviewsDto {

    @NotNull
    @Pattern(regexp = "^\\d",message = "Id of person is numeric and  required")
    private int owner;

    @Pattern(regexp = "^\\d",message = "Rating for this restaurant is numeric")
    @Range(min = 1,max =5,message = "Rating for this restaurant with a between 1 and 5 rating")
    private int gradle;

    @NotNull
    private String comment;

    public RestaurantReviewsDto() {
    }

    public RestaurantReviews mapReview(PeopleService peopleService) {
        RestaurantReviews dto = new RestaurantReviews();
        dto.setOwner(peopleService.findOne(this.owner));
        dto.setGradle(this.gradle);
        dto.setComment(this.comment);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantReviewsDto that)) return false;
        return getOwner() == that.getOwner() && getGradle() == that.getGradle() && Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner(), getGradle(), getComment());
    }
}
