package com.yiqiding.ktvbox.view;

import android.os.Bundle;
import android.view.MotionEvent;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.widget.adapter.SingMenuViewFlowAdapter;
import com.yiqiding.ktvbox.widget.fragment.SingerStarFragment;
import com.yiqiding.ktvbox.widget.viewflow.ViewFlow;

/**
 * MainSingActivity.java
 * com.yiqiding.ktvbox.view
 * <p/>
 * Created by culm on 13-11-25.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class MainSingActivity extends BaseActivity{

    private @ViewInject(id=R.id.viewflow)
    ViewFlow viewFlow;

    private SingMenuViewFlowAdapter adapter;

    private boolean isFocus=false;

    public void gotoSingerStarFragment()
    {
    	MainViewActivity activity=(MainViewActivity) getParent();
    	activity.gotoActivity(new SingerStarFragment());
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main_center_fragment_content);
        adapter=new SingMenuViewFlowAdapter(this);
        viewFlow.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        {
            try {
                isFocus=hasFocus;
                adapter.startReflect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFocus)
        {
            try {
                adapter.startReflect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            adapter.startReflect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(event);
    }

	@Override
	protected void initMainView() {
		// TODO Auto-generated method stub
		
	}
}
