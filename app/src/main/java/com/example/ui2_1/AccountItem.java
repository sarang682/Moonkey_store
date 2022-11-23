package com.example.ui2_1;

import java.io.Serializable;

public class AccountItem implements Serializable {
    private String id;
    private String pw;
    private String phone;
    private String uid;
    private String address;
    private String nickname;
    private int flag;

    public AccountItem(String id, String password, String phone, String address, String nickname,int flag) {
        this.id = id;
        this.pw = password;
        this.phone = phone;
        this.address = address;
        this.nickname = nickname;
        this.flag=flag;
    }

    public AccountItem(String id, String uid, String phone, String address, String nickname) {
        this.id = id;
        this.uid = uid;
        this.phone = phone;
        this.address = address;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}