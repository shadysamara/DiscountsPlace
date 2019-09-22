package com.example.discountsplace.models;

public class Comment {
    int id;
    String username;
    String content;
    String rating;
    int product_id;
    String product_name;

    public Comment(int id, String content,String username, String rating, int product_id, String product_name) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.rating = rating;
        this.product_id = product_id;
        this.product_name = product_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
