package com.example.duanmotnhom12.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmotnhom12.Model.ModelQuanHuyen;
import com.example.duanmotnhom12.R;

import java.util.ArrayList;

public class SpinnerAdapterQuanHuyen extends BaseAdapter {

    final ArrayList<ModelQuanHuyen> modelQuanHuyens;

    public SpinnerAdapterQuanHuyen(ArrayList<ModelQuanHuyen> modelQuanHuyens) {
        this.modelQuanHuyens = modelQuanHuyens;
    }

    @Override
    public int getCount() {
        return modelQuanHuyens.size();
    }

    @Override
    public Object getItem(int position) {
        return modelQuanHuyens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modelQuanHuyens.get(position).getId_huyen();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = View.inflate(parent.getContext(), R.layout.item_quan_huyen, null);
        } else
            itemView = convertView;
        ModelQuanHuyen modelQuanHuyen = (ModelQuanHuyen) modelQuanHuyens.get(position);
        TextView tv_ = (TextView) itemView.findViewById(R.id.tv_quanHuyen);

        tv_.setText(modelQuanHuyen.getTitle() + "");
        return itemView;
    }
}
