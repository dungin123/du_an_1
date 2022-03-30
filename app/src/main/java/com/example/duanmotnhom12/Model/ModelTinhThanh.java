package com.example.duanmotnhom12.Model;

public class ModelTinhThanh {
    int id;
    String Title;
    String STT;

    public ModelTinhThanh(int id, String title, String STT) {
        this.id = id;
        Title = title;
        this.STT = STT;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
