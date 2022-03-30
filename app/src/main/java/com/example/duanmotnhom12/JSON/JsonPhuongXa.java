package com.example.duanmotnhom12.JSON;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.SpinnerAdapterPhuongXa;
import com.example.duanmotnhom12.Model.ModelPhuongXa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonPhuongXa {
    public static ArrayList<ModelPhuongXa> modelPhuongXas = new ArrayList<>();

    public void getJsonArrayPhuongXa(String url, Context context, Spinner spinner) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID_ = 0;
                    String Title_ = "";
                    String STT_ = "";
                    int TinhThanhID_ = 0;
                    String TinhThanhTitle_ = "";
                    int QuanHuyenID_ = 0;
                    String QuanHuyenTitle_ = "";

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID_ = jsonObject.getInt("ID");
                            Title_ = jsonObject.getString("Title");
                            STT_ = jsonObject.getString("STT");
                            TinhThanhID_ = jsonObject.getInt("TinhThanhID");
                            TinhThanhTitle_ = jsonObject.getString("TinhThanhTitle");
                            QuanHuyenID_ = jsonObject.getInt("QuanHuyenID");
                            QuanHuyenTitle_ = jsonObject.getString("QuanHuyenTitle");
                            modelPhuongXas.add(new ModelPhuongXa(ID_, Title_, STT_, TinhThanhID_, TinhThanhTitle_, QuanHuyenID_, QuanHuyenTitle_));

                            SpinnerAdapterPhuongXa spinnerAdapterPhuongXa = new SpinnerAdapterPhuongXa(modelPhuongXas);
                            spinner.setAdapter(spinnerAdapterPhuongXa);
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
