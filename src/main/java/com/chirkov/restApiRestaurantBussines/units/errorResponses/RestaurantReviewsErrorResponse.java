package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record RestaurantReviewsErrorResponse(String entityObjectName, String message, long timestamp) {
}
