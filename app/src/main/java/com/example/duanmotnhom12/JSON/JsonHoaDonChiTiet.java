package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.HoaDonChiTietUserAdapter;
import com.example.duanmotnhom12.Model.ModelHoaDonChiTiet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonHoaDonChiTiet {
    public static HoaDonChiTietUserAdapter hoaDonChiTietUserAdapter;
    public static ArrayList<ModelHoaDonChiTiet> modelHoaDonChiTiets = new ArrayList<>();
    public static ModelHoaDonChiTiet modelHoaDonChiTiet ;
    public void getHoaDonChiTiet(String url , Context context , RecyclerView view){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelHoaDonChiTiets!=null){
                    modelHoaDonChiTiets.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0 ; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        modelHoaDonChiTiet = new ModelHoaDonChiTiet(
                                jsonObject.getInt("id_hdct_"),
                                jsonObject.getString("tenSanPham_hdct"),
                                jsonObject.getDouble("donGiaNn_Sp_"),
                                jsonObject.getInt("soLuogNn_Sp_"),
                                jsonObject.getString("ngayDh_sp"),
                                jsonObject.getString("mau_giay"),
                                jsonObject.getString("size_giay"),
                                jsonObject.getInt("id_HoaDon_"),
                                jsonObject.getInt("id_sanPham_")
                        );
                      modelHoaDonChiTiet.setId_hdct(jsonObject.getInt("id_hdct_"));
                      modelHoaDonChiTiet.setTenSanPham(jsonObject.getString("tenSanPham_hdct"));
                      modelHoaDonChiTiet.setDonGia(jsonObject.getDouble("donGiaNn_Sp_"));
                      modelHoaDonChiTiet.setSoLuong(jsonObject.getInt("soLuogNn_Sp_"));
                      modelHoaDonChiTiet.setNgayHoaDon(jsonObject.getString("ngayDh_sp"));
                      modelHoaDonChiTiet.setMauSac(jsonObject.getString("mau_giay"));
                      modelHoaDonChiTiet.setSize(jsonObject.getString("size_giay"));
                      modelHoaDonChiTiet.setIdHoaDon(jsonObject.getInt("id_HoaDon_"));
                      modelHoaDonChiTiet.setIdSanPham(jsonObject.getInt("id_sanPham_"));
                        Log.d("aaacs",jsonObject.getString("tenSanPham_hdct"));
                        modelHoaDonChiTiets.add(modelHoaDonChiTiet);
                    }
                    hoaDonChiTietUserAdapter = new HoaDonChiTietUserAdapter(context, modelHoaDonChiTiets);
                    view.setAdapter(hoaDonChiTietUserAdapter);
                    hoaDonChiTietUserAdapter.notifyDataSetChanged();
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
