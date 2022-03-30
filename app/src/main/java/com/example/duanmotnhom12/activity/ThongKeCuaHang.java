package com.example.duanmotnhom12.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FragmentFromBanGiay.AdminFragment;
import com.example.duanmotnhom12.JSON.JsonTongSanPham;
import com.example.duanmotnhom12.JSON.JsonTongTienBanDuoc;
import com.example.duanmotnhom12.JSON.JsonTongTienNhapHang;
import com.example.duanmotnhom12.JSON.JsonTopSanPhamMua;
import com.example.duanmotnhom12.R;

import java.text.DecimalFormat;

public class ThongKeCuaHang extends AppCompatActivity {
    TextView view_tienBan;
    TextView view_tienNhap;
    TextView view_soLuongSp;
    TextView view_tienLai;
    RecyclerView view;

    private SharedPreferences sharedPreferences_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_cua_hang);

        Toolbar tb_dangKy = findViewById(R.id.tb_thongKe);
        setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        //
        anhXa();

        JsonTongTienBanDuoc jsonTongTienBanDuoc = new JsonTongTienBanDuoc();
        jsonTongTienBanDuoc.getTongTienBanDuoc(URLss.URL_TONGSOTIENBAN, ThongKeCuaHang.this, view_tienBan);

        JsonTongTienNhapHang tongTienNhapHang = new JsonTongTienNhapHang();
        tongTienNhapHang.getJsonTongTienNhapHangArray(URLss.URL_TONGSOTIENNHAP, ThongKeCuaHang.this, view_tienNhap);


        JsonTongSanPham jsonTongSanPham = new JsonTongSanPham();
        jsonTongSanPham.getTongSanPham(URLss.URL_TONGSANPHAM, ThongKeCuaHang.this, view_soLuongSp);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        view.setLayoutManager(layoutManager);
        JsonTopSanPhamMua jsonTopSanPhamMua = new JsonTopSanPhamMua();
        jsonTopSanPhamMua.getJsonTopSanPhamArray(URLss.URL_TOPSPBANCHAY, ThongKeCuaHang.this, view);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        view_tienLai.setText(decimalFormat.format(JsonTongTienNhapHang.tienLai)+"\tđ");
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

    void anhXa() {
        view_tienBan = findViewById(R.id.textView106);
        view_tienNhap = findViewById(R.id.textView107);
        view_soLuongSp = findViewById(R.id.textView108);
        view_tienLai = findViewById(R.id.textView109);
        view = findViewById(R.id.rcv_topSp);
    }
}