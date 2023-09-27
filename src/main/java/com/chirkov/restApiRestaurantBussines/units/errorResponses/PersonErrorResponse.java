package com.chirkov.restApiRestaurantBussines.units.errorResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonErrorResponse {
    private String entityObjectName;
    private String message;
    private long timestamp;

    public PersonErrorResponse(String message, long timestamp, String entityObjectName) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}
