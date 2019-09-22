package com.example.discountsplace.models;

public class Market {


    int id;
    String name;
    String city;
    String address;
    String phone;
    String cagtegory;
    String image_url;
    double rating;

    public Market(int id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Market(int id, String name, String city, String address, String phone, String cagtegory, String image_url, double rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.cagtegory = cagtegory;
        this.image_url = image_url;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Market(String name, String city, String address, String phone, String cagtegory, String image_url, double rating) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.cagtegory = cagtegory;
        this.image_url = image_url;
        this.rating=rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCagtegory() {
        return cagtegory;
    }

    public void setCagtegory(String cagtegory) {
        this.cagtegory = cagtegory;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
