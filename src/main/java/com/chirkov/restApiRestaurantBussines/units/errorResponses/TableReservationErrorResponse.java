package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

public record TableReservationErrorResponse(String message, long timestamp, String entityObjectName) {
}
