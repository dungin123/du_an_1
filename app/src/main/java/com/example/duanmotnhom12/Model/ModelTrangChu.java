package com.example.duanmotnhom12.Model;

public class ModelTrangChu {
    int img_camera ;
    String img_anhChinh;
    String nameFb ;

    public ModelTrangChu(int img_camera, String img_anhChinh, String nameFb) {
        this.img_camera = img_camera;
        this.img_anhChinh = img_anhChinh;
        this.nameFb = nameFb;
    }

    public int getImg_camera() {
        return img_camera;
    }

    public void setImg_camera(int img_camera) {
        this.img_camera = img_camera;
    }

    public String getImg_anhChinh() {
        return img_anhChinh;
    }

    public void setImg_anhChinh(String img_anhChinh) {
        this.img_anhChinh = img_anhChinh;
    }

    public String getNameFb() {
        return nameFb;
    }

    public void setNameFb(String nameFb) {
        this.nameFb = nameFb;
    }
}
