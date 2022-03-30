package com.example.duanmotnhom12.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.JSON.JsonHoaDonChiTietAdmin;
import com.example.duanmotnhom12.R;

public class AdminViewHoaDonChiTiet extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_hoa_don_chi_tiet);

        Toolbar tv_chiTietHoaDon = findViewById(R.id.tb_xemct_admin);
        setSupportActionBar(tv_chiTietHoaDon);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        recyclerView = findViewById(R.id.rcv_xemct);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        JsonHoaDonChiTietAdmin jsonHoaDonChiTietAdmin = new JsonHoaDonChiTietAdmin();
        SharedPreferences sharedPreferences = AdminViewHoaDonChiTiet.this.getSharedPreferences("id_hoaDon", MODE_PRIVATE);
        String id_hoaDon = sharedPreferences.getString("key_id_hoaDon", "");
        jsonHoaDonChiTietAdmin.getHoaDonChiTietAdmin(URLss.URL_HOADONCHITIETTHEOIDHOADON + "/" + id_hoaDon, AdminViewHoaDonChiTiet.this, recyclerView);

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