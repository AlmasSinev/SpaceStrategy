package com.plassrever.spacestrategy;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public Integer[] team = {
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
    };



    public ImageAdapter(Context c){
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

    public void setNewValue(int position, int value) {
        team[position] = value;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams((new GridView.LayoutParams(165, 165)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(team[position]);

        return imageView;
    }
}
