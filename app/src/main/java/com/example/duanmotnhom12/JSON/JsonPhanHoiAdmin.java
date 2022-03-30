package com.example.duanmotnhom12.JSON;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.ListPhanHoiAdminAdapter;
import com.example.duanmotnhom12.Model.ModelPhanHoiAdmin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonPhanHoiAdmin {
    public static ListPhanHoiAdminAdapter listPhanHoiAdminAdapter;
    public static ArrayList<ModelPhanHoiAdmin> modelPhanHoiAdmins = new ArrayList<>();
    public static ModelPhanHoiAdmin modelPhanHoiAdmin;

    public void getJsonPhanHoiAdminArray(String url, Context context, RecyclerView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelPhanHoiAdmins != null) {
                    modelPhanHoiAdmins.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelPhanHoiAdmin = new ModelPhanHoiAdmin(
                                object.getInt("id_phanHoi_admin"),
                                object.getString("phanHoi_admin"),
                                object.getString("id_user_")
                        );
                        modelPhanHoiAdmin.setId_phanHoi_admin(object.getInt("id_phanHoi_admin"));
                        modelPhanHoiAdmin.setPhanHoi_admin(object.getString("phanHoi_admin"));
                        modelPhanHoiAdmin.setId_user(object.getString("id_user_"));
                        modelPhanHoiAdmins.add(modelPhanHoiAdmin);
                    }
                    listPhanHoiAdminAdapter = new ListPhanHoiAdminAdapter(context, modelPhanHoiAdmins);
                    view.setAdapter(listPhanHoiAdminAdapter);
                    listPhanHoiAdminAdapter.notifyDataSetChanged();
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
