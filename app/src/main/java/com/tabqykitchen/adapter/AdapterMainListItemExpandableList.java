package com.tabqykitchen.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tabqykitchen.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vaibhav on 7/25/2018.
 */
public class AdapterMainListItemExpandableList extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private HashMap<String, List<String>> _listDataChild1;
    private String title;
//    private InterfaceAdapterMainListItemExpandableList click;

    public AdapterMainListItemExpandableList(Context context, List<String> listDataHeader,
                                             HashMap<String, List<String>> listChildData,
                                             HashMap<String, List<String>> _listDataChild1,/*,
                                             InterfaceAdapterMainListItemExpandableList click*/String title) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._listDataChild1 = _listDataChild1;
//        this.click = click;
        this.title = title;
    }

/*
    private interface InterfaceAdapterMainListItemExpandableList{
        public void method_AdapterMainListItemExpandableList(int pos);
    }
*/

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_expandable_list_item, null);
        }

        TextView txtListChild =  convertView
                .findViewById(R.id.item_main_expandable_list_item_text);

        txtListChild.setText(childText);

        LinearLayout layout_checkbox = convertView
                .findViewById(R.id.item_main_list_expandable_list_item_checkbox);
        LinearLayout layout_checkbox_1 = convertView
                .findViewById(R.id.item_main_list_expandable_list_item_checkbox_1);
        TextView tv_dot = convertView.findViewById(R.id.item_main_list_expandable_list_item_dot);

        if(_listDataChild1.get(_listDataHeader.get(groupPosition)).get(childPosition).equals("0")){
//            layout_checkbox.setBackgroundResource(R.drawable.shape_border_theme_pink);
            layout_checkbox.setVisibility(View.VISIBLE);
            layout_checkbox_1.setVisibility(View.GONE);
        } else{
            layout_checkbox.setVisibility(View.GONE);
            layout_checkbox_1.setVisibility(View.VISIBLE);
//            layout_checkbox.setBackgroundResource(R.drawable.shape_background_theme_pink);
        }

        if(title.equals("Take Away Order")){

            layout_checkbox.setVisibility(View.GONE);
            layout_checkbox_1.setVisibility(View.GONE);
            tv_dot.setVisibility(View.VISIBLE);
            tv_dot.setBackgroundResource(R.drawable.shape_border_theme_orange);
        } else if(title.equals("Online Order")){

            layout_checkbox.setVisibility(View.GONE);
            layout_checkbox_1.setVisibility(View.GONE);
            tv_dot.setVisibility(View.VISIBLE);
            tv_dot.setBackgroundResource(R.drawable.shape_border_theme_light_blue);
        } else{
            tv_dot.setVisibility(View.GONE);
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_expandable_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.item_main_expandable_list_group_text);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}