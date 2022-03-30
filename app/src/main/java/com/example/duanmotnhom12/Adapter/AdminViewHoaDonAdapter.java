package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.JSON.JsonQuanLyHoaDon;
import com.example.duanmotnhom12.Model.ModelHoaDonTheoUser;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.example.duanmotnhom12.activity.AdminViewHoaDonChiTiet;
import com.example.duanmotnhom12.activity.ChiTietHoaDon;
import com.example.duanmotnhom12.activity.QuanLyDonHang;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminViewHoaDonAdapter extends RecyclerView.Adapter<AdminViewHoaDonAdapter.ListSanPhamAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelHoaDonTheoUser> modelHoaDonTheoUsers;
    String trangThai_ = "";
    String id_hoaDon;
    SpinKitView progressBar;

    public AdminViewHoaDonAdapter(Context context, ArrayList<ModelHoaDonTheoUser> modelHoaDonTheoUsers) {
        this.context = context;
        this.modelHoaDonTheoUsers = modelHoaDonTheoUsers;

    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_quan_ly_hoa_don, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelHoaDonTheoUser modelHoaDonTheoUser = modelHoaDonTheoUsers.get(position);
        if (modelHoaDonTheoUser == null) {
            return;
        }
        holder.tv_soHD.setText(modelHoaDonTheoUser.getId_hoaDon() + "");
        holder.tv_tenNg.setText(modelHoaDonTheoUser.getHoTen());
        holder.tv_diaChi.setText(modelHoaDonTheoUser.getPhuongXa() + "\t" + modelHoaDonTheoUser.getQuanHuyen() + "\t" + modelHoaDonTheoUser.getTinhThanhPho());
        holder.tv_tinhTrang.setText(modelHoaDonTheoUser.getTinhTrang());

        if (holder.tv_tinhTrang.getText().toString().equals("Chưa giao")) {
            holder.tv_tinhTrang.setTextColor(Color.RED);
        }
        if (holder.tv_tinhTrang.getText().toString().equals("Đang giao")) {
            holder.tv_tinhTrang.setTextColor(Color.BLUE);
        }
        if (holder.tv_tinhTrang.getText().toString().equals("Đã giao")) {
            holder.tv_tinhTrang.setTextColor(Color.GREEN);
        }
        Circle circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence sequence[] = {"Xem hoá đơn", "Xem danh sách chi tiết hoá đơn", "Đánh dấu là đang giao", "Đánh dấu là đã giao"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chức năng ");
                builder.setItems(sequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            AlertDialog.Builder builder_xemHD = new AlertDialog.Builder(v.getContext());
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.dia_log_view_xem_hoa_don, null);
                            builder_xemHD.setView(view);
                            TextView tv_soHoaDon = (TextView) view.findViewById(R.id.ed_tv_TenDonVi);
                            TextView tv_tenNguoiNhan = (TextView) view.findViewById(R.id.ed_tv_DaiDiendv);
                            TextView tv_phuongXa = (TextView) view.findViewById(R.id.ed_tv_diaChidv);
                            TextView tv_quanHuyen = (TextView) view.findViewById(R.id.textView50);
                            TextView tv_tinhThanh = (TextView) view.findViewById(R.id.textView53);
                            TextView tv_soDienThoai = (TextView) view.findViewById(R.id.textView54);
                            TextView tv_diaChiCuThe = (TextView) view.findViewById(R.id.textView55);

                            tv_soHoaDon.setText(modelHoaDonTheoUser.getId_hoaDon() + "");
                            tv_tenNguoiNhan.setText(modelHoaDonTheoUser.getHoTen());
                            tv_phuongXa.setText(modelHoaDonTheoUser.getPhuongXa());
                            tv_quanHuyen.setText(modelHoaDonTheoUser.getQuanHuyen());
                            tv_tinhThanh.setText(modelHoaDonTheoUser.getTinhThanhPho());
                            tv_soDienThoai.setText(modelHoaDonTheoUser.getSoDienThoai());
                            tv_diaChiCuThe.setText(modelHoaDonTheoUser.getDiaChiCuThe());
                            builder_xemHD.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder_xemHD.create();
                            alertDialog.show();
                        } else if (which == 1) {
                            SharedPreferences.Editor editor = context.getSharedPreferences("id_hoaDon", Context.MODE_PRIVATE).edit();
                            editor.putString("key_id_hoaDon", modelHoaDonTheoUser.getId_hoaDon() + "");
                            editor.apply();
                            Log.d("cccccc", modelHoaDonTheoUser.getId_hoaDon() + "");

                            Intent intent = new Intent(context, AdminViewHoaDonChiTiet.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } else if (which == 2) {
                            id_hoaDon = modelHoaDonTheoUser.getId_hoaDon() + "";
                            trangThai_ = "Đang giao";
                            capNhatTrangThai();
                        } else {
                            id_hoaDon = modelHoaDonTheoUser.getId_hoaDon() + "";
                            trangThai_ = "Đã giao";
                            capNhatTrangThai();
                        }
                    }
                });
                AlertDialog alertDialog = builder.show();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelHoaDonTheoUsers != null) {
            return modelHoaDonTheoUsers.size();
        }
        return 0;
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_soHD, tv_tenNg, tv_diaChi, tv_tinhTrang;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_soHD = itemView.findViewById(R.id.textView49);
            tv_tenNg = itemView.findViewById(R.id.textView50);
            tv_diaChi = itemView.findViewById(R.id.textView51);
            progressBar = itemView.findViewById(R.id.spin_kit);
            tv_tinhTrang = itemView.findViewById(R.id.textView76);
        }
    }

    void capNhatTrangThai() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URLss.URL_CAPNHATHOADON, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (modelHoaDonTheoUsers != null) {
                            modelHoaDonTheoUsers.clear();
                        }
                        JsonQuanLyHoaDon jsonQuanLyHoaDon = new JsonQuanLyHoaDon();
                        jsonQuanLyHoaDon.getSanPhamTheoUser(URLss.URL_HOADON, context, QuanLyDonHang.recyclerView);

                        progressBar.setVisibility(View.GONE);
                        CustomToast.makeText(context, "Cập nhật sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                    }
                }, 2000);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("loicapNhapHd", error.toString());
            }
        }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("tinh_trang_", trangThai_);
                map.put("id_HoaDon_", id_hoaDon + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
