package com.chirkov.restApiRestaurantBussines.models;

import java.io.Serializable;

public enum StateFromTableEnum implements Serializable {
    READY_TO_BOARD, RESERVE, WAITING_TO_BOARD, GUESTS_AT_THE_TABLE, ASKED_FOR_THE_BILL, PAID_THE_BILL
}
