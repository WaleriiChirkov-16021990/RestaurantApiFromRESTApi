package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum StateFromTableEnum implements Serializable {
    READY_TO_BOARD(0),
    RESERVE(1),
    WAITING_TO_BOARD(2),
    GUESTS_AT_THE_TABLE(3),
    ASKED_FOR_THE_BILL(4),
    PAID_THE_BILL(5);

    private final int code;

    StateFromTableEnum(int code) {
        this.code = code;
    }
}
