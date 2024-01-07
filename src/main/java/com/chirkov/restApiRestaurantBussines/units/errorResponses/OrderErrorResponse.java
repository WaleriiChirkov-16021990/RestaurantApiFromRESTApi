package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record OrderErrorResponse(String message, long timestamp, String entityObjectName) {
}
