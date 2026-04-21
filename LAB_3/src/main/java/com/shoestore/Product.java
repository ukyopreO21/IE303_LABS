package com.shoestore;

public class Product {
    private final String title;
    private final String brand;
    private final String price;
    private final String shortDesc;
    private final String imageUrl;

    public Product(String title, String brand, String price, String shortDesc, String imageUrl) {
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.shortDesc = shortDesc;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getPrice() { return price; }
    public String getBrand() { return brand; }
    public String getShortDesc() { return shortDesc; }
}
