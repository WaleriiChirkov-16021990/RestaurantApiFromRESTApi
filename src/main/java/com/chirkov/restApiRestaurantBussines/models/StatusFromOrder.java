package com.chirkov.restApiRestaurantBussines.models;


import java.io.Serializable;

public enum StatusFromOrder implements Serializable {
    OPEN, COOKING_HAS_STARTED, COOKING_IN_PROGRESS, SUCCESSFUL_COOKING, CLOSED_REFUSAL, CLOSED_SUCCESSFUL_ORDER
}
