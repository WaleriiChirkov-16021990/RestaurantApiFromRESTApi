package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountErrorResponse {
    private String entityObjectName;
    private String message;
    private long timestamp;

    public DiscountErrorResponse(String message, long timestamp, String entityObjectName) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}
