package com.example.duanmotnhom12.Model;

public class ModelGioHang {
    int id_sanPham_gioHang;
    String img_gioHang;
    String ten_sanPham_gioHang;
    String gia_tien_gioHang;
    String mauSac;
    String sizeGiay;
    int so_luong_gioHang;

    public ModelGioHang(int id_sanPham_gioHang, String img_gioHang, String ten_sanPham_gioHang, String gia_tien_gioHang, String mauSac, String sizeGiay, int so_luong_gioHang) {
        this.id_sanPham_gioHang = id_sanPham_gioHang;
        this.img_gioHang = img_gioHang;
        this.ten_sanPham_gioHang = ten_sanPham_gioHang;
        this.gia_tien_gioHang = gia_tien_gioHang;
        this.mauSac = mauSac;
        this.sizeGiay = sizeGiay;
        this.so_luong_gioHang = so_luong_gioHang;
    }

    public int getId_sanPham_gioHang() {
        return id_sanPham_gioHang;
    }

    public void setId_sanPham_gioHang(int id_sanPham_gioHang) {
        this.id_sanPham_gioHang = id_sanPham_gioHang;
    }

    public String getImg_gioHang() {
        return img_gioHang;
    }

    public void setImg_gioHang(String img_gioHang) {
        this.img_gioHang = img_gioHang;
    }

    public String getTen_sanPham_gioHang() {
        return ten_sanPham_gioHang;
    }

    public void setTen_sanPham_gioHang(String ten_sanPham_gioHang) {
        this.ten_sanPham_gioHang = ten_sanPham_gioHang;
    }

    public String getGia_tien_gioHang() {
        return gia_tien_gioHang;
    }

    public void setGia_tien_gioHang(String gia_tien_gioHang) {
        this.gia_tien_gioHang = gia_tien_gioHang;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSizeGiay() {
        return sizeGiay;
    }

    public void setSizeGiay(String sizeGiay) {
        this.sizeGiay = sizeGiay;
    }

    public int getSo_luong_gioHang() {
        return so_luong_gioHang;
    }

    public void setSo_luong_gioHang(int so_luong_gioHang) {
        this.so_luong_gioHang = so_luong_gioHang;
    }
}
