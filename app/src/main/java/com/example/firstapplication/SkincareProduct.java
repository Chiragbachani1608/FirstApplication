package com.example.firstapplication;

public class SkincareProduct {
    private String name;
    private String brand;
    private String purpose;
    private String productUrl;

    public SkincareProduct(String name, String brand, String purpose, String productUrl) {
        this.name = name;
        this.brand = brand;
        this.purpose = purpose;
        this.productUrl = productUrl;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getProductUrl() {
        return productUrl;
    }
}
