package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.repositories.OrderRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED, rollbackFor = {OrderNotFoundException.class})
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Не найден Order с id = " + id));
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }
}
