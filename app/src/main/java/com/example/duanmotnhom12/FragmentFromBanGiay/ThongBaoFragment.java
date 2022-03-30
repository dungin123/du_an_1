package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.JSON.JsonPhanHoiAdmin;
import com.example.duanmotnhom12.R;

import org.jetbrains.annotations.NotNull;

public class ThongBaoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerView;

    public ThongBaoFragment() {
        // Required empty public constructor
    }

    public static ThongBaoFragment newInstance(String param1, String param2) {
        ThongBaoFragment fragment = new ThongBaoFragment();
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
        return inflater.inflate(R.layout.fragment_thong_bao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar tb_dangKy = view.findViewById(R.id.tb_thongBao);
        ((FromBanGiay) getActivity()).setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        ((FromBanGiay) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((FromBanGiay) getActivity()).getSupportActionBar().setHomeAsUpIndicator(drawable);

        recyclerView = getView().findViewById(R.id.rcv_thongBao);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (FromDangNhap.title == 1) {
            JsonPhanHoiAdmin jsonPhanHoiAdmin = new JsonPhanHoiAdmin();
            jsonPhanHoiAdmin.getJsonPhanHoiAdminArray(URLss.URL_USERSENNPH + "/" + FromDangNhap.modelDangNhap.getId_user(), getContext(), recyclerView);
        } else if (FromDangNhap.title == 2) {
            JsonPhanHoiAdmin jsonPhanHoiAdmin = new JsonPhanHoiAdmin();
            jsonPhanHoiAdmin.getJsonPhanHoiAdminArray(URLss.URL_USERSENNPH + "/" + FromBanGiay.personid, getContext(), recyclerView);
        } else if (FromDangNhap.title == 3) {
            JsonPhanHoiAdmin jsonPhanHoiAdmin = new JsonPhanHoiAdmin();
            jsonPhanHoiAdmin.getJsonPhanHoiAdminArray(URLss.URL_USERSENNPH + "/" + FromDangNhap.id_ + "", getContext(), recyclerView);
            Log.d("id_fb", FromDangNhap.id_ + "");
        }
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
}