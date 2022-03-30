package com.example.duanmotnhom12.Model;

import java.io.Serializable;
import java.util.Comparator;

public class ModelSanPham implements Serializable {
    private int id_sanPham_;
    private String tenSp_;
    private String nhaSX_;
    private String imgSP_;
    private String giaTienSP_;
    private String mieuTaSP_;
    private String binhLuanSP_;
    private String trang_thai_hang;
    private String ngay_san_xuat;
    private int id_loaiSP_;

    public ModelSanPham(int id_sanPham_, String tenSp_, String nhaSX_, String imgSP_, String giaTienSP_, String mieuTaSP_, String binhLuanSP_, String trang_thai_hang, String ngay_san_xuat, int id_loaiSP_) {
        this.id_sanPham_ = id_sanPham_;
        this.tenSp_ = tenSp_;
        this.nhaSX_ = nhaSX_;
        this.imgSP_ = imgSP_;
        this.giaTienSP_ = giaTienSP_;
        this.mieuTaSP_ = mieuTaSP_;
        this.binhLuanSP_ = binhLuanSP_;
        this.trang_thai_hang = trang_thai_hang;
        this.ngay_san_xuat = ngay_san_xuat;
        this.id_loaiSP_ = id_loaiSP_;
    }

    public static Comparator<ModelSanPham> modelSanPhamComparatorAZ = new Comparator<ModelSanPham>() {
        @Override
        public int compare(ModelSanPham o1, ModelSanPham o2) {
            return o1.getTenSp_().compareTo(o2.getTenSp_());
        }
    };
    public static Comparator<ModelSanPham> modelSanPhamComparatorZA = new Comparator<ModelSanPham>() {
        @Override
        public int compare(ModelSanPham o1, ModelSanPham o2) {
            return o2.getTenSp_().compareTo(o1.getTenSp_());
        }
    };
    public static Comparator<ModelSanPham> modelSanPhamComparatorCaoThap = new Comparator<ModelSanPham>() {
        @Override
        public int compare(ModelSanPham o1, ModelSanPham o2) {
            return Integer.parseInt(o2.getGiaTienSP_()) - Integer.parseInt(o1.getGiaTienSP_());
        }
    };
    public static Comparator<ModelSanPham> modelSanPhamComparatorThapCao = new Comparator<ModelSanPham>() {
        @Override
        public int compare(ModelSanPham o1, ModelSanPham o2) {
            return Integer.parseInt(o1.getGiaTienSP_()) - Integer.parseInt(o2.getGiaTienSP_());
        }
    };


    public int getId_sanPham_() {
        return id_sanPham_;
    }

    public void setId_sanPham_(int id_sanPham_) {
        this.id_sanPham_ = id_sanPham_;
    }

    public String getTenSp_() {
        return tenSp_;
    }

    public void setTenSp_(String tenSp_) {
        this.tenSp_ = tenSp_;
    }

    public String getNhaSX_() {
        return nhaSX_;
    }

    public void setNhaSX_(String nhaSX_) {
        this.nhaSX_ = nhaSX_;
    }

    public String getImgSP_() {
        return imgSP_;
    }

    public void setImgSP_(String imgSP_) {
        this.imgSP_ = imgSP_;
    }

    public String getGiaTienSP_() {
        return giaTienSP_;
    }

    public void setGiaTienSP_(String giaTienSP_) {
        this.giaTienSP_ = giaTienSP_;
    }

    public String getMieuTaSP_() {
        return mieuTaSP_;
    }

    public void setMieuTaSP_(String mieuTaSP_) {
        this.mieuTaSP_ = mieuTaSP_;
    }

    public String getBinhLuanSP_() {
        return binhLuanSP_;
    }

    public void setBinhLuanSP_(String binhLuanSP_) {
        this.binhLuanSP_ = binhLuanSP_;
    }

    public String getTrang_thai_hang() {
        return trang_thai_hang;
    }

    public void setTrang_thai_hang(String trang_thai_hang) {
        this.trang_thai_hang = trang_thai_hang;
    }

    public String getNgay_san_xuat() {
        return ngay_san_xuat;
    }

    public void setNgay_san_xuat(String ngay_san_xuat) {
        this.ngay_san_xuat = ngay_san_xuat;
    }

    public int getId_loaiSP_() {
        return id_loaiSP_;
    }

    public void setId_loaiSP_(int id_loaiSP_) {
        this.id_loaiSP_ = id_loaiSP_;
    }
}
