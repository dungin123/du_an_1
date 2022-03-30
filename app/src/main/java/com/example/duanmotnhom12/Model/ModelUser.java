package com.example.duanmotnhom12.Model;

public class ModelUser {
    int id_user ;
    String ten_user ;
    String tenDn_user;
    String pass;

    public ModelUser(int id_user, String ten_user, String tenDn_user, String pass) {
        this.id_user = id_user;
        this.ten_user = ten_user;
        this.tenDn_user = tenDn_user;
        this.pass = pass;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTen_user() {
        return ten_user;
    }

    public void setTen_user(String ten_user) {
        this.ten_user = ten_user;
    }

    public String getTenDn_user() {
        return tenDn_user;
    }

    public void setTenDn_user(String tenDn_user) {
        this.tenDn_user = tenDn_user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
