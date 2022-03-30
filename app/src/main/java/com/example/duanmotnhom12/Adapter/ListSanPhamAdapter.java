package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.FromBanGiay.MainActivityViewChiTietSanPham;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListSanPhamAdapter extends RecyclerView.Adapter<ListSanPhamAdapter.ListSanPhamAdapterViewHolder> implements Filterable {
    private Context context;
    private ArrayList<ModelSanPham> modelSanPhams;
    private ArrayList<ModelSanPham> modelSanPhamOld;

    public ListSanPhamAdapter(Context context, ArrayList<ModelSanPham> modelSanPhams) {
        this.context = context;
        this.modelSanPhams = modelSanPhams;
        this.modelSanPhamOld = modelSanPhams;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_san_pham, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelSanPham modelSanPham = modelSanPhams.get(position);
        if (modelSanPham == null) {
            return;
        }

        // holder.tv_giaTien.setText(modelSanPham.getGiaTienSP_());
        int tienLai = 50000;
        int giaNhap = Integer.parseInt(modelSanPham.getGiaTienSP_());
        long giaBan = Long.parseLong(String.valueOf(tienLai + giaNhap));
        Log.d("giaLai",giaBan+"");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tv_giaTien.setText(decimalFormat.format(giaBan) + "\tđ");
     //   holder.tv_giaTien.setText((giaBan )+ "\tđ");
        holder.tv_tenSanPham.setText(modelSanPham.getTenSp_());
        holder.tv_Trangthai.setText(modelSanPham.getTrang_thai_hang());
        try {
            Picasso.get().load(modelSanPham.getImgSP_()).placeholder(R.drawable.noimageavailablesvg).
                    error(R.drawable.error).
                    fit().
                    centerCrop().
                    into(holder.img_giay);
        } catch (Exception e) {
            Log.d("get_error", e + "");
        }

        if (holder.tv_Trangthai.getText().toString().equalsIgnoreCase("Hết hàng")) {
            holder.tv_Trangthai.setTextColor(R.color.black);
            holder.tv_Trangthai.setPaintFlags(holder.tv_Trangthai.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivityViewChiTietSanPham.class);
                intent.putExtra("viewChiTietSanPham", modelSanPhams.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelSanPhams != null) {
            return modelSanPhams.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()) {
                    modelSanPhams = modelSanPhamOld;
                } else {
                    ArrayList<ModelSanPham> modelSanPhams_List = new ArrayList<>();
                    for (ModelSanPham modelSanPham : modelSanPhamOld) {
                        if (modelSanPham.getTenSp_().toLowerCase().contains(search.toLowerCase())) {
                            modelSanPhams_List.add(modelSanPham);
                        }
                    }
                    modelSanPhams = modelSanPhams_List;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = modelSanPhams;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                modelSanPhams = (ArrayList<ModelSanPham>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_giay;
        private TextView tv_giaTien, tv_tenSanPham, tv_xemChitiet, tv_Trangthai;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            img_giay = itemView.findViewById(R.id.img_book_KeSach);
            tv_giaTien = itemView.findViewById(R.id.textView9);
            tv_tenSanPham = itemView.findViewById(R.id.textView10);
            tv_xemChitiet = itemView.findViewById(R.id.textView13);
            tv_Trangthai = itemView.findViewById(R.id.textView8);
        }
    }
}
