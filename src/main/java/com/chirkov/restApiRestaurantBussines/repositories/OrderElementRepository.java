package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderElementRepository extends JpaRepository<OrderElements,Long> {
}
