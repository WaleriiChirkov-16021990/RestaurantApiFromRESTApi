package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record DiscountErrorResponse(String message, long timestamp, String entityObjectName) {
}
