package com.example.Moonkey_store;

public class Menu {

    private int price;
    private int number;
    private String name;
    private String option;
    private int menu_id;
    private String comment;



    public Menu(int price, int number, String name, String option, int menu_id, String comment) {
        this.price = price;
        this.number = number;
        this.name = name;
        this.option = option;
        this.menu_id = menu_id;
        this.comment=comment;
    }

    public Menu(int price, String name, String comment) {
        this.price = price;
        this.name = name;
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getPrice() { return price; }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
}
