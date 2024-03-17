package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum EnumUnitsOfMeasurement implements Serializable {
    GRAMMES(1),
    KILOGRAMMES(1000),
    OUNCE(29),
    MILLILITERS(1),
    LITERS(1000),
    TABLESPOON(15),
    TEASPOON(6),
    PINCH(3);

    private final int value;

    EnumUnitsOfMeasurement(int value) {
        this.value = value;
    }
}
