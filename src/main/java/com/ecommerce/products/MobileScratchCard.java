package com.ecommerce.products;

import com.ecommerce.models.NonExpirableProduct;

public class MobileScratchCard extends NonExpirableProduct {
    private String provider;
    private double denomination;
    
    public MobileScratchCard(String name, double price, int quantity, String provider, double denomination) {
        super(name, price, quantity);
        if (provider == null || provider.trim().isEmpty()) {
            throw new IllegalArgumentException("Provider cannot be null or empty");
        }
        if (denomination <= 0) {
            throw new IllegalArgumentException("Denomination must be positive");
        }
        this.provider = provider.trim();
        this.denomination = denomination;
    }
    
    public String getProvider() {
        return provider;
    }
    
    public void setProvider(String provider) {
        if (provider == null || provider.trim().isEmpty()) {
            throw new IllegalArgumentException("Provider cannot be null or empty");
        }
        this.provider = provider.trim();
    }
    
    public double getDenomination() {
        return denomination;
    }
    
    public void setDenomination(double denomination) {
        if (denomination <= 0) {
            throw new IllegalArgumentException("Denomination must be positive");
        }
        this.denomination = denomination;
    }
    
    @Override
    public String toString() {
        return String.format("Mobile Scratch Card: %s - $%.2f (Qty: %d, Provider: %s, Value: $%.2f)", 
                           name, price, quantity, provider, denomination);
    }
} 