package com.example.duanmotnhom12.Model;

public class ModelPhanHoi {
    int id_phanHoi;
    String trangThai;
    String phanHoi;
    String id_user;

    public ModelPhanHoi(int id_phanHoi, String trangThai, String phanHoi, String id_user) {
        this.id_phanHoi = id_phanHoi;
        this.trangThai = trangThai;
        this.phanHoi = phanHoi;
        this.id_user = id_user;
    }

    public int getId_phanHoi() {
        return id_phanHoi;
    }

    public void setId_phanHoi(int id_phanHoi) {
        this.id_phanHoi = id_phanHoi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getPhanHoi() {
        return phanHoi;
    }

    public void setPhanHoi(String phanHoi) {
        this.phanHoi = phanHoi;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
