package com.yiqiding.ktvbox.view;

import android.os.Bundle;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.widget.HorizontalListView;
import com.yiqiding.ktvbox.widget.adapter.ActivityListAdapter;

/**
 * MainViewActivity.java
 * com.yiqiding.ktvbox.view
 * <p/>
 * Created by culm on 13-11-22.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class MainActivitysActivity extends BaseActivity{

    private @ViewInject(id=R.id.horizontalListView)
    HorizontalListView horizontalListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main_activity_content);
        try {
            initUI();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initUI() throws Exception{

       horizontalListView.setVerticalFadingEdgeEnabled(false);
       horizontalListView.setAdapter(new ActivityListAdapter(this));

    }

	@Override
	protected void initMainView() {
		// TODO Auto-generated method stub
		
	}


}
