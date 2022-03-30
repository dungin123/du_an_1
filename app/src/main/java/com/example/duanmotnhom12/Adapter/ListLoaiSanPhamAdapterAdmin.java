package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.JSON.JsonLoaiSanPham;
import com.example.duanmotnhom12.Model.ModelLoaiSanPham;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.example.duanmotnhom12.activity.DanhSachLoaiSanPham;
import com.example.duanmotnhom12.activity.DanhSachSanPham;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListLoaiSanPhamAdapterAdmin extends RecyclerView.Adapter<ListLoaiSanPhamAdapterAdmin.ListSanPhamAdapterViewHolder> {
    private Context mcontext;
    private ArrayList<ModelLoaiSanPham> modelLoaiSanPhams;
    static SpinKitView progressBar;
    String id_loaiSp;
    String id_sp_update;

    String tenLoai;
    String kieuDang;

    EditText ed_kieuDang;
    EditText ed_tenLoai;

    AlertDialog alertDialog;

    public ListLoaiSanPhamAdapterAdmin(Context mcontext, ArrayList<ModelLoaiSanPham> modelLoaiSanPhams) {
        this.mcontext = mcontext;
        this.modelLoaiSanPhams = modelLoaiSanPhams;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_san_pham_, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelLoaiSanPham modelLoaiSanPham = modelLoaiSanPhams.get(position);
        if (modelLoaiSanPham == null) {
            return;
        }
        holder.tv_tenLoai.setText(modelLoaiSanPham.getTenLoai());
        holder.tv_kieuDang.setText(modelLoaiSanPham.getKieuGioTinh());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence sequence[] = {"Chỉnh sửa loại sản phẩm", "Xoá loại sản phẩm"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chức năng thêm");
                builder.setItems(sequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            id_sp_update = modelLoaiSanPham.getId_loai() + "";
                            Log.d("id_up", id_sp_update);
                            LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.dia_log_view_chinh_sua_loai_san_pham, null);
                            alertDialog = new AlertDialog.Builder(v.getContext())
                                    .setPositiveButton("Lưu", null)
                                    .setNegativeButton("Hủy", null)
                                    .setView(view)
                                    .show();
                            ed_tenLoai = view.findViewById(R.id.editTextTextPersonName4);
                            ed_kieuDang = view.findViewById(R.id.editTextTextPersonName5);

                            ed_tenLoai.setText(modelLoaiSanPham.getTenLoai());
                            ed_kieuDang.setText(modelLoaiSanPham.getKieuGioTinh());

                            Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    id_sp_update = modelLoaiSanPham.getId_loai() + "";
                                    tenLoai = ed_tenLoai.getText().toString();
                                    kieuDang = ed_kieuDang.getText().toString();
                                    CapNhatSanPham();
                                }
                            });
                        } else {
                            AlertDialog.Builder builder_Xoa = new AlertDialog.Builder(v.getContext());
                            builder_Xoa.setTitle("Bạn có chắc chắn muốn xoá loại sản phẩm ? ");
                            builder_Xoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    id_loaiSp = modelLoaiSanPham.getId_loai() + "";
                                    Log.d("idsp", id_loaiSp + "");
                                    xoaSanPham();
                                    dialog.dismiss();
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
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelLoaiSanPhams != null) {
            return modelLoaiSanPhams.size();
        }
        return 0;
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_tenLoai, tv_kieuDang;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_tenLoai = itemView.findViewById(R.id.textView93);
            tv_kieuDang = itemView.findViewById(R.id.textView94);

            progressBar = itemView.findViewById(R.id.progressBar3_loaiSp);

        }
    }

    void xoaSanPham() {
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URLss.URL_XOAlOAISANPHAM + "/" + id_loaiSp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        CustomToast.makeText(mcontext, "Xoá loại sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                        if (modelLoaiSanPhams != null) {
                            modelLoaiSanPhams.clear();
                        }
                        JsonLoaiSanPham jsonLoaiSanPham = new JsonLoaiSanPham();
                        jsonLoaiSanPham.getJsonLoaiSanPhamArray_(URLss.URL_LOAISANPHAM, mcontext, DanhSachLoaiSanPham.recyclerView);
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
                map.put("id_sanPham_", id_loaiSp + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        requestQueue.add(stringRequest);
    }

    void CapNhatSanPham() {
        if (TextUtils.isEmpty(tenLoai)) {
            ed_tenLoai.setError("Vui lòng không bỏ trống");
            ed_tenLoai.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(kieuDang)) {
            ed_kieuDang.setError("Vui lòng không bỏ trống");
            ed_kieuDang.requestFocus();
            return;
        }
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URLss.URL_CAPNHATLOAISANPHAM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (modelLoaiSanPhams != null) {
                    modelLoaiSanPhams.clear();
                }
                JsonLoaiSanPham jsonLoaiSanPham = new JsonLoaiSanPham();
                jsonLoaiSanPham.getJsonLoaiSanPhamArray_(URLss.URL_LOAISANPHAM, mcontext, DanhSachLoaiSanPham.recyclerView);

                CustomToast.makeText(mcontext, "Cập nhật loại sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                alertDialog.dismiss();
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
                map.put("ten_loaiSP_", tenLoai);
                map.put("gioi_tinhLSp_", kieuDang);
                map.put("id_loaiSP_", id_sp_update + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        requestQueue.add(stringRequest);
    }
}
