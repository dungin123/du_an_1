package com.example.duanmotnhom12.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.JSON.JsonPhuongXa;
import com.example.duanmotnhom12.JSON.JsonQuanHuyen;
import com.example.duanmotnhom12.JSON.JsonTinh;
import com.example.duanmotnhom12.JSON.JsonSanPham;
import com.example.duanmotnhom12.Model.ModelPhuongXa;
import com.example.duanmotnhom12.Model.ModelQuanHuyen;
import com.example.duanmotnhom12.Model.ModelTinhThanh;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.duanmotnhom12.FromBanGiay.FromBanGiay.MY_PREFS_NAME;

public class ThanhToanGioHang extends AppCompatActivity {

    Spinner spinner_tinhThanh, spinner_quanHuyen, spinner_phuongXa;
    Button btn_ThanhToan;
    EditText ed_hoTen, ed_soDienThoai, ed_diaChi, cmt;
    String tenTinh;
    String tenHuyen;
    String phuongxa;
    String soCmt;
    String key;
    int value;
    ProgressBar progressBar;

    TextView tv_tinhTrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_gio_hang);


        Toolbar tb_dangKy = findViewById(R.id.hoaDon);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_ThanhToan = findViewById(R.id.button5);
        ed_hoTen = findViewById(R.id.editTextTextPersonName21);
        ed_soDienThoai = findViewById(R.id.editTextTextPersonName2);
        ed_diaChi = findViewById(R.id.editTextTextPassword);
        cmt = findViewById(R.id.editTextTextPersonName);

        tv_tinhTrang = findViewById(R.id.textView74);

        callApiTinhThanh();
        thongTinThanhToan();

        if (FromDangNhap.title == 1) {
            ed_hoTen.setText(FromDangNhap.modelDangNhap.getHoTen_user());
        } else if (FromDangNhap.title == 2) {
            ed_hoTen.setText(FromBanGiay.personName);
        } else if (FromDangNhap.title == 3) {
            ed_hoTen.setText(FromDangNhap.name_);
        }
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

    public void callApiTinhThanh() {
        spinner_tinhThanh = findViewById(R.id.spinner);
        JsonTinh jsonTinh = new JsonTinh();
        jsonTinh.getJsonArray(URLss.URL_TINH, getApplicationContext(), spinner_tinhThanh);
        spinner_tinhThanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final ModelTinhThanh modelTinhThanh = JsonTinh.modelTinhThanhs.get(position);
                String srcTenTinh = modelTinhThanh.getId() + "";
                tenTinh = modelTinhThanh.getTitle() + "";
                if (JsonQuanHuyen.modelQuanHuyens != null) {
                    JsonQuanHuyen.modelQuanHuyens.clear();
                    spinner_quanHuyen = findViewById(R.id.spinner2);
                    JsonQuanHuyen jsonQuanHuyen = new JsonQuanHuyen();
                    jsonQuanHuyen.getJsonArrayQuanHuyen(URLss.URL_HUYEN + srcTenTinh + "/district", getApplicationContext(), spinner_quanHuyen);
                }
                spinner_quanHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final ModelQuanHuyen modelQuanHuyen = JsonQuanHuyen.modelQuanHuyens.get(position);
                        String srcTenHuyen = modelQuanHuyen.getId_huyen() + "";
                        tenHuyen = modelQuanHuyen.getTitle() + "";
                        if (JsonPhuongXa.modelPhuongXas != null) {
                            JsonPhuongXa.modelPhuongXas.clear();
                            spinner_phuongXa = findViewById(R.id.spinner3);
                            JsonPhuongXa jsonPhuongXa = new JsonPhuongXa();
                            jsonPhuongXa.getJsonArrayPhuongXa(URLss.URL_XA + srcTenHuyen + "/ward", getApplicationContext(), spinner_phuongXa);
                        }
                        spinner_phuongXa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                ModelPhuongXa modelPhuongXa = JsonPhuongXa.modelPhuongXas.get(position);
                                phuongxa = modelPhuongXa.getTitle();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void thongTinThanhToan() {
        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = ed_hoTen.getText().toString();
                String soDienThoai = ed_soDienThoai.getText().toString();
                String diaChi = ed_diaChi.getText().toString();
                String tinhTrang = tv_tinhTrang.getText().toString();
                String soCmt = cmt.getText().toString();

                if (TextUtils.isEmpty(hoTen)) {
                    ed_hoTen.setError("Vui không để trống");
                    ed_hoTen.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(soDienThoai)) {
                    ed_soDienThoai.setError("Vui không để trống");
                    ed_soDienThoai.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(diaChi)) {
                    ed_diaChi.setError("Vui không để trống");
                    ed_diaChi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(soCmt)) {
                    cmt.setError("Vui không để trống");
                    cmt.requestFocus();
                    return;
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_THEMHOADON, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Hoá đơn chi tiết
                        progressBar.setVisibility(View.GONE);
                        getHoaDonChiTiet();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject object = jsonObject.getJSONObject("result");
                            JSONArray jsonArray = object.names();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                key = jsonArray.getString(i);
                                value = object.getInt(key);
                                Log.d("ax", value + "");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        CustomToast.makeText(getApplicationContext(), "Bạn đã đặt hàng thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                        startActivity(new Intent(getApplicationContext(), FromBanGiay.class));
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @org.jetbrains.annotations.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("hoTenNn_Sp_", hoTen);
                        params.put("sdt_hd_", soDienThoai);
                        params.put("tinh_thanh_pho", tenTinh);
                        params.put("quan_huyen", tenHuyen);
                        params.put("phuong_xa", phuongxa);
                        params.put("dia_chi_cuThe", diaChi);
                        params.put("tinh_trang_", tinhTrang);
                        params.put("soCMT", soCmt);
                        if (FromDangNhap.title == 1) {
                            params.put("id_user_", FromDangNhap.modelDangNhap.getId_user() + "");
                        } else if (FromDangNhap.title == 2) {
                            params.put("id_user_", FromBanGiay.personid + "");
                        } else if (FromDangNhap.title == 3) {
                            params.put("id_user_", FromDangNhap.id_ + "");
                        }
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ThanhToanGioHang.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    public void getHoaDonChiTiet() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_THEMHOADONCHITIET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.d("ok233", "123");
                    Log.d("RESPONSE", response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CustomToast.makeText(getApplicationContext(), error.toString(), (int) CustomToast.LONG, CustomToast.ERROR, true).show();

                //  Log.d("loi", error.toString() + "");
            }
        }) {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Lấy ngày giờ hệ thống
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String date_ = simpleDateFormat.format(date);
                //
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < FromBanGiay.modelGioHangs.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("tenSanPham_hdct", FromBanGiay.modelGioHangs.get(i).getTen_sanPham_gioHang());
                        jsonObject.put("donGiaNn_Sp_", FromBanGiay.modelGioHangs.get(i).getGia_tien_gioHang());
                        jsonObject.put("soLuogNn_Sp_", FromBanGiay.modelGioHangs.get(i).getSo_luong_gioHang());
                        jsonObject.put("ngayDh_sp", date_);
                        jsonObject.put("mau_giay", FromBanGiay.modelGioHangs.get(i).getMauSac());
                        jsonObject.put("size_giay", FromBanGiay.modelGioHangs.get(i).getSizeGiay());
                        jsonObject.put("id_HoaDon_", value);
                        jsonObject.put("id_sanPham_", FromBanGiay.modelGioHangs.get(i).getId_sanPham_gioHang());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("result", jsonArray.toString());
                FromBanGiay.modelGioHangs.clear();
                SharedPreferences settings = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
                settings.edit().remove("tasklist").commit();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                Gson gson = new Gson();
                String json = gson.toJson(FromBanGiay.modelGioHangs);
                editor.putString("tasklist", json);
                editor.apply();
                return hashMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ThanhToanGioHang.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS));
        requestQueue.add(stringRequest);
    }
}