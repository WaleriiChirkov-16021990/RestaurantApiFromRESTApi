package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponceStateFromTable {
    private final String entityObjectName;
    private final String message;
    private final long timestamp;

    public ErrorResponceStateFromTable(String message, long timestamp, String entityObjectName) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}
