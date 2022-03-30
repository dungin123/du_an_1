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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.JSON.JsonLoaiSanPham;
import com.example.duanmotnhom12.Model.ModelLoaiSanPham;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import java.util.HashMap;
import java.util.Map;


public class ThemSanPham extends AppCompatActivity {

    String tenSanPham;
    int giaSanPham;
    String nhaSX;
    String ngaySX;
    String trangThai;
    String mieuTa;
    String binhLuan;
    String linkAnh;
    EditText ed_tenSP, ed_nhaSX, ed_ngaySX, ed_trangThai, ed_mieuTa, ed_binhLuan, ed_linkAnh;
    Button btn_themSP;
    Spinner spinner;
    AutoCompleteTextView ed_giaSP;

    String id_loaiSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);

        Toolbar tb_dangKy = findViewById(R.id.tb_themSanPham);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        anhXa();
        themSanPham();
        setSpinnerLoaiSanPham();

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line,Price
        );
        ed_giaSP.setAdapter(stringArrayAdapter);
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

    void themSanPham() {
        btn_themSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themSanPham_();
            }
        });
    }

    void setSpinnerLoaiSanPham() {
        JsonLoaiSanPham jsonLoaiSanPham = new JsonLoaiSanPham();
        jsonLoaiSanPham.getJsonLoaiSanPhamArray(URLss.URL_LOAISANPHAM, getApplicationContext(), spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ModelLoaiSanPham modelLoaiSanPham = JsonLoaiSanPham.modelLoaiSanPhams.get(position);
                id_loaiSanPham = modelLoaiSanPham.getId_loai() + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void anhXa() {
        ed_tenSP = findViewById(R.id.editTextTextPersonName11);
        ed_giaSP = findViewById(R.id.editTextTextPersonName12);
        ed_nhaSX = findViewById(R.id.editTextTextPersonName13);
        ed_ngaySX = findViewById(R.id.editTextTextPersonName14);
        ed_trangThai = findViewById(R.id.editTextTextPersonName15);
        ed_mieuTa = findViewById(R.id.editTextTextPersonName16);
        ed_binhLuan = findViewById(R.id.editTextTextPersonName17);
        ed_linkAnh = findViewById(R.id.editTextTextPersonName18);
        spinner = findViewById(R.id.spinner4);

        btn_themSP = findViewById(R.id.button8);
    }

    void themSanPham_() {
        tenSanPham = ed_tenSP.getText().toString();
        giaSanPham = Integer.parseInt(ed_giaSP.getText().toString());
        nhaSX = ed_nhaSX.getText().toString();
        ngaySX = ed_ngaySX.getText().toString();
        trangThai = ed_trangThai.getText().toString();
        mieuTa = ed_mieuTa.getText().toString();
        binhLuan = ed_binhLuan.getText().toString();
        linkAnh = ed_linkAnh.getText().toString();
        if (TextUtils.isEmpty(tenSanPham)) {
            ed_tenSP.setError("Vui không để trống");
            ed_tenSP.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(giaSanPham + "")) {
            ed_giaSP.setError("Vui không để trống");
            ed_giaSP.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nhaSX)) {
            ed_nhaSX.setError("Vui không để trống");
            ed_nhaSX.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ngaySX)) {
            ed_ngaySX.setError("Vui không để trống");
            ed_ngaySX.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(trangThai)) {
            ed_trangThai.setError("Vui không để trống");
            ed_trangThai.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mieuTa)) {
            ed_mieuTa.setError("Vui không để trống");
            ed_mieuTa.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(binhLuan)) {
            ed_binhLuan.setError("Vui không để trống");
            ed_binhLuan.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(linkAnh)) {
            ed_linkAnh.setError("Vui không để trống");
            ed_linkAnh.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_THEMSANPHAM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ed_tenSP.setText("");
                ed_giaSP.setText("");
                ed_nhaSX.setText("");
                ed_ngaySX.setText("");
                ed_trangThai.setText("");
                ed_mieuTa.setText("");
                ed_binhLuan.setText("");
                ed_linkAnh.setText("");
                CustomToast.makeText(getApplicationContext(), "Thêm sản phẩm thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
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

                map.put("tenSp_", tenSanPham);
                map.put("nhaSX_", nhaSX);
                map.put("imgSP_", linkAnh);
                map.put("giaTienSP_", giaSanPham + "");
                map.put("mieuTaSP_", mieuTa);
                map.put("binhLuanSP_", binhLuan);
                map.put("trang_thai_hang", trangThai);
                map.put("ngay_san_xuat", ngaySX);
                map.put("id_loaiSP_", id_loaiSanPham + "");

                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    private static final String [] Price = new String[]{
            "10000",
            "100000",
            "1000000",
            "10000000",
            //
            "12000",
            "120000",
            "1200000",
            "12000000",
            //
            "13000",
            "130000",
            "1300000",
            "13000000",
            //
            "14000",
            "140000",
            "1400000",
            "14000000",
            //
            "15000",
            "150000",
            "1500000",
            "15000000",
            //
            "16000",
            "160000",
            "1600000",
            "16000000",
            //
            "17000",
            "10000",
            "1700000",
            "18000000",
            //
            "19000",
            "190000",
            "1900000",
            "19000000",
            //
            "20000",
            "200000",
            "2000000",
            "20000000",
            //
            "21000",
            "210000",
            "2100000",
            "21000000",
            //
            "22000",
            "220000",
            "2200000",
            "22000000",
            //
            "23000",
            "230000",
            "2400000",
            "26000000",
            //
            "27000",
            "265000",
            "6660000",
            "23400000",
            //
            "72000",
            "2220000",
            "7200000",
            "23060000",
            //
            "220040",
            "2205500",
            "2202000",
            "23990000",
            //
            "99000",
            "880000",
            "1100000",
            "33000000",
            //
    };
}