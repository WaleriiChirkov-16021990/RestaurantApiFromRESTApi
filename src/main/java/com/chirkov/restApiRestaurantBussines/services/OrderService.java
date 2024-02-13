package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;
import com.chirkov.restApiRestaurantBussines.repositories.OrderRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.OrderServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {OrderNotCreatedException.class, OrderNotDeletedException.class})
public class OrderService implements OrderServiceByRepository<Order> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Order deleteById(Long id) {
        Order order = findById(id);
        try {
            orderRepository.deleteById(id);
            return order;
        } catch (Exception e) {
            throw new OrderNotDeletedException("Error deleting order " + id + "_\n" + e.getMessage(), e);
        }
    }

    public Optional<List<Order>> findByPrice(double price){
        return orderRepository.findByPrice(price);
    }


    public Optional<List<Order>> findByStatusFromOrder(StatusFromOrder status){
        return orderRepository.findByStatusFromOrder(status);
    }

    /**
     * @param owner
     * @return
     */
    @Override
    public List<Order> findByOwner(Person owner) {
        return orderRepository.findByOwner(owner).orElseThrow(() -> new OrderNotFoundException("Could not find"));

    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Не найден Order с id = " + id));
    }

    @Transactional
    public Order save(Order order) {
        try {
            enrichedOrder(order);
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderNotCreatedException("Error creating order " + order.getId() + "_\n" + e.getMessage(), e);
        }
    }

    private void enrichedOrder(Order order) {
        order.setDateCreate(LocalDateTime.now());
        order.setStatusFromOrder(StatusFromOrder.OPEN);
    }
}
