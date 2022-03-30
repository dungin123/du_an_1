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
import com.example.duanmotnhom12.Adapter.ListTopSanPhamAdapter;
import com.example.duanmotnhom12.Model.ModelTopSanPhamBanChay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonTopSanPhamMua {
    public static ListTopSanPhamAdapter  listTopSanPhamAdapter ;
    public static ArrayList<ModelTopSanPhamBanChay> modelTopSanPhamBanChays = new ArrayList<>();
    public static ModelTopSanPhamBanChay modelTopSanPhamBanChay;

    public void getJsonTopSanPhamArray(String url, Context context, RecyclerView recyclerView) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelTopSanPhamBanChays != null) {
                    modelTopSanPhamBanChays.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelTopSanPhamBanChay = new ModelTopSanPhamBanChay(
                                object.getString("tenSanPham_hdct"),
                                object.getInt("SOLUONG")
                        );
                        modelTopSanPhamBanChay.setTenSanPham(object.getString("tenSanPham_hdct"));
                        modelTopSanPhamBanChay.setSoLuong(object.getInt("SOLUONG"));
                        modelTopSanPhamBanChays.add(modelTopSanPhamBanChay);
                    }
                    Log.d("tenSp",modelTopSanPhamBanChay.getTenSanPham());
                    listTopSanPhamAdapter = new ListTopSanPhamAdapter(context, modelTopSanPhamBanChays);
                    recyclerView.setAdapter(listTopSanPhamAdapter);
                    listTopSanPhamAdapter.notifyDataSetChanged();
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
