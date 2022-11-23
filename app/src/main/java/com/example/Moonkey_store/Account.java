package com.example.Moonkey_store;

public class Account {
    private String id;
    private String pw;
    private String phone;
    private int user_id;
    private String address;
    private String nickname;
    private int flag;

    public Account(String id, String pw, String phone, int user_id, String address, String nickname) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.user_id = user_id;
        this.address = address;
        this.nickname = nickname;
    }

    public Account(String id, String pw, String phone, String address, String nickname) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
        this.nickname = nickname;
    }

    public Account(String id, String password, String phone, String address, String nickname,int flag) {
        this.id = id;
        this.pw = password;
        this.phone = phone;
        this.address = address;
        this.nickname = nickname;
        this.flag=flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}