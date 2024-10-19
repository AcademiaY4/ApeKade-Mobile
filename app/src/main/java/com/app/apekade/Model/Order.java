package com.app.apekade.Model;

import java.io.Serializable;

public class Order implements Serializable {
    private long orderId;
    private String productName;
    private int quantity;
    private double price;
    private String color;
    private String size;
    private String userId;
    private String status;

    // Constructor
    public Order(long orderId, String productName, int quantity, double price, String color, String size, String userId, String status) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.color = color;
        this.size = size;
        this.userId = userId;
        this.status = status;
    }

    // Getters and Setters
    public long getOrderId() { return orderId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getColor() { return color; }
    public String getSize() { return size; }
    public String getUserId() { return userId; }
    public String getStatus() { return status; }

    public void setOrderId(long orderId) { this.orderId = orderId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setColor(String color) { this.color = color; }
    public void setSize(String size) { this.size = size; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setStatus(String status) { this.status = status; }
}
