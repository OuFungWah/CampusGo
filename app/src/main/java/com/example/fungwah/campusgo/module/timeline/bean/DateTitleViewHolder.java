package com.example.fungwah.campusgo.module.timeline.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;

/**
 * Created by FungWah on 2017/10/22.
 */

public class DateTitleViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTv;

    public DateTitleViewHolder(View itemView) {
        super(itemView);
        titleTv = itemView.findViewById(R.id.timeline_date_item_title_tv);
    }
}
