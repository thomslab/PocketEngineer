package com.thomslab.proengineer;

/**
 * Created by mitohida on 8/7/2016.
 */

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thomslab.proengineer.electricaltab.CurrentCalculation;

import java.util.ArrayList;
import java.util.List;


//Our class extending fragment
public class electrical extends Fragment{

    String[] listTitle;
    TypedArray ImageTitle;

    List<MenuRowItem> menuRowItems;
    ListView Electrical_Listview;


    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in classes
        View view = inflater.inflate(R.layout.electrical_tab, container, false);
        menuRowItems = new ArrayList<MenuRowItem>();

        listTitle = getResources().getStringArray(R.array.electrical_menu);

        ImageTitle = getResources().obtainTypedArray(R.array.electrical_icons);

        for (int i = 0; i < listTitle.length; i++) {
            MenuRowItem item = new MenuRowItem(ImageTitle.getResourceId(i,-1),listTitle[i]);
            menuRowItems.add(item);
        }

        Electrical_Listview = (ListView) view.findViewById(R.id.list_electrical);
        CustomAdapter adapter = new CustomAdapter(getContext(), menuRowItems);
        Electrical_Listview.setAdapter(adapter);

        Electrical_Listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent myIntent = new Intent(view.getContext(), CurrentCalculation.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });

        return view;

    }

}