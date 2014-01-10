package com.yiqiding.ktvbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * MySurfaceVIEW.java
 * com.yiqiding.ktvbox
 * KTVBox
 * Created by culm on 13-12-24
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class MySurfaceVIEW extends SurfaceView implements SurfaceHolder.Callback
{

    private SurfaceHolder holder;
    private MyThread mThread ;

    private int positionX=0;
    private int positionY=0;

    public MySurfaceVIEW(Context context){
        super(context);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
        holder = this.getHolder(); //获取holder对象
        holder.addCallback(this); // 添加surface回调函数
        mThread = new MyThread(holder); //创建一个绘图线程
    }

    public MySurfaceVIEW(Context context,AttributeSet set)
    {
        super(context);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder = this.getHolder(); //获取holder对象
        holder.addCallback(this); // 添加surface回调函数
        mThread = new MyThread(holder); //创建一个绘图线程

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
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

    class MyThread extends Thread{
        private SurfaceHolder holder ;
        public  boolean isRun = false;

        public MyThread(SurfaceHolder holder){
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run(){
            Canvas canvas = null;
            int count = 0;
            while (isRun) {
                try {
                    synchronized (holder) {
                        canvas = holder.lockCanvas();// 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// 设置画布背景颜色
                        positionX+=3;
                        positionY+=3;
                        Paint p = new Paint(); // 创建画笔
                        p.setColor(Color.RED);
                        Rect r = new Rect(positionX+500, 200, positionX+100, 100);
                        if(positionY>1200)
                            positionY=0;
                        if(positionX>1920)
                            positionX=0;
                        canvas.drawRect(r, p);
                        canvas.drawText("SurfaceView " + (count++) + "秒", positionX, 100, p);
                        canvas.drawText("测试", positionX-100, 200, p);
                        p.setTextSize(23);
                        canvas.drawText("测试", positionX-300, 300, p);
                        canvas.drawText("测试", positionX-400, 400, p);

                        Thread.sleep(5);// 睡眠时间为1秒
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        holder.unlockCanvasAndPost(canvas);// 结束锁定画图，并提交改变。
                    }
                }
            }
        }
    }
}