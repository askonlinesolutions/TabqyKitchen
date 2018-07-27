package com.tabqykitchen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tabqykitchen.R;

import java.util.ArrayList;

public class AdapterMainGrid extends RecyclerView.Adapter<AdapterMainGrid.MyViewHolder> {

    private  Context context;
    private ArrayList<String> arr_total_time = new ArrayList<>();
    private ArrayList<String> arr_order_title = new ArrayList<>();
    private int time;

    public AdapterMainGrid(Context context, ArrayList<String> arr_total_time, ArrayList<String> arr_order_title,
                           int time){
        this.context = context;
        this.arr_total_time = arr_total_time;
        this.arr_order_title = arr_order_title;
        this.time = time;

//        String s = "";
//        for(int i=0; i<arr_total_time.size(); i++){
//            s = s + (((float)(300/(Float.parseFloat(arr_total_time.get(i)))) * time) + "\t");
//        }
//        Log.d("MyTimer", s + "\n");
     }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_timer, tv_time_left, tv_title;
        public RelativeLayout layout_top;
        public LinearLayout layout_timer;
        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);

            tv_title = view.findViewById(R.id.item_main_grid_title_name);
            iv = view.findViewById(R.id.item_main_grid_image);
            tv_timer = view.findViewById(R.id.item_main_grid_timer);
            tv_time_left = view.findViewById(R.id.item_main_grid_time_left);
            layout_top = view.findViewById(R.id.item_main_grid_layout_top);
            layout_timer = view.findViewById(R.id.item_main_grid_layout_timer);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_grid, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

   /*     RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                .WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
*/

        float total_time = Float.parseFloat(arr_total_time.get(position));
        float width_per_second = 300/total_time;

        holder.tv_timer.setWidth((int)((width_per_second*time) + .5f));
//        Log.d("MyTimer", width_per_second*time + "");
        if(total_time-time >=0) {
            holder.tv_time_left.setText(total_time - time/*width_per_second*time*/ + "");
            /*if (width_per_second*time < 270) {
                params.setMargins((int) ((width_per_second * time) + .5f), 0, 0, 0);
                holder.tv_time_left.setLayoutParams(params);
            } else{
                params.setMargins((int) ((270) + .5f), 0, 0, 0);
                holder.tv_time_left.setLayoutParams(params);
            }*/
        } else{
            holder.tv_time_left.setText(0.0+"");
            /*params.setMargins((int) ((270) + .5f), 0, 0, 0);
            holder.tv_time_left.setLayoutParams(params);*/
        }


        holder.tv_title.setText(arr_order_title.get(position));

        if(arr_order_title.get(position).equals("Table: 02")){
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_table));
            holder.layout_top.setBackgroundResource(R.drawable.shape_background_theme_blue);
            holder.layout_timer.setBackgroundResource(R.drawable.shape_border_theme_blue);
            holder.tv_timer.setBackgroundResource(R.color.colorThemeBlue);
            holder.tv_time_left.setTextColor(context.getResources().getColor(R.color.colorThemeBlue));
        } else if(arr_order_title.get(position).equals("Take Away Order")){
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bag));
            holder.layout_top.setBackgroundResource(R.drawable.shape_background_theme_orange);
            holder.layout_timer.setBackgroundResource(R.drawable.shape_border_theme_orange);
            holder.tv_timer.setBackgroundResource(R.color.colorThemeOrange);
            holder.tv_time_left.setTextColor(context.getResources().getColor(R.color.colorThemeOrange));
        } else if(arr_order_title.get(position).equals("Online Order")){
            holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_online_order));
            holder.layout_top.setBackgroundResource(R.drawable.shape_background_theme_light_blue);
            holder.layout_timer.setBackgroundResource(R.drawable.shape_border_theme_light_blue);
            holder.tv_timer.setBackgroundResource(R.color.colorThemeLightBlue);
            holder.tv_time_left.setTextColor(context.getResources().getColor(R.color.colorThemeLightBlue));
        } else {

        }



    }

    @Override
    public int getItemCount() {
        return arr_total_time.size();
    }
}