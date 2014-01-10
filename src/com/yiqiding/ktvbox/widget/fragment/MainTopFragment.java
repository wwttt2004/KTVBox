/**
 * 
 *  MainTopFragment.java
 *  com.yiqiding.ktvbox.widget
 *
 *  Created by culm on 2013-11-22.
 *  Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 *
 */
package com.yiqiding.ktvbox.widget.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;

public class MainTopFragment extends BaseFragment{

    private @ViewInject(id=R.id.btn_weather,click = "onWeatherClick")
    Button btn_weather;
    private @ViewInject(id=R.id.et_search)
    EditText et_search;
    private @ViewInject(id=R.id.et_search_hl)
    EditText et_search_hl;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_top_fragment,null);
	}

    public void onWeatherClick(View v)
    {
/*        BaseKeyBoardDialog dialog=new BaseKeyBoardDialog(this);
        WindowManager.LayoutParams layoutParams=dialog.getWindow().getAttributes();

        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        p.height = (int) (d.getHeight() * 0.6);
        p.width = (int) (d.getWidth() * 0.65);


        dialog.getWindow().setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        dialog.getWindow().setAttributes(p);

        dialog.show();*/
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
/*        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                et_search_hl.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.search_box_in));
                return false;
            }
        });
        et_search_hl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Animation animationn=AnimationUtils.loadAnimation(getActivity(), R.anim.search_box_out);
                et_search_hl.startAnimation(animationn);
                return false;
            }
        });*/
    }

	@Override
	protected void initContentView() {
		// TODO Auto-generated method stub
		
	}
}
