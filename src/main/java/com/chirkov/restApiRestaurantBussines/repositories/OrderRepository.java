package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<List<Order>> findByOwner(Person owner);
    Optional<List<Order>> findByPrice(double price);
    Optional<List<Order>> findByStatusFromOrder(StatusFromOrder status);
}
