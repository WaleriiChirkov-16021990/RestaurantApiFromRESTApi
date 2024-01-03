package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;

@Getter
public class TableReservationErrorResponse {
    private String entityObjectName;
    private String message;
    private long timestamp;

    public TableReservationErrorResponse(String message, long timestamp, String entityObjectName) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}
