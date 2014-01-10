package com.yiqiding.ktvbox.widget.fragment;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.view.PopupWindowOrdereSongsActivity;
import com.yiqiding.ktvbox.view.SingerStartAllViewActivity;

public class SingerStarFragment extends BaseFragment {

    private TabHost tabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initContentView();
        return mainView;
    }

    @Override
    protected void initContentView() {
        mainView = new FrameLayout(this.getActivity());
        DisplayMetrics displayMetrics = ViewUtils
                .getScreenResolution(getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                displayMetrics.widthPixels,
                displayMetrics.heightPixels);
        mainView.setLayoutParams(layoutParams);

        // init top box
        FrameLayout fl_top = new FrameLayout(getActivity());
        FrameLayout.LayoutParams fl_params = new FrameLayout.LayoutParams(
                displayMetrics.widthPixels,
                (int) (displayMetrics.heightPixels * (154 / 1200.0f)));
/*		fl_params.setMargins((int) (displayMetrics.heightPixels * (2 / 70.0f)),
                0, (int) (displayMetrics.heightPixels * (160 / 1200.0f)), 0);*/
        fl_top.setLayoutParams(fl_params);

        ImageView iv_all = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (242.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));

        iv_all.setLayoutParams(fl_params);
        iv_all.setImageResource(R.drawable.light_select);
        iv_all.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_zh_man = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (165.0f / 1920f));
        iv_zh_man.setLayoutParams(fl_params);
        iv_zh_man.setImageResource(R.drawable.light_select);
        iv_zh_man.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_zh_woman = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (400.0f / 1920f));
        iv_zh_woman.setLayoutParams(fl_params);
        iv_zh_woman.setImageResource(R.drawable.light_select);
        iv_zh_woman.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_hktw_man = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (640.0f / 1920f));
        iv_hktw_man.setLayoutParams(fl_params);
        iv_hktw_man.setImageResource(R.drawable.light_select);
        iv_hktw_man.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_hktw_woman = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (870.0f / 1920f));
        iv_hktw_woman.setLayoutParams(fl_params);
        iv_hktw_woman.setImageResource(R.drawable.light_select);
        iv_hktw_woman.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_zh_group = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1120.0f / 1920f));
        iv_zh_group.setLayoutParams(fl_params);
        iv_zh_group.setImageResource(R.drawable.light_select);
        iv_zh_group.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_for_singer = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1360.0f / 1920f));
        iv_for_singer.setLayoutParams(fl_params);
        iv_for_singer.setImageResource(R.drawable.light_select);
        iv_for_singer.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView iv_for_group = new ImageView(getActivity());
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (342.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (174.0f / 1200)));
        fl_params.topMargin = -(int) (displayMetrics.widthPixels * (10.0f / 1920f));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1584.0f / 1920f));
        iv_for_group.setLayoutParams(fl_params);
        iv_for_group.setImageResource(R.drawable.light_select);
        iv_for_group.setScaleType(ImageView.ScaleType.FIT_XY);

        fl_top.addView(iv_all);
        fl_top.addView(iv_zh_man);
        fl_top.addView(iv_zh_woman);
        fl_top.addView(iv_hktw_man);
        fl_top.addView(iv_hktw_woman);
        fl_top.addView(iv_zh_group);
        fl_top.addView(iv_for_singer);
        fl_top.addView(iv_for_group);

        cb_all = new CheckBox(this.getActivity());
        cb_all.setButtonDrawable(R.drawable.blank_button);
        cb_all.setId(CONSTANT_VIEW_ID++);
        cb_all.setTextColor(Color.WHITE);
        cb_all.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        FrameLayout.LayoutParams fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (142.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (50.0f / 1920f));
        //cb_all.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_all.setLayoutParams(fl_child_parms);
        cb_all.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_all));
        fl_top.addView(cb_all, fl_child_parms);

        cb_zh_man = new CheckBox(this.getActivity());
        cb_zh_man.setId(CONSTANT_VIEW_ID++);
        cb_zh_man.setButtonDrawable(R.drawable.blank_button);
        cb_zh_man.setTextColor(Color.GRAY);
        cb_zh_man.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_zh_man.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (0.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_zh_man.setLayoutParams(fl_child_parms);
        cb_zh_man.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_zh_man));
        fl_top.addView(cb_zh_man);

        cb_zh_woman = new CheckBox(this.getActivity());
        cb_zh_woman.setId(CONSTANT_VIEW_ID++);
        cb_zh_woman.setButtonDrawable(R.drawable.blank_button);
        cb_zh_woman.setTextColor(Color.GRAY);
        cb_zh_woman.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_zh_woman.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (1.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_zh_woman.setLayoutParams(fl_child_parms);
        cb_zh_woman.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_zh_woman));
        fl_top.addView(cb_zh_woman, fl_child_parms);

        cb_hktw_man = new CheckBox(this.getActivity());
        cb_hktw_man.setId(CONSTANT_VIEW_ID++);
        cb_hktw_man.setButtonDrawable(R.drawable.blank_button);
        cb_hktw_man.setTextColor(Color.GRAY);
        cb_hktw_man.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_hktw_man.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (2.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;

        cb_hktw_man.setLayoutParams(fl_child_parms);
        cb_hktw_man.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_hktw_man));
        fl_top.addView(cb_hktw_man, fl_child_parms);

        cb_hktw_woman = new CheckBox(this.getActivity());
        cb_hktw_woman.setId(CONSTANT_VIEW_ID++);
        cb_hktw_woman.setButtonDrawable(R.drawable.blank_button);
        cb_hktw_woman.setTextColor(Color.GRAY);
        cb_hktw_woman.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_hktw_woman.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (3.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_hktw_woman.setLayoutParams(fl_child_parms);
        cb_hktw_woman.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_hktw_woman));
        fl_top.addView(cb_hktw_woman, fl_child_parms);

        cb_zh_group = new CheckBox(this.getActivity());
        cb_zh_group.setId(CONSTANT_VIEW_ID++);
        cb_zh_group.setButtonDrawable(R.drawable.blank_button);
        cb_zh_group.setTextColor(Color.GRAY);
        cb_zh_group.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_zh_group.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (4.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_zh_group.setLayoutParams(fl_child_parms);
        cb_zh_group.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_zh_group));
        fl_top.addView(cb_zh_group, fl_child_parms);

        cb_for_singer = new CheckBox(this.getActivity());
        cb_for_singer.setId(CONSTANT_VIEW_ID++);
        cb_for_singer.setTextColor(Color.GRAY);
        cb_for_singer.setButtonDrawable(R.drawable.blank_button);
        cb_for_singer.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_for_singer.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (5.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_for_singer.setLayoutParams(fl_child_parms);
        cb_for_singer.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_for_singer));
        fl_top.addView(cb_for_singer, fl_child_parms);

        cb_for_group = new CheckBox(this.getActivity());
        cb_for_group.setId(CONSTANT_VIEW_ID++);
        cb_for_group.setTextColor(Color.GRAY);
        cb_for_group.setButtonDrawable(R.drawable.blank_button);
        cb_for_group.setTextSize(CONSTANT_NORMAL_TOP_TEXTSIZE);
        fl_child_parms = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (272.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (154.0f / 1200)));
        //cb_for_group.setBackgroundResource(R.drawable.light_select);
        fl_child_parms.leftMargin = (int) (displayMetrics.widthPixels * (6.8f / 8));
        fl_child_parms.gravity = Gravity.CENTER_VERTICAL;
        cb_for_group.setLayoutParams(fl_child_parms);
        cb_for_group.setText(getActivity().getResources().getString(
                R.string.menu_pop_star_for_group));
        fl_top.addView(cb_for_group, fl_child_parms);

        cb_categories[0] = cb_all;
        cb_categories[1] = cb_zh_man;
        cb_categories[2] = cb_zh_woman;
        cb_categories[3] = cb_hktw_man;
        cb_categories[4] = cb_hktw_woman;
        cb_categories[5] = cb_zh_group;
        cb_categories[6] = cb_for_singer;
        cb_categories[7] = cb_for_group;

        iv_text_bgs[0] = iv_all;
        iv_text_bgs[1] = iv_zh_man;
        iv_text_bgs[2] = iv_zh_woman;
        iv_text_bgs[3] = iv_hktw_man;
        iv_text_bgs[4] = iv_hktw_woman;
        iv_text_bgs[5] = iv_zh_group;
        iv_text_bgs[6] = iv_for_singer;
        iv_text_bgs[7] = iv_for_group;

        for (CheckBox c : cb_categories) {
            c.setOnClickListener(new CategoriesCheckBoxClickListener());
            c.setOnCheckedChangeListener(new CategoriesCheckListener());
            c.setGravity(Gravity.CENTER);
        }

        //Add arrow
        ImageView iv_arrow_all = new ImageView(getActivity());
        iv_arrow_all.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (100.0f / 1920f));
        iv_arrow_all.setLayoutParams(fl_params);
        iv_arrow_all.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_all);

        ImageView iv_arrow_zh_man = new ImageView(getActivity());
        iv_arrow_zh_man.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (310.0f / 1920f));
        iv_arrow_zh_man.setLayoutParams(fl_params);
        iv_arrow_zh_man.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_zh_man);

        ImageView iv_arrow_zh_woman = new ImageView(getActivity());
        iv_arrow_zh_woman.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (550.0f / 1920f));
        iv_arrow_zh_woman.setLayoutParams(fl_params);
        iv_arrow_zh_woman.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_zh_woman);

        ImageView iv_arrow_hkzh_man = new ImageView(getActivity());
        iv_arrow_hkzh_man.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (790.0f / 1920f));
        iv_arrow_hkzh_man.setLayoutParams(fl_params);
        iv_arrow_hkzh_man.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_hkzh_man);

        ImageView iv_arrow_hkzh_woman = new ImageView(getActivity());
        iv_arrow_hkzh_woman.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1030.0f / 1920f));
        iv_arrow_hkzh_woman.setLayoutParams(fl_params);
        iv_arrow_hkzh_woman.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_hkzh_woman);


        ImageView iv_arrow_zh_group = new ImageView(getActivity());
        iv_arrow_zh_group.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1265.0f / 1920f));
        iv_arrow_zh_group.setLayoutParams(fl_params);
        iv_arrow_zh_group.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_zh_group);

        ImageView iv_arrow_for_singer = new ImageView(getActivity());
        iv_arrow_for_singer.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1510.0f / 1920f));
        iv_arrow_for_singer.setLayoutParams(fl_params);
        iv_arrow_for_singer.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_for_singer);

        ImageView iv_arrow_for_group = new ImageView(getActivity());
        iv_arrow_for_group.setScaleType(ImageView.ScaleType.FIT_XY);
        fl_params = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels * (42.0f / 1920f)),
                (int) (displayMetrics.heightPixels * (22.0f / 1200)));
        fl_params.topMargin = (int) (displayMetrics.heightPixels * (120.0f / 1200));
        fl_params.leftMargin = (int) (displayMetrics.widthPixels * (1750.0f / 1920f));
        iv_arrow_for_group.setLayoutParams(fl_params);
        iv_arrow_for_group.setImageResource(R.drawable.star_arrow_down);
        fl_top.addView(iv_arrow_for_group);

        iv_arrow[0] = iv_arrow_all;
        iv_arrow[1] = iv_arrow_zh_man;
        iv_arrow[2] = iv_arrow_zh_woman;
        iv_arrow[3] = iv_arrow_hkzh_man;
        iv_arrow[4] = iv_arrow_hkzh_woman;
        iv_arrow[5] = iv_arrow_zh_group;
        iv_arrow[6] = iv_arrow_for_singer;
        iv_arrow[7] = iv_arrow_for_group;

        for (ImageView v : iv_arrow) {
            if (!v.equals(iv_arrow_all))
                v.setVisibility(View.INVISIBLE);
        }
        for (ImageView v : iv_text_bgs) {
            if (!v.equals(iv_all))
                v.setVisibility(View.INVISIBLE);
        }
        // Content View
        tabHost = new TabHost(getActivity(), null);
        layoutParams = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels),
                (int) (displayMetrics.heightPixels * (920 / 1200.0f)));
        layoutParams.topMargin = (int) (displayMetrics.heightPixels * (10 / 120.0f));
/*		tabHost.setPadding((int) (displayMetrics.widthPixels * (2.0f / 112f)),
                0, 0, 0);*/
        tabHost.setLayoutParams(layoutParams);
        tabHost.setId(android.R.id.tabhost);
        FrameLayout fl_content = new FrameLayout(getActivity());
        fl_content.setId(android.R.id.tabcontent);
        layoutParams = new FrameLayout.LayoutParams(layoutParams.MATCH_PARENT,
                layoutParams.MATCH_PARENT);
        fl_content.setLayoutParams(layoutParams);
        TabWidget tabWidget = new TabWidget(getActivity());
        tabWidget.setId(android.R.id.tabs);
        layoutParams = new FrameLayout.LayoutParams(0, 0);
        tabWidget.setLayoutParams(layoutParams);
        tabHost.addView(tabWidget);
        tabHost.addView(fl_content);
        ((FrameLayout) mainView).addView(fl_top);
        ((FrameLayout) mainView).addView(tabHost);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        try {
            initTabContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTabContent() throws Exception {
        LocalActivityManager lam = new LocalActivityManager(getActivity(),
                false);
        lam.dispatchCreate(new Bundle());
        tabHost.setup(lam);
        tabHost.setPadding(tabHost.getPaddingLeft(), tabHost.getPaddingTop(),
                tabHost.getPaddingRight(), tabHost.getPaddingBottom() - 5);
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_ALL)
                .setIndicator(CONSTANT_TABS_ALL)
                .setContent(
                        new Intent(this.getActivity(),
                                SingerStartAllViewActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_FOR_GROUP)
                .setIndicator(CONSTANT_TABS_FOR_GROUP)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_FOR_SINGER)
                .setIndicator(CONSTANT_TABS_FOR_SINGER)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_HKTW_MAN)
                .setIndicator(CONSTANT_TABS_HKTW_MAN)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_HKTW_WOMAN)
                .setIndicator(CONSTANT_TABS_HKTW_WOMAN)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_ZH_GROUP)
                .setIndicator(CONSTANT_TABS_ZH_GROUP)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_ZH_MAN)
                .setIndicator(CONSTANT_TABS_ZH_MAN)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        tabHost.addTab(tabHost
                .newTabSpec(CONSTANT_TABS_ZH_WOMAN)
                .setIndicator(CONSTANT_TABS_ZH_WOMAN)
                .setContent(
                        new Intent(this.getActivity(),
                                PopupWindowOrdereSongsActivity.class)));
        initData();
    }

    private void initData() throws Exception {
        cb_categories[0].setChecked(true);

        dealEvent(cb_categories[0]);
    }

    private int pre_position = 0;

    private class CategoriesCheckListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            buttonView.setTextColor(isChecked ? Color.WHITE : Color.GRAY);
        }
    }

    private class CategoriesCheckBoxClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                dealEvent(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    private void dealEvent(View v) throws Exception{
        int curr_position = 0;
        int i = 0;
        if (v.equals(cb_all))// all click
        {

            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_all)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_all.isChecked())
            {
                cb_all.setChecked(true);
                return;
            }
        } else if (v.equals(cb_zh_man))// all click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_zh_man)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_zh_man.isChecked())
            {
                cb_zh_man.setChecked(true);
                return;
            }
        } else if (v.equals(cb_zh_woman))// all click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_zh_woman)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_zh_woman.isChecked())
            {
                cb_zh_woman.setChecked(true);
                return;
            }
        } else if (v.equals(cb_hktw_man))// all click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_hktw_man)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_hktw_man.isChecked())
            {
                cb_hktw_man.setChecked(true);
                return;
            }
        } else if (v.equals(cb_hktw_woman))// all
        // click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_hktw_woman)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_hktw_woman.isChecked())
            {
                cb_hktw_woman.setChecked(true);
                return;
            }
        } else if (v.equals(cb_zh_group))// all click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_zh_group)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_zh_group.isChecked())
            {
                cb_zh_group.setChecked(true);
                return;
            }
        } else if (v.equals(cb_for_singer))// all // click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_for_singer)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_for_singer.isChecked())
            {
                cb_for_singer.setChecked(true);
                return;
            }
        } else if (v.equals(cb_for_group))// all click
        {
            for (CheckBox c : cb_categories) {
                if (!c.equals(cb_for_group)) {
                    c.setChecked(false);
                } else {
                    curr_position = i;
                    c.setChecked(true);
                }
                i++;
            }
            if(!cb_for_group.isChecked())
            {
                cb_for_group.setChecked(true);
                return;
            }
        }

/*        if (curr_position == pre_position)
            return;*/

        Animation text_show_animation = AnimationUtils.loadAnimation(getActivity(), R.anim.star_tab_text_show);
        Animation text_hide_animation = AnimationUtils.loadAnimation(getActivity(), R.anim.star_tab_text_hide);
        cb_categories[pre_position].startAnimation(text_hide_animation);
        iv_text_bgs[pre_position].startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.star_tab_bg_hide));
        iv_arrow[pre_position].startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.star_tab_arrow_hide));
        pre_position = curr_position;
        cb_categories[curr_position].startAnimation(text_show_animation);
        iv_text_bgs[curr_position].startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.star_tab_bg_show));
        iv_arrow[curr_position].startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.star_tab_arrow_show));
    }

    public static final String TAG = SingerStarFragment.class.getSimpleName();

    private int CONSTANT_VIEW_ID = 0x001;
    private static final int CONSTANT_NORMAL_TOP_TEXTSIZE = 24;
    private static final String CONSTANT_TABS_ALL = "tab_all";
    private static final String CONSTANT_TABS_ZH_MAN = "tab_zh_man";
    private static final String CONSTANT_TABS_ZH_WOMAN = "tab_zh_woman";
    private static final String CONSTANT_TABS_HKTW_MAN = "tab_hktw_man";
    private static final String CONSTANT_TABS_HKTW_WOMAN = "tab_hktw_woman";
    private static final String CONSTANT_TABS_ZH_GROUP = "tab_zh_group";
    private static final String CONSTANT_TABS_FOR_SINGER = "tab_for_singer";
    private static final String CONSTANT_TABS_FOR_GROUP = "tab_for_group";

    private CheckBox[] cb_categories = new CheckBox[8];
    private CheckBox cb_all;
    private CheckBox cb_zh_man;
    private CheckBox cb_zh_woman;
    private CheckBox cb_hktw_man;
    private CheckBox cb_hktw_woman;
    private CheckBox cb_zh_group;
    private CheckBox cb_for_singer;
    private CheckBox cb_for_group;

    private ImageView[] iv_text_bgs = new ImageView[8];
    private ImageView[] iv_arrow = new ImageView[8];

}
