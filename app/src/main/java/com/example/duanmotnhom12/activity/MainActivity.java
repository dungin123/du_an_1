package com.example.duanmotnhom12.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.duanmotnhom12.CheckNetwork.AppUtil;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;

public class MainActivity extends AppCompatActivity {
    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinKitView = findViewById(R.id.progressBar);

        loadSplashScreen();
    }

    public void loadSplashScreen() {
        Circle circle = new Circle();
        spinKitView.setIndeterminateDrawable(circle);
        if (AppUtil.isNetWorkAvailable(this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, FromDangNhap.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }, 4800);
        } else {
            CustomToast.makeText(getApplicationContext(), "Không có kế nối internet", (int) CustomToast.LONG, CustomToast.ERROR, true).show();
        }
    }
}