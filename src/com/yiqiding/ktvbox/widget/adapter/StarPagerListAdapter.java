package com.yiqiding.ktvbox.widget.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.infomanage.SingerInfoStructure;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.view.SingerStartAllViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * StarPagerListAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * KTVBox
 * Created by culm on 13-12-24
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class StarPagerListAdapter extends PagerAdapter{

    private SingerStartAllViewActivity mContext;
    private List<SingerInfoStructure> list_data;

    public StarPagerListAdapter(SingerStartAllViewActivity context,List<SingerInfoStructure> list_data)
    {
        this.mContext=context;
        this.list_data=list_data;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GridView gv_content = new GridView(container.getContext());
        DisplayMetrics displayMetrics= ViewUtils.getScreenResolution(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                (displayMetrics.widthPixels),
                (int) (displayMetrics.heightPixels * (905 / 1200.0f)));
        gv_content.setLayoutParams(layoutParams);
        gv_content.setNumColumns(6);
        gv_content.setSelector(R.drawable.blank_button);
        gv_content.setAdapter(new StarGridListAdapter(mContext,new ArrayList<SingerInfoStructure>()));
        gv_content
                .setVerticalSpacing(-(int) (displayMetrics.heightPixels * 95 / 1200.0f));
        gv_content
                .setHorizontalSpacing(-(int) (displayMetrics.heightPixels * (63 / 1920.0f)));
        container.addView(gv_content);
        return gv_content;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
