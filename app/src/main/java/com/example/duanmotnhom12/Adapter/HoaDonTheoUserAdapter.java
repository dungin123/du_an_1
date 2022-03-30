package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FragmentFromBanGiay.LichSuMuaHangFragment;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.JSON.JsonLichSuMuaHang;
import com.example.duanmotnhom12.Model.ModelHoaDonTheoUser;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.example.duanmotnhom12.activity.ChiTietHoaDon;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoaDonTheoUserAdapter extends RecyclerView.Adapter<HoaDonTheoUserAdapter.ListSanPhamAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelHoaDonTheoUser> modelHoaDonTheoUsers;
    SpinKitView progressBar;
    String id_hd;
    public static TextView tv_tinhTranng;

    public HoaDonTheoUserAdapter(Context context, ArrayList<ModelHoaDonTheoUser> modelHoaDonTheoUsers) {
        this.context = context;
        this.modelHoaDonTheoUsers = modelHoaDonTheoUsers;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_hoa_don_user, parent, false);

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
        tv_tinhTranng.setText(modelHoaDonTheoUser.getTinhTrang());

        if (tv_tinhTranng.getText().toString().equals("Chưa giao")) {
            tv_tinhTranng.setTextColor(Color.RED);
        }
        if (tv_tinhTranng.getText().toString().equals("Đang giao")) {
            tv_tinhTranng.setTextColor(Color.BLUE);
            holder.btn_huy.setVisibility(View.GONE);

        }
        if (tv_tinhTranng.getText().toString().equals("Đã giao")) {
            tv_tinhTranng.setTextColor(Color.GREEN);
            holder.btn_huy.setVisibility(View.GONE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence sequence[] = {"Xemm hoá đơn", "Xem danh chi tiết hoá đơn"};
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
                        } else {
                            SharedPreferences.Editor editor = context.getSharedPreferences("id_hoaDon", Context.MODE_PRIVATE).edit();
                            editor.putString("key_id_hoaDon", modelHoaDonTheoUser.getId_hoaDon() + "");
                            editor.apply();
                            Log.d("cccccc", modelHoaDonTheoUser.getId_hoaDon() + "");

                            Intent intent = new Intent(context, ChiTietHoaDon.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                });
                AlertDialog alertDialog = builder.show();
                alertDialog.show();
                return true;
            }
        });
        holder.btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id+hd", modelHoaDonTheoUser.getId_hoaDon() + "");
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Huỷ đơn hàng");
                builder.setMessage("Bạn có muốn chắc chắn huỷ đơn hàng này(bao gồm các sản phẩm) không?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        id_hd = modelHoaDonTheoUser.getId_hoaDon() + "";
                        xoaHoaDon();
                    }
                });
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
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

        private TextView tv_soHD, tv_tenNg, tv_diaChi;
        private TextView btn_huy;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_soHD = itemView.findViewById(R.id.textView49);
            tv_tenNg = itemView.findViewById(R.id.textView50);
            tv_diaChi = itemView.findViewById(R.id.textView51);
            tv_tinhTranng = itemView.findViewById(R.id.textView76);
            btn_huy = itemView.findViewById(R.id.button9);
            progressBar = itemView.findViewById(R.id.progressBar3);
        }
    }

    void xoaHoaDon() {
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URLss.URL_HUYHOADON + "/" + id_hd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        if (modelHoaDonTheoUsers != null) {
                            modelHoaDonTheoUsers.clear();
                        }
                        if (FromDangNhap.title == 1) {
                            String id_user = FromDangNhap.modelDangNhap.getId_user() + "";
                            Log.d("avv", id_user + "");

                            JsonLichSuMuaHang jsonLichSuMuaHang = new JsonLichSuMuaHang();
                            jsonLichSuMuaHang.getSanPhamTheoUser(URLss.URL_HOADONTHEOUSER + "/" + id_user, context, LichSuMuaHangFragment.recyclerView);

                        } else if (FromDangNhap.title == 2) {
                            JsonLichSuMuaHang jsonLichSuMuaHang = new JsonLichSuMuaHang();
                            jsonLichSuMuaHang.getSanPhamTheoUser(URLss.URL_HOADONTHEOUSER + "/" + FromBanGiay.personid, context, LichSuMuaHangFragment.recyclerView);

                        } else if (FromDangNhap.title == 3) {
                            JsonLichSuMuaHang jsonLichSuMuaHang = new JsonLichSuMuaHang();
                            jsonLichSuMuaHang.getSanPhamTheoUser(URLss.URL_HOADONTHEOUSER + "/" + FromDangNhap.id_, context, LichSuMuaHangFragment.recyclerView);
                        }
                        CustomToast.makeText(context, "Huỷ đơn hàng thành công", (int) CustomToast.LONG, CustomToast.ERROR, true).show();
                        notifyDataSetChanged();
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
                map.put("id_HoaDon_", id_hd + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
