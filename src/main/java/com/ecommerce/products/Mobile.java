package com.ecommerce.products;

import com.ecommerce.interfaces.Shippable;
import com.ecommerce.models.NonExpirableProduct;

public class Mobile extends NonExpirableProduct implements Shippable {
    private double weight;
    
    public Mobile(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }
    
    @Override
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return String.format("Mobile: %s - $%.2f (Qty: %d, Weight: %.2fkg)", 
                           name, price, quantity, weight);
    }
} 