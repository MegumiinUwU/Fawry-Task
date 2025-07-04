package com.ecommerce;

import com.ecommerce.models.*;
import com.ecommerce.products.*;
import com.ecommerce.services.CheckoutService;
import java.time.LocalDate;

public class ECommerceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== E-COMMERCE SYSTEM DEMONSTRATION ===\n");
        
        // Create products
        Product[] products = createProducts();
        
        // Create customers
        Customer[] customers = createCustomers();
        
        // Demonstrate various scenarios
        demonstrateSuccessfulCheckout(products, customers[0]);
        demonstrateExpiredProductError(products, customers[1]);
        demonstrateInsufficientBalanceError(products, customers[2]);
        demonstrateOutOfStockError(products, customers[0]);
        demonstrateEmptyCartError(customers[0]);
        demonstrateMixedCart(products, customers[0]);
    }
    
    private static Product[] createProducts() {
        System.out.println("Creating sample products...\n");
        
        Product[] products = {
            new Cheese("Cheddar Cheese", 8.99, 50, LocalDate.now().plusDays(10), 0.5),
            new Cheese("Expired Cheese", 6.99, 20, LocalDate.now().minusDays(2), 0.3),
            new Biscuits("Chocolate Biscuits", 4.50, 100, LocalDate.now().plusDays(30), 0.2),
            
            new TV("Samsung 55\" 4K TV", 899.99, 5, 15.5),
            new Mobile("iPhone 14", 999.99, 10, 0.174),
            
            new MobileScratchCard("Vodafone Recharge", 10.00, 1000, "Vodafone", 10.00),
            new MobileScratchCard("Orange Recharge", 15.00, 500, "Orange", 15.00)
        };
        
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println("  " + product);
        }
        System.out.println();
        
        return products;
    }
    
    private static Customer[] createCustomers() {
        System.out.println("Creating sample customers...\n");
        
        Customer[] customers = {
            new Customer("Alice Johnson", "alice@email.com", 1500.00),
            new Customer("Bob Smith", "bob@email.com", 500.00),
            new Customer("Charlie Brown", "charlie@email.com", 50.00)
        };
        
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println("  " + customer);
        }
        System.out.println();
        
        return customers;
    }
    
    private static void demonstrateSuccessfulCheckout(Product[] products, Customer customer) {
        System.out.println("=== SCENARIO 1: Successful Checkout ===");
        
        Cart cart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        
        try {
            cart.addProduct(products[0], 2);
            cart.addProduct(products[2], 3);
            cart.addProduct(products[5], 1);
            
            System.out.println("Cart before checkout:");
            System.out.println(cart);
            System.out.println();
            
            var result = checkoutService.processCheckout(customer, cart);
            
            if (result.isSuccess()) {
                System.out.println("✅ " + result.getMessage());
            } else {
                System.out.println("❌ " + result.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        System.out.println("Customer after checkout: " + customer);
        System.out.println();
    }
    
    private static void demonstrateExpiredProductError(Product[] products, Customer customer) {
        System.out.println("=== SCENARIO 2: Expired Product Error ===");
        
        Cart cart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        
        try {
            cart.addProduct(products[1], 1);
            
            System.out.println("Attempting to checkout with expired product:");
            System.out.println(cart);
            System.out.println();
            
            var result = checkoutService.processCheckout(customer, cart);
            
            if (result.isSuccess()) {
                System.out.println("✅ " + result.getMessage());
            } else {
                System.out.println("❌ " + result.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        cart.clear();
        System.out.println();
    }
    
    private static void demonstrateInsufficientBalanceError(Product[] products, Customer customer) {
        System.out.println("=== SCENARIO 3: Insufficient Balance Error ===");
        
        Cart cart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        
        try {
            cart.addProduct(products[3], 1);
            
            System.out.println("Customer: " + customer);
            System.out.println("Attempting to checkout:");
            System.out.println(cart);
            System.out.println();
            
            var result = checkoutService.processCheckout(customer, cart);
            
            if (result.isSuccess()) {
                System.out.println("✅ " + result.getMessage());
            } else {
                System.out.println("❌ " + result.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        cart.clear();
        System.out.println();
    }
    
    private static void demonstrateOutOfStockError(Product[] products, Customer customer) {
        System.out.println("=== SCENARIO 4: Out of Stock Error ===");
        
        Cart cart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        
        try {
            cart.addProduct(products[3], 10);
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
        
        try {
            products[4].setQuantity(0);
            cart.addProduct(products[4], 1);
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
        
        products[4].setQuantity(10);
        System.out.println();
    }
    
    private static void demonstrateEmptyCartError(Customer customer) {
        System.out.println("=== SCENARIO 5: Empty Cart Error ===");
        
        Cart emptyCart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        
        System.out.println("Attempting to checkout with empty cart:");
        
        var result = checkoutService.processCheckout(customer, emptyCart);
        
        if (result.isSuccess()) {
            System.out.println("✅ " + result.getMessage());
        } else {
            System.out.println("❌ " + result.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateMixedCart(Product[] products, Customer customer) {
        System.out.println("=== SCENARIO 6: Mixed Cart (Shippable + Non-Shippable) ===");
        
        Cart cart = new Cart();
        CheckoutService checkoutService = new CheckoutService();
        
        try {
            cart.addProduct(products[4], 1);
            cart.addProduct(products[5], 2);
            cart.addProduct(products[6], 1);
            
            System.out.println("Cart with mixed items:");
            System.out.println(cart);
            System.out.println();
            
            var result = checkoutService.processCheckout(customer, cart);
            
            if (result.isSuccess()) {
                System.out.println("✅ " + result.getMessage());
            } else {
                System.out.println("❌ " + result.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        System.out.println();
        
        System.out.println("=== DEMONSTRATION COMPLETE ===");
    }
} 