package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmotnhom12.FragmentFromBanGiay.GioHangFragment;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.Model.ModelGioHang;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.duanmotnhom12.FromBanGiay.MainActivityViewChiTietSanPham.MY_PREFS_NAME;

public class ListGioHangAdapter extends RecyclerView.Adapter<ListGioHangAdapter.ListGioHangAdapterViewHolder> {

    public Context context;
    public ArrayList<ModelGioHang> modelGioHangs;

    public ListGioHangAdapter(Context context, ArrayList<ModelGioHang> modelGioHangs) {
        this.context = context;
        this.modelGioHangs = modelGioHangs;
    }

    @NonNull
    @NotNull
    @Override
    public ListGioHangAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gio_hang, parent, false);

        return new ListGioHangAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListGioHangAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelGioHang modelGioHang = modelGioHangs.get(position);
        if (modelGioHang == null) {
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tv_tenSanPham.setText(modelGioHang.getTen_sanPham_gioHang());
        int giaTien = Integer.parseInt(modelGioHang.getGia_tien_gioHang());
        holder.tv_giaSanPham.setText(decimalFormat.format(giaTien) + "\tđ");
        holder.textView_mau.setText((modelGioHang.getMauSac()));
        holder.textView_size.setText((modelGioHang.getSizeGiay()));
        holder.ed_soLuong.setText(modelGioHang.getSo_luong_gioHang() + "");
        Picasso.get().load(modelGioHang.getImg_gioHang())
                .placeholder(R.drawable.noimageavailablesvg)
                .error(R.drawable.error)
                .into(holder.imageView);
        int ed_soLuong = Integer.parseInt(holder.ed_soLuong.getText().toString());
        if (ed_soLuong >= 50) {
            holder.btn_Cong.setVisibility(View.INVISIBLE);
            holder.btn_Tru.setVisibility(View.VISIBLE);
        } else if (ed_soLuong <= 1) {
            holder.btn_Tru.setVisibility(View.INVISIBLE);
        } else if (ed_soLuong >= 1) {
            holder.btn_Cong.setVisibility(View.VISIBLE);
            holder.btn_Tru.setVisibility(View.VISIBLE);
        }
        holder.btn_Cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnCongSoLuong = Integer.parseInt(holder.ed_soLuong.getText().toString()) + 1;
                int soLuongHienTai = FromBanGiay.modelGioHangs.get(position).getSo_luong_gioHang();
                long giaHienTai = Integer.parseInt(FromBanGiay.modelGioHangs.get(position).getGia_tien_gioHang());
                FromBanGiay.modelGioHangs.get(position).setSo_luong_gioHang(btnCongSoLuong);
                long giaMoiNhat = (giaHienTai * btnCongSoLuong) / soLuongHienTai;
                FromBanGiay.modelGioHangs.get(position).setGia_tien_gioHang(giaMoiNhat + "");
                DecimalFormat decimalFormat_ = new DecimalFormat("###,###,###");
                holder.tv_giaSanPham.setText(decimalFormat_.format(giaMoiNhat) + "\tđ");
                GioHangFragment.getAllTongTien();
                if (btnCongSoLuong > 50) {
                    holder.btn_Cong.setVisibility(View.INVISIBLE);
                    holder.btn_Tru.setVisibility(View.VISIBLE);
                    holder.ed_soLuong.setText(btnCongSoLuong + "");
                } else {
                    holder.btn_Cong.setVisibility(View.VISIBLE);
                    holder.btn_Tru.setVisibility(View.VISIBLE);
                    holder.ed_soLuong.setText(btnCongSoLuong + "");
                }
                getShar();
            }
        });

        holder.btn_Tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnCongSoLuong = Integer.parseInt(holder.ed_soLuong.getText().toString()) - 1;
                int soLuongHienTai = FromBanGiay.modelGioHangs.get(position).getSo_luong_gioHang();
                long giaHienTai = Integer.parseInt(FromBanGiay.modelGioHangs.get(position).getGia_tien_gioHang());
                FromBanGiay.modelGioHangs.get(position).setSo_luong_gioHang(btnCongSoLuong);
                long giaMoiNhat = (giaHienTai * btnCongSoLuong) / soLuongHienTai;
                FromBanGiay.modelGioHangs.get(position).setGia_tien_gioHang(giaMoiNhat + "");
                DecimalFormat decimalFormat_ = new DecimalFormat("###,###,###");
                holder.tv_giaSanPham.setText(decimalFormat_.format(giaMoiNhat) + "\tđ");
                GioHangFragment.getAllTongTien();
                if (btnCongSoLuong < 2) {
                    holder.btn_Cong.setVisibility(View.VISIBLE);
                    holder.btn_Tru.setVisibility(View.INVISIBLE);
                    holder.ed_soLuong.setText(btnCongSoLuong + "");
                } else {
                    holder.btn_Cong.setVisibility(View.VISIBLE);
                    holder.btn_Tru.setVisibility(View.VISIBLE);
                    holder.ed_soLuong.setText(btnCongSoLuong + "");
                }
                getShar();
            }
        });
        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn chắc chắn muốn xoá sản phẩm này ra khỏi giỏ hàng?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (FromBanGiay.modelGioHangs.size() <= 0) {

                        } else {
                            FromBanGiay.modelGioHangs.remove(position);
                            notifyDataSetChanged();
                            GioHangFragment.getAllTongTien();
                            GioHangFragment.checkData();
                        }
                        getShar();
                        CustomToast.makeText(context, "Sản phẩm đã được xoá", (int) CustomToast.LONG, CustomToast.ERROR, true).show();
                    }

                });
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelGioHangs != null) {
            return modelGioHangs.size();
        }
        return 0;
    }

    public class ListGioHangAdapterViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv_tenSanPham, tv_giaSanPham, textView2, textView_mau, textView_size;
        EditText ed_soLuong;
        Button btn_Tru, btn_Cong;

        public ListGioHangAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_sanPhamGioHang);
            tv_tenSanPham = itemView.findViewById(R.id.textView10);
            tv_giaSanPham = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            textView_mau = itemView.findViewById(R.id.textView87);
            textView_size = itemView.findViewById(R.id.textView88);

            ed_soLuong = itemView.findViewById(R.id.ed_soLuong);
            btn_Tru = itemView.findViewById(R.id.btn_tru);
            btn_Cong = itemView.findViewById(R.id.btn_cong);

        }
    }

    public void getShar() {
        SharedPreferences settings = context.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        settings.edit().remove("tasklist").commit();
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(FromBanGiay.modelGioHangs);
        editor.putString("tasklist", json);
        editor.apply();
    }
}
