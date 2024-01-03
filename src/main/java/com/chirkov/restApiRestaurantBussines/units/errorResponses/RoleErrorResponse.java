package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RoleErrorResponse {
    private String entityObjectName;
    private String message;
    private long timestamp;

    public RoleErrorResponse(String message, long timestamp, String entityObjectName) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}
