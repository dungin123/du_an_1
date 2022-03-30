package com.example.duanmotnhom12.FromDangNhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.CheckNetwork.VolleySingleton;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FromDangKy extends AppCompatActivity {

    Button btn_DangKy;
    TextInputEditText ed_name, ed_email, ed_matKhau, ed_xacNhan;
    SpinKitView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_dang_ky);

        Toolbar tb_dangKy = findViewById(R.id.tb_dangKy);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        anhXa();
        dangKyTaiKhoan();

    }

    //back
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

    public void anhXa() {
        btn_DangKy = findViewById(R.id.btn_DangKy);
        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email_);
        ed_matKhau = findViewById(R.id.ed_matKhau_);
        ed_xacNhan = findViewById(R.id.ed_xacNhan_);
        progressBar = findViewById(R.id.progressBar);
    }

    public void dangKyTaiKhoan() {
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Circle circle = new Circle();
                progressBar.setIndeterminateDrawable(circle);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        registerUser();
                    }
                }, 2000);
            }
        });
    }

    private void registerUser() {
        final String username = ed_name.getText().toString().trim();
        final String email = ed_email.getText().toString().trim();
        final String password = ed_matKhau.getText().toString().trim();
        final String password_XacNhan = ed_xacNhan.getText().toString().trim();


        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            ed_name.setError("Vui lòng không để trống tên");
            ed_name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            ed_email.setError("Vui lòng không để trống email");
            ed_email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed_email.setError("Email không hợp lệ");
            ed_email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ed_xacNhan.setError("Vui lòng không để trống mật khẩu");
            ed_xacNhan.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            String message = obj.getString("message");
                            if (message.equals("Đăng ký tài khoản thành công")) {
                                startActivity(new Intent(FromDangKy.this, FromDangNhap.class));
                                finish();
                                CustomToast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                            } else {
                                CustomToast.makeText(getApplicationContext(), message, (int) CustomToast.LONG, CustomToast.ERROR, true).show();
                                Log.d("mm", message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FromDangKy.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("ero", error.toString().trim());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", username);
                params.put("email", email);
                params.put("password", password);
                params.put("passwordConfirm", password_XacNhan);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}