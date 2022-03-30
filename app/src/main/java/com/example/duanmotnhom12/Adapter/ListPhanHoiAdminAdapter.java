package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duanmotnhom12.Model.ModelPhanHoiAdmin;
import com.example.duanmotnhom12.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListPhanHoiAdminAdapter extends RecyclerView.Adapter<ListPhanHoiAdminAdapter.ListSanPhamAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelPhanHoiAdmin> modelPhanHoiAdmins;

    public ListPhanHoiAdminAdapter(Context context, ArrayList<ModelPhanHoiAdmin> modelPhanHoiAdmins) {
        this.context = context;
        this.modelPhanHoiAdmins = modelPhanHoiAdmins;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phan_hoi_admin, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelPhanHoiAdmin modelPhanHoiAdmin = modelPhanHoiAdmins.get(position);
        if (modelPhanHoiAdmin == null) {
            return;
        }
        holder.tv_maPhanHoi.setText(modelPhanHoiAdmin.getId_phanHoi_admin() + "");
        holder.tv_trangThai.setText(modelPhanHoiAdmin.getPhanHoi_admin());
    }

    @Override
    public int getItemCount() {
        if (modelPhanHoiAdmins != null) {
            return modelPhanHoiAdmins.size();
        }
        return 0;
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_maPhanHoi, tv_trangThai;
        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_maPhanHoi = itemView.findViewById(R.id.textView227);
            tv_trangThai = itemView.findViewById(R.id.textView320);
        }
    }
}
