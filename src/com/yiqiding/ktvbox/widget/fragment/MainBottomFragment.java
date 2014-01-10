/**
 *
 *  MainBottomFragment.java
 *  com.yiqiding.ktvbox.widget
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */
package com.yiqiding.ktvbox.widget.fragment;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.view.PopupWindowOrdereSongsActivity;
import com.yiqiding.ktvbox.view.PopupWindowSangSongsActivity;
import com.yiqiding.ktvbox.widget.MagicTextView;
import com.yiqiding.ktvbox.widget.RepeatingButton;
import com.yiqiding.ktvbox.widget.VerticalProgressBar;

public class MainBottomFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_bottom_fragment, null);
    }

    private void showShadowText() throws Exception {
        mtv_num.setShadowLayer(20, 3, 0, Color.rgb(255, 144, 0));
        mtv_num.addOuterShadow(20, 3, 0, Color.rgb(255, 144, 0));
        mtv_tips.addOuterShadow(20, 3, 0, Color.rgb(255, 255, 255));
        mtv_tips.setShadowLayer(20, 3, 0, Color.rgb(255, 255, 255));
        mtv_num.addOuterShadow(20, 3, 0, Color.rgb(255, 144, 0));
        mtv_tips.addOuterShadow(20, 3, 0, Color.rgb(255, 255, 255));
        mtv_tips.setTextColor(Color.argb(255, 255, 255, 255));
        mtv_num.setTextColor(Color.argb(255, 255, 144, 0));
    }

    private void hideShadowText() throws Exception {
        mtv_num.setTextColor(Color.argb(155, 255, 144, 0));
        mtv_tips.setTextColor(Color.argb(155, 255, 255, 255));
        mtv_num.setShadowLayer(20, 3, 0, Color.argb(0, 0, 0, 0));
        mtv_tips.setShadowLayer(20, 3, 0, Color.argb(0, 0, 0, 0));
        mtv_num.clearOuterShadows();
        mtv_tips.clearOuterShadows();
    }

    private void initPopUpWindowsOrdered() throws Exception {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.popup_ordersongs, null);

        v_ll_poporder_bg = mView.findViewById(R.id.ll_bg);
        Button btn_close = (Button) mView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new CloseListener());

        v_ordered = mView.findViewById(R.id.fg_content);
        rg_ordered = (RadioGroup) mView.findViewById(R.id.rg_menu);
        rg_ordered.setOnCheckedChangeListener(new OrderedMenuCheckListener());

        rb_ordered = (RadioButton) mView.findViewById(R.id.rb_order);
        tabHost_popup = (TabHost) mView.findViewById(android.R.id.tabhost);

        LocalActivityManager lam = new LocalActivityManager(getActivity(), false);
        lam.dispatchCreate(new Bundle());
        tabHost_popup.setup(lam);
        tabHost_popup.setPadding(tabHost_popup.getPaddingLeft(), tabHost_popup.getPaddingTop(),
                tabHost_popup.getPaddingRight(), tabHost_popup.getPaddingBottom() - 5);
        tabHost_popup.addTab(tabHost_popup.newTabSpec(CONSTANT_TAB_ORDERED_SING).setIndicator(CONSTANT_TAB_ORDERED_SING).setContent(new Intent(this.getActivity(), PopupWindowOrdereSongsActivity.class)));
        tabHost_popup.addTab(tabHost_popup.newTabSpec(CONSTANT_TAB_ORDERED_SANG).setIndicator(CONSTANT_TAB_ORDERED_SANG).setContent(new Intent(this.getActivity(), PopupWindowSangSongsActivity.class)));
        rb_ordered.setChecked(true);
        int width = (int) (dm.widthPixels * (360.0 / 1008));
        popup_Ordered = new PopupWindow(mView, width, (int) (width * (500.0f / 360.0f)));
        popup_Ordered.setAnimationStyle(R.style.PopUpWindowAnimation);
        popup_Ordered.showAsDropDown(btn_ordered, 0, 50);
        popup_Ordered.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                try {
                    hideShadowText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        showShadowText();
    }

    private void initPopUpWindowsEffect() throws Exception {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.popup_control_effect, null);
        int width = (int) (dm.widthPixels * (640.0 / 1008));

        vp_popEffect_microphone_1 = (VerticalProgressBar) mView.findViewById(R.id.vp_microphone_1);
        vp_popEffect_microphone_2 = (VerticalProgressBar) mView.findViewById(R.id.vp_microphone_2);
        vp_popEffect_microphone_1.setCurrMode(VerticalProgressBar.MODE_BOTTOM);
        vp_popEffect_microphone_2.setCurrMode(VerticalProgressBar.MODE_TOP);
        vp_popEffect_microphone_1.setMax(80);
        vp_popEffect_microphone_2.setMax(80);

        RepeatingButton btn_microphone_raise=(RepeatingButton)mView.findViewById(R.id.btn_microphone_raise);
        RepeatingButton btn_microphone_reduce=(RepeatingButton)mView.findViewById(R.id.btn_microphone_reduce);
        Button btn_microphone_origin=(Button)mView.findViewById(R.id.btn_microphone_origin);

        btn_microphone_origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp_popEffect_microphone_1.setProgress(0);
                vp_popEffect_microphone_2.setProgress(0);
            }
        });

        btn_microphone_raise.setRepeatListener(new RepeatingButton.RepeatListener() {
            @Override
            public void onRepeat(View v, long duration, int repeatcount) {
                int curr_progress_1= vp_popEffect_microphone_1.getProgress();
                int curr_progress_2=vp_popEffect_microphone_2.getProgress();

                if(curr_progress_1==0&&curr_progress_2>0)
                {
                    vp_popEffect_microphone_2.setProgress(vp_popEffect_microphone_2.getProgress()-20);
                }
                else if(curr_progress_2==0&&curr_progress_1<80)
                {
                    vp_popEffect_microphone_1.setProgress(vp_popEffect_microphone_1.getProgress()+20);
                }
            }
        },CONSTANT_LONG_REPEAT_TIME);
        btn_microphone_reduce.setRepeatListener(new RepeatingButton.RepeatListener() {
            @Override
            public void onRepeat(View v, long duration, int repeatcount) {
                int curr_progress_1= vp_popEffect_microphone_1.getProgress();
                int curr_progress_2=vp_popEffect_microphone_2.getProgress();

                if(curr_progress_2==0&&curr_progress_1>0)
                {
                    vp_popEffect_microphone_1.setProgress(vp_popEffect_microphone_1.getProgress()-20);
                }
                else if(curr_progress_1==0&&curr_progress_2<80)
                {
                    vp_popEffect_microphone_2.setProgress(vp_popEffect_microphone_2.getProgress()+20);
                }
            }
        },CONSTANT_LONG_REPEAT_TIME);
        btn_microphone_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curr_progress_1= vp_popEffect_microphone_1.getProgress();
                int curr_progress_2=vp_popEffect_microphone_2.getProgress();

                if(curr_progress_2==0&&curr_progress_1>0)
                {
                    vp_popEffect_microphone_1.setProgress(vp_popEffect_microphone_1.getProgress()-20);
                }
                else if(curr_progress_1==0&&curr_progress_2<80)
                {
                    vp_popEffect_microphone_2.setProgress(vp_popEffect_microphone_2.getProgress()+20);
                }
            }
        });

        btn_microphone_raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curr_progress_1= vp_popEffect_microphone_1.getProgress();
                int curr_progress_2=vp_popEffect_microphone_2.getProgress();

                if(curr_progress_1==0&&curr_progress_2>0)
                {
                    vp_popEffect_microphone_2.setProgress(vp_popEffect_microphone_2.getProgress()-20);
                }
                else if(curr_progress_2==0&&curr_progress_1<80)
                {
                    vp_popEffect_microphone_1.setProgress(vp_popEffect_microphone_1.getProgress()+20);
                }
            }
        });

        Button btn_close = (Button) mView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (popup_Effect != null && popup_Effect.isShowing()) {
                        popup_Effect.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cb_light = (CheckBox) mView.findViewById(R.id.cb_light);
        cb_meeting = (CheckBox) mView.findViewById(R.id.cb_meeting);
        cb_power = (CheckBox) mView.findViewById(R.id.cb_power);
        cb_romantic = (CheckBox) mView.findViewById(R.id.cb_romantic);
        cb_smooth = (CheckBox) mView.findViewById(R.id.cb_smooth);
        cb_switch = (CheckBox) mView.findViewById(R.id.cb_switch);

        cb_switch.setOnClickListener(new PopEffectLightClickListener());
        cb_smooth.setOnClickListener(new PopEffectLightClickListener());
        cb_romantic.setOnClickListener(new PopEffectLightClickListener());
        cb_power.setOnClickListener(new PopEffectLightClickListener());
        cb_meeting.setOnClickListener(new PopEffectLightClickListener());
        cb_light.setOnClickListener(new PopEffectLightClickListener());

        popup_Effect = new PopupWindow(mView, width, (int) (width * (360.0 / 640.0)));
        popup_Effect.setAnimationStyle(R.style.PopUpWindowEffectAnimation);
        popup_Effect.showAsDropDown(btn_effect, -CONSTANT_OFFSET_DISTANCE, -40);
    }

    private void initPopUpWindowsVolume() throws Exception {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.popup_control_volume, null);
        int width = (int) (dm.widthPixels * (370.0 / 1008));
        Button btn_close = (Button) mView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup_Volume.dismiss();
            }
        });

        vp_popVolume_microPhone = (VerticalProgressBar) mView.findViewById(R.id.vp_microphone);
        vp_popVolume_music = (VerticalProgressBar) mView.findViewById(R.id.vp_music);
        vp_popVolume_power = (VerticalProgressBar) mView.findViewById(R.id.vp_power);

        btn_popVolume_microRaise = (RepeatingButton) mView.findViewById(R.id.btn_microphone_raise);
        btn_popVolume_microReduce = (RepeatingButton) mView.findViewById(R.id.btn_microphone_reduce);
        btn_popVolume_musicRaise = (RepeatingButton) mView.findViewById(R.id.btn_music_raise);
        btn_popVolume_musicReduce = (RepeatingButton) mView.findViewById(R.id.btn_music_reduce);
        btn_popVolume_powerRaise = (RepeatingButton) mView.findViewById(R.id.btn_power_raise);
        btn_popVolume_powerReduce = (RepeatingButton) mView.findViewById(R.id.btn_power_reduce);

        btn_microphone_mark=(Button)mView.findViewById(R.id.btn_microphone_mark);
        btn_music_mark= (Button) mView.findViewById(R.id.btn_music_mark);
        btn_power_mark= (Button) mView.findViewById(R.id.btn_power_mark);

        btn_popVolume_microRaise.setRepeatListener(new MicrophoneRepeatingClickListener(), CONSTANT_LONG_REPEAT_TIME);
        btn_popVolume_microReduce.setRepeatListener(new MicrophoneRepeatingClickListener(), CONSTANT_LONG_REPEAT_TIME);
        btn_popVolume_musicReduce.setRepeatListener(new MicrophoneRepeatingClickListener(), CONSTANT_LONG_REPEAT_TIME);
        btn_popVolume_musicRaise.setRepeatListener(new MicrophoneRepeatingClickListener(), CONSTANT_LONG_REPEAT_TIME);
        btn_popVolume_powerReduce.setRepeatListener(new MicrophoneRepeatingClickListener(), CONSTANT_LONG_REPEAT_TIME);
        btn_popVolume_powerRaise.setRepeatListener(new MicrophoneRepeatingClickListener(), CONSTANT_LONG_REPEAT_TIME);

        btn_popVolume_microRaise.setOnClickListener(new MicrophoneSingleClickListener());
        btn_popVolume_microReduce.setOnClickListener(new MicrophoneSingleClickListener());
        btn_popVolume_musicReduce.setOnClickListener(new MicrophoneSingleClickListener());
        btn_popVolume_musicRaise.setOnClickListener(new MicrophoneSingleClickListener());
        btn_popVolume_powerReduce.setOnClickListener(new MicrophoneSingleClickListener());
        btn_popVolume_powerRaise.setOnClickListener(new MicrophoneSingleClickListener());


        //btn_popVolume_microRaise=mView.findViewById(R.id.)
        popup_Volume = new PopupWindow(mView, width, (int) (width * (360.0 / 370.0)));
        popup_Volume.setAnimationStyle(R.style.PopUpWindowVolumeAnimation);
        popup_Volume.showAsDropDown(btn_volume, -50, -40);
    }

    private class OrderedMenuCheckListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            switch (i) {
                case R.id.rb_order:
                    v_ll_poporder_bg.setBackgroundResource(R.drawable.song_bg);
                    tabHost_popup.setCurrentTabByTag(CONSTANT_TAB_ORDERED_SING);
                    break;
                case R.id.rb_rank:
                    v_ll_poporder_bg.setBackgroundResource(R.drawable.sing_bg);
                    tabHost_popup.setCurrentTabByTag(CONSTANT_TAB_ORDERED_SANG);
                    break;
            }
        }
    }

    private class CloseListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (popup_Ordered != null && popup_Ordered.isShowing()) {
                popup_Ordered.dismiss();
                return;
            }

        }
    }

    private class PopEffectLightClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            try {
                CompoundButton compoundButton = (CompoundButton) view;
                if (!compoundButton.isChecked()) {
                    return;
                }
                switch (view.getId()) {
                    case R.id.cb_switch:
                        cb_light.setChecked(false);
                        cb_smooth.setChecked(false);
                        cb_meeting.setChecked(false);
                        cb_romantic.setChecked(false);
                        cb_power.setChecked(false);
                        break;
                    case R.id.cb_smooth:
                        cb_light.setChecked(false);
                        cb_switch.setChecked(false);
                        cb_meeting.setChecked(false);
                        cb_romantic.setChecked(false);
                        cb_power.setChecked(false);
                        break;
                    case R.id.cb_light:
                        cb_romantic.setChecked(false);
                        cb_smooth.setChecked(false);
                        cb_meeting.setChecked(false);
                        cb_switch.setChecked(false);
                        cb_power.setChecked(false);
                        break;
                    case R.id.cb_power:
                        cb_light.setChecked(false);
                        cb_romantic.setChecked(false);
                        cb_meeting.setChecked(false);
                        cb_switch.setChecked(false);
                        cb_smooth.setChecked(false);
                        break;
                    case R.id.cb_romantic:
                        cb_light.setChecked(false);
                        cb_smooth.setChecked(false);
                        cb_meeting.setChecked(false);
                        cb_switch.setChecked(false);
                        cb_power.setChecked(false);
                        break;
                    case R.id.cb_meeting:
                        cb_light.setChecked(false);
                        cb_smooth.setChecked(false);
                        cb_romantic.setChecked(false);
                        cb_switch.setChecked(false);
                        cb_power.setChecked(false);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private
    @ViewInject(id = R.id.rl_ordered, click = "onOrderedClick")
    View btn_ordered;
    private
    @ViewInject(id = R.id.btn_volume, click = "onVolumeClick")
    Button btn_volume;
    private
    @ViewInject(id = R.id.btn_effect, click = "onEffectClick")
    Button btn_effect;
    private
    @ViewInject(id = R.id.tv_order_tips)
    MagicTextView mtv_tips;
    private
    @ViewInject(id = R.id.tv_order_num)
    MagicTextView mtv_num;
    private CheckBox cb_light;
    private CheckBox cb_romantic;
    private CheckBox cb_power;
    private CheckBox cb_meeting;
    private CheckBox cb_smooth;
    private CheckBox cb_switch;
    private RepeatingButton btn_popVolume_microRaise;
    private RepeatingButton btn_popVolume_microReduce;
    private RepeatingButton btn_popVolume_musicRaise;
    private RepeatingButton btn_popVolume_musicReduce;
    private RepeatingButton btn_popVolume_powerRaise;
    private RepeatingButton btn_popVolume_powerReduce;
    private Button btn_microphone_mark;
    private Button btn_music_mark;
    private Button btn_power_mark;
    private VerticalProgressBar vp_popVolume_microPhone;
    private VerticalProgressBar vp_popVolume_music;
    private VerticalProgressBar vp_popVolume_power;
    private VerticalProgressBar vp_popEffect_microphone_1;
    private VerticalProgressBar vp_popEffect_microphone_2;
    private RadioGroup rg_ordered;
    private TabHost tabHost_popup;
    private PopupWindow popup_Ordered;
    private PopupWindow popup_Volume;
    private PopupWindow popup_Effect;
    private RadioButton rb_ordered;
    private View v_ordered;
    private View v_ll_poporder_bg;

    private int pop_mark_micro_margin=0;
    private int pop_mark_music_margin=0;
    private int pop_mark_power_margin=0;

    public final int CONSTANT_LONG_REPEAT_TIME=100;
    public int CONSTANT_OFFSET_DISTANCE =30;
    public static final String CONSTANT_TAB_ORDERED_SING = "constant_tab_ordered_sing";
    public static final String CONSTANT_TAB_ORDERED_SANG = "constant_tab_ordered_sang";
    
    

    /**
     * Ordered Song List Popup Window
     *
     * @param v
     */
    public void onOrderedClick(View v) {
        if (popup_Volume != null && popup_Volume.isShowing()) {
            popup_Volume.dismiss();
        }
        if (popup_Effect != null && popup_Effect.isShowing()) {
            popup_Effect.dismiss();
        }
        if (popup_Ordered != null && popup_Ordered.isShowing()) {
            popup_Ordered.dismiss();
            return;
        }
        try {
            initPopUpWindowsOrdered();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Volume Control Dialog
     *
     * @param v button view
     */
    public void onVolumeClick(View v) {
        if (popup_Ordered != null && popup_Ordered.isShowing()) {
            popup_Ordered.dismiss();
        }
        if (popup_Effect != null && popup_Effect.isShowing()) {
            popup_Effect.dismiss();
        }
        if (popup_Volume != null && popup_Volume.isShowing()) {
            popup_Volume.dismiss();
            return;
        }
        try {
            initPopUpWindowsVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Effect Show Dialog
     *
     * @param v
     */
    public void onEffectClick(View v) {
        if (popup_Ordered != null && popup_Ordered.isShowing()) {
            popup_Ordered.dismiss();
        }
        if (popup_Volume != null && popup_Volume.isShowing()) {
            popup_Volume.dismiss();
        }
        if (popup_Effect != null && popup_Effect.isShowing()) {
            popup_Effect.dismiss();
            return;
        }
        try {
            initPopUpWindowsEffect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MicrophoneSingleClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_microphone_raise:
                    if (vp_popVolume_microPhone.getProgress() < 100)
                    {
                        vp_popVolume_microPhone.setProgress(vp_popVolume_microPhone.getProgress() + 10);
                        btn_microphone_mark.setText(vp_popVolume_microPhone.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_microphone_mark.getLayoutParams();
                        layoutParams.bottomMargin+= CONSTANT_OFFSET_DISTANCE;
                        btn_microphone_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_microPhone.getProgress()==100)
                            btn_microphone_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_microphone_mark.setVisibility(View.VISIBLE);

                    }
                    break;
                case R.id.btn_microphone_reduce:
                    if (vp_popVolume_microPhone.getProgress() > 0)
                    {
                        vp_popVolume_microPhone.setProgress((vp_popVolume_microPhone.getProgress() - 10) >= 0 ? (vp_popVolume_microPhone.getProgress() - 10) : 0);
                        btn_microphone_mark.setText(vp_popVolume_microPhone.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_microphone_mark.getLayoutParams();
                        layoutParams.bottomMargin-= CONSTANT_OFFSET_DISTANCE;
                        btn_microphone_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_microPhone.getProgress()==0)
                            btn_microphone_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_microphone_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_music_reduce:
                    if (vp_popVolume_music.getProgress() > 0)
                    {
                        vp_popVolume_music.setProgress((vp_popVolume_music.getProgress() - 10) >= 0 ? (vp_popVolume_music.getProgress() - 10) : 0);
                        btn_music_mark.setText(vp_popVolume_music.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_music_mark.getLayoutParams();
                        layoutParams.bottomMargin-= CONSTANT_OFFSET_DISTANCE;
                        btn_music_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_music.getProgress()==0)
                            btn_music_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_music_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_music_raise:
                    if (vp_popVolume_music.getProgress() < 100)
                    {
                        vp_popVolume_music.setProgress(vp_popVolume_music.getProgress() + 10);
                        btn_music_mark.setText(vp_popVolume_music.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_music_mark.getLayoutParams();
                        layoutParams.bottomMargin+= CONSTANT_OFFSET_DISTANCE;
                        btn_music_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_music.getProgress()==100)
                            btn_music_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_music_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_power_reduce:
                    if (vp_popVolume_power.getProgress() > 0)
                    {
                        vp_popVolume_power.setProgress((vp_popVolume_power.getProgress() - 10) >= 0 ? (vp_popVolume_power.getProgress() - 10) : 0);
                        btn_power_mark.setText(vp_popVolume_power.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_power_mark.getLayoutParams();
                        layoutParams.bottomMargin-= CONSTANT_OFFSET_DISTANCE;
                        btn_power_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_power.getProgress()==0)
                            btn_power_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_power_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_power_raise:
                    if (vp_popVolume_power.getProgress() < 100)
                    {
                        vp_popVolume_power.setProgress(vp_popVolume_power.getProgress() + 10);
                        btn_power_mark.setText(vp_popVolume_power.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_power_mark.getLayoutParams();
                        layoutParams.bottomMargin+= CONSTANT_OFFSET_DISTANCE;
                        btn_power_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_power.getProgress()==100)
                            btn_power_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_power_mark.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }

    private class MicrophoneRepeatingClickListener implements RepeatingButton.RepeatListener {

        @Override
        public void onRepeat(View v, long duration, int repeatcount) {
            switch (v.getId()) {
                case R.id.btn_microphone_raise:
                    if (vp_popVolume_microPhone.getProgress() < 100)
                    {
                        vp_popVolume_microPhone.setProgress(vp_popVolume_microPhone.getProgress() + 10);
                        btn_microphone_mark.setText(vp_popVolume_microPhone.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_microphone_mark.getLayoutParams();
                        layoutParams.bottomMargin+= CONSTANT_OFFSET_DISTANCE;
                        btn_microphone_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_microPhone.getProgress()==100)
                            btn_microphone_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_microphone_mark.setVisibility(View.VISIBLE);

                    }
                    break;
                case R.id.btn_microphone_reduce:
                    if (vp_popVolume_microPhone.getProgress() > 0)
                    {
                        vp_popVolume_microPhone.setProgress((vp_popVolume_microPhone.getProgress() - 10) >= 0 ? (vp_popVolume_microPhone.getProgress() - 10) : 0);
                        btn_microphone_mark.setText(vp_popVolume_microPhone.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_microphone_mark.getLayoutParams();
                        layoutParams.bottomMargin-= CONSTANT_OFFSET_DISTANCE;
                        btn_microphone_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_microPhone.getProgress()==0)
                            btn_microphone_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_microphone_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_music_reduce:
                    if (vp_popVolume_music.getProgress() > 0)
                    {
                        vp_popVolume_music.setProgress((vp_popVolume_music.getProgress() - 10) >= 0 ? (vp_popVolume_music.getProgress() - 10) : 0);
                        btn_music_mark.setText(vp_popVolume_music.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_music_mark.getLayoutParams();
                        layoutParams.bottomMargin-= CONSTANT_OFFSET_DISTANCE;
                        btn_music_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_music.getProgress()==0)
                            btn_music_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_music_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_music_raise:
                    if (vp_popVolume_music.getProgress() < 100)
                    {
                        vp_popVolume_music.setProgress(vp_popVolume_music.getProgress() + 10);
                        btn_music_mark.setText(vp_popVolume_music.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_music_mark.getLayoutParams();
                        layoutParams.bottomMargin+= CONSTANT_OFFSET_DISTANCE;
                        btn_music_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_music.getProgress()==100)
                            btn_music_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_music_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_power_reduce:
                    if (vp_popVolume_power.getProgress() > 0)
                    {
                        vp_popVolume_power.setProgress((vp_popVolume_power.getProgress() - 10) >= 0 ? (vp_popVolume_power.getProgress() - 10) : 0);
                        btn_power_mark.setText(vp_popVolume_power.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_power_mark.getLayoutParams();
                        layoutParams.bottomMargin-= CONSTANT_OFFSET_DISTANCE;
                        btn_power_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_power.getProgress()==0)
                            btn_power_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_power_mark.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.btn_power_raise:
                    if (vp_popVolume_power.getProgress() < 100)
                    {
                        vp_popVolume_power.setProgress(vp_popVolume_power.getProgress() + 10);
                        btn_power_mark.setText(vp_popVolume_power.getProgress()+"");
                        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) btn_power_mark.getLayoutParams();
                        layoutParams.bottomMargin+= CONSTANT_OFFSET_DISTANCE;
                        btn_power_mark.setLayoutParams(layoutParams);
                        if(vp_popVolume_power.getProgress()==100)
                            btn_power_mark.setVisibility(View.INVISIBLE);
                        else
                            btn_power_mark.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DisplayMetrics displayMetrics= ViewUtils.getScreenResolution(getActivity());
        if(displayMetrics.widthPixels<1280)
        {
            CONSTANT_OFFSET_DISTANCE=28;
        }
        else
        {
            CONSTANT_OFFSET_DISTANCE=19;
        }
    }

	@Override
	protected void initContentView() {
		// TODO Auto-generated method stub
		
	}
}
