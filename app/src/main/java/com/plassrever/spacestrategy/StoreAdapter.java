package com.plassrever.spacestrategy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class StoreAdapter extends BaseAdapter {

    private Context mContext;

    public Integer[] team = {
            R.drawable.q,
            R.drawable.w,
            R.drawable.e,
            R.drawable.r,
            R.drawable.t,
            R.drawable.y
    };



    public StoreAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return team.length;
    }

    @Override
    public Object getItem(int position) {
        return team[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams((new GridView.LayoutParams(250, 250)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(18, 8, 18, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(team[position]);

        return imageView;
    }
}
