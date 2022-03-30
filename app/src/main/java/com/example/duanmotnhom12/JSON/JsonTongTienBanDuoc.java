package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.ListSanPhamAdapter;
import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.Model.ModelTongTienBan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class JsonTongTienBanDuoc {

    public static ArrayList<ModelTongTienBan> modelTongTienBans = new ArrayList<>();
    public static ModelTongTienBan modelTongTienBan;
    int tienBanRa;

    public void getTongTienBanDuoc(String url, Context context, TextView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelTongTienBans != null) {
                    modelTongTienBans.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelTongTienBan = new ModelTongTienBan(
                                object.getString("tongSoTien")
                        );
                        modelTongTienBan.setTongTienBan(object.getString("tongSoTien"));
                        modelTongTienBans.add(modelTongTienBan);
                    }
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    tienBanRa = Integer.parseInt(modelTongTienBan.getTongTienBan());
                    view.setText(decimalFormat.format(tienBanRa) + "\tđ");
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
