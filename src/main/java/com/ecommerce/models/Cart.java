package com.ecommerce.models;

import com.ecommerce.interfaces.Shippable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException(
                String.format("Requested quantity (%d) exceeds available stock (%d) for %s", 
                             quantity, product.getQuantity(), product.getName()));
        }
        
        Optional<CartItem> existingItem = items.stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();
        
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity;
            if (!product.isAvailable(newQuantity)) {
                throw new IllegalArgumentException(
                    String.format("Total quantity (%d) would exceed available stock (%d) for %s", 
                                 newQuantity, product.getQuantity(), product.getName()));
            }
            item.setQuantity(newQuantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }
    
    public boolean removeProduct(Product product) {
        return items.removeIf(item -> item.getProduct().equals(product));
    }
    
    public void updateProductQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeProduct(product);
            return;
        }
        
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException(
                String.format("Requested quantity (%d) exceeds available stock (%d) for %s", 
                             quantity, product.getQuantity(), product.getName()));
        }
        
        Optional<CartItem> existingItem = items.stream()
            .filter(item -> item.getProduct().equals(product))
            .findFirst();
        
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(quantity);
        } else {
            throw new IllegalArgumentException("Product not found in cart");
        }
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public int getTotalQuantity() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }
    
    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) item.getProduct());
                }
            }
        }
        return shippableItems;
    }
    
    public List<String> validateForCheckout() {
        List<String> errors = new ArrayList<>();
        
        if (isEmpty()) {
            errors.add("Cart is empty");
            return errors;
        }
        
        for (CartItem item : items) {
            Product product = item.getProduct();
            
            if (!product.isAvailable(item.getQuantity())) {
                errors.add(String.format("Product '%s' is out of stock or insufficient quantity available", 
                                       product.getName()));
            }
            
            if (product instanceof ExpirableProduct) {
                ExpirableProduct expirableProduct = (ExpirableProduct) product;
                if (expirableProduct.isExpired()) {
                    errors.add(String.format("Product '%s' is expired", product.getName()));
                }
            }
        }
        
        return errors;
    }
    
    public void clear() {
        items.clear();
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cart is empty";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Cart Contents:\n");
        for (CartItem item : items) {
            sb.append("  ").append(item.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: $%.2f", getSubtotal()));
        return sb.toString();
    }
} 