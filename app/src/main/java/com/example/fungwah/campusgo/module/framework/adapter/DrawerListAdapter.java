package com.example.fungwah.campusgo.module.framework.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.framework.bean.DrawerDividerViewHolder;
import com.example.fungwah.campusgo.module.framework.bean.DrawerItemBean;
import com.example.fungwah.campusgo.module.framework.bean.DrawerItemViewHolder;

import java.util.List;

/**
 * Created by FungWah on 2017/10/17.
 */

public class DrawerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    enum TYPE {
        DIVIDER,
        ITEM
    }

    private List<DrawerItemBean> list;

    public DrawerListAdapter(List<DrawerItemBean> list){
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE.DIVIDER.ordinal()){
            View view = new View(parent.getContext());
            view.setBackgroundColor(parent.getResources().getColor(R.color.colorDivider));
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,3));
            return new DrawerDividerViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item,parent,false);
            return new DrawerItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DrawerItemViewHolder){
            DrawerItemBean bean = list.get(position);
            ((DrawerItemViewHolder)holder).icon.setImageResource(bean.getIconRes());
            ((DrawerItemViewHolder)holder).name.setText(bean.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        DrawerItemBean bean = list.get(position);
        if(bean == null){
            return TYPE.DIVIDER.ordinal();
        }else{
            return TYPE.ITEM.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
