package com.example.fungwah.campusgo.module.timeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.homepage.bean.TimeLineItem;
import com.example.fungwah.campusgo.module.homepage.bean.TimeLineViewHolder;

import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class TimeLineListAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimeLineItem> list;

    public TimeLineListAdapter(List<TimeLineItem> list){
        this.list = list;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_timeline_item,parent,false);
        return new TimeLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
