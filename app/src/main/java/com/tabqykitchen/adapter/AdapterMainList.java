package com.tabqykitchen.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tabqykitchen.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterMainList extends RecyclerView.Adapter<AdapterMainList.MyViewHolder> {

    private Context context;
    private ArrayList<String> arr_order_title = new ArrayList<>();
    private ArrayList<String> arr_item_width = new ArrayList<>();
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private HashMap<String, List<String>> _listDataChild1;
    private Interface_AdapterMainList click;
    private Interface_AdapterMainListClick click1;
    private Activity activity;

    public AdapterMainList(Context context, ArrayList<String> arr_order_title, ArrayList<String> arr_item_width,
                           List<String> listDataHeader, HashMap<String, List<String>> _listDataChild,
                           HashMap<String, List<String>> listDataChild1,
                           Interface_AdapterMainList click, Interface_AdapterMainListClick click1,
                           Activity activity) {
        this.context = context;
        this.arr_order_title = arr_order_title;
        this.arr_item_width = arr_item_width;
        this._listDataHeader = listDataHeader;
        this._listDataChild = _listDataChild;
        this._listDataChild1 = listDataChild1;
        this.click = click;
        this.click1 = click1;
        this.activity = activity;
    }

    public interface Interface_AdapterMainList{
        public void method_AdapterMainList(int group_position, int child_position);
    }
    public interface Interface_AdapterMainListClick{
        public void method_AdapterMainListClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements ExpandableListView.OnChildClickListener,
            ExpandableListView.OnGroupClickListener {

        public ExpandableListView expandableListView;
        public TextView tv_timer, tv_time_left, tv_title, tv_btn;
        public RelativeLayout layout_top, layout_main;
        public LinearLayout layout_timer, layout_bottom_1, layout_bottom_2;
        public ImageView iv;


        public MyViewHolder(final View view) {
            super(view);

            tv_title = view.findViewById(R.id.item_main_list_title_name);
            iv = view.findViewById(R.id.item_main_list_image);
            tv_timer = view.findViewById(R.id.item_main_list_timer);
            tv_time_left = view.findViewById(R.id.item_main_list_time_left);
            layout_top = view.findViewById(R.id.item_main_list_layout_top);
            layout_timer = view.findViewById(R.id.item_main_list_layout_timer);
            layout_main = view.findViewById(R.id.item_main_list_layout);

            layout_bottom_2 = view.findViewById(R.id.item_main_list_layout_bottom_2);
            layout_bottom_1 = view.findViewById(R.id.item_main_list_layout_bottom_1);
            tv_btn = view.findViewById(R.id.item_main_list_layout_bottom_1_btn);

            expandableListView = view.findViewById(R.id.item_main_list_expandable_list_view);
            expandableListView.setOnChildClickListener(this);
            expandableListView.setOnGroupClickListener(this);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in_tv);
//                    view.startAnimation(anim);
//                    anim.setFillAfter(true);

                    click1.method_AdapterMainListClick(getAdapterPosition());
                }
            });
/*
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int focus = 0;
                    if (hasFocus) {
                        focus = R.anim.enlarge;
                    } else {
                        focus = R.anim.decrease;
                    }
                    Animation mAnimation = AnimationUtils.loadAnimation(
                            activity.getApplication(), focus);
                    mAnimation.setBackgroundColor(Color.TRANSPARENT);
                    mAnimation.setFillAfter(hasFocus);
                    v.startAnimation(mAnimation);
                    mAnimation.start();
                    v.bringToFront();
                }
            });*/
/*
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // run scale animation and make it bigger
                        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in_tv);
                        view.startAnimation(anim);
                        anim.setFillAfter(true);
                    } else {
                        // run scale animation and make it smaller
                        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_out_tv);
                        view.startAnimation(anim);
                        anim.setFillAfter(true);
                    }
                }
            });
*/



        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            expandableListView.expandGroup(groupPosition);
//
            if(arr_order_title.get(getAdapterPosition()).equals("Table: 02"))
                click.method_AdapterMainList(groupPosition, childPosition);
            return true;
        }

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            expandableListView.expandGroup(groupPosition);
            return true;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final int focus; final boolean hasFocus;
        if(arr_item_width.equals("1")){
            focus = R.anim.enlarge;
            hasFocus = true;
            Animation mAnimation = AnimationUtils.loadAnimation(
                    activity.getApplication(), focus);
            mAnimation.setBackgroundColor(Color.TRANSPARENT);
            mAnimation.setFillAfter(hasFocus);
            holder.layout_main.startAnimation(mAnimation);
            mAnimation.start();
        } else{
            focus = R.anim.decrease;
            hasFocus = false;
            Animation mAnimation = AnimationUtils.loadAnimation(
                    activity.getApplication(), focus);
            mAnimation.setBackgroundColor(Color.TRANSPARENT);
            mAnimation.setFillAfter(hasFocus);
            holder.layout_main.startAnimation(mAnimation);
            mAnimation.start();
        }
//a

        holder.layout_main.bringToFront();





        holder.tv_title.setText(arr_order_title.get(position));



        if(arr_order_title.get(position).equals("Table: 02")){
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_table));
            holder.layout_top.setBackgroundResource(R.drawable.shape_background_theme_blue);
            holder.layout_timer.setBackgroundResource(R.drawable.shape_border_theme_blue);
            holder.tv_timer.setBackgroundResource(R.color.colorThemeBlue);
            holder.tv_time_left.setTextColor(context.getResources().getColor(R.color.colorThemeBlue));
            holder.layout_bottom_2.setVisibility(View.VISIBLE);
            holder.layout_bottom_1.setVisibility(View.GONE);
        } else if(arr_order_title.get(position).equals("Take Away Order")){
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bag));
            holder.layout_top.setBackgroundResource(R.drawable.shape_background_theme_orange);
            holder.layout_timer.setBackgroundResource(R.drawable.shape_border_theme_orange);
            holder.tv_timer.setBackgroundResource(R.color.colorThemeOrange);
            holder.tv_time_left.setTextColor(context.getResources().getColor(R.color.colorThemeOrange));
            holder.layout_bottom_2.setVisibility(View.GONE);
            holder.layout_bottom_1.setVisibility(View.VISIBLE);
            holder.tv_btn.setBackgroundResource(R.drawable.shape_background_theme_orange);
        } else if(arr_order_title.get(position).equals("Online Order")){
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_online_order));
            holder.layout_top.setBackgroundResource(R.drawable.shape_background_theme_light_blue);
            holder.layout_timer.setBackgroundResource(R.drawable.shape_border_theme_light_blue);
            holder.tv_timer.setBackgroundResource(R.color.colorThemeLightBlue);
            holder.tv_time_left.setTextColor(context.getResources().getColor(R.color.colorThemeLightBlue));
            holder.layout_bottom_2.setVisibility(View.GONE);
            holder.layout_bottom_1.setVisibility(View.VISIBLE);
            holder.tv_btn.setBackgroundResource(R.drawable.shape_background_theme_light_blue);
        } else {

        }


        AdapterMainListItemExpandableList adapter = new AdapterMainListItemExpandableList(context,
                _listDataHeader, _listDataChild, _listDataChild1, arr_order_title.get(position));
        holder.expandableListView.setAdapter(adapter);

        holder.expandableListView.expandGroup(0);
        holder.expandableListView.expandGroup(1);
        holder.expandableListView.expandGroup(2);

    }

    @Override
    public int getItemCount() {
        return 9;
    }

}