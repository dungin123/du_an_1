package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmotnhom12.Adapter.ListGioHangAdapter;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.activity.ThanhToanGioHang;


import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;


public class GioHangFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    public static ListGioHangAdapter listGioHangAdapter;

    static Button btn_DatHang;
    static TextView tv_tongTien;
    static TextView tv_tien;
    static ImageView img_viewData;
    static TextView tv_nodata;

    private String mParam1;
    private String mParam2;

    public GioHangFragment() {
        // Required empty public constructor
    }

    public static GioHangFragment newInstance(String param1, String param2) {
        GioHangFragment fragment = new GioHangFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rcv_gioHang);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        listGioHangAdapter = new ListGioHangAdapter(getContext(), FromBanGiay.modelGioHangs);
        recyclerView.setAdapter(listGioHangAdapter);

        listGioHangAdapter.notifyDataSetChanged();
        anhXa();
        checkData();
        getAllTongTien();
        thanhToanGioHang();

        Log.d("ccxzxcc",FromBanGiay.modelGioHangs+"");
    }

    public static void checkData() {
        if (FromBanGiay.modelGioHangs.size() <= 0) {
            listGioHangAdapter.notifyDataSetChanged();
            tv_tien.setVisibility(View.INVISIBLE);
            tv_tongTien.setVisibility(View.INVISIBLE);
            btn_DatHang.setVisibility(View.INVISIBLE);
            img_viewData.setVisibility(View.VISIBLE);
            tv_nodata.setVisibility(View.VISIBLE);
        } else {
            listGioHangAdapter.notifyDataSetChanged();
            img_viewData.setVisibility(View.INVISIBLE);
            tv_nodata.setVisibility(View.INVISIBLE);
            tv_tien.setVisibility(View.VISIBLE);
            tv_tongTien.setVisibility(View.VISIBLE);
            btn_DatHang.setVisibility(View.VISIBLE);
        }
    }

    public void anhXa() {
        img_viewData = getView().findViewById(R.id.empty_imageview);
        tv_nodata = getView().findViewById(R.id.no_data);
        tv_tien = getView().findViewById(R.id.textView35);
        tv_tongTien = getView().findViewById(R.id.textView36);
        btn_DatHang = getView().findViewById(R.id.button6);
    }

    public static void getAllTongTien() {
        long tongTien = 0;
        for (int i = 0; i < FromBanGiay.modelGioHangs.size(); i++) {
            tongTien += Integer.parseInt(FromBanGiay.modelGioHangs.get(i).getGia_tien_gioHang());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_tongTien.setText(decimalFormat.format(tongTien) + "\tđ");
    }

    public void thanhToanGioHang() {
        btn_DatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThanhToanGioHang.class);
                startActivity(intent);
            }
        });
    }
}