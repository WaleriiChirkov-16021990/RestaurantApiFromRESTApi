package com.chirkov.restApiRestaurantBussines.units.errorResponses;

public record DishesErrorResponse(String message, long timestamp, String entityObjectName) {
}
