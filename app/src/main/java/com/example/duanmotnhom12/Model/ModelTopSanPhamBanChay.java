package com.example.duanmotnhom12.Model;

public class ModelTopSanPhamBanChay {
    String tenSanPham ;
    int soLuong ;

    public ModelTopSanPhamBanChay(String tenSanPham, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
