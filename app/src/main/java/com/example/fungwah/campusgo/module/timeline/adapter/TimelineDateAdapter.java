package com.example.fungwah.campusgo.module.timeline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.timeline.bean.DateItemViewHolder;
import com.example.fungwah.campusgo.module.timeline.bean.DateTitleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/22.
 */

public class TimelineDateAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    enum TYPE {
        TITLE,
        DATE
    }

    private List<String> list = new ArrayList<>();
    private String strArr[] = new String[]{"SUN", "MON", "TUE", "WEN", "THU", "FIR", "STA"};
    private OnItemClickListener onItemClickListener;

    public TimelineDateAdapter(List<String> list) {
        for (int i = 0; i < strArr.length; i++) {
            this.list.add(strArr[i]);
        }
        this.list.addAll(list);
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
            ((DateTitleViewHolder) holder).titleTv.setText(list.get(position));
        } else {
            ((DateItemViewHolder) holder).dateTv.setText(list.get(position));
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
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

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
