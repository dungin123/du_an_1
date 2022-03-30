package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Model.ModelTongTienNhap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class JsonTongTienNhapHang {
    public static ArrayList<ModelTongTienNhap> modelTongTienNhaps = new ArrayList<>();
    public static ModelTongTienNhap modelTongTienNhap;
    public static long tienLai;
    int tienNhap;
    int tienBan;

    public void getJsonTongTienNhapHangArray(String url, Context context, TextView view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelTongTienNhaps != null) {
                    modelTongTienNhaps.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelTongTienNhap = new ModelTongTienNhap(
                                object.getString("tongSoTien")
                        );
                        modelTongTienNhap.setTongTienNhap(object.getString("tongSoTien"));
                        modelTongTienNhaps.add(modelTongTienNhap);
                    }
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    tienNhap = Integer.parseInt(modelTongTienNhap.getTongTienNhap());
                    view.setText(decimalFormat.format(tienNhap) + "\tđ");


                    tienBan = Integer.parseInt(JsonTongTienBanDuoc.modelTongTienBan.getTongTienBan());
                    tienLai = tienBan - tienNhap;
                    Log.d("tongTien", tienLai + "");
                    Log.d("tongTien", tienBan + "");
                    Log.d("tongTien", tienNhap + "");


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
