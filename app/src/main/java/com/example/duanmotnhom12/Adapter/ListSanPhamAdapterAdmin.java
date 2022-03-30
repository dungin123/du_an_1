package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.MainActivityViewChiTietSanPhamAdmin;
import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.example.duanmotnhom12.activity.DanhSachSanPham;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListSanPhamAdapterAdmin extends RecyclerView.Adapter<ListSanPhamAdapterAdmin.ListSanPhamAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelSanPham> modelSanPhams;
    static ProgressBar progressBar;
    String id_sp;
    String id_sp_update;

    String tenSp;
    int giaTien;
    String nhaSX;
    String ngaySanXuat;
    String trangThai;
    String mieuTa;
    String binhLuan;

    public ListSanPhamAdapterAdmin(Context context, ArrayList<ModelSanPham> modelSanPhams) {
        this.context = context;
        this.modelSanPhams = modelSanPhams;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_san_pham_admin, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelSanPham modelSanPham = modelSanPhams.get(position);
        if (modelSanPham == null) {
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tv_giaTien.setText(decimalFormat.format(Integer.parseInt(modelSanPham.getGiaTienSP_())) + "VND");
        holder.tv_tenSanPham.setText(modelSanPham.getTenSp_());
        holder.tv_Trangthai.setText(modelSanPham.getTrang_thai_hang());
        try {
            Picasso.get().load(modelSanPham.getImgSP_()).placeholder(R.drawable.noimageavailablesvg).
                    error(R.drawable.error).
                    fit().
                    centerCrop().
                    into(holder.img_giay);
        } catch (Exception e) {
            Log.d("get_error", e + "");
        }
        if (holder.tv_Trangthai.getText().toString().equals("Hết hàng")) {
            holder.tv_Trangthai.setTextColor(R.color.black);
            holder.tv_Trangthai.setPaintFlags(holder.tv_Trangthai.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence sequence[] = {"Xem chi tiết sản phẩm", "Chỉnh sửa sản phẩm", "Xoá sản phẩm"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chức năng");
                builder.setItems(sequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(context, MainActivityViewChiTietSanPhamAdmin.class);
                            intent.putExtra("viewChiTietSanPhamAdmin", modelSanPhams.get(position));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } else if (which == 1) {
                            id_sp_update = modelSanPham.getId_sanPham_() + "";
                            Log.d("id_up", id_sp_update);
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.dia_log_view_chinh_sua_san_pham, null);
                            AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                                    .setPositiveButton("Lưu", null)
                                    .setNegativeButton("Hủy", null)
                                    .setCancelable(false)
                                    .setView(view)
                                    .show();
                            EditText ed_ten = view.findViewById(R.id.editTextTextPersonName4);
                            EditText ed_giaTien = view.findViewById(R.id.editTextTextPersonName5);
                            EditText ed_nhaSx = view.findViewById(R.id.editTextTextPersonName6);
                            EditText ed_ngaySx = view.findViewById(R.id.editTextTextPersonName7);
                            EditText ed_trangThai = view.findViewById(R.id.editTextTextPersonName8);
                            EditText ed_binhLuan = view.findViewById(R.id.editTextTextPersonName9);
                            EditText ed_mieuTa = view.findViewById(R.id.editTextTextPersonName10);

                            ed_ten.setText(modelSanPham.getTenSp_());
                            ed_giaTien.setText(modelSanPham.getGiaTienSP_());
                            ed_nhaSx.setText(modelSanPham.getNhaSX_());
                            ed_ngaySx.setText(modelSanPham.getNgay_san_xuat());
                            ed_trangThai.setText(modelSanPham.getTrang_thai_hang());
                            ed_binhLuan.setText(modelSanPham.getBinhLuanSP_());
                            ed_mieuTa.setText(modelSanPham.getMieuTaSP_());


                            Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tenSp = ed_ten.getText().toString();
                                    giaTien = Integer.parseInt(ed_giaTien.getText().toString());
                                    nhaSX = ed_nhaSx.getText().toString();
                                    ngaySanXuat = ed_ngaySx.getText().toString();
                                    trangThai = ed_trangThai.getText().toString();
                                    mieuTa = ed_mieuTa.getText().toString();
                                    binhLuan = ed_binhLuan.getText().toString();
                                    CapNhatSanPham();

                                }
                            });
                        } else {
                            AlertDialog.Builder builder_Xoa = new AlertDialog.Builder(context);
                            builder_Xoa.setTitle("Bạn có chắc chắn muốn xoá sản phẩm ? ");
                            builder_Xoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    id_sp = modelSanPham.getId_sanPham_() + "";
                                    Log.d("idsp", id_sp + "");
                                    xoaSanPham();
                                }
                            });
                            builder_Xoa.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog_ = builder_Xoa.create();
                            dialog_.show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelSanPhams != null) {
            return modelSanPhams.size();
        }
        return 0;
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_giay;
        private TextView tv_giaTien, tv_tenSanPham, tv_xemChitiet, tv_Trangthai;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            img_giay = itemView.findViewById(R.id.img_book_KeSach);
            tv_giaTien = itemView.findViewById(R.id.textView9);
            tv_tenSanPham = itemView.findViewById(R.id.textView10);
            tv_xemChitiet = itemView.findViewById(R.id.textView13);
            tv_Trangthai = itemView.findViewById(R.id.textView8);
            progressBar = itemView.findViewById(R.id.progressBar2);

        }
    }

    void xoaSanPham() {
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
        progressBar.setVisibility(View.VISIBLE);
       // progressBar.setProgressTintList(ColorStateList.valueOf(Color.BLACK));
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URLss.URL_XOASANPHAM + "/" + id_sp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        CustomToast.makeText(context, "Xoá sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                        Intent intent = new Intent(context, DanhSachSanPham.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                }, 2000);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("xao", error.toString());

            }
        }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_sanPham_", id_sp + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    void CapNhatSanPham() {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URLss.URL_CAPNHAT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(context, DanhSachSanPham.class);
                context.startActivity(intent);
                ((Activity) context).finish();

                CustomToast.makeText(context, "Cập nhật sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("loiUp", error.toString() + "");
            }
        }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("tenSp_", tenSp);
                map.put("nhaSX_", nhaSX);
                map.put("giaTienSP_", giaTien + "");
                map.put("mieuTaSP_", mieuTa);
                map.put("binhLuanSP_", binhLuan);
                map.put("trang_thai_hang", trangThai);
                map.put("ngay_san_xuat", ngaySanXuat);
                map.put("id_sanPham_", id_sp_update + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
