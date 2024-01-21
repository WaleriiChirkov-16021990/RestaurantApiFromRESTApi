package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderElementRepository extends JpaRepository<OrderElements,Long> {
    Optional<List<OrderElements>> findByOrder(Order order);
    Optional<OrderElements> findOrderElementsById(long id);

}
