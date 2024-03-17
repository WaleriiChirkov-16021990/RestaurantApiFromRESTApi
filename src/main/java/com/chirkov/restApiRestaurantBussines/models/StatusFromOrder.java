package com.chirkov.restApiRestaurantBussines.models;


import lombok.Getter;

import java.io.Serializable;

@Getter
public enum StatusFromOrder implements Serializable {
    OPEN(0),
    COOKING_HAS_STARTED(1),
    COOKING_IN_PROGRESS(2),
    SUCCESSFUL_COOKING(3),
    CLOSED_REFUSAL(4),
    CLOSED_SUCCESSFUL_ORDER(5);
    private final int id;

    StatusFromOrder(int id) {
        this.id = id;
    }
}
