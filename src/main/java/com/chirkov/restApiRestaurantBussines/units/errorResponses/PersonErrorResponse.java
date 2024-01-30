package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record PersonErrorResponse(String message, long timestamp, String entityObjectName) {
}
