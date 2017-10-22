package com.example.fungwah.campusgo.module.setting.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;

/**
 * Created by FungWah on 2017/10/22.
 */

public class ItemTitleViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ItemTitleViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.setting_item_title_tv);
    }
}
