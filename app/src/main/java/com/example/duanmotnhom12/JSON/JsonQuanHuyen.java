package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.SpinnerAdapterQuanHuyen;
import com.example.duanmotnhom12.Model.ModelQuanHuyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonQuanHuyen {
    public static ArrayList<ModelQuanHuyen> modelQuanHuyens = new ArrayList<>();

    public void getJsonArrayQuanHuyen(String url, Context context, Spinner spinner) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int id_huyen_;
                    String Title_;
                    String STT_;
                    int TinhThanhID_;
                    String TinhThanhTitle_;

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id_huyen_ = jsonObject.getInt("ID");
                            Title_ = jsonObject.getString("Title");
                            STT_ = jsonObject.getString("STT");
                            TinhThanhID_ = jsonObject.getInt("TinhThanhID");
                            TinhThanhTitle_ = jsonObject.getString("TinhThanhTitle");
                            modelQuanHuyens.add(new ModelQuanHuyen(id_huyen_, Title_, STT_, TinhThanhID_, TinhThanhTitle_));
                            SpinnerAdapterQuanHuyen spinnerAdapterQuanHuyen = new SpinnerAdapterQuanHuyen(modelQuanHuyens);
                            spinner.setAdapter(spinnerAdapterQuanHuyen);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }
}
