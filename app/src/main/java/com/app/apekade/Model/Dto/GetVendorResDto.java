package com.app.apekade.Model.Dto;

import com.app.apekade.Model.Rating;

import java.util.List;

public class GetVendorResDto {
    private String id;
    private String shopName;
    private String shopDescription;
    private List<Rating> vendorRatings;

    public GetVendorResDto(String id, String shopName, String shopDescription, List<Rating> vendorRatings) {
        this.id = id;
        this.shopName = shopName;
        this.shopDescription = shopDescription;
        this.vendorRatings = vendorRatings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public List<Rating> getVendorRatings() {
        return vendorRatings;
    }

    public void setVendorRatings(List<Rating> vendorRatings) {
        this.vendorRatings = vendorRatings;
    }
}
