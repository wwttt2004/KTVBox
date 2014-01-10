package com.yiqiding.ktvbox.widget.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.view.PopupWindowOrdereSongsActivity;
import com.yiqiding.ktvbox.widget.MagicTextView;

import java.util.List;

/**
 * Created by Administrator on 13-12-12.
 */
public class PopOrderedListAdapter extends BaseAdapter {

    private PopupWindowOrdereSongsActivity context;
    private LayoutInflater layoutInflater;
    private List<String> list_data;
    private boolean isFirstPage = false;

    public PopOrderedListAdapter(PopupWindowOrdereSongsActivity context, List<String> list_data, boolean isFirstPage) {
        this.context = context;
        this.list_data = list_data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isFirstPage = isFirstPage;
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
        view = layoutInflater.inflate(R.layout.pop_ordered_list_item, null);

        MagicTextView tv_title = (MagicTextView) view.findViewById(R.id.tv_title);
        MagicTextView tv_state = (MagicTextView) view.findViewById(R.id.tv_state);
        Button btn_first = (Button) view.findViewById(R.id.btn_first);
        Button btn_delete = (Button) view.findViewById(R.id.btn_delete);

        tv_title.setText(String.format("%d.%s", i + 1, list_data.get(i)));

        //set shadow text effect
        if (i == 0 && isFirstPage) {
            tv_title.setTextColor(Color.rgb(255, 204, 0));
            tv_state.setTextColor(Color.rgb(255, 204, 0));
            tv_state.addOuterShadow(20, 3, 3, Color.argb(102, 215, 5, 0));
            tv_state.addOuterShadow(20, 3, 3, Color.argb(102, 215, 5, 0));
            tv_title.addOuterShadow(20, 3, 3, Color.argb(102, 215, 5, 0));
            btn_delete.setVisibility(View.GONE);
            btn_first.setVisibility(View.GONE);
            tv_state.setVisibility(View.VISIBLE);
        }

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    context.gotoFirstPosition(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    context.deleteItemByPosition(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
