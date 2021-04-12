package com.ruochen.shop.domain;


public class User {
    private int id;
    private String uname;
    private String upw;

    public User() {
    }

    public User(String uname, String upw) {
        this.uname = uname;
        this.upw = upw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpw() {
        return upw;
    }

    public void setUpw(String upw) {
        this.upw = upw;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" +
                ", uname='" + uname + '\'' +
                ", upw='" + upw + '\'' +
                '}';
    }
}
