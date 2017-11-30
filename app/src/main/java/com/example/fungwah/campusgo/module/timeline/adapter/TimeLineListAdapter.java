package com.example.fungwah.campusgo.module.timeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.common.bean.Events;
import com.example.fungwah.campusgo.module.homepage.bean.TimeLineViewHolder;

import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class TimeLineListAdapter extends RecyclerView.Adapter<TimeLineViewHolder> implements View.OnLongClickListener {

    private AdapterView.OnItemLongClickListener onItemLongClickListener;

    private static final int COURSE = 0;
    private static final int ACTIVITIES = 1;

    private List<Events> list;

    public TimeLineListAdapter(List<Events> list) {
        this.list = list;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == COURSE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_timeline_course_item, parent, false);
        } else if (viewType == ACTIVITIES) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_timeline_activities_item, parent, false);
        }
        return new TimeLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnLongClickListener(this);
        Events event = list.get(position);
        holder.timeLongTv.setText(null);
        String hour = event.getHour() < 10 ? "0" + event.getHour() : "" + event.getHour();
        String min = event.getMinute() < 10 ? "0" + event.getMinute() : "" + event.getMinute();
        holder.timeTv.setText(hour + ":" + min);
        holder.nameTv.setText(event.getName());
        holder.locationTv.setText(event.getLocation());
        holder.remarkTv.setText(event.getContent());
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("课程")) {
            return COURSE;
        } else {
            return ACTIVITIES;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener != null)
            onItemLongClickListener.onItemLongClick(null, v, (int) v.getTag(), v.getId());
        return true;
    }
}
