package com.app.apekade.Model;

public class Rating {
    private float itemQualityRating;
    private float communicationRating;
    private float shippingSpeedRating;
    private String comment;
    private String customerId;
    private String added;

    public Rating(float itemQualityRating, float communicationRating, float shippingSpeedRating, String comment, String customerId, String added) {
        this.itemQualityRating = itemQualityRating;
        this.communicationRating = communicationRating;
        this.shippingSpeedRating = shippingSpeedRating;
        this.comment = comment;
        this.customerId = customerId;
        this.added = added;
    }

    public float getItemQualityRating() {
        return itemQualityRating;
    }

    public void setItemQualityRating(float itemQualityRating) {
        this.itemQualityRating = itemQualityRating;
    }

    public float getCommunicationRating() {
        return communicationRating;
    }

    public void setCommunicationRating(float communicationRating) {
        this.communicationRating = communicationRating;
    }

    public float getShippingSpeedRating() {
        return shippingSpeedRating;
    }

    public void setShippingSpeedRating(float shippingSpeedRating) {
        this.shippingSpeedRating = shippingSpeedRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }
}
