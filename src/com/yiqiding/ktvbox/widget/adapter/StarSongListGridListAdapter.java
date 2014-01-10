package com.yiqiding.ktvbox.widget.adapter;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.SyncCommonLoadImage;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.widget.fragment.StarSongListFragment;

/**
 * StarSongListGridListAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * KTVBox
 * Created by culm on 13-12-27
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class StarSongListGridListAdapter extends BaseAdapter {

    private StarSongListFragment mContext;
    private int CONSTANT_LARGE_FONT_SIZE = 19;
    private int CONSTANT_SMALL_FONT_SIZE = 16;
    private TranslateAnimation tl_preview_show;
    private TranslateAnimation tl_preview_hide;
    private TranslateAnimation tl_delete_show;
    private TranslateAnimation tl_delete_hide;
    private TranslateAnimation tl_first_show;
    private TranslateAnimation tl_first_hide;

    public StarSongListGridListAdapter(StarSongListFragment context) {
        this.mContext = context;
        initAnimation();
    }

    @Override
    public int getCount() {
        return 9;
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

        ViewHolder viewHolder = null;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = initViewItem(viewHolder);
            convertView.setTag(viewHolder);
        }

        return convertView;
    }

    private View initViewItem(ViewHolder viewHolder) {

        DisplayMetrics displayMetrics = ViewUtils.getScreenResolution(mContext.getActivity());

        View mainView = new AbsoluteLayout(mContext.getActivity());

        AbsListView.LayoutParams as_params = new AbsListView.LayoutParams(
                ((int) (displayMetrics.widthPixels * (689.0f / 1920f))), (int) (displayMetrics.heightPixels * (334.0 / 1200.0f))
        );

        mainView.setLayoutParams(as_params);

        viewHolder = new ViewHolder();

        viewHolder.iv_avatar = new ImageView(mContext.getActivity());
        viewHolder.iv_avatar_bg = new ImageView(mContext.getActivity());
        viewHolder.cb_infomation_bg = new CheckBox(mContext.getActivity());
        viewHolder.tv_singer = new TextView(mContext.getActivity());
        viewHolder.tv_position = new TextView(mContext.getActivity());
        viewHolder.tv_title = new TextView(mContext.getActivity());
        viewHolder.cb_delete = new CheckBox(mContext.getActivity());
        viewHolder.cb_first = new CheckBox(mContext.getActivity());
        viewHolder.cb_preview = new CheckBox(mContext.getActivity());
        viewHolder.cb_delete.setButtonDrawable(R.drawable.blank_button);
        viewHolder.cb_first.setButtonDrawable(R.drawable.blank_button);
        viewHolder.cb_preview.setButtonDrawable(R.drawable.blank_button);

        AbsoluteLayout.LayoutParams al_params = new AbsoluteLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (304.0f / 1920.0f)),
                ((int) (displayMetrics.heightPixels * (304.0f / 1200.0f))),
                0, (int) (displayMetrics.heightPixels * (15.0f / 1920.0f))
        );

        viewHolder.iv_avatar_bg.setLayoutParams(al_params);
        viewHolder.iv_avatar_bg.setImageResource(R.drawable.song_list_pic_bg);

        al_params = new AbsoluteLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (195.0f / 1920.0f)),
                ((int) (displayMetrics.heightPixels * (195.0f / 1200.0f))),
                (int) (displayMetrics.widthPixels * (53.0f / 1920.0f)), (int) (displayMetrics.heightPixels * (47.0 / 1200.0f))
        );
        viewHolder.iv_avatar.setLayoutParams(al_params);
        new SyncCommonLoadImage().loadImage("http://wenwen.soso.com/p/20120131/20120131191714-1979069020.jpg", viewHolder.iv_avatar);

        al_params = new AbsoluteLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (454.0f / 1920.0f)),
                ((int) (displayMetrics.heightPixels * (334.0f / 1200.0f))),
                (int) (displayMetrics.widthPixels * (200.0f / 1920.0f)), -(int) (displayMetrics.heightPixels * (7.0 / 1200.0f))
        );


        AbsoluteLayout ab_infomation = new AbsoluteLayout(mContext.getActivity());
        ab_infomation.setLayoutParams(al_params);
        al_params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0, 0);

        viewHolder.cb_infomation_bg.setLayoutParams(al_params);
        viewHolder.cb_infomation_bg.setBackground(mContext.getActivity().getResources().getDrawable(R.drawable.btn_song_list_information_bg));
        viewHolder.cb_infomation_bg.setButtonDrawable(R.drawable.blank_button);

        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (120.0f / 1920.0f)),
                (int) (displayMetrics.widthPixels * (120.0f / 1920.0f)),
                (int) (displayMetrics.widthPixels * (82.0f / 1920.0f)),
                (int) (displayMetrics.heightPixels * (62.0f / 1200)));
        viewHolder.tv_title.setLayoutParams(al_params);
        viewHolder.tv_title.setTextSize(CONSTANT_LARGE_FONT_SIZE);
        viewHolder.tv_title.setTextColor(Color.WHITE);
        viewHolder.tv_title.setText("バクチ・ダンサー");
        viewHolder.tv_title.setSingleLine();

        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * (120.0f / 1920.0f)),
                (int) (displayMetrics.widthPixels * (120.0f / 1920.0f)),
                (int) (displayMetrics.widthPixels * (82.0f / 1920.0f)),
                (int) (displayMetrics.heightPixels * (108.0f / 1200)));
        viewHolder.tv_singer.setLayoutParams(al_params);
        viewHolder.tv_singer.setTextSize(CONSTANT_SMALL_FONT_SIZE);
        viewHolder.tv_singer.setTextColor(Color.WHITE);
        viewHolder.tv_singer.setAlpha(0.5f);
        viewHolder.tv_singer.setText("DOES");
        viewHolder.tv_singer.setSingleLine();

        al_params = new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0,
                (int) (displayMetrics.heightPixels * (62.0f / 1200)));
        viewHolder.tv_position.setLayoutParams(al_params);
        viewHolder.tv_position.setGravity(Gravity.RIGHT);
        viewHolder.tv_position.setPadding(0, 0, (int) (displayMetrics.widthPixels * (82.0f / 1920.0f)), 0);
        viewHolder.tv_position.setTextSize(CONSTANT_LARGE_FONT_SIZE);
        viewHolder.tv_position.setTextColor(Color.WHITE);
        viewHolder.tv_position.setText("(约6)");
        viewHolder.tv_position.setSingleLine();

        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * 0.044)
                , (int) (displayMetrics.heightPixels * 0.1),
                (int) (displayMetrics.widthPixels * (67.0f / 1920.0f))
                //0
                , (int) (displayMetrics.heightPixels * (136.0f / 1200.0f))
        );
        viewHolder.cb_first.setBackgroundResource(R.drawable.btn_song_list_priority);
        viewHolder.cb_first.setLayoutParams(al_params);

        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * 0.044)
                , (int) (displayMetrics.heightPixels * 0.1),
                (int) (displayMetrics.widthPixels * (182.0f / 1920.0f))
                //(int) (displayMetrics.widthPixels * (297.0f / 1920.0f))
                , (int) (displayMetrics.heightPixels * (136.0f / 1200.0f))
        );
        viewHolder.cb_preview.setBackgroundResource(R.drawable.btn_song_list_preview);
        viewHolder.cb_preview.setLayoutParams(al_params);

        al_params = new AbsoluteLayout.LayoutParams((int) (displayMetrics.widthPixels * 0.044)
                , (int) (displayMetrics.heightPixels * 0.1),
                (int) (displayMetrics.widthPixels * (297.0f / 1920.0f))
                //0
                , (int) (displayMetrics.heightPixels * (136.0f / 1200.0f))
        );

        viewHolder.cb_delete.setBackgroundResource(R.drawable.btn_song_list_delete);
        viewHolder.cb_delete.setLayoutParams(al_params);

        ab_infomation.addView(viewHolder.cb_infomation_bg);
        ab_infomation.addView(viewHolder.tv_title);
        ab_infomation.addView(viewHolder.tv_singer);
        ab_infomation.addView(viewHolder.tv_position);
        ab_infomation.addView(viewHolder.cb_delete);
        ab_infomation.addView(viewHolder.cb_first);
        ab_infomation.addView(viewHolder.cb_preview);

        ((AbsoluteLayout) mainView).addView(viewHolder.iv_avatar_bg);
        ((AbsoluteLayout) mainView).addView(viewHolder.iv_avatar);
        ((AbsoluteLayout) mainView).addView(ab_infomation);

        //set listener
        viewHolder.cb_delete.setOnClickListener(new PreViewClickListener());

        return mainView;
    }

    private void initAnimation() {
        DisplayMetrics displayMetrics=ViewUtils.getScreenResolution(mContext.getActivity());

        //preview show
        tl_preview_show=new TranslateAnimation(((int)displayMetrics.widthPixels*(297.0f/1980.0f)),
                ((int)displayMetrics.widthPixels*(182.0f/1980.0f)),0,0
                );
        tl_preview_show.setFillAfter(true);
        tl_preview_show.setInterpolator(mContext.getActivity(),android.R.interpolator.accelerate_decelerate);
        tl_preview_show.setDuration(300);

        //preview hide
        tl_preview_show=new TranslateAnimation(((int)displayMetrics.widthPixels*(182.0f/1980.0f)),
                ((int)displayMetrics.widthPixels*(297.0f/1980.0f)),0,0
        );
        tl_preview_show.setFillAfter(true);
        tl_preview_show.setInterpolator(mContext.getActivity(),android.R.interpolator.accelerate_decelerate);
        tl_preview_show.setDuration(300);
    }

    private class PreViewClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            CompoundButton compoundButton= (CompoundButton) v;
            if(compoundButton.isChecked())
            {
                compoundButton.startAnimation(tl_preview_show);
            }
            else
            {
                compoundButton.startAnimation(tl_preview_hide);
            }
        }
    }

    static class ViewHolder {
        TextView tv_title;
        TextView tv_singer;
        TextView tv_position;
        ImageView iv_tag;
        ImageView iv_avatar_bg;
        ImageView iv_avatar;
        CheckBox cb_infomation_bg;
        CheckBox cb_first;
        CheckBox cb_preview;
        CheckBox cb_delete;
    }


}
