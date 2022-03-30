package com.example.duanmotnhom12.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.duanmotnhom12.JSON.JsonHoaDonChiTiet;
import com.example.duanmotnhom12.Model.ModelHoaDonChiTiet;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.example.duanmotnhom12.activity.ChiTietHoaDon;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class HoaDonChiTietUserAdapter extends RecyclerView.Adapter<HoaDonChiTietUserAdapter.HoaDonChiTietAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelHoaDonChiTiet> modelHoaDonChiTiets;

    SpinKitView progressBar;
    String id_hdct;

    public HoaDonChiTietUserAdapter(Context context, ArrayList<ModelHoaDonChiTiet> modelHoaDonChiTiets) {
        this.context = context;
        this.modelHoaDonChiTiets = modelHoaDonChiTiets;

    }

    @NonNull
    @NotNull
    @Override
    public HoaDonChiTietAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_hoa_don_chi_tiet, parent, false);

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
        Log.d("ooo", modelHoaDonChiTiet.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tv_3.setText(decimalFormat.format((int) modelHoaDonChiTiet.getDonGia()) + "\tđ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dia_log_view_xem_hoa_don_chi_tiet_user, null);
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
                tv_donGia.setText(decimalFormat_.format((int) modelHoaDonChiTiet.getDonGia()) + "\tđ");
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
                builder.setNegativeButton("Huỷ sản phẩm này", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder_xoa = new AlertDialog.Builder(view.getContext());
                        builder_xoa.setTitle("Bạn có chắc chắn muốn xoá hoá đơn này không?");
                        builder_xoa.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                id_hdct = modelHoaDonChiTiet.getId_hdct() + "";
                                if (HoaDonTheoUserAdapter.tv_tinhTranng.getText().toString().equals("Đang giao")) {
                                    Toast.makeText(context, "Bạn không thể huỷ sản phẩm khi cửa hàng đang giao", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    return;
                                } else if (HoaDonTheoUserAdapter.tv_tinhTranng.getText().toString().equals("Đã giao")) {
                                    Toast.makeText(context, "Sản phẩm này đã được giao rồi", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    return;
                                } else {
                                    xoaHoaDonChiTiet();
                                }
                            }
                        });
                        builder_xoa.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog_ = builder_xoa.create();
                        dialog_.show();
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
            progressBar = itemView.findViewById(R.id.progressBar4);

        }
    }

    void xoaHoaDonChiTiet() {
        Circle circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URLss.URL_HUYHOADONCT + "/" + id_hdct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            progressBar.setVisibility(View.GONE);
                            if (modelHoaDonChiTiets != null) {
                                modelHoaDonChiTiets.clear();
                            }
                            hoaDonChiTietTheoUser();
                            CustomToast.makeText(context, "Huỷ sản phẩm này thành công", (int) CustomToast.LONG, CustomToast.ERROR, true).show();
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
                map.put("id_hdct_", id_hdct + "");
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    void hoaDonChiTietTheoUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("id_hoaDon", MODE_PRIVATE);
        String id_hoaDon = sharedPreferences.getString("key_id_hoaDon", "");
        JsonHoaDonChiTiet jsonHoaDonChiTiet = new JsonHoaDonChiTiet();
        jsonHoaDonChiTiet.getHoaDonChiTiet(URLss.URL_HOADONCHITIETTHEOIDHOADON + "/" + id_hoaDon, context, ChiTietHoaDon.recyclerView);
    }
}
