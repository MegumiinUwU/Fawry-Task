package com.ecommerce.services;

public class CheckoutDetails {
    private double subtotal;
    private double shippingFee;
    private double totalAmount;
    private double customerBalanceAfterPayment;
    
    public CheckoutDetails(double subtotal, double shippingFee, double totalAmount, double customerBalanceAfterPayment) {
        this.subtotal = subtotal;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.customerBalanceAfterPayment = customerBalanceAfterPayment;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public double getShippingFee() {
        return shippingFee;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public double getCustomerBalanceAfterPayment() {
        return customerBalanceAfterPayment;
    }
} 