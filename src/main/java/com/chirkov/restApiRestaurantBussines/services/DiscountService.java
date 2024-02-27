package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.repositories.DiscountsRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DiscountServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DiscountNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(
//        readOnly = true,
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {DiscountNotCreatedException.class, DishesNotDeletedException.class})

public class DiscountService implements DiscountServiceByRepository<Discount> {
    private final DiscountsRepository discountRepository;

    @Autowired
    public DiscountService(DiscountsRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> findAll() {
        return this.discountRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Discount deleteById(Long id) {
        try {
            Discount findDiscount = findById(id);
            discountRepository.deleteById(id);
            return findDiscount;
        } catch (Exception e) {
            throw new DiscountNotDeletedException("Discount " + id + "  is not deleted! \n__" + e.getMessage(), e);
        }
    }

    public Discount findById(Long id) {
        Optional<Discount> result = this.discountRepository.findDiscountById(id);
        return result.orElseThrow(() -> new DiscountNotFoundException("Discount " + id + "not found."));
    }

    public Optional<Discount> findByNameOptional(String name) {
        return this.discountRepository.findDiscountByName(name);
    }

    public Discount findByName(String name) {
        return this.discountRepository.findDiscountByName(name).orElseThrow(() -> new DiscountNotFoundException("Discount not found by name: " + name));
    }

    public Optional<Discount> findByDiscountValue(DiscountEnum discount) {
        return this.discountRepository.findDiscountBySale(discount);
//        return Optional.ofNullable(this.discountRepository.findDiscountBySale(discount).orElseThrow(DiscountNotFoundException::new));
    }

    @Transactional
    public Discount save(Discount discount) {
        return this.discountRepository.save(discount);
    }

    @Transactional
    public List<Discount> saveAll(List<Discount> zero) {
        return this.discountRepository.saveAll(zero);
    }
}
