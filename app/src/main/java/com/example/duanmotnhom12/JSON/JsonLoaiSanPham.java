package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.ListLoaiSanPhamAdapterAdmin;
import com.example.duanmotnhom12.Adapter.SpinnerAdapterLoaiSanPham;
import com.example.duanmotnhom12.Model.ModelLoaiSanPham;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonLoaiSanPham {
    public static ArrayList<ModelLoaiSanPham> modelLoaiSanPhams = new ArrayList<>();
    public static ModelLoaiSanPham modelLoaiSanPham;
    public static ModelLoaiSanPham modelLoaiSanPham_;
    SpinnerAdapterLoaiSanPham spinnerAdapterLoaiSanPham;
    ListLoaiSanPhamAdapterAdmin listLoaiSanPhamAdapterAdmin;
    public static ArrayList<ModelLoaiSanPham> modelLoaiSanPhams_ = new ArrayList<>();

    public void getJsonLoaiSanPhamArray(String url, Context context, Spinner spinner) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelLoaiSanPhams != null) {
                    modelLoaiSanPhams.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelLoaiSanPham = new ModelLoaiSanPham(
                                object.getInt("id_loaiSP_"),
                                object.getString("ten_loaiSP_"),
                                object.getString("gioi_tinhLSp_")

                        );
                        modelLoaiSanPham.setId_loai(object.getInt("id_loaiSP_"));
                        modelLoaiSanPham.setTenLoai(object.getString("ten_loaiSP_"));
                        modelLoaiSanPham.setKieuGioTinh(object.getString("gioi_tinhLSp_"));
                        modelLoaiSanPhams.add(modelLoaiSanPham);
                    }
                    spinnerAdapterLoaiSanPham = new SpinnerAdapterLoaiSanPham(modelLoaiSanPhams);
                    spinner.setAdapter(spinnerAdapterLoaiSanPham);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public void getJsonLoaiSanPhamArray_(String url, Context context, RecyclerView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelLoaiSanPhams_ != null) {
                    modelLoaiSanPhams_.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelLoaiSanPham_ = new ModelLoaiSanPham(
                                object.getInt("id_loaiSP_"),
                                object.getString("ten_loaiSP_"),
                                object.getString("gioi_tinhLSp_")

                        );
                        modelLoaiSanPham_.setId_loai(object.getInt("id_loaiSP_"));
                        modelLoaiSanPham_.setTenLoai(object.getString("ten_loaiSP_"));
                        modelLoaiSanPham_.setKieuGioTinh(object.getString("gioi_tinhLSp_"));
                        modelLoaiSanPhams_.add(modelLoaiSanPham_);
                    }
                    listLoaiSanPhamAdapterAdmin = new ListLoaiSanPhamAdapterAdmin(context, modelLoaiSanPhams_);
                    view.setAdapter(listLoaiSanPhamAdapterAdmin);
                    listLoaiSanPhamAdapterAdmin.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}
