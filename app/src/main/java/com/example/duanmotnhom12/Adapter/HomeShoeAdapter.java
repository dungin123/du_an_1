package com.example.duanmotnhom12.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duanmotnhom12.Model.HomeShoe;
import com.example.duanmotnhom12.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeShoeAdapter extends RecyclerView.Adapter<HomeShoeAdapter.HomeShoeViewHolder> {
    private List<HomeShoe> homeShoes;
    ViewPager2 viewPager2 ;

    public HomeShoeAdapter(List<HomeShoe> homeShoes,ViewPager2 viewPager2 ) {
        this.homeShoes = homeShoes;
        this.viewPager2 =viewPager2;
    }

    public HomeShoeAdapter() {

    }

    @NonNull
    @Override
    public HomeShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeShoeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_trang_chu_shoes, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HomeShoeViewHolder holder, int position) {
        holder.setShoeData(homeShoes.get(position));
        if (position==homeShoes.size()-2){
            viewPager2.post(runnable);
        }


    }

    @Override
    public int getItemCount() {
        return homeShoes.size();
    }

    static class HomeShoeViewHolder extends RecyclerView.ViewHolder {

        private KenBurnsView kbvShoe;
        private TextView textTitle, textRating, textShoe;

        public HomeShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            kbvShoe = itemView.findViewById(R.id.kbView);
          // textRating = itemView.findViewById(R.id.textStarRating);
            textShoe = itemView.findViewById(R.id.textShoe);
            textTitle = itemView.findViewById(R.id.textTitle);
        }

        public void setShoeData(HomeShoe homeShoe) {
            Picasso.get().load(homeShoe.imageurl_).into(kbvShoe);
            textTitle.setText(homeShoe.title_);
            textShoe.setText(homeShoe.shoe_);
        }
    }
    private Runnable runnable =new Runnable() {
        @Override
        public void run() {
            HomeShoe  homeShoe = new HomeShoe();
            homeShoes.addAll(homeShoes);
            notifyDataSetChanged();
        }
    };
}
