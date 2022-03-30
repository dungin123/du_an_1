package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.JSON.JsonTongTienNhapHang;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.activity.DanhSachLoaiSanPham;
import com.example.duanmotnhom12.activity.DanhSachNguoiDung;
import com.example.duanmotnhom12.activity.DanhSachSanPham;
import com.example.duanmotnhom12.activity.PhanHoiCuaNguoiDung;
import com.example.duanmotnhom12.activity.ThemLoaiSanPham;
import com.example.duanmotnhom12.activity.ThemSanPham;
import com.example.duanmotnhom12.activity.ThongKeCuaHang;
import com.example.duanmotnhom12.activity.QuanLyDonHang;

import org.jetbrains.annotations.NotNull;


public class AdminFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tv_ds, tv_them, tv_User, tv_Qlydonhang, tv_ThongKe, ds_sanPham, tv_themSanPham, phanHoiNguoiDung;

    private String mParam1;
    private String mParam2;
    public AdminFragment() {
        // Required empty public constructor
    }


    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar tb_dangKy = view.findViewById(R.id.tb_admin);
        ((FromBanGiay) getActivity()).setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        ((FromBanGiay) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((FromBanGiay) getActivity()).getSupportActionBar().setHomeAsUpIndicator(drawable);


        tv_ds = view.findViewById(R.id.textListSp);
        tv_them = view.findViewById(R.id.textAddSp);
        tv_User = view.findViewById(R.id.textUser);

        ds_sanPham = view.findViewById(R.id.textUser4);
        tv_themSanPham = view.findViewById(R.id.textQlyDonhang2);


        tv_Qlydonhang = view.findViewById(R.id.textQlyDonhang);
        tv_ThongKe = view.findViewById(R.id.textTke);

        phanHoiNguoiDung = view.findViewById(R.id.phanHoiNguoiDung);
        newIntent();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new NewTaiKhoanFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.VISIBLE);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void newIntent() {
        tv_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DanhSachSanPham.class));
            }
        });
        tv_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThemSanPham.class));
            }
        });
        tv_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DanhSachNguoiDung.class));
            }
        });
        tv_Qlydonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QuanLyDonHang.class));
            }
        });
        tv_ThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThongKeCuaHang.class));
            }
        });
        ds_sanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DanhSachLoaiSanPham.class));
            }
        });
        tv_themSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThemLoaiSanPham.class));
            }
        });
        phanHoiNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PhanHoiCuaNguoiDung.class);
                startActivity(intent);
            }
        });
    }

}