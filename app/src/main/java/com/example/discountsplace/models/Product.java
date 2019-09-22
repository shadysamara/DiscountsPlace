package com.example.discountsplace.models;

public class Product {


    private int id;
    private String name;
    private double old_price;
    private double new_price;
    private int market_id;
    private String category;
    private String description;
    private String image64;
    private double rating;
    public Product(){}

    public Product(String name, double old_price, double new_price, int market_id, String category, String description, String image64, double rating) {
        this.name = name;
        this.old_price = old_price;
        this.new_price = new_price;
        this.market_id = market_id;
        this.category = category;
        this.description = description;
        this.image64 = image64;
        this.rating = rating;
    }

    public Product(int id, String name, double old_price, double new_price, int market_id, String category, String description, String image64, double rating) {
        this.id = id;
        this.name = name;
        this.old_price = old_price;
        this.new_price = new_price;
        this.market_id = market_id;
        this.category = category;
        this.description = description;
        this.image64 = image64;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public int getMarket_id() {
        return market_id;
    }

    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage64() {
        return image64;
    }

    public void setImage64(String image64) {
        this.image64 = image64;
    }
}
