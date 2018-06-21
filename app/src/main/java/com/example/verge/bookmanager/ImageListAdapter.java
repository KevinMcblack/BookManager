package com.example.verge.bookmanager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by KevinWang on 2018/6/20.
 */

public class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;
    public ImageListAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.item, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        Glide
                .with(context)
                .load(imageUrls[position])
/*
                .override(120,200) //图片显示的分辨率 ，像素值 可以转化为DP再设置
*/
                .into((ImageView) convertView);

        return convertView;
    }
}
