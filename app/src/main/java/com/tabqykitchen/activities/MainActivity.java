package com.tabqykitchen.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tabqykitchen.Helper.BaseClass;
import com.tabqykitchen.R;
import com.tabqykitchen.adapter.AdapterCatTop;
import com.tabqykitchen.fragments.FragmentMainGrid;
import com.tabqykitchen.fragments.FragmentMainList;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterCatTop.Interface_AdapterOnlineTop{
    private TextView catspinner, all_order_Spinner;
    private ArrayList<String>name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListener();
    }

    private PopupWindow popupwindow_cateory, popupWindow_all_order;
    private ImageView iv_menu, iv_settings, iv_logout;
    private String selected_category, selected_order;

    private void init(){
        catspinner = findViewById(R.id.activity_main_spinner_all_cat);
        all_order_Spinner = findViewById(R.id.activity_main_spinner_all_order);

        iv_menu = findViewById(R.id.top_menu);
        iv_settings = findViewById(R.id.top_settings);
        iv_logout = findViewById(R.id.top_logout);

        BaseClass.callFragment(new FragmentMainGrid(), null, getSupportFragmentManager());
    }

    private void setListener(){
        all_order_Spinner.setOnClickListener(this);
        catspinner.setOnClickListener(this);
    }

    private AdapterCatTop adapterCatTop;

    public PopupWindow showMyPopup_order() {
        final PopupWindow popupWindow = new PopupWindow(getApplicationContext());

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.popup_walkin_categories, null);
        popup_recyclerView = view.findViewById(R.id.popup_cat_recycler);
        LinearLayoutManager manager2
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        popup_recyclerView.setLayoutManager(manager2);
        name.clear();
        name.add("All Orders");
        name.add("Chicken");
        name.add("Rice");
        name.add("Cold Drinks");
        setAdapterCatTop(popup_recyclerView, "orders");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        popupWindow.setFocusable(true);
        popupWindow.setWidth(width - 1000);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setContentView(view);
        return popupWindow;
    }

    private void setAdapterCatTop(RecyclerView popup_recyclerView, String TAG){

        adapterCatTop = new AdapterCatTop(getApplicationContext(), name,this, TAG);
        popup_recyclerView.setAdapter(adapterCatTop);
    }
    private RecyclerView popup_recyclerView;

    public PopupWindow showMyPopup_cateory() {
        final PopupWindow popupWindow = new PopupWindow(getApplicationContext());

        // inflate your layout or dynamically add view
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.popup_walkin_categories, null);

        popup_recyclerView = view.findViewById(R.id.popup_cat_recycler);
        LinearLayoutManager manager2
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        popup_recyclerView.setLayoutManager(manager2);
        name.clear();
        name.add("All Categories");
        name.add("Cold Beverages");
        name.add("Pizza");
        name.add("Sarvory");
        name.add("Dessert");
        setAdapterCatTop(popup_recyclerView, "cat");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        popupWindow.setFocusable(true);
        popupWindow.setWidth(width - 1000);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setContentView(view);
        return popupWindow;
    }

//    private void setSpinner(){
//
//        String[] categories =getResources().getStringArray(R.array.array_categories);
//
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.spinner_layout_txt, categories);
//
//        catspinner.setAdapter(adapter);
//
//        String[] all_orders =getResources().getStringArray(R.array.array_all_order);
//
//        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.spinner_layout_txt, all_orders);
//
//        all_order_Spinner.setAdapter(adapter1);
//
//    }

    @Override
    public void onClick(View v) {
        int id_ = v.getId();
        switch (v.getId()){
            case R.id.activity_main_spinner_all_order:
                popupWindow_all_order = showMyPopup_order();
                popupWindow_all_order.showAsDropDown(all_order_Spinner, -12, 05); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
                break;

            case R.id.activity_main_spinner_all_cat:
                popupwindow_cateory = showMyPopup_cateory();
                popupwindow_cateory.showAsDropDown(catspinner, -12, 05); // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
                break;
        }
    }

    private boolean top_menu_status = true;
    public void top_icons(View view){
        int id = view.getId();

        if(id == R.id.top_menu){
            if(top_menu_status){
                iv_menu.setImageResource(R.drawable.ic_grid);
                top_menu_status = false;

                BaseClass.callFragment(new FragmentMainList(), null, getSupportFragmentManager());
            } else {
                iv_menu.setImageResource(R.drawable.ic_menu);
                top_menu_status = true;

                BaseClass.callFragment(new FragmentMainGrid(), null, getSupportFragmentManager());
            }
        } else if(id == R.id.top_settings){
            BaseClass.showToast(getApplicationContext(), "Settings");
        } else if(id == R.id.top_logout){
            BaseClass.showToast(getApplicationContext(), "Logout");
        } else{
        }
    }

    @Override
    public void method_AdapterOnlineTop(int position, String tag) {
        if (tag.equals("orders")){
            all_order_Spinner.setText(name.get(position));
            Toast.makeText(this, ""+name.get(position), Toast.LENGTH_SHORT).show();
            popupWindow_all_order.dismiss();
        }else {
            catspinner.setText(name.get(position));
            Toast.makeText(this, ""+name.get(position), Toast.LENGTH_SHORT).show();
            popupwindow_cateory.dismiss();
        }

    }
}
