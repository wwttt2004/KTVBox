package com.yiqiding.ktvbox.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.widget.fragment.BaseFragment;
import com.yiqiding.ktvbox.widget.fragment.MainCenterFragment;

/**
 * MainViewActivity.java com.yiqiding.ktvbox.view
 * <p/>
 * Created by culm on 13-11-22. Copyright (c) 2013 YiQiDing Inc. All rights
 * reserved.
 */
public class MainViewActivity extends BaseActivity {

	private @ViewInject(id = R.id.v_main_2)
	ImageView v_main;

	public static Bitmap BITMAP_GREEN;
	public static Bitmap BITMAP_RED;
	public static Bitmap BITMAP_PURPLE;
	public static Bitmap BITMAP_BLUE;
	public static Bitmap BITMAP_ORANGE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		initResource();
		replaceFragment(R.id.fg_center, new MainCenterFragment());
	}

	public void initResource() {
		BITMAP_BLUE = BitmapFactory.decodeResource(getResources(),
				R.drawable.blue_bg);
		BITMAP_GREEN = BitmapFactory.decodeResource(getResources(),
				R.drawable.green_bg);
		BITMAP_ORANGE = BitmapFactory.decodeResource(getResources(),
				R.drawable.orange_bg);
		BITMAP_PURPLE = BitmapFactory.decodeResource(getResources(),
				R.drawable.purple_bg);
		BITMAP_RED = BitmapFactory.decodeResource(getResources(),
				R.drawable.red_bg);

	}

	public void changeMainBackGround(final int i) {

		switch (i) {
		case R.drawable.green_bg:
			if (BITMAP_GREEN == null)
				BITMAP_GREEN = BitmapFactory.decodeResource(getResources(),
						R.drawable.green_bg);
			v_main.setImageBitmap(BITMAP_GREEN);
			break;
		case R.drawable.orange_bg:
			if (BITMAP_ORANGE == null)
				BITMAP_ORANGE = BitmapFactory.decodeResource(getResources(),
						R.drawable.orange_bg);
			v_main.setImageBitmap(BITMAP_ORANGE);
			break;
		case R.drawable.red_bg:
			if (BITMAP_RED == null)
				BITMAP_RED = BitmapFactory.decodeResource(getResources(),
						R.drawable.red_bg);
			v_main.setImageBitmap(BITMAP_RED);
			break;
		case R.drawable.blue_bg:
			if (BITMAP_BLUE == null)
				BITMAP_BLUE = BitmapFactory.decodeResource(getResources(),
						R.drawable.blue_bg);
			v_main.setImageBitmap(BITMAP_BLUE);
			break;
		case R.drawable.purple_bg:
			if (BITMAP_PURPLE == null)
				BITMAP_PURPLE = BitmapFactory.decodeResource(getResources(),
						R.drawable.purple_bg);
			v_main.setImageBitmap(BITMAP_PURPLE);
			break;
		}
		/*
		 * AlphaAnimation animation_1=new AlphaAnimation(1.0f,0.0f);
		 * animation_1.setDuration(300); animation_1.setStartOffset(150);
		 * animation_1.setFillAfter(true); animation_1.setAnimationListener(new
		 * Animation.AnimationListener() {
		 * 
		 * @Override public void onAnimationStart(Animation animation) {
		 * 
		 * }
		 * 
		 * @Override public void onAnimationEnd(Animation animation) {
		 * v_main_2.setBackgroundResource(i); }
		 * 
		 * @Override public void onAnimationRepeat(Animation animation) {
		 * 
		 * } }); AlphaAnimation animation_2=new AlphaAnimation(0.0f,1.0f);
		 * animation_1.setDuration(300); animation_1.setFillAfter(true);
		 * v_main_1.setBackgroundResource(i);
		 * v_main_2.startAnimation(animation_1);
		 * v_main_1.startAnimation(animation_2);
		 */
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			releaseResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bitmap etc. Recycle Memory
	 * 
	 * @throws Exception
	 */
	private synchronized void releaseResource() throws Exception {
		BITMAP_BLUE.recycle();
		BITMAP_GREEN.recycle();
		BITMAP_RED.recycle();
		BITMAP_PURPLE.recycle();
		BITMAP_ORANGE.recycle();
	}

	public void gotoActivity(BaseFragment fragment) {
		replaceFragment(R.id.fg_center, fragment, R.animator.open_next,
				R.animator.close_next, R.animator.open_back, R.animator.close_back);
	}

	@Override
	protected void initMainView() {
		// TODO Auto-generated method stub
		
	}
}
