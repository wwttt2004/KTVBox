package com.yiqiding.ktvbox.widget.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.widget.fragment.StarSongListFragment;
import com.yiqiding.ktvbox.widget.fragment.YqcRankGridListFragment;

/**
 * RankYqcPagerListAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * KTVBox
 * Created by culm on 13-12-26
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class StarSongListPagerListAdapter extends PagerAdapter{

    private StarSongListFragment context;

    public StarSongListPagerListAdapter(StarSongListFragment context)
    {
        this.context=context;
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
        DisplayMetrics displayMetrics= ViewUtils.getScreenResolution(context.getActivity());
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (displayMetrics.heightPixels*(905/1200.0f)));
        gv_content.setLayoutParams(layoutParams);
        gv_content.setNumColumns(3);
        gv_content.setVerticalSpacing(-(int) (displayMetrics.heightPixels * (72 / 1200.0f)));
        gv_content.setHorizontalSpacing(-(int) (displayMetrics.widthPixels * (77 / 1920f)));
        gv_content.setAdapter(new StarSongListGridListAdapter(context));
        //gv_content.setPadding(0, -(int) (displayMetrics.heightPixels * (60 / 1200f)), 0, 0);
        gv_content.setSelector(R.drawable.blank_button);
        container.addView(gv_content);
        return gv_content;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
