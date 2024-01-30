package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record ReserveTableErrorResponse(String entityObjectName, String message, long timestamp) {
}
