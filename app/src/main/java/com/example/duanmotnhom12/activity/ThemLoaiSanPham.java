package com.example.duanmotnhom12.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;

import java.util.HashMap;
import java.util.Map;

public class ThemLoaiSanPham extends AppCompatActivity {

    EditText ed_tenHang, ed_kieuDang;
    Button btnThem;
    String tenHang;
    String kieuDang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_san_pham);

        Toolbar tb_dangKy = findViewById(R.id.tb_themLoaiSanPham);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        ed_tenHang = findViewById(R.id.editTextTextPersonName19);
        ed_kieuDang = findViewById(R.id.editTextTextPersonName20);
        btnThem = findViewById(R.id.button10);

        btnThemLoaiSanPham();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void btnThemLoaiSanPham() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themLoaiSanPham();
            }
        });
    }

    void themLoaiSanPham() {
        tenHang = ed_tenHang.getText().toString();
        kieuDang = ed_kieuDang.getText().toString();

        if (TextUtils.isEmpty(tenHang)) {
            ed_tenHang.setError("Vui không để trống");
            ed_tenHang.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(kieuDang)) {
            ed_kieuDang.setError("Vui không để trống");
            ed_kieuDang.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_THEMlOAISANPHAM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ed_tenHang.setText("");
                ed_kieuDang.setText("");
                CustomToast.makeText(getApplicationContext(), "Thêm loại sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("ten_loaiSP_", tenHang);
                map.put("gioi_tinhLSp_", kieuDang);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}