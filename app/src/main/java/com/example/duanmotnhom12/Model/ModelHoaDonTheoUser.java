package com.example.duanmotnhom12.Model;

public class ModelHoaDonTheoUser {
    int id_hoaDon ;
    String hoTen ;
    String soDienThoai;
    String tinhThanhPho;
    String quanHuyen ;
    String phuongXa ;
    String diaChiCuThe ;
    String tinhTrang;
    String id_user ;

    public ModelHoaDonTheoUser(int id_hoaDon, String hoTen, String soDienThoai, String tinhThanhPho, String quanHuyen, String phuongXa, String diaChiCuThe, String tinhTrang, String id_user) {
        this.id_hoaDon = id_hoaDon;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.tinhThanhPho = tinhThanhPho;
        this.quanHuyen = quanHuyen;
        this.phuongXa = phuongXa;
        this.diaChiCuThe = diaChiCuThe;
        this.tinhTrang = tinhTrang;
        this.id_user = id_user;
    }

    public int getId_hoaDon() {
        return id_hoaDon;
    }

    public void setId_hoaDon(int id_hoaDon) {
        this.id_hoaDon = id_hoaDon;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTinhThanhPho() {
        return tinhThanhPho;
    }

    public void setTinhThanhPho(String tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getDiaChiCuThe() {
        return diaChiCuThe;
    }

    public void setDiaChiCuThe(String diaChiCuThe) {
        this.diaChiCuThe = diaChiCuThe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
