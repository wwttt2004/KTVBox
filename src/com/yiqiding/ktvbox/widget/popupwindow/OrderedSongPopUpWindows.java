package com.yiqiding.ktvbox.widget.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.yiqiding.ktvbox.R;

/**
 * OrderedSongPopUpWindows.java
 * com.yiqiding.ktvbox.widget.popupwindow
 * <p/>
 * Created by culm on 13-12-2.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class OrderedSongPopUpWindows extends PopupWindow{

    private Context context;
    private View mView;

    public OrderedSongPopUpWindows(Context context)
    {
        this.context=context;
        try {
            initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() throws Exception{

        LayoutInflater mInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mView=mInflater.inflate(R.layout.popup_ordersongs,null);

        setContentView(mView);


    }


}
