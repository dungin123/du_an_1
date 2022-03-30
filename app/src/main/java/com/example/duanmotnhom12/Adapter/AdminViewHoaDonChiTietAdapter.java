package com.example.duanmotnhom12.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmotnhom12.Model.ModelHoaDonChiTiet;
import com.example.duanmotnhom12.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdminViewHoaDonChiTietAdapter extends RecyclerView.Adapter<AdminViewHoaDonChiTietAdapter.HoaDonChiTietAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelHoaDonChiTiet> modelHoaDonChiTiets;

    public AdminViewHoaDonChiTietAdapter(Context context, ArrayList<ModelHoaDonChiTiet> modelHoaDonChiTiets) {
        this.context = context;
        this.modelHoaDonChiTiets = modelHoaDonChiTiets;
    }
//
    @NonNull
    @NotNull
    @Override
    public HoaDonChiTietAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_admin_view_hoa_don_chi_tiet, parent, false);

        return new HoaDonChiTietAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HoaDonChiTietAdapterViewHolder holder, int position) {
        ModelHoaDonChiTiet modelHoaDonChiTiet = modelHoaDonChiTiets.get(position);
        if (modelHoaDonChiTiet == null) {
            return;
        }
        holder.tv_1.setText(modelHoaDonChiTiet.getId_hdct() + "");
        holder.tv_2.setText(modelHoaDonChiTiet.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tv_3.setText(decimalFormat.format((int) modelHoaDonChiTiet.getDonGia()) + "\tVND");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dia_log_view_xem_hoa_don_admin, null);
                builder.setView(view);

                DecimalFormat decimalFormat_ = new DecimalFormat("###,###,###");

                TextView tv_soHoaDonCT = (TextView) view.findViewById(R.id.ed_tv_TenDonVi);
                TextView tv_tenSP = (TextView) view.findViewById(R.id.ed_tv_DaiDiendv);
                TextView tv_donGia = (TextView) view.findViewById(R.id.ed_tv_diaChidv);
                TextView tv_soLuong = (TextView) view.findViewById(R.id.textView50);
                TextView tv_ngayMua = (TextView) view.findViewById(R.id.textView53);
                TextView tv_mau = (TextView) view.findViewById(R.id.textView90);
                TextView tv_size = (TextView) view.findViewById(R.id.textView92);

                tv_soHoaDonCT.setText(modelHoaDonChiTiet.getId_hdct() + "");
                tv_tenSP.setText(modelHoaDonChiTiet.getTenSanPham() + "");
                tv_donGia.setText(decimalFormat_.format((int) modelHoaDonChiTiet.getDonGia()) + "\tVND");
                tv_soLuong.setText(modelHoaDonChiTiet.getSoLuong() + "");
                tv_ngayMua.setText(modelHoaDonChiTiet.getNgayHoaDon() + "");
                tv_mau.setText(modelHoaDonChiTiet.getMauSac());
                tv_size.setText(modelHoaDonChiTiet.getSize());
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelHoaDonChiTiets != null) {
            return modelHoaDonChiTiets.size();
        }
        return 0;
    }

    public class HoaDonChiTietAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tv_1, tv_2, tv_3;

        public HoaDonChiTietAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.textView493);
            tv_2 = itemView.findViewById(R.id.textView502);
            tv_3 = itemView.findViewById(R.id.textView531);

        }
    }
}
