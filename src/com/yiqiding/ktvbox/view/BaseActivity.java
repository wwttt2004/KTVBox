/**
 * 
 *  BaseActivity.java
 *  com.yiqiding.ktvbox.widget
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */
package com.yiqiding.ktvbox.view;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;

import com.nostra13.universalimageloader.cache.disc.impl.FileCountLimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.yiqiding.ktvbox.util.EventListener;
import com.yiqiding.ktvbox.util.Select;
import com.yiqiding.ktvbox.util.ViewInject;

public abstract class BaseActivity extends Activity{
    protected View mainView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
            final ImageLoader imageLoader = ImageLoader.getInstance();
            final DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc()
                    .build();
            final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                    .memoryCacheExtraOptions(480, 800).discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75)
                    .threadPoolSize(2).threadPriority(Thread.MIN_PRIORITY).denyCacheImageMultipleSizesInMemory()
                    .offOutOfMemoryHandling().memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                    //.discCache(new FileCountLimitedDiscCache(cacheDir, 512))
                    .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                    .imageDownloader(new BaseImageDownloader(getApplicationContext(), 10 * 1000, 30 * 1000))
                    .tasksProcessingOrder(QueueProcessingType.FIFO).defaultDisplayImageOptions(defaultOptions).build();
            imageLoader.init(config);
			initMainView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initView() {
		Field[] fields = getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				ViewInject viewInject = field.getAnnotation(ViewInject.class);
				if (viewInject != null) {
					int viewId = viewInject.id();
					try {
						field.setAccessible(true);
						field.set(this, findViewById(viewId));
					} catch (Exception e) {
						e.printStackTrace();
					}

					String clickMethod = viewInject.click();
					if (!TextUtils.isEmpty(clickMethod))
						setViewClickListener(field, clickMethod);

					String longClickMethod = viewInject.longClick();
					if (!TextUtils.isEmpty(longClickMethod))
						setViewLongClickListener(field, longClickMethod);

					String itemClickMethod = viewInject.itemClick();
					if (!TextUtils.isEmpty(itemClickMethod))
						setItemClickListener(field, itemClickMethod);

					String itemLongClickMethod = viewInject.itemLongClick();
					if (!TextUtils.isEmpty(itemLongClickMethod))
						setItemLongClickListener(field, itemLongClickMethod);

					Select select = viewInject.select();
					if (!TextUtils.isEmpty(select.selected()))
						setViewSelectListener(field, select.selected(),
								select.noSelected());

				}
			}
		}
	}

	public void setContentView(int layoutResID) {
        mainView = getLayoutInflater().from(this).inflate(layoutResID, null);
        setContentView(mainView);
		initView();
	}

	public void setContentView(View view) {
        //view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        super.setContentView(view);
		initView();

	}

	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		initView();
	}

	private void setItemClickListener(Field field, String itemClickMethod) {
		try {
			Object obj = field.get(this);
			if (obj instanceof AbsListView) {
				((AbsListView) obj).setOnItemClickListener(new EventListener(
						this).itemClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setItemLongClickListener(Field field, String itemClickMethod) {
		try {
			Object obj = field.get(this);
			if (obj instanceof AbsListView) {
				((AbsListView) obj)
						.setOnItemLongClickListener(new EventListener(this)
								.itemLongClick(itemClickMethod));
			}
		} catch (Exception e) {
		}
	}

	private void setViewClickListener(Field field, String clickMethod) {
		try {
			Object obj = field.get(this);
			if (obj instanceof View) {
				((View) obj).setOnClickListener(new EventListener(this)
						.click(clickMethod));
			}
		} catch (Exception e) {
		}
	}

	private void setViewLongClickListener(Field field, String clickMethod) {
		try {
			Object obj = field.get(this);
			if (obj instanceof View) {
				((View) obj).setOnLongClickListener(new EventListener(this)
						.longClick(clickMethod));
			}
		} catch (Exception e) {

		}
	}

	private void setViewSelectListener(Field field, String select,
			String noSelect) {
		try {
			Object obj = field.get(this);
			if (obj instanceof View) {
				((AbsListView) obj)
						.setOnItemSelectedListener(new EventListener(this)
								.select(select).noSelect(noSelect));
			}
		} catch (Exception e) {

		}
	}

	public void backToFragment()
	{
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.popBackStack();
	}

    public void  replaceFragment(int id,Fragment fragment)
    {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void  replaceFragment(int id,Fragment fragment,int i11,int i12)
    {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(i11,i12);
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    
    public void  replaceFragment(int id,Fragment fragment,int i11,int i12,int i21,int i22)
    {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(i11,i12,i21,i22);
        fragmentTransaction.replace(id,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public   boolean  onTouchEvent(MotionEvent event) {
        InputMethodManager imm= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if  (event.getAction() == MotionEvent.ACTION_DOWN) {
            if  (this .getCurrentFocus() !=  null ) {
                if  (this .getCurrentFocus().getWindowToken() !=  null ) {
                    imm.hideSoftInputFromWindow(this .getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }
    
    protected abstract void initMainView() throws Exception;
}
