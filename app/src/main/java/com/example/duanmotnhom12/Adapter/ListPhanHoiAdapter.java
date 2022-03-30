package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.Model.ModelPhanHoi;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListPhanHoiAdapter extends RecyclerView.Adapter<ListPhanHoiAdapter.ListSanPhamAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelPhanHoi> modelPhanHois;

    String txt_phanHoi;
    EditText ed_phanHoi;
    String id_userPh ;

    public ListPhanHoiAdapter(Context context, ArrayList<ModelPhanHoi> modelPhanHois) {
        this.context = context;
        this.modelPhanHois = modelPhanHois;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phan_hoi, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelPhanHoi modelPhanHoi = modelPhanHois.get(position);
        if (modelPhanHoi == null) {
            return;
        }
        holder.tv_maPhanHoi.setText(modelPhanHoi.getId_phanHoi() + "");
        holder.tv_trangThai.setText(modelPhanHoi.getTrangThai());
        holder.tv_phanHoi.setText(modelPhanHoi.getPhanHoi());
        holder.tv_iduser.setText(modelPhanHoi.getId_user() + "");

        if (holder.tv_trangThai.getText().toString().equals("Không tốt")) {
            holder.imageView.setImageResource(R.drawable.icons8sad24_);
        } else if (holder.tv_trangThai.getText().toString().equals("Bình thường"))
            holder.imageView.setImageResource(R.drawable.icons8warningshield24_);
        else {
            holder.imageView.setImageResource(R.drawable.icons8heart24);
        }

        holder.img_phanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id_PhanHoi", modelPhanHoi.getId_user() + "");
                id_userPh = modelPhanHoi.getId_user() + "";
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dia_log_gui_laiphanhoichouser, null);
                androidx.appcompat.app.AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                        .setPositiveButton("Gửi đi", null)
                        .setNegativeButton("Hủy", null)
                        .setCancelable(false)
                        .setView(view)
                        .show();
                ed_phanHoi = view.findViewById(R.id.editTextTextPersonName32);
                Button button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt_phanHoi = ed_phanHoi.getText().toString();
                        guiTraPhanHoiUser();
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (modelPhanHois != null) {
            return modelPhanHois.size();
        }
        return 0;
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_maPhanHoi, tv_trangThai, tv_phanHoi, tv_iduser;
        private ImageView imageView, img_phanHoi;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_maPhanHoi = itemView.findViewById(R.id.textView27);
            tv_trangThai = itemView.findViewById(R.id.textView30);
            tv_phanHoi = itemView.findViewById(R.id.tv_trangThai);
            tv_iduser = itemView.findViewById(R.id.textView12);
            imageView = itemView.findViewById(R.id.imageView15);
            img_phanHoi = itemView.findViewById(R.id.imageView16);
        }
    }

    void guiTraPhanHoiUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_ADMINPHANHOI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                CustomToast.makeText(context, "Gửi trả thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                ed_phanHoi.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CustomToast.makeText(context, "Gửi trả thất bại", (int) CustomToast.LONG, CustomToast.ERROR, true).show();

            }
        }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                try {
                    map.put("phanHoi_admin", txt_phanHoi);
                    map.put("id_user_", id_userPh + "");
                    map.put("id_admin_", FromDangNhap.modelDangNhap.getId_user() + "");
                } catch (Exception e) {
                    e.getStackTrace();
                }
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
