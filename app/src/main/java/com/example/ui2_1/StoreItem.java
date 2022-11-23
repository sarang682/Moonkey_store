package com.example.ui2_1;

import java.io.Serializable;

public class StoreItem implements Serializable {
    private String uid;
    private String storeId;
    private String name;
    private String address;
    private String description;
    private String category;
    private String contact;


    public StoreItem(String storeId, String name, String address, String category, String contact) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
        this.category = category;
        this.contact = contact;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}