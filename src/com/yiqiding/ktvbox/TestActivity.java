package com.yiqiding.ktvbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.view.BaseActivity;

import java.io.File;

/**
 * TestActivity.java
 * com.yiqiding.ktvbox
 * KTVBox
 * Created by culm on 13-12-24
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class TestActivity extends BaseActivity{

/*
    private
    @ViewInject(id=R.id.VideoView)
    VideoView videoView;

    private @ViewInject(id=R.id.surfaceView)
    SurfaceView surfaceView;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview);
/*        if(!new File(Environment.getExternalStorageDirectory().getPath()+"/test.mp4").exists())
            return;
        videoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/test.mp4"));
        videoView.start();*/
    }

    @Override
    protected void initMainView() throws Exception {

    }



}
