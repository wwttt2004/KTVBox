package com.yiqiding.ktvbox.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingRightInAnimationAdapter;
import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.util.ViewInject;
import com.yiqiding.ktvbox.widget.adapter.PopOrderedListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PopupWindowOrdereSongsActivity.java
 * com.yiqiding.ktvbox.view
 * <p/>
 * Created by culm on 13-12-4.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class PopupWindowOrdereSongsActivity extends BaseActivity {

    private
    @ViewInject(id = android.R.id.list)
    ListView listView;
    private
    @ViewInject(id = R.id.btn_prePage, click = "onPrePageClick")
    Button btn_prePage;
    private
    @ViewInject(id = R.id.btn_nextPage, click = "onNextPageClick")
    Button btn_nextPage;
    private
    @ViewInject(id = R.id.btn_messCard, click = "onMessCardClick")
    Button btnMessCard;
    private
    @ViewInject(id = R.id.tv_pageNum)
    TextView tv_pageNum;

    private List<String> list_data;
    private List<String> list_curr_data;
    private int pageIndex = 0;
    private int pageNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.popup_ordered_songs_frgment);
        init();
    }

    private void init() {
        new DataHandleAsyncTask().execute();
    }

    public void loadDataByIndex(int i) {
        pageNum = list_data.size() % 8 == 0 ? list_data.size() / 8 : list_data.size() / 8 + 1;
        if (i > pageNum) {
            btn_nextPage.setClickable(false);
            return;
        } else {
            btn_nextPage.setClickable(true);
        }

        if (i < 0) {
            btn_prePage.setClickable(false);
            return;
        } else {
            btn_prePage.setClickable(true);
        }
        try {
            list_curr_data = list_data.subList(8 * i, 8 * i + 8);
        } catch (Exception e) {
            list_curr_data = list_data.subList(8 * i, list_data.size());
        }
        tv_pageNum.setText(String.format("%d/%d", i + 1, pageNum));
        pageIndex = i;
        PopOrderedListAdapter adapter = new PopOrderedListAdapter(this, list_curr_data, i == 0 ? true : false);
        AnimationAdapter animationAdapter = new SwingRightInAnimationAdapter(adapter, 100);
        animationAdapter.setAbsListView(listView);
        listView.setAdapter(animationAdapter);
    }

    public void onNextPageClick(View v) {
        if (pageIndex + 1 > pageNum) {
            return;
        } else {
            loadDataByIndex(pageIndex + 1);
        }
    }

    public void onPrePageClick(View v) {
        if (pageIndex - 1 < 0) {
            return;
        } else {
            loadDataByIndex(pageIndex - 1);
        }
    }

    public void onMessCardClick(View v) {
        if (list_data != null && list_data.size() > 0) {
            Collections.shuffle(list_data);
            loadDataByIndex(0);
        }
    }

    public void gotoFirstPosition(int i) {
        Collections.swap(list_data, i, 0);
        loadDataByIndex(pageIndex);
    }

    public void deleteItemByPosition(int i) {
        list_data.remove(i);
        loadDataByIndex(pageIndex);
    }

    private class DataHandleAsyncTask extends AsyncTask<Integer, Integer, List<String>> {

        @Override
        protected List<String> doInBackground(Integer... integers) {
            list_data = new ArrayList<String>();
            for (int i = 0; i < 8; i++)
                list_data.add("七里香");
            for (int i = 0; i < 7; i++)
                list_data.add("黑色幽默");
            for (int i = 0; i < 9; i++)
                list_data.add("怒放的生命");
            for (int i = 0; i < 5; i++)
                list_data.add("退后");
            Collections.shuffle(list_data);
            return list_data;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            loadDataByIndex(pageIndex);
        }
    }

	@Override
	protected void initMainView() {
		// TODO Auto-generated method stub
		
	}

}
