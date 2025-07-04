package com.ecommerce.services;

import com.ecommerce.interfaces.Shippable;
import java.util.List;

public class ShippingService {
    
    private static final double SHIPPING_RATE_PER_KG = 2.50;
    private static final double BASE_SHIPPING_FEE = 5.00;
    private static final double FREE_SHIPPING_THRESHOLD = 100.00;
    
    public double processShipment(List<Shippable> shippableItems) {
        if (shippableItems == null || shippableItems.isEmpty()) {
            return 0.0;
        }
        
        System.out.println("=== SHIPPING SERVICE ===");
        System.out.println("Processing shipment for " + shippableItems.size() + " items:");
        
        double totalWeight = 0.0;
        
        for (Shippable item : shippableItems) {
            System.out.printf("  - %s (Weight: %.2fkg)%n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }
        
        System.out.printf("Total shipping weight: %.2fkg%n", totalWeight);
        
        double shippingFee = calculateShippingFee(totalWeight);
        System.out.printf("Shipping fee: $%.2f%n", shippingFee);
        System.out.println("=== END SHIPPING ===");
        
        return shippingFee;
    }
    
    public double calculateShippingFee(double totalWeight) {
        if (totalWeight <= 0) {
            return 0.0;
        }
        
        double shippingFee = BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
        
        return Math.round(shippingFee * 100.0) / 100.0;
    }
    
    public double calculateShippingFee(double totalWeight, double orderValue) {
        if (totalWeight <= 0) {
            return 0.0;
        }
        
        if (orderValue >= FREE_SHIPPING_THRESHOLD) {
            return 0.0;
        }
        
        return calculateShippingFee(totalWeight);
    }
    
    public double getShippingRatePerKg() {
        return SHIPPING_RATE_PER_KG;
    }
    
    public double getBaseShippingFee() {
        return BASE_SHIPPING_FEE;
    }
    
    public double getFreeShippingThreshold() {
        return FREE_SHIPPING_THRESHOLD;
    }
} 