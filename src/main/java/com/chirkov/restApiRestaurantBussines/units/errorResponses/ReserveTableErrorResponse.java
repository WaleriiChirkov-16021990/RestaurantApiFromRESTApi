package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
public class ReserveTableErrorResponse {
    private String entityObjectName;
    private String message;
    private long timestamp;

    public ReserveTableErrorResponse(String entityObjectName, String message, long timestamp) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}
