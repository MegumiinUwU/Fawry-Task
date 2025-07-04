package com.ecommerce.services;

public class CheckoutResult {
    private boolean success;
    private String message;
    private CheckoutDetails details;
    
    public CheckoutResult(boolean success, String message, CheckoutDetails details) {
        this.success = success;
        this.message = message;
        this.details = details;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public CheckoutDetails getDetails() {
        return details;
    }
} 