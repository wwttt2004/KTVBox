package com.yiqiding.ktvbox.widget.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiqiding.ktvbox.R;

/**
 * SecondFragment.java
 * com.yiqiding.ktvbox.widget
 * <p/>
 * Created by culm on 13-12-2.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class SecondFragment extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.main_center_fragment_content,null);
    }

	@Override
	protected void initContentView() {
		// TODO Auto-generated method stub
		
	}
}
