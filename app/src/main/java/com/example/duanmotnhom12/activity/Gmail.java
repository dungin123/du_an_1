package com.example.duanmotnhom12.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.MainActivityViewChiTietSanPham;
import com.example.duanmotnhom12.R;

public class Gmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);

        if (URLss.urlGmail.isEmpty()) {
            Toast.makeText(this, "Lỗi hệ thống", Toast.LENGTH_LONG).show();
            return;
        }
        WebView webView;
        webView = findViewById(R.id.webViewEmail);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(URLss.urlGmail);
    }
}