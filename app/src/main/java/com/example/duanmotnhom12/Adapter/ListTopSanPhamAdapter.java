package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duanmotnhom12.Model.ModelTopSanPhamBanChay;
import com.example.duanmotnhom12.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListTopSanPhamAdapter extends RecyclerView.Adapter<ListTopSanPhamAdapter.ListSanPhamTopAdapterViewHolder> {
    public Context context;
    public ArrayList<ModelTopSanPhamBanChay> modelTopSanPhamBanChays;

    public ListTopSanPhamAdapter(Context context, ArrayList<ModelTopSanPhamBanChay> modelTopSanPhamBanChays) {
        this.context = context;
        this.modelTopSanPhamBanChays = modelTopSanPhamBanChays;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamTopAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_san_pham_, parent, false);

        return new ListSanPhamTopAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamTopAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelTopSanPhamBanChay modelTopSanPhamBanChay = modelTopSanPhamBanChays.get(position);
        if (modelTopSanPhamBanChays == null) {
            return;
        }
        holder.tv_tenSanPham.setText(modelTopSanPhamBanChay.getTenSanPham());
        holder.tv_SoLuongBan.setText(modelTopSanPhamBanChay.getSoLuong()+"");
        Log.d("ddd",modelTopSanPhamBanChay.getTenSanPham()+"");
    }

    @Override
    public int getItemCount() {
        if (modelTopSanPhamBanChays != null) {
            return modelTopSanPhamBanChays.size();
        }
        return 0;
    }

    public class ListSanPhamTopAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tenSanPham, tv_SoLuongBan;

        public ListSanPhamTopAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_tenSanPham = itemView.findViewById(R.id.textView112);
            tv_SoLuongBan = itemView.findViewById(R.id.textView113);
        }
    }
}
