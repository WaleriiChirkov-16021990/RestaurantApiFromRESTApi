package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface DiscountsRepository extends JpaRepository<Discount,Integer> {
    Optional<Discount> findDiscountById(int id);
    Optional<Discount> findDiscountByName(String name);
    Optional<Discount> findDiscountBySale(@NotNull DiscountEnum value);

}
