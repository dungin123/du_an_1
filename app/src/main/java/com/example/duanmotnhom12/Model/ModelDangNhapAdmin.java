package com.example.duanmotnhom12.Model;

public class ModelDangNhapAdmin {
    int id_admin ;
    String name_admin;
    String email_admin;


    public ModelDangNhapAdmin(int id_admin, String name_admin, String email_admin) {
        this.id_admin = id_admin;
        this.name_admin = name_admin;
        this.email_admin = email_admin;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getName_admin() {
        return name_admin;
    }

    public void setName_admin(String name_admin) {
        this.name_admin = name_admin;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }
}
