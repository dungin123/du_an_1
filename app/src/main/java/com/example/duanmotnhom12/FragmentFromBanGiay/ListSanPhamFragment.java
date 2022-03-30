package com.example.duanmotnhom12.FragmentFromBanGiay;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.duanmotnhom12.CheckNetwork.URLss;
import com.example.duanmotnhom12.FromBanGiay.FromBanGiay;
import com.example.duanmotnhom12.JSON.JsonSanPham;
import com.example.duanmotnhom12.Model.ModelSanPham;
import com.example.duanmotnhom12.R;
import com.example.duanmotnhom12.Translate.TranslateAnimation_nav;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class ListSanPhamFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    private SearchView searchView;
    private FloatingActionButton mfb;

    private String mParam1;
    private String mParam2;

    public ListSanPhamFragment() {
        // Required empty public constructor
    }


    public static ListSanPhamFragment newInstance(String param1, String param2) {
        ListSanPhamFragment fragment = new ListSanPhamFragment();
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
        View view = inflater.inflate(R.layout.fragment_san_pham_list, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar tb_listSP = view.findViewById(R.id.tb_listSanPham);
        ((FromBanGiay) getActivity()).setSupportActionBar(tb_listSP);

        anhXa();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        JsonSanPham jsonSanPham = new JsonSanPham();
        jsonSanPham.getJsonSanPhamArray(URLss.URL_LISTSANPHAM, getContext(), recyclerView);

        //scroll view
        recyclerView.setOnTouchListener(new TranslateAnimation_nav(getContext(), FromBanGiay.ahBottomNavigation));

        mfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.filter_view_che_do, menu);
        menu.findItem(R.id.filter_view).setVisible(true);
        menu.findItem(R.id.search_view).setVisible(true);
        //menu.findItem(R.id.admin_view).setVisible(false);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                JsonSanPham.listSanPhamAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                JsonSanPham.listSanPhamAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_view:
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dia_log_view_collections, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(view);
                builder.setCancelable(false);
                builder.setTitle("Sắp xếp");
                RadioGroup group = view.findViewById(R.id.rd_group);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int idChecked = group.getCheckedRadioButtonId();
                        switch (idChecked) {
                            case R.id.radioButton:
                                Collections.sort(JsonSanPham.modelSanPhams, ModelSanPham.modelSanPhamComparatorAZ);
                                JsonSanPham.listSanPhamAdapter.notifyDataSetChanged();
                                break;
                            case R.id.radioButton2:
                                Collections.sort(JsonSanPham.modelSanPhams, ModelSanPham.modelSanPhamComparatorZA);
                                JsonSanPham.listSanPhamAdapter.notifyDataSetChanged();
                                break;
                            case R.id.radioButton3:
                                Collections.sort(JsonSanPham.modelSanPhams, ModelSanPham.modelSanPhamComparatorCaoThap);
                                JsonSanPham.listSanPhamAdapter.notifyDataSetChanged();
                                break;
                            case R.id.radioButton4:
                                Collections.sort(JsonSanPham.modelSanPhams, ModelSanPham.modelSanPhamComparatorThapCao);
                                JsonSanPham.listSanPhamAdapter.notifyDataSetChanged();
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhXa() {
        recyclerView = getView().findViewById(R.id.rcv_ListSanPham);
        mfb = getView().findViewById(R.id.floatingActionButton);
    }
}