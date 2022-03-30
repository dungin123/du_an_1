package com.example.duanmotnhom12.FragmentFromBanGiay;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Header;
import com.example.duanmotnhom12.Adapter.HomeShoeAdapter;
import com.example.duanmotnhom12.Model.HomeShoe;
import com.example.duanmotnhom12.Model.ModelTrangChu;
import com.example.duanmotnhom12.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class TrangChuFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    ArrayList<ModelTrangChu> modelTrangChus;
    private Handler handler =new Handler();
    ViewPager2 shoeViewpager ;

    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       shoeViewpager = view.findViewById(R.id.viewPager2);
        final List<HomeShoe> homeShoes = new ArrayList<>();

        HomeShoe homeShoeAdidas = new HomeShoe();
        homeShoeAdidas.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas_trx_vintage_red_h052511.jpg";
        homeShoeAdidas.title_ = "ASICS TARTHER BLUST";
        homeShoeAdidas.shoe_ = "1201A219-101";
        // homeShoeAdidas.starRating_ = 4.6f;
        homeShoes.add(homeShoeAdidas);

        HomeShoe homeShoePulma = new HomeShoe();
        homeShoePulma.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/2/5/25800001-1.jpg";
        homeShoePulma.title_ = "PUMA X MR";
        homeShoePulma.shoe_ = "375790_01";
        // homeShoePulma.starRating_ = 5.0f;
        homeShoes.add(homeShoePulma);

        HomeShoe homeShoeNike = new HomeShoe();
        homeShoeNike.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/n/i/nike-tailwind-79-cz5928-001-9.jpg";
        homeShoeNike.title_ = "NIKE TAILWIND 79";
        homeShoeNike.shoe_ = "005790_01";
        //  homeShoeNike.starRating_ = 4.1f;
        homeShoes.add(homeShoeNike);

        HomeShoe homeShoeConverse = new HomeShoe();
        homeShoeConverse.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/c/o/converse-chuck-taylor-all-star-hi-black-62.jpg";
        homeShoeConverse.title_ = "ASICS TARTHER BLUST";
        homeShoeConverse.shoe_ = "1201A219-101";
        // homeShoeConverse.starRating_ = 4.2f;
        homeShoes.add(homeShoeConverse);

        HomeShoe homeShoeConverse_1 = new HomeShoe();
        homeShoeConverse_1.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/n/e/new-balance-cm996sha-4.jpg";
        homeShoeConverse_1.title_ = "VANS STYLE 73 DX";
        homeShoeConverse_1.shoe_ = "VN0A3WLQ4ZD";
        // homeShoeConverse_1.starRating_ = 4.2f;
        homeShoes.add(homeShoeConverse_1);


        HomeShoe homeShoeConverse_2 = new HomeShoe();
        homeShoeConverse_2.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas-stan-smith-fz2706.jpg";
        homeShoeConverse_2.title_ = "ADIDAS STAN ";
        homeShoeConverse_2.shoe_ = "ADIDAS";
        // homeShoeConverse_2.starRating_ = 4.2f;
        homeShoes.add(homeShoeConverse_2);

        HomeShoe homeShoeConverse_3 = new HomeShoe();
        homeShoeConverse_3.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/1/7/172485c.jpg";
        homeShoeConverse_3.title_ = "CONVERSE X SPACE";
        homeShoeConverse_3.shoe_ = "ALL STAR";
        // homeShoeConverse_3.starRating_ = 4.2f;
        homeShoes.add(homeShoeConverse_3);

        HomeShoe homeShoeConverse_4 = new HomeShoe();
        homeShoeConverse_4.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/d/v/dv64341.jpg";
        homeShoeConverse_4.title_ = "REEBOK CLUB C 1985";
        homeShoeConverse_4.shoe_ = "REEBOK";
        // homeShoeConverse_4.starRating_ = 4.2f;
        homeShoes.add(homeShoeConverse_4);

        HomeShoe homeShoeConverse_5 = new HomeShoe();
        homeShoeConverse_5.imageurl_ = "https://www.shooos.com/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/t/i/timberland-teddy-fleece-wp-8355b-wht-6.jpg";
        homeShoeConverse_5.title_ = "TIMBERLAND TEDDY ";
        homeShoeConverse_5.shoe_ = "Timberland";
        // homeShoeConverse_5.starRating_ = 4.2f;
        homeShoes.add(homeShoeConverse_5);


        shoeViewpager.setAdapter(new HomeShoeAdapter(homeShoes,shoeViewpager));
        shoeViewpager.setClipToPadding(false);
        shoeViewpager.setClipChildren(false);
        shoeViewpager.setOffscreenPageLimit(9);
        shoeViewpager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
     shoeViewpager.setCurrentItem(4);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
               // page.setScaleY(0.95f + r * 0.05f);
            }
        });
        shoeViewpager.setPageTransformer(compositePageTransformer);
        shoeViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,2000);
            }
        });
    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            shoeViewpager.setCurrentItem(shoeViewpager.getCurrentItem()+1);
        }
    };
}





