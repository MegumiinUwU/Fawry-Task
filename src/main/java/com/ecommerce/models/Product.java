package com.ecommerce.models;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    
    public Product(String name, double price, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        
        this.name = name.trim();
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.price = price;
    }
    
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    
    public void reduceQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to reduce cannot be negative");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce quantity by more than available");
        }
        this.quantity -= amount;
    }
    
    public boolean isAvailable() {
        return quantity > 0;
    }
    
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }
    
    public abstract boolean isValidForPurchase();
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f (Qty: %d)", name, price, quantity);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return name.equals(product.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
} 