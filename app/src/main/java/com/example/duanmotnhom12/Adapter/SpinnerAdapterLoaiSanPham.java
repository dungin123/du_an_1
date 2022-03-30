package com.example.duanmotnhom12.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmotnhom12.Model.ModelLoaiSanPham;
import com.example.duanmotnhom12.R;

import java.util.ArrayList;

public class SpinnerAdapterLoaiSanPham extends BaseAdapter {

    final ArrayList<ModelLoaiSanPham> modelLoaiSanPhams;

    public SpinnerAdapterLoaiSanPham(ArrayList<ModelLoaiSanPham> modelLoaiSanPhams) {
        this.modelLoaiSanPhams = modelLoaiSanPhams;
    }

    @Override
    public int getCount() {
        return modelLoaiSanPhams.size();
    }

    @Override
    public Object getItem(int position) {
        return modelLoaiSanPhams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modelLoaiSanPhams.get(position).getId_loai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = View.inflate(parent.getContext(), R.layout.item_loai_san_pham_spinner, null);
        } else
            itemView = convertView;
        ModelLoaiSanPham modelLoaiSanPham = (ModelLoaiSanPham) modelLoaiSanPhams.get(position);
        TextView tv_Item_tenLoai = (TextView) itemView.findViewById(R.id.tv_tenLoaiSanPham);

        tv_Item_tenLoai.setText(modelLoaiSanPham.getTenLoai() + "");
        return itemView;
    }
}
