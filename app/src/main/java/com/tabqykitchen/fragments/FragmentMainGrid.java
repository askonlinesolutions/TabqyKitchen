package com.tabqykitchen.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tabqykitchen.Helper.BaseClass;
import com.tabqykitchen.R;
import com.tabqykitchen.activities.MainActivity;
import com.tabqykitchen.adapter.AdapterMainGrid;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMainGrid extends Fragment implements AdapterMainGrid.InterfaceAdapterMainGrid {

    public FragmentMainGrid() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_main_grid, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private RecyclerView rv_grid;
    private ArrayList<String> arr_total_time = new ArrayList<>();
    private ArrayList<String> arr_order_title = new ArrayList<>();

    private void init(){

        rv_grid = getView().findViewById(R.id.main_recycler_grid);
        rv_grid.setLayoutManager(new GridLayoutManager(getActivity(),
                BaseClass.calculateNoOfRows(getActivity()), GridLayoutManager.HORIZONTAL, false));

        addData();
    }

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).iv_menu.setImageResource(R.drawable.ic_menu);

    }

    private void addData(){

        arr_total_time.clear();
        arr_total_time.add("100");
        arr_total_time.add("20");
        arr_total_time.add("35");
        arr_total_time.add("22");
        arr_total_time.add("92");
        arr_total_time.add("78");
        arr_total_time.add("63");
        arr_total_time.add("38");
        arr_total_time.add("41");

        arr_order_title.clear();
        arr_order_title.add("Table: 02");
        arr_order_title.add("Take Away Order");
        arr_order_title.add("Online Order");
        arr_order_title.add("Table: 02");
        arr_order_title.add("Take Away Order");
        arr_order_title.add("Online Order");
        arr_order_title.add("Table: 02");
        arr_order_title.add("Take Away Order");
        arr_order_title.add("Online Order");

        setMyAdapter();

        new CountDownTimer(300000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
//                Log.d("MyTimer", "Time Remaining: " + millisUntilFinished/1000);
                time = time+1;
                setMyAdapter();

            }

            @Override
            public void onFinish() {
                Log.d("MyTimer", "Finished");
            }
        }.start();
    }

    private int time = 1;
    private void setMyAdapter(){
        rv_grid.setAdapter(new AdapterMainGrid(getContext(), arr_total_time, arr_order_title, time, this));
    }

    @Override
    public void method_AdapterMainGrid(int position) {

        ((MainActivity) getActivity()).iv_menu.setImageResource(R.drawable.ic_grid);
        ((MainActivity) getActivity()).top_menu_status = false;


        Bundle bundle = new Bundle();
        bundle.putInt("position_key", position);

        BaseClass.callFragment(new FragmentMainList(), bundle, getFragmentManager());

    }
}