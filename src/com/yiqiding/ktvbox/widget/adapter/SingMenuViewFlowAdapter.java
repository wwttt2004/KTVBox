package com.yiqiding.ktvbox.widget.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.BitmapUtils;
import com.yiqiding.ktvbox.view.MainSingActivity;

/**
 * SingMenuViewFlowAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * <p/>
 * Created by culm on 13-12-2.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class SingMenuViewFlowAdapter extends BaseAdapter implements View.OnClickListener, View.OnTouchListener {

    private LayoutInflater inflater;
    private MainSingActivity mContext;
    private View v_main;
    private ImageView iv_reflect;

    private ImageButton ibn_star;
    private ImageButton ibn_yz;
    private ImageButton ibn_name;
    private ImageButton ibn_ad;
    private ImageButton ibn_cate;
    private ImageButton ibn_newSong;
    private ImageView iv_ad_reflect;

    public SingMenuViewFlowAdapter(MainSingActivity context)
    {
        this.mContext=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=inflater.inflate(R.layout.main_sing_menu_tab_item_1,null);

        iv_reflect= (ImageView) view.findViewById(R.id.iv_reflect);

        v_main=view.findViewById(R.id.ll_top);

        iv_ad_reflect=(ImageView)view.findViewById(R.id.iv_ad_reflect);
        ibn_ad= (ImageButton) view.findViewById(R.id.btn_ad);
        ibn_star= (ImageButton) view.findViewById(R.id.btn_star);
        ibn_cate=(ImageButton)view.findViewById(R.id.btn_cate);
        ibn_name=(ImageButton)view.findViewById(R.id.btn_songname);
        ibn_newSong=(ImageButton)view.findViewById(R.id.btn_new_songs);
        ibn_yz=(ImageButton)view.findViewById(R.id.btn_yz);

        ibn_yz.setOnTouchListener(this);
        ibn_name.setOnTouchListener(this);
        ibn_newSong.setOnTouchListener(this);
        ibn_ad.setOnTouchListener(this);
        ibn_cate.setOnTouchListener(this);
        ibn_star.setOnClickListener(this);

        ibn_yz.setOnClickListener(this);
        ibn_name.setOnClickListener(this);
        ibn_newSong.setOnClickListener(this);
        ibn_ad.setOnClickListener(this);
        ibn_cate.setOnClickListener(this);
        ibn_star.setOnClickListener(this);
        
        return view;
    }

    private Bitmap reflectBitmap;

    public void startReflect()throws Exception
    {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    reflectBitmap=BitmapUtils.createReflectedImage(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.advertisement_normal_reflect));
                    handler.sendEmptyMessage(0x001);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            iv_ad_reflect.setImageBitmap(reflectBitmap);
        }
    };

    @Override
    public void onClick(View view) {
    	switch (view.getId()) {
		case R.id.btn_star:
			mContext.gotoSingerStarFragment();
			break;

		default:
			break;
		}
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            //startReflect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
