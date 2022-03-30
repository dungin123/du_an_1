package com.example.duanmotnhom12.JSON;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.ListSanPhamAdapter;
import com.example.duanmotnhom12.Model.ModelSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonSanPham {
    public static ListSanPhamAdapter listSanPhamAdapter;
    public static ArrayList<ModelSanPham> modelSanPhams = new ArrayList<>();
    public  static  ModelSanPham modelSanPham ;

    public void getJsonSanPhamArray(String url, Context context, RecyclerView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelSanPhams!=null){
                    modelSanPhams.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                         modelSanPham = new ModelSanPham(
                                object.getInt("id_sanPham_"),
                                object.getString("tenSp_"),
                                object.getString("nhaSX_"),
                                object.getString("imgSP_"),
                                object.getString("giaTienSP_"),
                                object.getString("mieuTaSP_"),
                                object.getString("binhLuanSP_"),
                                object.getString("trang_thai_hang"),
                                object.getString("ngay_san_xuat"),
                                object.getInt("id_loaiSP_")
                        );
                        modelSanPham.setId_sanPham_(object.getInt("id_sanPham_"));
                        modelSanPham.setTenSp_(object.getString("tenSp_"));
                        modelSanPham.setNhaSX_(object.getString("nhaSX_"));
                        modelSanPham.setImgSP_(object.getString("imgSP_"));
                        modelSanPham.setGiaTienSP_(object.getString("giaTienSP_"));
                        modelSanPham.setMieuTaSP_(object.getString("mieuTaSP_"));
                        modelSanPham.setBinhLuanSP_(object.getString("binhLuanSP_"));
                        modelSanPham.setTrang_thai_hang(object.getString("trang_thai_hang"));
                        modelSanPham.setNgay_san_xuat(object.getString("ngay_san_xuat"));
                        modelSanPham.setId_loaiSP_(object.getInt("id_loaiSP_"));
                        modelSanPhams.add(modelSanPham);
                    }
                    listSanPhamAdapter = new ListSanPhamAdapter(context, modelSanPhams);
                    view.setAdapter(listSanPhamAdapter);
                    listSanPhamAdapter.notifyDataSetChanged();
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
