package com.example.duanmotnhom12.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmotnhom12.Model.ModelUser;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.CustomToast;
import com.example.duanmotnhom12.activity.PhanHoiCuaNguoiDung;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListSanPhamAdapterViewHolder> {
    private Context context;
    private ArrayList<ModelUser> modelUsers;

    public ListUserAdapter(Context context, ArrayList<ModelUser> modelUsers) {
        this.context = context;
        this.modelUsers = modelUsers;
    }

    @NonNull
    @NotNull
    @Override
    public ListSanPhamAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_, parent, false);

        return new ListSanPhamAdapterViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ListSanPhamAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelUser modelUser = modelUsers.get(position);
        if (modelUser == null) {
            return;
        }
        holder.tv_tenDonVi.setText(modelUser.getTen_user());
        holder.tv_diaChiDonVi.setText(modelUser.getTenDn_user());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence sequence[] = {"Khoá tài khoản này", "Xem chi tiết"};
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Chức năng thêm");
                builder.setItems(sequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            CustomToast.makeText(context, "Chức năng đang trong quá trình phát triển", (int) CustomToast.LONG_, CustomToast.WARNING, true).show();

                        } else if (which == 1) {
                            AlertDialog.Builder builder_view = new AlertDialog.Builder(v.getContext());
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater.inflate(R.layout.dia_log_xem_user, null);
                            builder_view.setView(view);

                            TextView tv_maND = view.findViewById(R.id.textView66);
                            TextView tv_tenND = view.findViewById(R.id.textView70);
                            TextView tv_EmailND = view.findViewById(R.id.textView71);
                            TextView tv_Pass = view.findViewById(R.id.textView72);

                            tv_maND.setText(modelUser.getId_user() + "");
                            tv_tenND.setText(modelUser.getTen_user());
                            tv_EmailND.setText(modelUser.getTenDn_user());
                            tv_Pass.setText(modelUser.getPass());
                            builder_view.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog_ = builder_view.create();
                            dialog_.show();
//                        } else {
//                            SharedPreferences.Editor editor = context.getSharedPreferences("id_ph", Context.MODE_PRIVATE).edit();
//                            editor.putString("key_id_user", modelUser.getId_user() + "");
//                            editor.apply();
//                            Log.d("cccccc", modelUser.getId_user() + "");
//
//                            Intent intent = new Intent(context, PhanHoiCuaNguoiDung.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            context.startActivity(intent);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (modelUsers != null) {
            return modelUsers.size();
        }
        return 0;
    }

    public class ListSanPhamAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tenDonVi, tv_diaChiDonVi;

        public ListSanPhamAdapterViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_tenDonVi = itemView.findViewById(R.id.tv_tenDonVi);
            tv_diaChiDonVi = itemView.findViewById(R.id.tv_diaChiDonVi);
        }
    }
}
