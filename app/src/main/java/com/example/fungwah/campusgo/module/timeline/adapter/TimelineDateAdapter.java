package com.example.fungwah.campusgo.module.timeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.timeline.bean.DateItemViewHolder;
import com.example.fungwah.campusgo.module.timeline.bean.DateTitleViewHolder;
import com.example.fungwah.campusgo.module.timeline.bean.TagDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/22.
 */

public class TimelineDateAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private static final String TAG = "TimelineDateAdapter";

    enum TYPE {
        TITLE,
        DATE
    }

    private List<TagDate> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public TimelineDateAdapter(List<TagDate> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE.TITLE.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_date_item_title, parent, false);
            return new DateTitleViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_date_item_num, parent, false);
            return new DateItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DateTitleViewHolder) {
            ((DateTitleViewHolder) holder).titleTv.setText(list.get(position).getTitle());
        } else {
            ((DateItemViewHolder) holder).dateTv.setText(list.get(position).getDay()+"");
            ((DateItemViewHolder) holder).indicatorV.setVisibility(list.get(position).isSelected() ? View.VISIBLE : View.INVISIBLE);
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount:   list.size() = "+list.size());
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 7) {
            return TYPE.TITLE.ordinal();
        } else {
            return TYPE.DATE.ordinal();
        }
    }


    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
