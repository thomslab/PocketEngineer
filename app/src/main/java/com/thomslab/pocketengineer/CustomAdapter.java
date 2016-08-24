package com.thomslab.pocketengineer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mitohida on 8/15/2016.
 */

public class CustomAdapter extends BaseAdapter {


    private Context context;
    private List<MenuRowItem> menuRowItems;

    CustomAdapter(Context context, List<MenuRowItem> menuRowItems) {
        this.context = context;
        this.menuRowItems = menuRowItems;
    }

    @Override
    public int getCount() {
        return menuRowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuRowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menuRowItems.indexOf(getItem(position));
    }

    private class ViewHolder{
        ImageView Image_Title;
        TextView List_Title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_view, null);

            viewHolder = new ViewHolder();
            viewHolder.Image_Title = (ImageView) convertView
                    .findViewById(R.id.listview_image);
            viewHolder.List_Title = (TextView) convertView
                    .findViewById(R.id.listview_title);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuRowItem row_pos = menuRowItems.get(position);

        viewHolder.Image_Title.setImageResource(row_pos.getImage_Title());
        viewHolder.List_Title.setText(row_pos.getList_Title());

        return convertView;
    }
}
