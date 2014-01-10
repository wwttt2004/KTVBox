package com.yiqiding.ktvbox.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewUtils.java
 * com.yiqiding.ktvbox.utils
 * <p/>
 * Created by culm on 13-12-4.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class ViewUtils {

    public static DisplayMetrics getScreenResolution(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    private static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
}
