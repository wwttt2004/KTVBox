package com.yiqiding.ktvbox.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;

/**
 * MainViewActivity.java
 * com.yiqiding.ktvbox.view
 * <p/>
 * Created by culm on 13-11-22.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class MainGamesActivity extends BaseActivity{
    private @ViewInject(id=R.id.btn_enter,click = "onEnterClick")
    Button btn_enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main_games_content);
    }

    public void onEnterClick(View v)
    {

    }

	@Override
	protected void initMainView() {
		// TODO Auto-generated method stub
		
	}

}
