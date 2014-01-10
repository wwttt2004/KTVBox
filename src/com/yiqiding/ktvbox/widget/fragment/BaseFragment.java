package com.yiqiding.ktvbox.widget.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.yiqiding.ktvbox.util.EventListener;
import com.yiqiding.ktvbox.util.Select;
import com.yiqiding.ktvbox.util.ViewInject;

import java.lang.reflect.Field;

/**
 * BaseFragment.java
 * com.yiqiding.ktvbox.widget
 * <p/>
 * Created by culm on 13-11-22.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public abstract class BaseFragment extends Fragment {
	
	protected View mainView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() throws Exception{
        Field[] fields = getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                if (viewInject != null) {
                    int viewId = viewInject.id();
                    try {
                        field.setAccessible(true);
                        field.set(this, this.getView().findViewById(viewId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String clickMethod = viewInject.click();
                    if (!TextUtils.isEmpty(clickMethod))
                        setViewClickListener(field, clickMethod);

                    String longClickMethod = viewInject.longClick();
                    if (!TextUtils.isEmpty(longClickMethod))
                        setViewLongClickListener(field, longClickMethod);

                    String itemClickMethod = viewInject.itemClick();
                    if (!TextUtils.isEmpty(itemClickMethod))
                        setItemClickListener(field, itemClickMethod);

                    String itemLongClickMethod = viewInject.itemLongClick();
                    if (!TextUtils.isEmpty(itemLongClickMethod))
                        setItemLongClickListener(field, itemLongClickMethod);

                    Select select = viewInject.select();
                    if (!TextUtils.isEmpty(select.selected()))
                        setViewSelectListener(field, select.selected(),
                                select.noSelected());

                }
            }
        }
    }


    private void setItemClickListener(Field field, String itemClickMethod) {
        try {
            Object obj = field.get(this);
            if (obj instanceof AbsListView) {
                ((AbsListView) obj).setOnItemClickListener(new EventListener(
                        this).itemClick(itemClickMethod));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemLongClickListener(Field field, String itemClickMethod) {
        try {
            Object obj = field.get(this);
            if (obj instanceof AbsListView) {
                ((AbsListView) obj)
                        .setOnItemLongClickListener(new EventListener(this)
                                .itemLongClick(itemClickMethod));
            }
        } catch (Exception e) {
        }
    }

    private void setViewClickListener(Field field, String clickMethod) {
        try {
            Object obj = field.get(this);
            if (obj instanceof View) {
                ((View) obj).setOnClickListener(new EventListener(this)
                        .click(clickMethod));
            }
        } catch (Exception e) {
        }
    }

    private void setViewLongClickListener(Field field, String clickMethod) {
        try {
            Object obj = field.get(this);
            if (obj instanceof View) {
                ((View) obj).setOnLongClickListener(new EventListener(this)
                        .longClick(clickMethod));
            }
        } catch (Exception e) {

        }
    }

    private void setViewSelectListener(Field field, String select,
                                       String noSelect) {
        try {
            Object obj = field.get(this);
            if (obj instanceof View) {
                ((AbsListView) obj)
                        .setOnItemSelectedListener(new EventListener(this)
                                .select(select).noSelect(noSelect));
            }
        } catch (Exception e) {

        }
    }
    
    protected abstract void initContentView();
    
}
