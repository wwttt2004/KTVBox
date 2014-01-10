package com.yiqiding.ktvbox.widget.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.view.PopupWindowOrdereSongsActivity;
import com.yiqiding.ktvbox.view.PopupWindowSangSongsActivity;
import com.yiqiding.ktvbox.widget.MagicTextView;

import java.util.List;

/**
 * Created by Administrator on 13-12-12.
 */
public class PopSangListAdapter extends BaseAdapter {

    private PopupWindowSangSongsActivity context;
    private LayoutInflater layoutInflater;
    private List<String> list_data;

    public PopSangListAdapter(PopupWindowSangSongsActivity context, List<String> list_data) {
        this.context = context;
        this.list_data = list_data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_data.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.pop_sang_list_item, null);

        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        Button btn_again = (Button) view.findViewById(R.id.btn_again);

        tv_title.setText(String.format("%d.%s", i + 1, list_data.get(i)));

        return view;
    }
}
