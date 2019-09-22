package com.example.discountsplace.models;

public class cart {

    private int product_id;
    private String product_name;
    private float product_price;
    byte[] image;
    String mrket_name;

    public final static String table_name = "gazaDiscount_cart";
    public final static String col_id = "product_id";
    public final static String col_product_name = "product_name";
    public final static String col_product_price = "product_price";
    public final static String col_market_name = "market_name";
    public final static String col_image = "image";


    public final static String create_table = "create table "+table_name+" ("+col_id+" integer primary key autoincrement,"
            +col_product_name+" varchar(20),"
            +col_product_price+" integer,"
            +col_market_name+" varchar(20),"
            +col_image+" blob)";

    public cart(int product_id, String product_name, float product_price, byte[] image, String mrket_name) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.image = image;
        this.mrket_name = mrket_name;
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

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMrket_name() {
        return mrket_name;
    }

    public void setMrket_name(String mrket_name) {
        this.mrket_name = mrket_name;
    }
}
