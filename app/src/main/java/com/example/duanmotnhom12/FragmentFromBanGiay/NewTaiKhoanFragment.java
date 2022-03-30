package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
import com.example.duanmotnhom12.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewTaiKhoanFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView textView5, textView4;
    ConstraintLayout textView33, textView333, textView3332, textView33321;
    Button btnLogOut;
    ConstraintLayout constraintLayout_88, Adminview;
    GoogleSignInClient mGoogleSignInClient;

    CircleImageView viewIMG;


    private String mParam1;
    private String mParam2;

    public NewTaiKhoanFragment() {
        // Required empty public constructor
    }

    public static NewTaiKhoanFragment newInstance(String param1, String param2) {
        NewTaiKhoanFragment fragment = new NewTaiKhoanFragment();
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
        return inflater.inflate(R.layout.fragment_new_cai_dat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhXa();
        newCaiDatTaiKhoan();
        newBanHangVoiChungToi();
        newPhanHoiChatLuong();
        newLichSuMuaHang();
        newAdmin();
        newThongBao();
        dangXuat();
        if (FromDangNhap.title == 1) {
            textView4.setText(FromDangNhap.modelDangNhap.getHoTen_user());
            textView3332.setVisibility(View.GONE);
            Adminview.setVisibility(View.GONE);
        } else if (FromDangNhap.title == 2) {
            textView4.setText(FromBanGiay.personName);
            Glide.with(getActivity()).load(String.valueOf(FromBanGiay.personPhoto)).into(viewIMG);
            textView3332.setVisibility(View.GONE);
            Adminview.setVisibility(View.GONE);
        } else if (FromDangNhap.title == 3) {
            textView4.setText(FromDangNhap.name_);
            Picasso.get().load("https://graph.facebook.com/" + FromDangNhap.id_ + "/picture?type=large").into(viewIMG);
            textView3332.setVisibility(View.GONE);
            Adminview.setVisibility(View.GONE);
        } else if (FromDangNhap.title == 4) {
            textView3332.setVisibility(View.VISIBLE);
            Adminview.setVisibility(View.VISIBLE);
            textView4.setText(FromDangNhap.modelDangNhapAdmin.getName_admin());
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
    }

    void anhXa() {
        textView5 = getView().findViewById(R.id.textView5);
        textView33 = getView().findViewById(R.id.constraintLayout_2);
        textView333 = getView().findViewById(R.id.constraintLayout_3);
        textView3332 = getView().findViewById(R.id.constraintLayout_8);
        textView33321 = getView().findViewById(R.id.constraintLayout_7);
        textView4 = getView().findViewById(R.id.textView4);

        viewIMG = getView().findViewById(R.id.cri_ImgView);

        constraintLayout_88 = getView().findViewById(R.id.constraintLayout_88);
        Adminview = getView().findViewById(R.id.Adminview);
        btnLogOut = getView().findViewById(R.id.btn_dangXuat);
    }

    void newAdmin() {
        textView3332.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new AdminFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.INVISIBLE);

            }
        });
    }

    void newCaiDatTaiKhoan() {
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new CaiDatTaiKhoanFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.INVISIBLE);

            }
        });
    }

    void newLichSuMuaHang() {
        textView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new LichSuMuaHangFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.INVISIBLE);
            }
        });
    }

    void newPhanHoiChatLuong() {
        textView333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new PhanHoiChatLuongFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.INVISIBLE);

            }
        });
    }

    void newBanHangVoiChungToi() {
        textView33321.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new BanHangVoiChungToiFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.INVISIBLE);
            }
        });
    }

    void newThongBao() {
        constraintLayout_88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mfmCennter_,
                        new ThongBaoFragment()).commit();
                FromBanGiay.ahBottomNavigation.setVisibility(View.INVISIBLE);

            }
        });
    }

    void dangXuat() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FromDangNhap.title == 1) {
                    Intent intent_ = new Intent(getContext(), FromDangNhap.class);
                    startActivity(intent_);
                    getActivity().finish();
                } else if (FromDangNhap.title == 2) {
                    switch (v.getId()) {
                        case R.id.btn_dangXuat:
                            signOut();
                            Intent intent_ = new Intent(getContext(), FromDangNhap.class);
                            startActivity(intent_);
                            getActivity().finish();
                            break;
                    }
                } else if (FromDangNhap.title == 3) {
                    LoginManager.getInstance().logOut();
                    textView4.setText("");
                    viewIMG.setImageResource(0);
                    getActivity().finish();
                } else if (FromDangNhap.title == 4) {
                    Intent intent = new Intent(getContext(), FromDangNhap.class);
                    startActivity(intent);
                    getActivity().finish();
                    Log.d("chayvaoDayChu", "op");

                }
            }
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("chayvaoDayChu", "op");
                        revokeAccess();
                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }
}