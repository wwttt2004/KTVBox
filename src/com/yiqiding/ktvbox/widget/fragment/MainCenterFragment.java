/**
 *
 *  MainCenterFragment.java
 *  com.yiqiding.ktvbox.widget
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */

package com.yiqiding.ktvbox.widget.fragment;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.view.MainActivitysActivity;
import com.yiqiding.ktvbox.view.MainGamesActivity;
import com.yiqiding.ktvbox.view.MainRankActivity;
import com.yiqiding.ktvbox.view.MainServicesActivity;
import com.yiqiding.ktvbox.view.MainSingActivity;
import com.yiqiding.ktvbox.view.MainViewActivity;

public class MainCenterFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private
    @ViewInject(id = R.id.rb_activity)
    CheckBox rb_activity;
    private
    @ViewInject(id = R.id.rb_game)
    CheckBox rb_game;
    private
    @ViewInject(id = R.id.rb_rank)
    CheckBox rb_rank;
    private
    @ViewInject(id = R.id.rb_service)
    CheckBox rb_service;
    private
    @ViewInject(id = R.id.rb_sing)
    CheckBox rb_sing;
    private
    @ViewInject(id = android.R.id.tabhost)
    TabHost tabHost;
    private
    @ViewInject(id = R.id.rg_menu)
    RadioGroup rg_menu;

    private CheckBox[] rb_all = new CheckBox[5];

    private
    @ViewInject(id = R.id.request_music_arrow)
    ImageView iv_music_arrow;
    private
    @ViewInject(id = R.id.request_music_icon)
    ImageView iv_music_icon;
    private
    @ViewInject(id = R.id.request_music_text)
    ImageView iv_music_text;

    private
    @ViewInject(id = R.id.rank_arrow)
    ImageView iv_rank_arrow;
    private
    @ViewInject(id = R.id.rank_icon)
    ImageView iv_rank_icon;
    private
    @ViewInject(id = R.id.rank_text)
    ImageView iv_rank_text;

    private
    @ViewInject(id = R.id.service_arrow)
    ImageView iv_service_arrow;
    private
    @ViewInject(id = R.id.service_icon)
    ImageView iv_service_icon;
    private
    @ViewInject(id = R.id.service_text)
    ImageView iv_service_text;

    private
    @ViewInject(id = R.id.game_arrow)
    ImageView iv_game_arrow;
    private
    @ViewInject(id = R.id.game_icon)
    ImageView iv_game_icon;
    private
    @ViewInject(id = R.id.game_text)
    ImageView iv_game_text;

    private
    @ViewInject(id = R.id.activity_arrow)
    ImageView iv_activity_arrow;
    private
    @ViewInject(id = R.id.activity_icon)
    ImageView iv_activity_icon;
    private
    @ViewInject(id = R.id.activity_text)
    ImageView iv_activity_text;

    public static final String CONSTANT_TAB_SING = "sing";
    public static final String CONSTANT_TAB_RANK = "rank";
    public static final String CONSTANT_TAB_SERVICES = "services";
    public static final String CONSTANT_TAB_GAME = "game";
    public static final String CONSTANT_TAB_ACTIVITY = "activity";


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initUI(savedInstanceState);
            setListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListener() throws Exception {
        //rg_menu.setOnCheckedChangeListener(this);
    }

    private void initUI(Bundle savedInstanceState) throws Exception {
        LocalActivityManager lam = new LocalActivityManager(getActivity(), false);
        lam.dispatchCreate(savedInstanceState);
        tabHost.setup(lam);
        tabHost.setPadding(tabHost.getPaddingLeft(), tabHost.getPaddingTop(),
                tabHost.getPaddingRight(), tabHost.getPaddingBottom() - 5);
        tabHost.addTab(tabHost.newTabSpec(MainCenterFragment.CONSTANT_TAB_ACTIVITY).setIndicator(MainCenterFragment.CONSTANT_TAB_ACTIVITY).setContent(new Intent(this.getActivity(), MainActivitysActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(MainCenterFragment.CONSTANT_TAB_GAME).setIndicator(MainCenterFragment.CONSTANT_TAB_GAME).setContent(new Intent(this.getActivity(), MainGamesActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(MainCenterFragment.CONSTANT_TAB_RANK).setIndicator(MainCenterFragment.CONSTANT_TAB_RANK).setContent(new Intent(this.getActivity(), MainRankActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(MainCenterFragment.CONSTANT_TAB_SERVICES).setIndicator(MainCenterFragment.CONSTANT_TAB_SERVICES).setContent(new Intent(this.getActivity(), MainServicesActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(MainCenterFragment.CONSTANT_TAB_SING).setIndicator(MainCenterFragment.CONSTANT_TAB_SING).setContent(new Intent(this.getActivity(), MainSingActivity.class)));
        rb_all[0] = rb_sing;
        rb_all[1] = rb_rank;
        rb_all[2] = rb_service;
        rb_all[3] = rb_game;
        rb_all[4] = rb_activity;

        for (CheckBox r : rb_all) {
            r.setOnClickListener(this);
            r.setOnCheckedChangeListener(this);
        }
        rb_sing.setChecked(true);
        tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_back));
        tabHost.setCurrentTabByTag(MainCenterFragment.CONSTANT_TAB_SING);
        tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_next));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_center_fragment, null);
    }
/*

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i)
        {
            case R.id.rb_sing:

                break;
            case R.id.rb_service:

                break;
            case R.id.rb_rank:

                break;
            case R.id.rb_activity:

                break;
            case R.id.rb_game:

                break;
        }
    }
*/


    private void changeToMusicTab() {
        comRadioButtons(View.VISIBLE);
        rb_sing.setVisibility(View.INVISIBLE);
    }

    private void comRadioButtons(int i) {
        for (CheckBox r : rb_all) {
            if (r.getVisibility() == View.INVISIBLE)
                r.setVisibility(i);
        }
    }

    private void changeToRankTab() {
        comRadioButtons(View.VISIBLE);
        rb_rank.setVisibility(View.INVISIBLE);
    }

    private void changeToServiceTab() {
        comRadioButtons(View.VISIBLE);
        rb_service.setVisibility(View.INVISIBLE);
    }

    private void changeToGameTab() {
        comRadioButtons(View.VISIBLE);
        rb_game.setVisibility(View.INVISIBLE);
    }

    private void changeToActivityTab() {
        comRadioButtons(View.VISIBLE);
        rb_activity.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onPause() {
        super.onPause();
        curr_tag = tabHost.getCurrentTabTag();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            tabHost.setCurrentTabByTag(curr_tag);
            if (curr_tag.equals(CONSTANT_TAB_ACTIVITY)) {
                rb_activity.setVisibility(View.INVISIBLE);
            } else if (curr_tag.equals(CONSTANT_TAB_GAME)) {
                rb_game.setVisibility(View.INVISIBLE);
            } else if (curr_tag.equals(CONSTANT_TAB_RANK)) {
                rb_rank.setVisibility(View.INVISIBLE);
            } else if (curr_tag.equals(CONSTANT_TAB_SERVICES)) {
                rb_service.setVisibility(View.INVISIBLE);
            } else if (curr_tag.equals(CONSTANT_TAB_SING)) {
                rb_sing.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        MainViewActivity activity = (MainViewActivity) getActivity();

        switch (compoundButton.getId()) {
            case R.id.rb_sing:

                if (b) {
                    iv_music_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_show));
                    iv_music_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_show));
                    iv_music_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.red_bg);
                } else {
                    iv_music_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_hide));
                    iv_music_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_hide));
                    iv_music_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_hide));
                }

                break;
            case R.id.rb_activity:
                if (b) {
                    iv_activity_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_show));
                    iv_activity_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_show));
                    iv_activity_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.orange_bg);
                } else {
                    iv_activity_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_hide));
                    iv_activity_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_hide));
                    iv_activity_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_hide));
                }
                break;
            case R.id.rb_game:
                if (b) {
                    iv_game_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_show));
                    iv_game_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_show));
                    iv_game_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.purple_bg);
                } else {
                    iv_game_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_hide));
                    iv_game_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_hide));
                    iv_game_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_hide));
                }
                break;
            case R.id.rb_rank:
                if (b) {
                    iv_rank_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_show));
                    iv_rank_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_show));
                    iv_rank_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.blue_bg);
                } else {
                    iv_rank_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_hide));
                    iv_rank_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_hide));
                    iv_rank_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_hide));
                }
                break;
            case R.id.rb_service:
                if (b) {
                    iv_service_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_show));
                    iv_service_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_show));
                    iv_service_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.green_bg);
                } else {
                    iv_service_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_right_hide));
                    iv_service_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_left_hide));
                    iv_service_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_text_hide));
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        MainViewActivity activity = (MainViewActivity) getActivity();

        CompoundButton compoundButton = (CompoundButton) view;

        boolean b = compoundButton.isChecked();

        switch (compoundButton.getId()) {
            case R.id.rb_sing:

                if (b) {
/*                    iv_music_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_show));
                    iv_music_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_show));
                    iv_music_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.red_bg);*/
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_back));
                    tabHost.setCurrentTabByTag(MainCenterFragment.CONSTANT_TAB_SING);
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_next));
                    rb_activity.setChecked(false);
                    rb_game.setChecked(false);
                    rb_service.setChecked(false);
                    rb_rank.setChecked(false);
                    changeToMusicTab();
                }
 /*               else
                {
                    iv_music_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_hide));
                    iv_music_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_hide));
                    iv_music_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_hide));
                }*/

                break;
            case R.id.rb_activity:
                if (b) {
/*                    iv_activity_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_show));
                    iv_activity_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_show));
                    iv_activity_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.orange_bg);*/
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_back));
                    tabHost.setCurrentTabByTag(MainCenterFragment.CONSTANT_TAB_ACTIVITY);
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_next));
                    rb_sing.setChecked(false);
                    rb_game.setChecked(false);
                    rb_service.setChecked(false);
                    rb_rank.setChecked(false);
                    changeToActivityTab();
                }
/*                else
                {
                    iv_activity_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_hide));
                    iv_activity_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_hide));
                    iv_activity_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_hide));
                }*/
                break;
            case R.id.rb_game:
                if (b) {
/*                    iv_game_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_show));
                    iv_game_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_show));
                    iv_game_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.purple_bg);*/
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_back));
                    tabHost.setCurrentTabByTag(MainCenterFragment.CONSTANT_TAB_GAME);
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_next));
                    rb_activity.setChecked(false);
                    rb_sing.setChecked(false);
                    rb_service.setChecked(false);
                    rb_rank.setChecked(false);
                    changeToGameTab();
                }
/*                else
                {
                    iv_game_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_hide));
                    iv_game_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_hide));
                    iv_game_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_hide));
                }*/
                break;
            case R.id.rb_rank:
                if (b) {
/*                    iv_rank_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_show));
                    iv_rank_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_show));
                    iv_rank_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.blue_bg);*/
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_back));
                    tabHost.setCurrentTabByTag(MainCenterFragment.CONSTANT_TAB_RANK);
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_next));
                    rb_activity.setChecked(false);
                    rb_game.setChecked(false);
                    rb_service.setChecked(false);
                    rb_sing.setChecked(false);
                    changeToRankTab();
                }
/*                else
                {
                    iv_rank_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_hide));
                    iv_rank_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_hide));
                    iv_rank_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_hide));
                }*/
                break;
            case R.id.rb_service:
                if (b) {
/*                    iv_service_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_show));
                    iv_service_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_show));
                    iv_service_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_show));
                    activity.changeMainBackGround(R.drawable.green_bg);*/
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_back));
                    tabHost.setCurrentTabByTag(MainCenterFragment.CONSTANT_TAB_SERVICES);
                    tabHost.getCurrentTabView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.tab_open_next));
                    rb_activity.setChecked(false);
                    rb_game.setChecked(false);
                    rb_sing.setChecked(false);
                    rb_rank.setChecked(false);
                    changeToServiceTab();
                }
/*                else
                {
                    iv_service_arrow.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_right_hide));
                    iv_service_icon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_left_hide));
                    iv_service_text.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.tab_text_hide));
                }*/
                break;
        }
    }

    private String curr_tag;


    @Override
    protected void initContentView() {
        // TODO Auto-generated method stub

    }
}
