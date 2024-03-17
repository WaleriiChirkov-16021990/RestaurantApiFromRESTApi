package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum DiscountEnum implements Serializable {
    ZERO(0), FIVE(5), TEN(10), FIFTEEN(15), TWENTY(20);

    private final int discountValue;
    DiscountEnum(int i) {
        discountValue = i;
    }
}

