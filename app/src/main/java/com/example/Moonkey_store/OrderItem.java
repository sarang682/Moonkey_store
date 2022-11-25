package com.example.Moonkey_store;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int num;
    private String address;
    private int amount;

    private String product;


    public OrderItem(String product, String address, int amount) {
        this.product = product;
        this.address = address;
        this.amount = amount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
