package com.app.apekade.Model.Dto;

public class AddVendorRatingDto {
    private String vendorId;
    private float itemQualityRating;
    private float communicationRating;
    private float shippingSpeedRating;
    private String comment;

    // Constructor
    public AddVendorRatingDto(String vendorId, float itemQualityRating, float communicationRating,
                              float shippingSpeedRating, String comment) {
        this.vendorId = vendorId;
        this.itemQualityRating = itemQualityRating;
        this.communicationRating = communicationRating;
        this.shippingSpeedRating = shippingSpeedRating;
        this.comment = comment;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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
}
