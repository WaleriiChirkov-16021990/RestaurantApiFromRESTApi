package com.chirkov.restApiRestaurantBussines.units.errorResponses;

public record OrderElementErrorResponse(String message, long timestamp, String entityObjectName) {
}
