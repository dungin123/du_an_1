package com.example.duanmotnhom12.Model;

public class ModelQuanHuyen {
    int id_huyen;
    String Title;
    String STT;
    int TinhThanhID;
    String TinhThanhTitle;

    public ModelQuanHuyen(int id_huyen,  String title, String STT, int tinhThanhID, String tinhThanhTitle) {
        this.id_huyen = id_huyen;
        Title = title;
        this.STT = STT;
        TinhThanhID = tinhThanhID;
        TinhThanhTitle = tinhThanhTitle;
    }

    public int getId_huyen() {
        return id_huyen;
    }

    public void setId_huyen(int id_huyen) {
        this.id_huyen = id_huyen;
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

}
