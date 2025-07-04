package com.ecommerce.models;

public class Customer {
    private String name;
    private String email;
    private double balance;
    
    public Customer(String name, String email, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Customer balance cannot be negative");
        }
        
        this.name = name.trim();
        this.email = email.trim();
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        this.name = name.trim();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }
        this.email = email.trim();
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void addBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        this.balance += amount;
    }
    
    public void deductBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deduct cannot be negative");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }
    
    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }
    
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Customer balance cannot be negative");
        }
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return String.format("Customer: %s (%s) - Balance: $%.2f", name, email, balance);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return email.equals(customer.email);
    }
    
    @Override
    public int hashCode() {
        return email.hashCode();
    }
} 