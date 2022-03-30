package com.example.duanmotnhom12.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmotnhom12.Model.ModelTinhThanh;
import com.example.duanmotnhom12.R;

import java.util.ArrayList;

public class SpinnerAdapterTinhThanh extends BaseAdapter {

    final ArrayList<ModelTinhThanh> modelTinhThanhs;

    public SpinnerAdapterTinhThanh(ArrayList<ModelTinhThanh> modelTinhThanhs) {
        this.modelTinhThanhs = modelTinhThanhs;
    }

    @Override
    public int getCount() {
        return modelTinhThanhs.size();
    }

    @Override
    public Object getItem(int position) {
        return modelTinhThanhs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modelTinhThanhs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = View.inflate(parent.getContext(), R.layout.item_tinh_thanh, null);
        } else
            itemView = convertView;
        ModelTinhThanh modelTinhThanh = (ModelTinhThanh) modelTinhThanhs.get(position);
        TextView tv_Item = (TextView) itemView.findViewById(R.id.tv_tinhThanh);

        tv_Item.setText(modelTinhThanh.getTitle() + "");
        return itemView;
    }
}
