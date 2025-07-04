package com.ecommerce.models;

import java.time.LocalDate;

public abstract class ExpirableProduct extends Product {
    protected LocalDate expirationDate;
    
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expirationDate = expirationDate;
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
    
    @Override
    public boolean isValidForPurchase() {
        return isAvailable() && !isExpired();
    }
    
    @Override
    public String toString() {
        String status = isExpired() ? " (EXPIRED)" : "";
        return String.format("%s - $%.2f (Qty: %d, Exp: %s)%s", 
                           name, price, quantity, expirationDate, status);
    }
} 