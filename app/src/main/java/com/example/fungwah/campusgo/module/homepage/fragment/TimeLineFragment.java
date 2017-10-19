package com.example.fungwah.campusgo.module.homepage.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.homepage.adapter.TimeLineListAdapter;
import com.example.fungwah.campusgo.module.homepage.bean.TimeLineItem;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class TimeLineFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private TimeLineListAdapter timeLineListAdapter;
    private List<TimeLineItem> timelineItemList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected int setLayoutId() {
        return R.layout.homepage_timeline_fragment;
    }

    @Override
    protected void initView(View parent) {
        initTimeLineItemList();
        recyclerView = findView(R.id.homepage_fragment_content_rv);
        timeLineListAdapter = new TimeLineListAdapter(timelineItemList);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    }

    /**
     * 初始化列表
     */
    private void initTimeLineItemList() {
        timelineItemList.add(new TimeLineItem());
        timelineItemList.add(new TimeLineItem());
        timelineItemList.add(new TimeLineItem());
        timelineItemList.add(new TimeLineItem());
        timelineItemList.add(new TimeLineItem());
    }

    @Override
    protected void setView() {
        recyclerView.setAdapter(timeLineListAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initListener() {

    }
}
