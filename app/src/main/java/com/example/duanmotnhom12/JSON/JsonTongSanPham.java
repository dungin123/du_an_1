package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Model.ModelTongSanPham;
import com.example.duanmotnhom12.Model.ModelTongTienBan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonTongSanPham {
    public static ArrayList<ModelTongSanPham> tongSanPhams = new ArrayList<>();
    public static ModelTongSanPham modelTongSanPham;

    public void getTongSanPham(String url, Context context, TextView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (tongSanPhams != null) {
                    tongSanPhams.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelTongSanPham = new ModelTongSanPham(
                                object.getString("tongSanPham")
                        );
                        modelTongSanPham.setTongSanPham(object.getString("tongSanPham"));
                        tongSanPhams.add(modelTongSanPham);
                    }
                    view.setText(modelTongSanPham.getTongSanPham() + "");
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
