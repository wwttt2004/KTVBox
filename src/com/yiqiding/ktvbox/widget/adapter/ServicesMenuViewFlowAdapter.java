package com.yiqiding.ktvbox.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.BitmapUtils;

/**
 * SingMenuViewFlowAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * <p/>
 * Created by culm on 13-12-2.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class ServicesMenuViewFlowAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context mContext;
    private View v_main;
    private ImageView iv_reflect;

    public ServicesMenuViewFlowAdapter(Context context)
    {
        this.mContext=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 1;
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
        view=inflater.inflate(R.layout.main_service_menu_tab_item1,null);
        iv_reflect= (ImageView) view.findViewById(R.id.iv_reflect);
        v_main=view.findViewById(R.id.ll_top);
        return view;
    }


    public void startReflect()throws Exception
    {
        iv_reflect.setImageBitmap(BitmapUtils.createNewReflectedImage(v_main));
    }

}
