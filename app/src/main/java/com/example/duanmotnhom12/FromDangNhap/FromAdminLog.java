package com.example.duanmotnhom12.FromDangNhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.CheckNetwork.VolleySingleton;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.Model.ModelDangNhap;
import com.example.duanmotnhom12.Model.ModelDangNhapAdmin;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FromAdminLog extends AppCompatActivity {

    EditText ed_user, ed_pass;
    SpinKitView progressBar_dn;
    Button btnLogAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_admin_log);

        Toolbar tb_dangKy = findViewById(R.id.tb_admin_log);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        ed_user = findViewById(R.id.ed_tenDangNhap);
        ed_pass = findViewById(R.id.ed_passWord);
        progressBar_dn = findViewById(R.id.progressBar_dnAdmin);

        btnLogAdmin = findViewById(R.id.btn_DangNhap);

        clickLogADMIN();
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

    void clickLogADMIN() {
        btnLogAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhapRs();
            }
        });

    }

    void dangNhapRs() {
        final String username = ed_user.getText().toString();
        final String password = ed_pass.getText().toString();

        if (TextUtils.isEmpty(username)) {
            ed_user.setError("Vui lòng không để trống");
            ed_user.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ed_pass.setError("Vui lòng không để trống");
            ed_pass.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_LOGINADMIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar_dn.setVisibility(View.GONE);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject user = jsonArray.getJSONObject(i);
                                Log.d("dddd", user.getInt("id_admin") + "");
                                Log.d("dddd", user.getString("name_admin") + "");
                                Log.d("dddd", user.getString("user_admin") + "");

                                FromDangNhap.modelDangNhapAdmin = new ModelDangNhapAdmin(
                                        user.getInt("id_admin"),
                                        user.getString("name_admin"),
                                        user.getString("user_admin")
                                );
                            }
                            //        CustomToast.makeText(getApplicationContext(), "Đăng nhập thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), FromBanGiay.class));
                            FromDangNhap.title = 4;
                        } catch (JSONException e) {
                            CustomToast.makeText(getApplicationContext(), "Email hoặc mật khẩu không chính xác", (int) CustomToast.LONG, CustomToast.ERROR, true).show();
                            e.printStackTrace();
                            Log.d("loiii", e + "");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), ("Kiểm tra tên đăng nhập hoặc mật khẩu?"), Toast.LENGTH_SHORT).show();
                        progressBar_dn.setVisibility(View.GONE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_log", username);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}