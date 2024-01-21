package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.repositories.OrderElementRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {OrderElementNotFoundException.class})
// TODO Auto
public class OrderElementsService {
    private final OrderElementRepository repository;

    @Autowired
    public OrderElementsService(OrderElementRepository repository) {
        this.repository = repository;
    }

    public List<OrderElements> getAllOrderElements() {
        return repository.findAll();
    }

    public OrderElements getOrderElementsById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderElementNotFoundException("Not found orderElement by id = " + id));
    }

    public Optional<OrderElements> getOrderElementById(long id) {
        return repository.findOrderElementsById(id);
    }

    public List<OrderElements> getOrderElementsByOrder(Order order) {
        return repository.findByOrder(order)
                .orElseThrow(() -> new OrderElementNotFoundException("Not Found OrderElement for Order + " + order.getId()));
    }

    @Transactional
    public void save(OrderElements orderElements) {
        repository.save(orderElements);
    }
}
