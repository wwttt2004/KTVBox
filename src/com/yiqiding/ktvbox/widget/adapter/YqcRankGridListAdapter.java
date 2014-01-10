package com.yiqiding.ktvbox.widget.adapter;

import android.graphics.Color;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.SyncCommonLoadImage;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.widget.MarqueeTextView;
import com.yiqiding.ktvbox.widget.fragment.YqcRankGridListFragment;

/**
 * YqcRankGridListAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * KTVBox
 * Created by culm on 13-12-26
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class YqcRankGridListAdapter extends BaseAdapter {
    private YqcRankGridListFragment mContext;

    private int CONSTANT_TEXTSIZE_BIG = 23;
    private int CONSTANT_TEXTSIZE_SMALL = 20;


    public YqcRankGridListAdapter(YqcRankGridListFragment context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = new AbsoluteLayout(mContext.getActivity());
        DisplayMetrics displayMetrics = ViewUtils.getScreenResolution(mContext.getActivity());
        AbsListView.LayoutParams vl_params = new AbsListView.LayoutParams(
                (int) (displayMetrics.widthPixels * (0x1.dbcc48p-4f)),
                (int) (displayMetrics.heightPixels * (334.31 / 1200f))
        );
        convertView.setLayoutParams(vl_params);
        ImageView iv_icon = new ImageView(mContext.getActivity());
        iv_icon.setImageResource(R.drawable.tip_recommend);
        AbsoluteLayout.LayoutParams al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (76 / 1920.0f)), (int) (displayMetrics.heightPixels * (76 / 1200f)), 0, 0);
        iv_icon.setLayoutParams(al_params);

        ImageView iv_avatar = new ImageView(mContext.getActivity());
        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (187 / 1920.0f)), (int) (displayMetrics.heightPixels * (192 / 1200f)), (int) (displayMetrics.widthPixels * (30 / 1920.0f)), (int) (displayMetrics.widthPixels * (20 / 1920.0f)));
        iv_avatar.setImageResource(R.drawable.test);
        iv_avatar.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_avatar.setLayoutParams(al_params);

        //new SyncCommonLoadImage().loadImage("http://e.hiphotos.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=0558e3eb34d3d539d53007915bee8235/738b4710b912c8fc0f7c35d8fe039245d788d43f87940924.jpg",iv_avatar);

        //content
        ImageView iv_bg = new ImageView(mContext.getActivity());
        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (210 / 1920.0f)), (int) (displayMetrics.widthPixels * (210 / 1920.0f)), (int) (displayMetrics.widthPixels * (20 / 1920.0f)), (int) (displayMetrics.widthPixels * (20 / 1920.0f)));
        iv_bg.setLayoutParams(al_params);
        iv_bg.setImageResource(R.drawable.cd);
        iv_bg.setScaleType(ImageView.ScaleType.FIT_XY);

        MarqueeTextView tv_title = new MarqueeTextView(mContext.getActivity());
        //tv_title.setText("轻音乐");
        tv_title.setTextSize(CONSTANT_TEXTSIZE_BIG);
        tv_title.setSingleLine();
        tv_title.setGravity(Gravity.CENTER);
        tv_title.setTextColor(Color.WHITE);
        //tv_title.setPadding((int) (displayMetrics.widthPixels * (6.0f / 198)),0,0,0);
        al_params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, (int) (displayMetrics.widthPixels * (25 / 1920.0f)), (int) (displayMetrics.heightPixels * (230.0f / 1200)));
        tv_title.setLayoutParams(al_params);
        tv_title.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
/*        tv_title.setFocusableInTouchMode(true);
        tv_title.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv_title.setFocusable(true);
        tv_title.setMarqueeRepeatLimit(-1);
        tv_title.setHorizontallyScrolling(true);*/

        TextView tv_num = new TextView(mContext.getActivity());
        tv_num.setTextSize(CONSTANT_TEXTSIZE_SMALL);
        tv_num.setSingleLine();
        tv_num.setGravity(Gravity.CENTER);
        tv_num.setTextColor(Color.GRAY);
        tv_num.setPadding(-(int) (displayMetrics.widthPixels * (6.4f / 198)), 0, 0, 0);
        al_params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, (int) (displayMetrics.heightPixels * (280.0f / 1200)));
        tv_num.setLayoutParams(al_params);
        tv_num.setText(String.format("%d首歌", 300));

        ((AbsoluteLayout) convertView).addView(iv_avatar);
        ((AbsoluteLayout) convertView).addView(iv_bg);
        ((AbsoluteLayout) convertView).addView(iv_icon);
        ((AbsoluteLayout) convertView).addView(tv_title);
        ((AbsoluteLayout) convertView).addView(tv_num);

        switch (position) {
            case 0:
                tv_title.setText("你在烦恼什么");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/lpic/s6987806.jpg", iv_avatar);
                break;
            case 1:
                tv_title.setText("盛夏光年 电影主题曲概念专辑");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/mpic/s2171876.jpg", iv_avatar);
                break;
            case 2:
                tv_title.setText("你在烦恼什么");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/lpic/s6987806.jpg", iv_avatar);
                break;
            case 3:
                tv_title.setText("现在开始我爱你");
                new SyncCommonLoadImage().loadImage("http://img3.douban.com/mpic/s4711652.jpg", iv_avatar);
                break;
            case 4:
                tv_title.setText("Live For Today");
                new SyncCommonLoadImage().loadImage("http://img3.douban.com/mpic/s4598993.jpg", iv_avatar);
                break;
            case 5:
                tv_title.setText("Summer Of Love");
                new SyncCommonLoadImage().loadImage("http://img3.douban.com/mpic/s6338285.jpg", iv_avatar);
                break;
            case 6:
                tv_title.setText("Swing到盡");
                new SyncCommonLoadImage().loadImage("http://img3.douban.com/mpic/s6995812.jpg", iv_avatar);
                break;
            case 7:
                tv_title.setText("陌生人");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/mpic/s1647637.jpg", iv_avatar);
                break;
            case 8:
                tv_title.setText("Concert YY 黄伟文作品展演唱会");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/mpic/s22708338.jpg", iv_avatar);
                break;
            case 9:
                tv_title.setText("美丽的爱情");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/mpic/s1417379.jpg", iv_avatar);
                break;
            default:
                tv_title.setText("美丽的爱情");
                new SyncCommonLoadImage().loadImage("http://img5.douban.com/mpic/s1417379.jpg", iv_avatar);
                break;
        }

        return convertView;
    }
}
