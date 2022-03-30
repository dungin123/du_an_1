package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.SpinnerAdapterTinhThanh;
import com.example.duanmotnhom12.Model.ModelTinhThanh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonTinh {
    public static ArrayList<ModelTinhThanh> modelTinhThanhs = new ArrayList<>();

    public void getJsonArray(String url, Context context, Spinner spinner) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("LtsItem");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ModelTinhThanh modelTinhThanh = new ModelTinhThanh(
                                jsonObject.getInt("ID"),
//                                jsonObject.getString("Type"),
                             //   jsonObject.getString("SolrID"),
                                jsonObject.getString("Title"),
                                jsonObject.getString("STT"));
//                                jsonObject.getString("Created"),
//                                jsonObject.getString("Updated"));
//                                jsonObject.getString("TotalDoanhNghiep"));
                        modelTinhThanh.setId(jsonObject.getInt("ID"));
                        // modelTinhThanh.setType(jsonObject.getString("Type"));
                  //      modelTinhThanh.setSolrID(jsonObject.getString("SolrID"));
                        modelTinhThanh.setTitle(jsonObject.getString("Title"));
                        modelTinhThanh.setSTT(jsonObject.getString("STT"));
                        //  modelTinhThanh.setCreated(jsonObject.getString("Created"));
                        //  modelTinhThanh.setUpdated(jsonObject.getString("Updated"));
//                        modelTinhThanh.setTotalDoanhNghiep(jsonObject.getString("TotalDoanhNghiep"));
                        modelTinhThanhs.add(modelTinhThanh);
                    }
                    SpinnerAdapterTinhThanh customAdapter = new SpinnerAdapterTinhThanh(modelTinhThanhs);
                    spinner.setAdapter(customAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}