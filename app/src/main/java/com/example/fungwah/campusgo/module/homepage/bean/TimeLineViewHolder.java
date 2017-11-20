package com.example.fungwah.campusgo.module.homepage.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;

/**
 * Created by FungWah on 2017/10/19.
 */

public class TimeLineViewHolder extends RecyclerView.ViewHolder {

    public TextView timeTv;
    public TextView nameTv;
    public TextView locationTv;
    public TextView remarkTv;
    public TextView timeLongTv;

    public TimeLineViewHolder(View itemView) {
        super(itemView);
        timeTv = itemView.findViewById(R.id.event_time_tv);
        nameTv = itemView.findViewById(R.id.event_name_tv);
        locationTv = itemView.findViewById(R.id.event_location_tv);
        remarkTv = itemView.findViewById(R.id.event_remark_tv);
        timeLongTv = itemView.findViewById(R.id.event_time_long_tv);
    }
}
