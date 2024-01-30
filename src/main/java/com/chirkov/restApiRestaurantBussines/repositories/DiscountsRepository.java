package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.util.Optional;
@Repository
public interface DiscountsRepository extends JpaRepository<Discount,Long> {
    Optional<Discount> findDiscountById(Long id);
    Optional<Discount> findDiscountByName(String name);
    Optional<Discount> findDiscountBySale(@NotNull DiscountEnum value);

}
