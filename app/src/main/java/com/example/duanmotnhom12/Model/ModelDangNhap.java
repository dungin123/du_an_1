package com.example.duanmotnhom12.Model;

public class ModelDangNhap {
    int id_user ;
    String hoTen_user;
    String email_user;


    public ModelDangNhap(int id_user, String hoTen_user, String email_user) {
        this.id_user = id_user;
        this.hoTen_user = hoTen_user;
        this.email_user = email_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getHoTen_user() {
        return hoTen_user;
    }

    public void setHoTen_user(String hoTen_user) {
        this.hoTen_user = hoTen_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

}
