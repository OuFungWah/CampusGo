package com.example.fungwah.campusgo.module.setting.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.setting.adapter.SettingListAdapter;
import com.example.fungwah.campusgo.module.setting.bean.SettingItemBean;
import com.example.fungwahtools.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/22.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ImageView actionbarLeftImg;
    private TextView actionTitleTv;
    private List<SettingItemBean> list = new ArrayList<>();
    private SettingListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.setting_activity;
    }

    private void initList() {
        list.add(new SettingItemBean(SettingItemBean.TITLE, "账户与安全"));
        list.add(new SettingItemBean(SettingItemBean.CONTENT, "个人资料"));
        list.add(new SettingItemBean(SettingItemBean.CONTENT, "账号管理"));
        list.add(new SettingItemBean(SettingItemBean.TITLE, "消息通知"));
        list.add(new SettingItemBean(SettingItemBean.CONTENT, "推送选项"));
        list.add(new SettingItemBean(SettingItemBean.CONTENT, "声音与震动"));
        list.add(new SettingItemBean(SettingItemBean.TITLE, "管理"));
        list.add(new SettingItemBean(SettingItemBean.CONTENT, "班级管理"));
        list.add(new SettingItemBean(SettingItemBean.CONTENT, "社团管理"));
    }

    @Override
    protected void initView() {
        initList();
        recyclerView = findView(R.id.setting_rv);
        actionbarLeftImg = findView(R.id.actionbar_left_img);
        actionTitleTv = findView(R.id.actionbar_title_tv);

        adapter = new SettingListAdapter(list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected void setView() {
        actionTitleTv.setText("设置");
        actionbarLeftImg.setImageResource(R.drawable.ic_arrow_back_white_36dp);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initListener() {
        actionbarLeftImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionbar_left_img:
                onBackPressed();
                break;
        }
    }
}
