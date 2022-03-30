package com.example.duanmotnhom12.Model;

public class ModelPhuongXa {
    int ID;
    String Title ;
    String STT ;
    int TinhThanhID ;
    String TinhThanhTitle ;
    int QuanHuyenID  ;
    String QuanHuyenTitle ;


    public ModelPhuongXa(int ID, String title, String STT, int tinhThanhID, String tinhThanhTitle,  int quanHuyenID, String quanHuyenTitle) {
        this.ID = ID;
        Title = title;
        this.STT = STT;
        TinhThanhID = tinhThanhID;
        TinhThanhTitle = tinhThanhTitle;
        QuanHuyenID = quanHuyenID;
        QuanHuyenTitle = quanHuyenTitle;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSTT() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public int getTinhThanhID() {
        return TinhThanhID;
    }

    public void setTinhThanhID(int tinhThanhID) {
        TinhThanhID = tinhThanhID;
    }

    public String getTinhThanhTitle() {
        return TinhThanhTitle;
    }

    public void setTinhThanhTitle(String tinhThanhTitle) {
        TinhThanhTitle = tinhThanhTitle;
    }

    public int getQuanHuyenID() {
        return QuanHuyenID;
    }

    public void setQuanHuyenID(int quanHuyenID) {
        QuanHuyenID = quanHuyenID;
    }

    public String getQuanHuyenTitle() {
        return QuanHuyenTitle;
    }

    public void setQuanHuyenTitle(String quanHuyenTitle) {
        QuanHuyenTitle = quanHuyenTitle;
    }

}
