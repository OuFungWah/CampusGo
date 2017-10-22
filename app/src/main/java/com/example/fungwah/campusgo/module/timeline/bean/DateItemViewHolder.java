package com.example.fungwah.campusgo.module.timeline.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;

/**
 * Created by FungWah on 2017/10/22.
 */

public class DateItemViewHolder extends RecyclerView.ViewHolder {

    public TextView dateTv;
    public View indicatorV;

    public DateItemViewHolder(View itemView) {
        super(itemView);
        dateTv = itemView.findViewById(R.id.timeline_date_tv);
        indicatorV = itemView.findViewById(R.id.timeline_date_indicator);
    }
}
