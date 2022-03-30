package com.example.duanmotnhom12.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Header;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.JSON.JsonSanPhamAdmin;
import com.example.duanmotnhom12.R;

public class DanhSachSanPham extends AppCompatActivity {
    RecyclerView rcv_dsAdimSp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);

        rcv_dsAdimSp = findViewById(R.id.rcv_dsAdimSp);
        Toolbar tb_dangKy = findViewById(R.id.tb_dsadmin);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);


        // RecyclerView.LayoutManager manager = new LinearLayoutManager(DanhSachSanPham.this, RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(DanhSachSanPham.this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcv_dsAdimSp.setLayoutManager(gridLayoutManager);
        JsonSanPhamAdmin jsonSanPhamAdmin = new JsonSanPhamAdmin();
        jsonSanPhamAdmin.getJsonSanPhamArray(URLss.URL_LISTSANPHAM, DanhSachSanPham.this, rcv_dsAdimSp);



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


}