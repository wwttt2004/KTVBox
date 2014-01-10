package com.yiqiding.ktvbox.widget.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.BitmapUtils;
import com.yiqiding.ktvbox.view.MainRankActivity;

/**
 * SingMenuViewFlowAdapter.java
 * com.yiqiding.ktvbox.widget.adapter
 * <p/>
 * Created by culm on 13-12-2.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class RankMenuViewFlowAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private MainRankActivity mContext;
    private View v_main;
    private ImageView iv_reflect;

    public RankMenuViewFlowAdapter(MainRankActivity context)
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

        view=inflater.inflate(R.layout.main_rank_menu_tab_item_1,null);
        iv_reflect= (ImageView) view.findViewById(R.id.iv_reflect);

        v_main=view.findViewById(R.id.ll_top);

        ImageButton btn_fm= (ImageButton) view.findViewById(R.id.btn_fm);
        btn_fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.gotoYqcRankFragment();
            }
        });

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
                    reflectBitmap=BitmapUtils.createNewReflectedImage(v_main);
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
            iv_reflect.setImageBitmap(reflectBitmap);
        }
    };


}
