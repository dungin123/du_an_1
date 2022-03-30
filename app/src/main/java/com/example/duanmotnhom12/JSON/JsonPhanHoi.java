package com.example.duanmotnhom12.JSON;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.ListPhanHoiAdapter;
import com.example.duanmotnhom12.Adapter.ListSanPhamAdapter;
import com.example.duanmotnhom12.Model.ModelPhanHoi;
import com.example.duanmotnhom12.Model.ModelSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonPhanHoi {
    public static ListPhanHoiAdapter listPhanHoiAdapter;
    public static ArrayList<ModelPhanHoi> modelPhanHois = new ArrayList<>();
    public static ModelPhanHoi modelPhanHoi;

    public void getJsonPhanHoiArray(String url, Context context, RecyclerView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelPhanHois != null) {
                    modelPhanHois.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelPhanHoi = new ModelPhanHoi(
                                object.getInt("id_phanHoi"),
                                object.getString("trang_thai"),
                                object.getString("phan_hoi"),
                                object.getString("id_user_")
                        );
                        modelPhanHoi.setId_phanHoi(object.getInt("id_phanHoi"));
                        modelPhanHoi.setTrangThai(object.getString("trang_thai"));
                        modelPhanHoi.setPhanHoi(object.getString("phan_hoi"));
                        modelPhanHoi.setId_user(object.getString("id_user_"));
                        modelPhanHois.add(modelPhanHoi);
                    }
                    listPhanHoiAdapter = new ListPhanHoiAdapter(context, modelPhanHois);
                    view.setAdapter(listPhanHoiAdapter);
                    listPhanHoiAdapter.notifyDataSetChanged();
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
