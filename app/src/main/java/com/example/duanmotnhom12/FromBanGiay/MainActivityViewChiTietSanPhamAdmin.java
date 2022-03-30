package com.example.duanmotnhom12.FromBanGiay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class MainActivityViewChiTietSanPhamAdmin extends AppCompatActivity {

    ImageView img_chiTiet;
    TextView tv_ten, tv_gia, tv_hang, tv_ngaySX, tv_trangThai, tv_mieuTa, tv_binhLuan;
    Button btn_thoat;

    int id_sp = 0;
    String img_chiTietSP = "";
    String tv_ten_ = "";
    int tv_gia_ = 0;
    String tv_hang_ = "";
    String tv_ngaySX_ = "";
    String tv_trangThai_ = "";
    String tv_mieuTa_ = "";
    String tv_binhLuan_ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_chi_tiet_san_pham_admin);


        Toolbar toolbar = findViewById(R.id.tb_chiTietAdmin);
        setSupportActionBar(toolbar);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);


        anhXa();
        getInfoChiTietSanPhamADMIN();
        clickThoat();
    }

    public void anhXa() {
        img_chiTiet = findViewById(R.id.imageView77);
        tv_ten = findViewById(R.id.textView222);
        tv_gia = findViewById(R.id.textView223);
        tv_hang = findViewById(R.id.textView24);
        tv_ngaySX = findViewById(R.id.textView2v5);
        tv_trangThai = findViewById(R.id.textView26c);
        tv_mieuTa = findViewById(R.id.textViewd27);
        tv_binhLuan = findViewById(R.id.textView2c8);
        btn_thoat = findViewById(R.id.button2);
    }

    public void getInfoChiTietSanPhamADMIN() {

        ModelSanPham modelSanPham = (ModelSanPham) getIntent().getSerializableExtra("viewChiTietSanPhamAdmin");

        id_sp = modelSanPham.getId_sanPham_();
        img_chiTietSP = modelSanPham.getImgSP_();
        tv_ten_ = modelSanPham.getTenSp_();
        tv_gia_ = Integer.parseInt(modelSanPham.getGiaTienSP_());
        tv_hang_ = modelSanPham.getNhaSX_();
        tv_ngaySX_ = modelSanPham.getNgay_san_xuat();
        tv_trangThai_ = modelSanPham.getTrang_thai_hang();
        tv_mieuTa_ = modelSanPham.getMieuTaSP_();
        tv_binhLuan_ = modelSanPham.getBinhLuanSP_();

        tv_ten.setText(tv_ten_);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_gia.setText(decimalFormat.format(tv_gia_) + "\t VND");
        tv_hang.setText(tv_hang_);
        tv_ngaySX.setText(tv_ngaySX_);
        tv_trangThai.setText(tv_trangThai_);
        tv_mieuTa.setText(tv_mieuTa_);
        tv_binhLuan.setText(tv_binhLuan_);
        Picasso.get().load(img_chiTietSP).
                placeholder(R.drawable.noimageavailablesvg).
                error(R.drawable.error).
                into(img_chiTiet);
    }

    void clickThoat() {
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityViewChiTietSanPhamAdmin.this, FromBanGiay.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}