package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.FromBanGiay.MainActivityViewChiTietSanPham;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class PhanHoiChatLuongFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageView img_te, img_bt, img_tot;
    EditText ed_phanHoi;
    Button btnGui;
    String trangThai;
    RadioGroup radioGroup;
    String phanHoi;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhanHoiChatLuongFragment() {
        // Required empty public constructor
    }


    public static PhanHoiChatLuongFragment newInstance(String param1, String param2) {
        PhanHoiChatLuongFragment fragment = new PhanHoiChatLuongFragment();
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
        return inflater.inflate(R.layout.fragment_phan_hoi_chat_luong, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar tb_dangKy = view.findViewById(R.id.tb_phanHoi);
        ((FromBanGiay) getActivity()).setSupportActionBar(tb_dangKy);
        Drawable drawable = getResources().getDrawable(R.drawable.icons8left24);
        ((FromBanGiay) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((FromBanGiay) getActivity()).getSupportActionBar().setHomeAsUpIndicator(drawable);

        anhXa();
        phanHoi();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new NewTaiKhoanFragment()).commit();
                //getActivity().finish();
                FromBanGiay.ahBottomNavigation.setVisibility(View.VISIBLE);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void phanHoi() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_te:
                        trangThai = "Không tốt";
                        CustomToast.makeText(getContext(), "Không tốt", (int) CustomToast.LONG, CustomToast.ERROR, true).show();
                        break;
                    case R.id.rd_bt:
                        trangThai = "Bình thường";
                        CustomToast.makeText(getContext(), "Bình thường", (int) CustomToast.LONG, CustomToast.WARNING, true).show();
                        break;
                    case R.id.rd_tot:
                        trangThai = "Tuyệt vời";
                        CustomToast.makeText(getContext(), "Tuyệt vời", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                        break;
                }
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phanHoi = ed_phanHoi.getText().toString();
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    CustomToast.makeText(getContext(), "Bạn chưa đánh giá app", (int) CustomToast.LONG, CustomToast.WARNING, true).show();
                    return;
                }
                if (ed_phanHoi.length() == 0) {
                    ed_phanHoi.setError("Không để trống");
                    ed_phanHoi.requestFocus();
                    return;
                }

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLss.URL_GUIPHANHOI, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CustomToast.makeText(getContext(), "Đánh giá của bạn đã được gửi đi.Cám ơn bạn", (int) CustomToast.LONG, CustomToast.SUCCESS, true).show();
                        ed_phanHoi.setText("");
                        radioGroup.clearCheck();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ôii", error.toString());
                    }
                }) {
                    @Nullable
                    @org.jetbrains.annotations.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        try {
                            map.put("trang_thai", trangThai);
                            map.put("phan_hoi", phanHoi);
                            if (FromDangNhap.title == 1) {
                                map.put("id_user_", FromDangNhap.modelDangNhap.getId_user() + "");
                                Log.d("idusss", FromDangNhap.modelDangNhap.getId_user() + "");
                            } else if (FromDangNhap.title == 2) {
                                map.put("id_user_", FromBanGiay.personid + "");
                                Log.d("idusss", FromBanGiay.personid +"");
                            } else if (FromDangNhap.title == 3) {
                                map.put("id_user_", FromDangNhap.id_ + "");
                                Log.d("idusss", FromDangNhap.id_ +"");
                            }

                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                        return map;
                    }
                };
                RequestQueue requestOptions = Volley.newRequestQueue(getContext());
                requestOptions.add(stringRequest);
            }
        });
    }

    void anhXa() {
        img_te = getView().findViewById(R.id.imageView5);
        img_bt = getView().findViewById(R.id.imageView8);
        img_tot = getView().findViewById(R.id.imageView9);
        ed_phanHoi = getView().findViewById(R.id.editTextTextPersonName3);
        btnGui = getView().findViewById(R.id.button7);
        radioGroup = getView().findViewById(R.id.rd_group);
    }
}