package com.chirkov.restApiRestaurantBussines.units.errorResponses;

public record FoodReviewErrorResponse(String description, long timestamp, String entityObjectName) {
}
