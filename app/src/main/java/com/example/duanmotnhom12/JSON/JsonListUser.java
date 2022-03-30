package com.example.duanmotnhom12.JSON;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.Adapter.ListSanPhamAdapter;
import com.example.duanmotnhom12.Adapter.ListUserAdapter;
import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.Model.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonListUser {
    public static ListUserAdapter listUserAdapter;
    public static ArrayList<ModelUser> modelUsers = new ArrayList<>();
    public static ModelUser modelUser;

    public void getJsonUserArray(String url, Context context, RecyclerView view) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (modelUsers != null) {
                    modelUsers.clear();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i = i + 1) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        modelUser = new ModelUser(
                                object.getInt("id_user_"),
                                object.getString("hoTen_"),
                                object.getString("email_"),
                                object.getString("matKhau_")

                        );
                        modelUser.setId_user(object.getInt("id_user_"));
                        modelUser.setTen_user(object.getString("hoTen_"));
                        modelUser.setTenDn_user(object.getString("email_"));
                        modelUser.setPass(object.getString("matKhau_"));
                        modelUsers.add(modelUser);
                    }
                    listUserAdapter = new ListUserAdapter(context, modelUsers);
                    view.setAdapter(listUserAdapter);
                    listUserAdapter.notifyDataSetChanged();
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
