package com.example.fungwah.campusgo.module.framework.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;

/**
 * Created by FungWah on 2017/10/17.
 */

public class DrawerItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView name;

    public DrawerItemViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.drawer_item_icon_img);
        name = (TextView) itemView.findViewById(R.id.drawer_item_name_tv);
    }

}
