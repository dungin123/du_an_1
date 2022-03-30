package com.example.duanmotnhom12.JSON;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.AdminViewHoaDonAdapter;
import com.example.duanmotnhom12.Model.ModelHoaDonTheoUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonQuanLyHoaDon {
    public static AdminViewHoaDonAdapter adminViewHoaDonAdapter;
    public static ArrayList<ModelHoaDonTheoUser> modelHoaDonTheoUsers = new ArrayList<>();
    public static ModelHoaDonTheoUser modelHoaDonTheoUser;


    public void getSanPhamTheoUser(String url, Context context, RecyclerView view ) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelHoaDonTheoUsers != null) {
                    modelHoaDonTheoUsers.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelHoaDonTheoUser = new ModelHoaDonTheoUser(
                                object.getInt("id_HoaDon_"),
                                object.getString("hoTenNn_Sp_"),
                                object.getString("sdt_hd_"),
                                object.getString("tinh_thanh_pho"),
                                object.getString("quan_huyen"),
                                object.getString("phuong_xa"),
                                object.getString("dia_chi_cuThe"),
                                object.getString("tinh_trang_"),
                                object.getString("id_user_")
                        );
                        modelHoaDonTheoUser.setId_hoaDon(object.getInt("id_HoaDon_"));
                        modelHoaDonTheoUser.setHoTen(object.getString("hoTenNn_Sp_"));
                        modelHoaDonTheoUser.setSoDienThoai(object.getString("sdt_hd_"));
                        modelHoaDonTheoUser.setTinhThanhPho(object.getString("tinh_thanh_pho"));
                        modelHoaDonTheoUser.setQuanHuyen(object.getString("quan_huyen"));
                        modelHoaDonTheoUser.setPhuongXa(object.getString("phuong_xa"));
                        modelHoaDonTheoUser.setDiaChiCuThe(object.getString("dia_chi_cuThe"));
                        modelHoaDonTheoUser.setTinhTrang(object.getString("tinh_trang_"));
                        modelHoaDonTheoUser.setId_user(object.getString("id_user_"));
                        modelHoaDonTheoUsers.add(modelHoaDonTheoUser);
                    }
                    adminViewHoaDonAdapter = new AdminViewHoaDonAdapter(context, modelHoaDonTheoUsers);
                    view.setAdapter(adminViewHoaDonAdapter);
                    adminViewHoaDonAdapter.notifyDataSetChanged();
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
