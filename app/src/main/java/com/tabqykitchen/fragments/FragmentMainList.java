package com.tabqykitchen.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tabqykitchen.Helper.BaseClass;
import com.tabqykitchen.R;
import com.tabqykitchen.adapter.AdapterMainList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMainList extends Fragment implements AdapterMainList.Interface_AdapterMainList, AdapterMainList.Interface_AdapterMainListClick {

    public FragmentMainList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_main_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private RecyclerView rv_list;
    private void init(){
        rv_list = getView().findViewById(R.id.main_recycler_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL
                , false));

        prepareListData();
    }

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private HashMap<String, List<String>> listDataChild1;

    private ArrayList<String> arr_total_time = new ArrayList<>();
    private ArrayList<String> arr_order_title = new ArrayList<>();
    private ArrayList<String> arr_item_width = new ArrayList<>();

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataChild1 = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Chicken");
        listDataHeader.add("Rice");
        listDataHeader.add("Cold Dirinks");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Chicken masala");
        top250.add("Chicken korma");
        List<String> top250_1 = new ArrayList<String>();
        top250_1.add("0");
        top250_1.add("0");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Paneer Rice");
        List<String> nowShowing_1 = new ArrayList<String>();
        nowShowing_1.add("1");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Maaza");
        comingSoon.add("Sprite");
        comingSoon.add("Coca Cola");
        List<String> comingSoon_1 = new ArrayList<String>();
        comingSoon_1.add("0");
        comingSoon_1.add("0");
        comingSoon_1.add("0");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);

        listDataChild1.put(listDataHeader.get(0), top250_1);
        listDataChild1.put(listDataHeader.get(1), nowShowing_1);
        listDataChild1.put(listDataHeader.get(2), comingSoon_1);

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

        arr_item_width.clear();
        arr_item_width.add("0");
        arr_item_width.add("0");
        arr_item_width.add("1");
        arr_item_width.add("0");
        arr_item_width.add("0");
        arr_item_width.add("0");
        arr_item_width.add("0");
        arr_item_width.add("0");
        arr_item_width.add("0");

        setMyAdapter();
    }

    AdapterMainList adapterMainList;
    private void setMyAdapter(){
        adapterMainList = new AdapterMainList(getContext(), arr_order_title, arr_item_width, listDataHeader,
                listDataChild, listDataChild1, this, this, getActivity());
        rv_list.setAdapter(adapterMainList);
    }

    @Override
    public void method_AdapterMainList(int group_position, int child_position) {

        if (listDataChild1.get(listDataHeader.get(group_position)).get(child_position).equals("0")) {
            listDataChild1.get(listDataHeader.get(group_position)).set(child_position, "1");
        } else {
            listDataChild1.get(listDataHeader.get(group_position)).set(child_position, "0");
        }
        adapterMainList.notifyDataSetChanged();
    }

    @Override
    public void method_AdapterMainListClick(int position) {

    }
}
