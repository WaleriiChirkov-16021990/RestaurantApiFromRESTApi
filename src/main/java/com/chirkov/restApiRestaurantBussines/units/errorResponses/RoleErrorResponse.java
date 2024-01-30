package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record RoleErrorResponse(String message, long timestamp, String entityObjectName) {
}
