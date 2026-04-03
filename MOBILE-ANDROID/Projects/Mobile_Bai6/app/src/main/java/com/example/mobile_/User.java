package com.example.mobile_;

public class User {
    private int id;

    public User(String imgUrl, String phonenumber, String name, int id) {
        this.imgUrl = imgUrl;
        this.phonenumber = phonenumber;
        this.name = name;
        this.id = id;
        check = false;
    }

    public User(String imgUrl, String phonenumber, String name, int id, int check) {
        this.imgUrl = imgUrl;
        this.phonenumber = phonenumber;
        this.name = name;
        this.id = id;
        this.check = check == 1 ? true : false;
    }

    private String name;
    private String phonenumber;
    private String imgUrl;
    private boolean check;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
