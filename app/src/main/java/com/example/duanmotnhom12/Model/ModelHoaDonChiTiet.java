package com.example.duanmotnhom12.Model;

public class ModelHoaDonChiTiet {
    int id_hdct ;
    String tenSanPham ;
    double donGia ;
    int soLuong ;
    String ngayHoaDon ;
    String mauSac ;
    String size ;
    int idHoaDon ;
    int idSanPham ;

    public ModelHoaDonChiTiet(int id_hdct, String tenSanPham, double donGia, int soLuong, String ngayHoaDon, String mauSac, String size, int idHoaDon, int idSanPham) {
        this.id_hdct = id_hdct;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngayHoaDon = ngayHoaDon;
        this.mauSac = mauSac;
        this.size = size;
        this.idHoaDon = idHoaDon;
        this.idSanPham = idSanPham;
    }

    public int getId_hdct() {
        return id_hdct;
    }

    public void setId_hdct(int id_hdct) {
        this.id_hdct = id_hdct;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayHoaDon() {
        return ngayHoaDon;
    }

    public void setNgayHoaDon(String ngayHoaDon) {
        this.ngayHoaDon = ngayHoaDon;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }
}

