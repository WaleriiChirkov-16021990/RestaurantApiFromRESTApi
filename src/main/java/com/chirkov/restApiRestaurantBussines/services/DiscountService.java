package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.repositories.DiscountsRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DiscountService {
    private final DiscountsRepository discountRepository;

    @Autowired
    public DiscountService(DiscountsRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> findAll() {
        return this.discountRepository.findAll();
    }

    public Discount findById(Long id) {
        Optional<Discount> result = this.discountRepository.findDiscountById(id);
        return result.orElseThrow(DiscountNotFoundException::new);
    }

    public Optional<Discount> findByName(String name) {
        return this.discountRepository.findDiscountByName(name);
    }

    public Optional<Discount> findByDiscountValue(@NotNull DiscountEnum discount) {
        return this.discountRepository.findDiscountBySale(discount);
//        return Optional.ofNullable(this.discountRepository.findDiscountBySale(discount).orElseThrow(DiscountNotFoundException::new));
    }

    @Transactional
    public Discount save(Discount discount) {
        return this.discountRepository.save(discount);
    }

}
