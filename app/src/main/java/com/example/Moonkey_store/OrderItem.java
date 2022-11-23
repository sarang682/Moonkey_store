package com.example.Moonkey_store;

public class OrderItem {
    private int num;
    private String address;
    private int price;

    public OrderItem(int num, String address, int price) {
        this.num = num;
        this.address = address;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
