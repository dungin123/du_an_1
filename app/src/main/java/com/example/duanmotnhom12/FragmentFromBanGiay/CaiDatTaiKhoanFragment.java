package com.example.duanmotnhom12.FragmentFromBanGiay;

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
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.R;

import org.jetbrains.annotations.NotNull;


public class CaiDatTaiKhoanFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView tv_maID, tv_hoTen, tv_email, tv_sdt, tv_ngaySinh, tv_doiMatKhau;

    public CaiDatTaiKhoanFragment() {
        // Required empty public constructor
    }

    public static CaiDatTaiKhoanFragment newInstance(String param1, String param2) {
        CaiDatTaiKhoanFragment fragment = new CaiDatTaiKhoanFragment();
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
        return inflater.inflate(R.layout.fragment_cai_dat_tai_khoan2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Toolbar tb_dangKy = view.findViewById(R.id.tb_cai_dat_taiKhoan);
        ((FromBanGiay) getActivity()).setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        ((FromBanGiay) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((FromBanGiay) getActivity()).getSupportActionBar().setHomeAsUpIndicator(drawable);

        anhXaView();
        setTextView();
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

    void anhXaView() {
        tv_maID = getView().findViewById(R.id.textView34);
        tv_hoTen = getView().findViewById(R.id.textView37);
        tv_email = getView().findViewById(R.id.textView38);
        tv_sdt = getView().findViewById(R.id.textView39);
        tv_ngaySinh = getView().findViewById(R.id.textView40);
        tv_doiMatKhau = getView().findViewById(R.id.textView41);
    }

    void setTextView() {
        if (FromDangNhap.title == 1) {
            tv_maID.setText(FromDangNhap.modelDangNhap.getId_user() + "");
            tv_hoTen.setText(FromDangNhap.modelDangNhap.getHoTen_user());
            tv_email.setText(FromDangNhap.modelDangNhap.getEmail_user());
            tv_sdt.setText("Trống");
            tv_ngaySinh.setText("Trống");
        } else if (FromDangNhap.title == 2) {
            tv_maID.setText(FromBanGiay.personid);
            tv_hoTen.setText(FromBanGiay.personName);
            tv_email.setText(FromBanGiay.personEmail);
            tv_sdt.setText("Trống");
            tv_ngaySinh.setText("Trống");
        } else if (FromDangNhap.title == 3) {
            tv_maID.setText(FromDangNhap.id_ + "");
            tv_hoTen.setText(FromDangNhap.name_);
            tv_email.setText(FromDangNhap.email_);
            tv_sdt.setText("Trống");
            tv_ngaySinh.setText("Trống");
        } else if (FromDangNhap.title == 4) {
            tv_maID.setText(FromDangNhap.modelDangNhapAdmin.getId_admin() + "");
            tv_hoTen.setText(FromDangNhap.modelDangNhapAdmin.getName_admin());
            tv_email.setText(FromDangNhap.modelDangNhapAdmin.getEmail_admin());
            tv_sdt.setText("Trống");
            tv_ngaySinh.setText("Trống");
        }
    }
}