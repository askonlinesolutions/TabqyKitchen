package com.tabqykitchen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tabqykitchen.R;

import java.util.ArrayList;

public class AdapterCatTop extends RecyclerView.Adapter<AdapterCatTop.MyViewHolder> {

    private Context context;
    private ArrayList<String> arr_names = new ArrayList<>();
    private Interface_AdapterOnlineTop click;
    private String tag;

    public AdapterCatTop(Context context, ArrayList<String> arr_names,Interface_AdapterOnlineTop click, String tag){

        this.context = context;
        this.arr_names = arr_names;
        this.click = click;
        this.tag = tag;
     }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;


        public MyViewHolder(View view) {
            super(view);

            tv = view.findViewById(R.id.spinner_layout_txt);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.method_AdapterOnlineTop(getAdapterPosition(),tag);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spinner_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv.setText(arr_names.get(position));

    }

    @Override
    public int getItemCount() {
        return arr_names.size();
    }

    public interface Interface_AdapterOnlineTop {
        public void method_AdapterOnlineTop(int position, String tag);
    }
}