package com.example.ui2_1;

import android.os.Parcelable;

import java.io.Serializable;

public class MenuItem implements Serializable  {

    private String menuId;
    private String price;
    private String menuName;
    private String options;
    private String description;

    public MenuItem(String menuId, String price, String menuName, String options, String description){
        this.menuId = menuId;
        this.price = price;
        this.menuName = menuName;
        this.options = options;
        this.description = description;

    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public String getDescript() {
        return description;
    }

    public void setDescript(String description) {
        this.description = description;
    }
}