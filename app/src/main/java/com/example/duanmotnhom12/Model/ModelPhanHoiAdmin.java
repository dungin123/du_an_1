package com.example.duanmotnhom12.Model;

public class ModelPhanHoiAdmin {
    int id_phanHoi_admin;
    String phanHoi_admin;
    String id_user;

    public ModelPhanHoiAdmin(int id_phanHoi_admin, String phanHoi_admin, String id_user) {
        this.id_phanHoi_admin = id_phanHoi_admin;
        this.phanHoi_admin = phanHoi_admin;
        this.id_user = id_user;
    }

    public int getId_phanHoi_admin() {
        return id_phanHoi_admin;
    }

    public void setId_phanHoi_admin(int id_phanHoi_admin) {
        this.id_phanHoi_admin = id_phanHoi_admin;
    }

    public String getPhanHoi_admin() {
        return phanHoi_admin;
    }

    public void setPhanHoi_admin(String phanHoi_admin) {
        this.phanHoi_admin = phanHoi_admin;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
