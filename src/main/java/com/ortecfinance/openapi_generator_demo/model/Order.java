package com.ortecfinance.openapi_generator_demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Order {
    
    @Schema(description = "Unique identifier of the order", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;
    
    @Schema(description = "Name of the product", example = "Laptop")
    private String productName;
    
    @Schema(description = "Price of the product", example = "999.99")
    private double price;
    
    @Schema(description = "Name of the customer", example = "John Doe")
    private String customerName;
    
    // Default constructor
    public Order() {
    }
    
    // Constructor with fields
    public Order(String id, String productName, double price, String customerName) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.customerName = customerName;
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
