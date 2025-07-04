package com.ecommerce.products;

import com.ecommerce.interfaces.Shippable;
import com.ecommerce.models.ExpirableProduct;
import java.time.LocalDate;

public class Cheese extends ExpirableProduct implements Shippable {
    private double weight;
    
    public Cheese(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity, expirationDate);
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
        String status = isExpired() ? " (EXPIRED)" : "";
        return String.format("Cheese: %s - $%.2f (Qty: %d, Weight: %.2fkg, Exp: %s)%s", 
                           name, price, quantity, weight, expirationDate, status);
    }
} 