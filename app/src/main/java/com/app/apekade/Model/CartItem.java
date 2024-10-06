package com.app.apekade.Model;

import java.io.Serializable;

public class CartItem  implements Serializable {

    private String cartItemId;
    private String productId;
    private String imageUrl;
    private int quantity;
    private double price;

    // Constructor
    public CartItem(String id, String productId, String imageUrl, int quantity, double price) {
        this.cartItemId = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String id) {
        this.cartItemId = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
