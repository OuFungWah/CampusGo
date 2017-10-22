package com.example.fungwah.campusgo.module.setting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.setting.bean.ItemContentViewHolder;
import com.example.fungwah.campusgo.module.setting.bean.ItemTitleViewHolder;
import com.example.fungwah.campusgo.module.setting.bean.SettingItemBean;

import java.util.List;

/**
 * Created by FungWah on 2017/10/22.
 */

public class SettingListAdapter extends RecyclerView.Adapter {

    enum TYPE {
        TITLE,
        CONTENT
    }

    private List<SettingItemBean> list;

    public SettingListAdapter(List<SettingItemBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE.TITLE.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_item_title, parent, false);
            return new ItemTitleViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_item, parent, false);
            return new ItemContentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemTitleViewHolder) {
            ((ItemTitleViewHolder) holder).textView.setText(list.get(position).getText());
        } else {
            ((ItemContentViewHolder) holder).textView.setText(list.get(position).getText());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == SettingItemBean.TITLE) {
            return TYPE.TITLE.ordinal();
        } else {
            return TYPE.CONTENT.ordinal();
        }
    }
}
