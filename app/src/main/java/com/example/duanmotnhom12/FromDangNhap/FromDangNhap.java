package com.example.duanmotnhom12.FromDangNhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FromDangNhap extends AppCompatActivity {
    GoogleSignInButton sign;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    //LogInfb
    CallbackManager callbackManager;
    LoginButton loginButton;
    Button btn_log, button, button_gg;

    public static String id_, name_, email_, first_name;
    TextView tv_dangKy, adminLog;
    public static ModelDangNhap modelDangNhap;
    public static ModelDangNhapAdmin modelDangNhapAdmin;

    TextInputEditText ed_email;
    TextInputEditText ed_pass;
    SpinKitView progressBar_dn;

    public static int title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_dang_nhap);

        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();

        btn_log = findViewById(R.id.btn_dangNhap);
        button = findViewById(R.id.fb);
        button_gg = findViewById(R.id.gg);
        sign = findViewById(R.id.sigin_btn);
        loginButton = findViewById(R.id.login_button);
        tv_dangKy = findViewById(R.id.tv_dangKy);

        adminLog = findViewById(R.id.textView11);

        ed_email = findViewById(R.id.ed_email);
        ed_pass = findViewById(R.id.ed_matKhau);
        progressBar_dn = findViewById(R.id.progressBar_dn);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

        editBtnFb();
        DangNhapGoogle();
        DangNhaplogApi();
        DangKyTaiKhoan();
        adminLog();
    }

    public void DangNhapGoogle() {
        button_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button_gg) {
                    sign.performClick();
                    switch (v.getId()) {
                        case R.id.gg:
                            signIn();
                            break;
                    }
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            title = 2;
            Intent intent = new Intent(getBaseContext(), FromBanGiay.class);
            startActivity(intent);
            Log.d("chayvaoDayChua", "chayvaoDayChua");
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void editBtnFb() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button) {
                    loginButton.performClick();
                    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            layThongTinve();
                            title = 3;
                        }

                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onError(FacebookException error) {

                        }
                    });
                }
            }
        });
    }

    private void layThongTinve() {
        //facebook
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("jsonn", response.getJSONObject().toString());
                try {
                    id_ = object.getString("id");
                    name_ = object.getString("name");
                    email_ = object.getString("email");
                    first_name = object.getString("first_name");

                    Log.d("name", id_);
                    Log.d("name", name_);
                    Log.d("name", first_name + "");
                    Log.d("name", email_ + "");
                    Intent intent = new Intent(FromDangNhap.this, FromBanGiay.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,email,first_name");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    public void DangNhaplogApi() {
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Circle circle = new Circle();
                progressBar_dn.setIndeterminateDrawable(circle);
                progressBar_dn.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   startActivity(new Intent(getApplicationContext(), FromBanGiay.class));
                        dangNhapRs();
                        progressBar_dn.setVisibility(View.GONE);
                    }
                }, 2000);

            }
        });
    }

    public void DangKyTaiKhoan() {
        tv_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FromDangNhap.this, FromDangKy.class));
            }
        });
    }

    void dangNhapRs() {
        final String username = ed_email.getText().toString();
        final String password = ed_pass.getText().toString();

        if (TextUtils.isEmpty(username)) {
            ed_email.setError("Vui lòng không để trống");
            ed_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ed_pass.setError("Vui lòng không để trống");
            ed_pass.requestFocus();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar_dn.setVisibility(View.GONE);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject user = jsonArray.getJSONObject(i);
                                Log.d("dddd", user.getInt("id_user_") + "");
                                Log.d("dddd", user.getString("hoTen_") + "");
                                Log.d("dddd", user.getString("email_") + "");

                                modelDangNhap = new ModelDangNhap(
                                        user.getInt("id_user_"),
                                        user.getString("hoTen_"),
                                        user.getString("email_")
                                );
                            }
                      //      CustomToast.makeText(getApplicationContext(), "Đăng nhập thành công", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), FromBanGiay.class));
                            title = 1;
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
                params.put("email", username);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    //}

    void adminLog() {
        adminLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FromDangNhap.this, FromAdminLog.class);
                startActivity(intent);
            }
        });
    }
}