package com.example.akash004.sdl_cargo_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;import java.util.List;

public class productlistadapter extends BaseAdapter {

    private Context mContent;
    private List<init_Ship> mproductlist;


    public productlistadapter(Context mContent, List<init_Ship> mproductlist) {
        this.mContent = mContent;
        this.mproductlist = mproductlist;
    }

    @Override
    public int getCount() {
        return mproductlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mproductlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(mContent,R.layout.listadapterview,null);
        TextView Sname = (TextView)v.findViewById(R.id.Ship_Name);
        TextView Sid = (TextView)v.findViewById(R.id.Ship_Id);
        TextView dod = v.findViewById(R.id.Time_of_Departure);
        TextView doa = v.findViewById(R.id.Time_of_Arrival);


        Sname.setText(mproductlist.get(i).getShip_name());
        Sid.setText(mproductlist.get(i).getShip_id());
        dod.setText(mproductlist.get(i).getDeparture_Time());
        doa.setText(mproductlist.get(i).getArrival_Time());

        v.setTag(mproductlist.get(i).getId());

        return v;
    }
}
