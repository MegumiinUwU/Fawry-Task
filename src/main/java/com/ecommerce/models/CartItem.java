package com.ecommerce.models;

public class CartItem {
    private Product product;
    private int quantity;
    
    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    public boolean isValidForPurchase() {
        return product.isValidForPurchase() && product.isAvailable(quantity);
    }
    
    @Override
    public String toString() {
        return String.format("%s x %d = $%.2f", product.getName(), quantity, getTotalPrice());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartItem cartItem = (CartItem) obj;
        return product.equals(cartItem.product);
    }
    
    @Override
    public int hashCode() {
        return product.hashCode();
    }
} 