package com.chirkov.restApiRestaurantBussines.units.errorResponses;

public class RestaurantReviewsErrorResponse {
    private String entityObjectName;
    private String message;
    private long timestamp;

    public RestaurantReviewsErrorResponse(String entityObjectName, String message, long timestamp) {
        this.entityObjectName = entityObjectName;
        this.message = message;
        this.timestamp = timestamp;
    }
}