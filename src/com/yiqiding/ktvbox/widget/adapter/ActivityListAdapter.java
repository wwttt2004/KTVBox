package com.yiqiding.ktvbox.widget.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewUtils;

/**
 * ActivityListAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * <p/>
 * Created by culm on 13-12-4.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class ActivityListAdapter extends BaseAdapter {
    public static final String TAG=ActivityListAdapter.class.getName();


    private Activity activity;

    private LayoutInflater layoutInflater;

    public ActivityListAdapter(Activity activity)
    {
        this.activity =activity;
        this.layoutInflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.activity_hori_list_item,null);
        int width=(int) (ViewUtils.getScreenResolution(activity).widthPixels*0.26);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(width, (int) (width * (710.0/484)));

        view.setLayoutParams(layoutParams);

        ImageView iv_bg= (ImageView) view.findViewById(R.id.iv_bg);

        switch (i%5)
        {
            case 0:
                iv_bg.setImageResource(R.drawable.at_bg_blue);
                break;
            case 1:
                iv_bg.setImageResource(R.drawable.at_bg_green);
                break;
            case 2:
                iv_bg.setImageResource(R.drawable.at_bg_pink);
                break;
            case 3:
                iv_bg.setImageResource(R.drawable.at_bg_purple);
                break;
            case 4:
                iv_bg.setImageResource(R.drawable.at_bg_red);
                break;
        }

        return view;
    }
}