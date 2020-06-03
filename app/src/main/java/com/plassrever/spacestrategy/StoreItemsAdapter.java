package com.plassrever.spacestrategy;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreItemsAdapter extends BaseAdapter {

    private Context mContext;

    public Integer[] store;

    public Integer[] redStore = {
            R.drawable.q,
            R.drawable.w,
            R.drawable.e,
            R.drawable.r,
            R.drawable.t,
            R.drawable.y
    };

    public Integer[] blueStore = {
            R.drawable.qq,
            R.drawable.ww,
            R.drawable.ee,
            R.drawable.rr,
            R.drawable.tt,
            R.drawable.yy
    };

    public String[] costs = {
        "100",
        "200",
        "300",
        "400",
        "500",
        "600"
    };




    public StoreItemsAdapter(Context c){
        mContext = c;
    }

    public void setTeam (boolean isBlue){
        if (isBlue)
            store = blueStore;
        else
            store = redStore;
    }

    @Override
    public int getCount() {
        return store.length;
    }

    @Override
    public Object getItem(int position) {
        return store[position];
    }

    @Override
    public long getItemId(int value) {
        int position = -1;
        for (int i = 0; i < store.length; i++)
            if (store[i] == value)
                position = i;

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;

        if (convertView == null){

            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = grid.findViewById(R.id.imagepart);
        imageView.setImageResource(store[position]);


        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/ELECT.TTF");
        TextView textView = grid.findViewById(R.id.textpart);
        textView.setText(costs[position]);
        textView.setTypeface(font);

        return grid;
    }
}
