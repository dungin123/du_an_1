package com.example.duanmotnhom12.Model;

public class ModelLoaiSanPham {
    int id_loai;
    String tenLoai;
    String kieuGioTinh;

    public ModelLoaiSanPham(int id_loai, String tenLoai, String kieuGioTinh) {
        this.id_loai = id_loai;
        this.tenLoai = tenLoai;
        this.kieuGioTinh = kieuGioTinh;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getKieuGioTinh() {
        return kieuGioTinh;
    }

    public void setKieuGioTinh(String kieuGioTinh) {
        this.kieuGioTinh = kieuGioTinh;
    }
}
