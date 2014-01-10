package com.yiqiding.ktvbox.widget;

import android.view.MotionEvent;
import android.view.View;

/**
 * SwipeOnTouchListener.java
 * com.yiqiding.ktvbox.widget
 * <p/>
 * Created by culm on 13-12-3.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public interface SwipeOnTouchListener extends View.OnTouchListener {
    public boolean isSwiping();
}
