package com.yiqiding.ktvbox.widget.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.infomanage.SingerInfoStructure;
import com.yiqiding.ktvbox.util.SyncCommonLoadImage;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.view.BaseActivity;
import com.yiqiding.ktvbox.view.MainViewActivity;
import com.yiqiding.ktvbox.view.SingerStartAllViewActivity;
import com.yiqiding.ktvbox.widget.fragment.StarSongListFragment;

import java.util.List;

/**
 * StartGridListAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * <p/>
 * Created by culm on 上午10:34:10.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class StarGridListAdapter extends BaseAdapter implements View.OnClickListener {

    public static final String TAG = StarGridListAdapter.class.getSimpleName();

    private SingerStartAllViewActivity mContext;
    private List<SingerInfoStructure> list_data;
    private int pageIndex;
    private DisplayMetrics displayMetrics;

    public StarGridListAdapter(SingerStartAllViewActivity context, List<SingerInfoStructure> list_data, int pageIndex) {
        this.mContext = context;
        this.list_data = list_data;
        this.pageIndex = pageIndex;
        this.displayMetrics = ViewUtils.getScreenResolution(mContext);
    }

    public StarGridListAdapter(SingerStartAllViewActivity context, List<SingerInfoStructure> list_data) {
        this.mContext = context;
        this.list_data = list_data;
        this.pageIndex = 0;
        this.displayMetrics = ViewUtils.getScreenResolution(mContext);
    }

    @Override
    public int getCount() {
        return 12;
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
        convertView = new FrameLayout(mContext);
        try {
            int width = (int) (displayMetrics.widthPixels * (376d / 1920.0d));
            int height = (int) (displayMetrics.widthPixels * (376d / 1920.0d) * 1.19681);
            //init main view layoutParams
            GridView.LayoutParams layoutParams = new GridView.LayoutParams(width, height);
            String IMG_URL;
            //add View
            switch (position / 3) {
                case 1:
                    convertView.setBackgroundResource(R.drawable.star_blue_bg);
                    IMG_URL = "http://e.hiphotos.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=0558e3eb34d3d539d53007915bee8235/738b4710b912c8fc0f7c35d8fe039245d788d43f87940924.jpg";
                    break;
                case 2:
                    convertView.setBackgroundResource(R.drawable.star_purple_bg);
                    IMG_URL = "http://d.hiphotos.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=ccc9e6faabec8a1300175fb2966afaea/3b292df5e0fe992527d4ab4536a85edf8cb1cb13485459f7.jpg";
                    break;
                case 0:
                    convertView.setBackgroundResource(R.drawable.star_red_bg);
                    IMG_URL = "http://g.hiphotos.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=2de90cc8a964034f1bc0ca54ceaa1254/11385343fbf2b211f501475bc88065380dd7912397ddaec4.jpg";
                    break;
                default:
                    convertView.setBackgroundResource(R.drawable.star_red_bg);
                    IMG_URL = "http://f.hiphotos.baidu.com/baike/c0%3Dbaike72%2C5%2C5%2C72%2C24/sign=8a6631327d3e6709aa0d4dad5aaef458/a8ec8a13632762d0dae369aaa2ec08fa503d269758eee6fa.jpg";
                    break;
            }
            //
            ImageView iv_avatar = new ImageView(mContext);
            FrameLayout.LayoutParams fl_params = new FrameLayout.LayoutParams(((int) (displayMetrics.widthPixels * (227.0f / 1920f))),
                    ((int) (displayMetrics.widthPixels * (220.0f / 1920f))));
            fl_params.topMargin = ((int) (displayMetrics.heightPixels * (75.0f / 1200f)));
            fl_params.leftMargin = ((int) (displayMetrics.heightPixels * (128.0f / 1920f)));
            iv_avatar.setLayoutParams(fl_params);
            iv_avatar.setScaleType(ImageView.ScaleType.FIT_XY);
            new SyncCommonLoadImage().loadImage(
                    IMG_URL, iv_avatar);

            //add topView
            TextView tv_songNum = new TextView(mContext);
            fl_params = new FrameLayout.LayoutParams(((int) (displayMetrics.widthPixels * (88.0f / 1920f))),
                    ((int) (displayMetrics.widthPixels * (88.0f / 1920f))));
            fl_params.leftMargin = ((int) (displayMetrics.widthPixels * (246.0f / 1920.0f)));
            fl_params.topMargin = ((int) (displayMetrics.heightPixels * (57.0f / 1200f)));
            tv_songNum.setLayoutParams(fl_params);
            tv_songNum.setBackgroundResource(R.drawable.tip_song);
            tv_songNum.setTextColor(Color.WHITE);
            tv_songNum.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            tv_songNum.setPadding(0, 0, 0, ((int) (displayMetrics.heightPixels * (13.0f / 1200f))));
            tv_songNum.setTextSize(16);
            tv_songNum.setText("123");

            TextView tv_singer = new TextView(mContext);
            fl_params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            fl_params.topMargin = ((int) (displayMetrics.widthPixels * (295.0f / 1920f)));
            fl_params.leftMargin = ((int) (displayMetrics.heightPixels * (145.0f / 1920f)));
            tv_singer.setTextColor(Color.WHITE);
            tv_singer.setTextSize(18);
            tv_singer.setSingleLine(true);
            tv_singer.setText("安西 光义");
            tv_singer.setLayoutParams(fl_params);

            ImageView iv_line = new ImageView(mContext);
            fl_params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            fl_params.leftMargin = ((int) (displayMetrics.widthPixels * (86.0f / 1920)));
            fl_params.topMargin = ((int) (displayMetrics.heightPixels * (355.0f / 1200f)));
            iv_line.setLayoutParams(fl_params);
            iv_line.setImageResource(R.drawable.line);

            //Ratting Bar
            RatingBar rb_score = (RatingBar) ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.star_ratingbar, null);
            rb_score.setNumStars(5);
            rb_score.setRating(3.5f);
            fl_params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    ((int) (displayMetrics.heightPixels * (18.0f / 1200f))));
            fl_params.leftMargin = ((int) (displayMetrics.widthPixels * (86.0f / 1920)));
            fl_params.topMargin = ((int) (displayMetrics.heightPixels * (366.0f / 1200f)));
            rb_score.setLayoutParams(fl_params);

            ((FrameLayout) convertView).addView(iv_avatar);
            ((FrameLayout) convertView).addView(tv_songNum);
            ((FrameLayout) convertView).addView(tv_singer);
            ((FrameLayout) convertView).addView(iv_line);
            ((FrameLayout) convertView).addView(rb_score);

            ((FrameLayout) convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

            convertView.setOnClickListener(this);

            convertView.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return convertView;
        }
    }

    @Override
    public void onClick(View v) {
        ((MainViewActivity)mContext.getParent()).gotoActivity(new StarSongListFragment());
    }
/*
   static class ViewHolder
    {
        TextView tv_name;
        TextView tv_songnum;
        ImageView iv_avatar;
        ImageView iv_line;
        RatingBar rb_score;
    }
*/

}
