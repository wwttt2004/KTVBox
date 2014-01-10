package com.yiqiding.ktvbox.view;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.infomanage.SingerInfoStructure;
import com.yiqiding.ktvbox.util.ViewUtils;
import com.yiqiding.ktvbox.widget.adapter.StarPagerListAdapter;

public class SingerStartAllViewActivity extends BaseActivity {
	
	private List<SingerInfoStructure> list_data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mainView);
	}

	@Override
	protected void initMainView() {
		displayMetrics = ViewUtils.getScreenResolution(getParent());
		mainView = new FrameLayout(this);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				(int) (displayMetrics.widthPixels),
				(int) (displayMetrics.heightPixels * (60 / 70.0f)));
		mainView.setLayoutParams(layoutParams);

        viewPager = new ViewPager(this);
        layoutParams = new FrameLayout.LayoutParams(
                (int) (displayMetrics.widthPixels),
                (int) (displayMetrics.heightPixels * (905 / 1200.0f)));
        viewPager.setLayoutParams(layoutParams);
        viewPager.setAdapter(new StarPagerListAdapter(this,new ArrayList<SingerInfoStructure>()));
		/*// gridView
		gv_content = new GridView(this);
		layoutParams = new FrameLayout.LayoutParams(
				(int) (displayMetrics.widthPixels),
				(int) (displayMetrics.heightPixels * (905 / 1200.0f)));
		gv_content.setLayoutParams(layoutParams);
		gv_content.setNumColumns(6);
        gv_content.setSelector(R.drawable.blank_button);
        gv_content.setAdapter(new StarGridListAdapter(this,new ArrayList<SingerInfoStructure>()));
    	gv_content
				.setVerticalSpacing(-(int) (displayMetrics.heightPixels * 95 / 1200.0f));
		gv_content
				.setHorizontalSpacing(-(int) (displayMetrics.heightPixels * (63 / 1920.0f)));
*/
		// bottom control
		LinearLayout ll_bottom = new LinearLayout(this);
		layoutParams = new FrameLayout.LayoutParams(layoutParams.MATCH_PARENT,
				(int) (displayMetrics.heightPixels * 0.1));
		layoutParams.gravity = Gravity.BOTTOM;
		ll_bottom.setLayoutParams(layoutParams);

		// bottom content
		Button btn_sq_az = new Button(this);
		LinearLayout.LayoutParams liParams = new LayoutParams(
				(int) (displayMetrics.widthPixels * (199.0f / 1920f)),
				LayoutParams.MATCH_PARENT);
		btn_sq_az.setLayoutParams(liParams);
		btn_sq_az.setBackgroundResource(R.drawable.btn_singerstar_az);

		Button btn_sq_first = new Button(this);
		liParams = new LayoutParams(
				(int) (displayMetrics.heightPixels * 0.1 * (242.0f / 120.0f)),
				LayoutParams.MATCH_PARENT);
		liParams.leftMargin = (int) (displayMetrics.heightPixels * (2 / 70.0f));
		btn_sq_first.setBackgroundResource(R.drawable.star_first_letter);

		Button btn_prePage = new Button(this);
		liParams = new LayoutParams((int) (displayMetrics.heightPixels
				* (8.0 / 120f) * (7f / 8.0f)),
				(int) (displayMetrics.heightPixels * (8.0 / 120f)));
		liParams.gravity = Gravity.CENTER_VERTICAL;
		liParams.leftMargin = (int) (displayMetrics.widthPixels * (12.0 / 70f));
		btn_prePage.setLayoutParams(liParams);
		btn_prePage.setBackgroundResource(R.drawable.star_left_arrow_letter);
        btn_prePage.setOnClickListener(new PagerClickListener(PagerClickListener.ACTION_PRE));

	    tv_pageNums = new TextView(this);
		tv_pageNums.setTextColor(Color.WHITE);
		tv_pageNums.setTextSize(24);
		tv_pageNums.setText("1/20");
		tv_pageNums.setGravity(Gravity.CENTER);
		liParams = new LayoutParams(
				(int) (displayMetrics.widthPixels * (8.0 / 70f)),
				LayoutParams.MATCH_PARENT);
		liParams.leftMargin = (int) (displayMetrics.widthPixels * (1.0 / 70f));
		tv_pageNums.setLayoutParams(liParams);

		Button btn_nextPage = new Button(this);
        btn_nextPage.setOnClickListener(new PagerClickListener(PagerClickListener.ACTION_NEXT));
		liParams = new LayoutParams((int) (displayMetrics.heightPixels
				* (8.0 / 120f) * (7f / 8.0f)),
				(int) (displayMetrics.heightPixels * (8.0 / 120f)));
		liParams.gravity = Gravity.CENTER_VERTICAL;
		liParams.leftMargin = (int) (displayMetrics.widthPixels * (1.0 / 70f));
		btn_nextPage.setLayoutParams(liParams);
		btn_nextPage.setBackgroundResource(R.drawable.star_right_arrow_letter);

		Button btn_home = new Button(this);
		liParams = new LayoutParams((int) (displayMetrics.heightPixels
				* (12.0 / 120f) * (199f / 120.0f)),
				(int) (displayMetrics.heightPixels * (12.0 / 120f)));
		liParams.gravity = Gravity.CENTER_VERTICAL ;
		liParams.leftMargin = (int) (displayMetrics.widthPixels * (12.0 / 70f));
		btn_home.setLayoutParams(liParams);
		btn_home.setBackgroundResource(R.drawable.btn_home);
		btn_home.setOnClickListener(new BackClickListener());

		Button btn_back = new Button(this);
		liParams = new LayoutParams((int) (displayMetrics.heightPixels
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

		((FrameLayout) mainView).addView(viewPager);
		((FrameLayout) mainView).addView(ll_bottom);

        viewPager.setOnPageChangeListener(new PagerListener());

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

    private class PagerClickListener implements View.OnClickListener
    {

        public static final int ACTION_NEXT=0x001;
        public static final int ACTION_PRE=ACTION_NEXT+1;

        private int action;

        public PagerClickListener(int action)
        {
            this.action=action;
        }
        @Override
        public void onClick(View v) {
            switch (action)
            {
                case ACTION_NEXT:
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1>19?19:viewPager.getCurrentItem()+1,true);
                    break;
                case ACTION_PRE:
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1<0?0:viewPager.getCurrentItem()-1,true);
                    break;
            }
        }
    }

	private class BackClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
				((MainViewActivity)getParent()).backToFragment();
		}

	}

	public static final String TAG = SingerStartAllViewActivity.class
			.getSimpleName();
    private ViewPager viewPager;
	private DisplayMetrics displayMetrics;
    private TextView tv_pageNums;
}
