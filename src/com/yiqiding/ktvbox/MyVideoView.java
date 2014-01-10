package com.yiqiding.ktvbox;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.widget.VideoView;

/**
 * MyVideoView.java
 * com.yiqiding.ktvbox
 * KTVBox
 * Created by culm on 13-12-24
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class MyVideoView extends VideoView{
    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 /*   public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder = this.getHolder(); //获取holder对象
        holder.addCallback(this); // 添加surface回调函数
        mThread = new MyThread(holder); //创建一个绘图线程
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.isRun = true;
        mThread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.isRun = false;
        mThread.stop();
    }

    private SurfaceHolder holder;
    private MyThread mThread ;

    private int positionX=0;
    private int positionY=0;

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //Log.i("@@@@", "onMeasure");
           int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
          int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
         *//*if (mVideoWidth > 0 && mVideoHeight > 0) {
 7            if ( mVideoWidth * height  > width * mVideoHeight ) {
 8                //Log.i("@@@", "image too tall, correcting");
 9                height = width * mVideoHeight / mVideoWidth;
10            } else if ( mVideoWidth * height  < width * mVideoHeight ) {
11                //Log.i("@@@", "image too wide, correcting");
12                width = height * mVideoWidth / mVideoHeight;
13            } else {
14                //Log.i("@@@", "aspect ratio is correct: " +
15                        //width+"/"+height+"="+
16                        //mVideoWidth+"/"+mVideoHeight);
17            }
18        }*//*
           //Log.i("@@@@@@@@@@", "setting size: " + width + 'x' + height);
            setMeasuredDimension(width,height);
           }*/
}
