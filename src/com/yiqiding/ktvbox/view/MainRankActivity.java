package com.yiqiding.ktvbox.view;

import android.os.Bundle;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.widget.adapter.RankMenuViewFlowAdapter;
import com.yiqiding.ktvbox.widget.fragment.SingerStarFragment;
import com.yiqiding.ktvbox.widget.fragment.YqcRankGridListFragment;
import com.yiqiding.ktvbox.widget.viewflow.ViewFlow;

/**
 * MainViewActivity.java
 * com.yiqiding.ktvbox.view
 * <p/>
 * Created by culm on 13-11-22.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class MainRankActivity extends BaseActivity{
    public static final String TAG=MainRankActivity.class.getName();

    private @ViewInject(id=R.id.viewflow)
    ViewFlow viewFlow;
    private RankMenuViewFlowAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main_center_fragment_content);
        adapter=new RankMenuViewFlowAdapter(this);
        viewFlow.setAdapter(adapter);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
/*        if(hasFocus)
        {
            try {
                adapter.startReflect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }


	@Override
	protected void initMainView() {
		// TODO Auto-generated method stub
		
	}

    public void gotoYqcRankFragment()
    {
        MainViewActivity activity=(MainViewActivity) getParent();
        activity.gotoActivity(new YqcRankGridListFragment());
    }

}
