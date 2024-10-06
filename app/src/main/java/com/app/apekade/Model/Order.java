package com.app.apekade.Model;

import java.io.Serializable;

// Order.java
public class Order implements Serializable { // Make it Serializable
    private int orderId; // Order ID
    private String name;
    private int quantity;
    private double price;
    private String color;
    private String size;
    private String userId;
    private String status;

    public Order(int orderId, String name, int quantity, double price, String color, String size, String userId, String status) {
        this.orderId = orderId; // Initialize orderId
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.size = size;
        this.userId = userId;
        this.status = status;
    }

    public int getOrderId() { // Getter for order ID
        return orderId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getUserId() {
        return userId;  // Getter for user ID
    }

    public String getStatus() {
        return status;  // Getter for order status
    }
}
