package com.example.duanmotnhom12.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmotnhom12.Model.ModelPhuongXa;
import com.example.duanmotnhom12.R;

import java.util.ArrayList;

public class SpinnerAdapterPhuongXa extends BaseAdapter {

    final ArrayList<ModelPhuongXa> modelPhuongXas;

    public SpinnerAdapterPhuongXa(ArrayList<ModelPhuongXa> modelPhuongXas) {
        this.modelPhuongXas = modelPhuongXas;
    }

    @Override
    public int getCount() {
        return modelPhuongXas.size();
    }

    @Override
    public Object getItem(int position) {
        return modelPhuongXas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modelPhuongXas.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null) {
            itemView = View.inflate(parent.getContext(), R.layout.item_phuong_xa, null);
        } else
            itemView = convertView;
        ModelPhuongXa modelPhuongXa = (ModelPhuongXa) modelPhuongXas.get(position);
        TextView tv_Item = (TextView) itemView.findViewById(R.id.tv_phuongXa);

        tv_Item.setText(modelPhuongXa.getTitle() + "");
        return itemView;
    }
}
