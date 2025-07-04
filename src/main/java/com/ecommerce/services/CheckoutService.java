package com.ecommerce.services;

import com.ecommerce.interfaces.Shippable;
import com.ecommerce.models.*;
import java.util.List;

public class CheckoutService {
    private ShippingService shippingService;
    
    public CheckoutService() {
        this.shippingService = new ShippingService();
    }
    
    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService != null ? shippingService : new ShippingService();
    }
    
    public CheckoutResult processCheckout(Customer customer, Cart cart) {
        if (customer == null) {
            return new CheckoutResult(false, "Customer cannot be null", null);
        }
        
        if (cart == null) {
            return new CheckoutResult(false, "Cart cannot be null", null);
        }
        
        List<String> validationErrors = cart.validateForCheckout();
        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join("; ", validationErrors);
            return new CheckoutResult(false, errorMessage, null);
        }
        
        double subtotal = cart.getSubtotal();
        
        List<Shippable> shippableItems = cart.getShippableItems();
        double shippingFee = 0.0;
        
        if (!shippableItems.isEmpty()) {
            shippingFee = shippingService.calculateShippingFee(
                shippableItems.stream().mapToDouble(Shippable::getWeight).sum(), 
                subtotal
            );
        }
        
        double totalAmount = subtotal + shippingFee;
        
        if (!customer.hasSufficientBalance(totalAmount)) {
            return new CheckoutResult(false, 
                String.format("Insufficient balance. Required: $%.2f, Available: $%.2f", 
                             totalAmount, customer.getBalance()), 
                new CheckoutDetails(subtotal, shippingFee, totalAmount, customer.getBalance()));
        }
        
        try {
            customer.deductBalance(totalAmount);
        } catch (IllegalArgumentException e) {
            return new CheckoutResult(false, "Payment processing failed: " + e.getMessage(), null);
        }
        
        for (CartItem item : cart.getItems()) {
            try {
                item.getProduct().reduceQuantity(item.getQuantity());
            } catch (IllegalArgumentException e) {
                customer.addBalance(totalAmount);
                return new CheckoutResult(false, 
                    "Failed to update product quantity: " + e.getMessage(), null);
            }
        }
        
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }
        
        CheckoutDetails details = new CheckoutDetails(
            subtotal, 
            shippingFee, 
            totalAmount, 
            customer.getBalance()
        );
        
        printCheckoutDetails(customer, cart, details);
        
        cart.clear();
        
        return new CheckoutResult(true, "Checkout completed successfully", details);
    }
    
    private void printCheckoutDetails(Customer customer, Cart cart, CheckoutDetails details) {
        System.out.println("\n=== CHECKOUT DETAILS ===");
        System.out.println("Customer: " + customer.getName());
        System.out.println("\nItems purchased:");
        for (CartItem item : cart.getItems()) {
            System.out.printf("  %s x %d = $%.2f%n", 
                             item.getProduct().getName(), 
                             item.getQuantity(), 
                             item.getTotalPrice());
        }
        System.out.println();
        System.out.printf("Order subtotal: $%.2f%n", details.getSubtotal());
        System.out.printf("Shipping fees: $%.2f%n", details.getShippingFee());
        System.out.printf("Paid amount: $%.2f%n", details.getTotalAmount());
        System.out.printf("Customer current balance after payment: $%.2f%n", details.getCustomerBalanceAfterPayment());
        System.out.println("=== END CHECKOUT ===\n");
    }
    
    public ShippingService getShippingService() {
        return shippingService;
    }
}

 