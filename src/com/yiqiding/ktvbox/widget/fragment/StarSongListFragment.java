package com.yiqiding.ktvbox.widget.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.view.MainViewActivity;
import com.yiqiding.ktvbox.widget.adapter.StarSongListPagerListAdapter;

/**
 * StarSongListFragment.java
 * com.yiqiding.ktvbox.widget.fragment
 * KTVBox
 * Created by culm on 13-12-26
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class StarSongListFragment extends BaseFragment {

    private TextView tv_pageNums;
    private LinearLayout ll_bottom;
    private ViewPager viewPager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new StarSongListPagerListAdapter(this));
        viewPager.setOnPageChangeListener(new PagerListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initContentView();
        return mainView;
    }

    @Override
    protected void initContentView() {
        mainView = new AbsoluteLayout(getActivity());
        DisplayMetrics displayMetrics = ViewUtils.getScreenResolution(getActivity());
        AbsoluteLayout.LayoutParams al_params = new AbsoluteLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels, 0, 0);
        mainView.setLayoutParams(al_params);
        ImageView iv_logo = new ImageView(getActivity());
        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (46.0f / 1920f)), (int) (displayMetrics.heightPixels * (46.0f / 1200)),
                (int) (displayMetrics.widthPixels * (70.0f / 1920f)), (int) (displayMetrics.widthPixels * (30.0f / 1920f)));

        iv_logo.setLayoutParams(al_params);
        iv_logo.setImageResource(R.drawable.yqc_logo);

        TextView tv_title = new TextView(getActivity());
        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (300.0f / 1920f)), AbsoluteLayout.LayoutParams.WRAP_CONTENT,
                (int) (displayMetrics.widthPixels * (125.0f / 1920f)), (int) (displayMetrics.widthPixels * (25.0f / 1920f)));

        tv_title.setLayoutParams(al_params);
        tv_title.setTextColor(Color.WHITE);
        tv_title.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        tv_title.setSingleLine();
        tv_title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        tv_title.setTextSize(CONSTANT_FONT_SIZE);
        tv_title.setText(getString(R.string.rank_yqc_title));
        viewPager = new ViewPager(this.getActivity());
        al_params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (displayMetrics.widthPixels * (30.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (86.0f / 1200f)));
        viewPager.setLayoutParams(al_params);
        LinearLayout ll_bottom = new LinearLayout(getActivity());
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (displayMetrics.heightPixels * 0.1), 0, (int) (displayMetrics.heightPixels*(906.0f/1200)));
        ll_bottom.setLayoutParams(layoutParams);

        // bottom content
        Button btn_sq_az = new Button(getActivity());
        LinearLayout.LayoutParams liParams = new LinearLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (199.0f / 1920f)),
                LinearLayout.LayoutParams.MATCH_PARENT);
        btn_sq_az.setLayoutParams(liParams);
        btn_sq_az.setBackgroundResource(R.drawable.btn_singerstar_az);

        Button btn_sq_first = new Button(getActivity());
        liParams = new LinearLayout.LayoutParams(
                (int) (displayMetrics.heightPixels * 0.1 * (242.0f / 120.0f)),
                LinearLayout.LayoutParams.MATCH_PARENT);
        liParams.leftMargin = (int) (displayMetrics.heightPixels * (2 / 70.0f));
        btn_sq_first.setBackgroundResource(R.drawable.star_first_letter);

        Button btn_prePage = new Button(getActivity());
        liParams = new LinearLayout.LayoutParams((int) (displayMetrics.heightPixels
                * (8.0 / 120f) * (7f / 8.0f)),
                (int) (displayMetrics.heightPixels * (8.0 / 120f)));
        liParams.gravity = Gravity.CENTER_VERTICAL;
        liParams.leftMargin = (int) (displayMetrics.widthPixels * (12.0 / 70f));
        btn_prePage.setLayoutParams(liParams);
        btn_prePage.setBackgroundResource(R.drawable.star_left_arrow_letter);
        //btn_prePage.setOnClickListener(new PagerClickListener(PagerClickListener.ACTION_PRE));

        tv_pageNums = new TextView(getActivity());
        tv_pageNums.setTextColor(Color.WHITE);
        tv_pageNums.setTextSize(24);
        tv_pageNums.setText("1/20");
        tv_pageNums.setGravity(Gravity.CENTER);
        liParams = new LinearLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (8.0 / 70f)),
                LinearLayout.LayoutParams.MATCH_PARENT);
        liParams.leftMargin = (int) (displayMetrics.widthPixels * (1.0 / 70f));
        tv_pageNums.setLayoutParams(liParams);

        Button btn_nextPage = new Button(getActivity());
        //btn_nextPage.setOnClickListener(new PagerClickListener(PagerClickListener.ACTION_NEXT));
        liParams = new LinearLayout.LayoutParams((int) (displayMetrics.heightPixels
                * (8.0 / 120f) * (7f / 8.0f)),
                (int) (displayMetrics.heightPixels * (8.0 / 120f)));
        liParams.gravity = Gravity.CENTER_VERTICAL;
        liParams.leftMargin = (int) (displayMetrics.widthPixels * (1.0 / 70f));
        btn_nextPage.setLayoutParams(liParams);
        btn_nextPage.setBackgroundResource(R.drawable.star_right_arrow_letter);

        Button btn_home = new Button(getActivity());
        liParams = new LinearLayout.LayoutParams((int) (displayMetrics.heightPixels
                * (12.0 / 120f) * (199f / 120.0f)),
                (int) (displayMetrics.heightPixels * (12.0 / 120f)));
        liParams.gravity = Gravity.CENTER_VERTICAL ;
        liParams.leftMargin = (int) (displayMetrics.widthPixels * (12.0 / 70f));
        btn_home.setLayoutParams(liParams);
        btn_home.setBackgroundResource(R.drawable.btn_home);
        btn_home.setOnClickListener(new BackClickListener());

        Button btn_back = new Button(getActivity());
        liParams = new LinearLayout.LayoutParams((int) (displayMetrics.heightPixels
                * (12.0 / 120f) * (199f / 120.0f)),
                (int) (displayMetrics.heightPixels * (12.0 / 120f)));
        liParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        btn_back.setLayoutParams(liParams);
        btn_back.setBackgroundResource(R.drawable.btn_star_back);
        btn_back.setOnClickListener(new BackClickListener());

        ll_bottom.addView(btn_sq_az);
        ll_bottom.addView(btn_sq_first);
        ll_bottom.addView(btn_prePage);
        ll_bottom.addView(tv_pageNums);
        ll_bottom.addView(btn_nextPage);
        ll_bottom.addView(btn_home);
        ll_bottom.addView(btn_back);


        ((AbsoluteLayout) mainView).addView(iv_logo);
        ((AbsoluteLayout) mainView).addView(tv_title);
        ((AbsoluteLayout) mainView).addView(viewPager);
        ((AbsoluteLayout) mainView).addView(ll_bottom);

    }
    private int CONSTANT_FONT_SIZE = 25;
    private class BackClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ((MainViewActivity) getActivity()).backToFragment();
        }

    }
    private class PagerListener implements ViewPager.OnPageChangeListener
    {

        @Override
        public void onPageScrolled(int i, float v, int i2) {
            tv_pageNums.setText(String.format("%d/%d",i+1,20));
        }

        @Override
        public void onPageSelected(int i) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
