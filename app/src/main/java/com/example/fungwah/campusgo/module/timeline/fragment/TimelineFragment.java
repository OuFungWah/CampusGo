package com.example.fungwah.campusgo.module.timeline.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.homepage.fragment.TimeLineFragment;
import com.example.fungwah.campusgo.module.timeline.adapter.TimelineDateAdapter;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/21.
 */

public class TimelineFragment extends BaseFragment {

    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton addFAB;

    private TimelineDateAdapter timelineDateAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> dateList = new ArrayList<>();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void initObject() {
        initDateList();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.timeline_fragment;
    }

    @Override
    protected void initView(View parent) {

        fragmentManager = getActivity().getSupportFragmentManager();

        timelineDateAdapter = new TimelineDateAdapter(dateList);
        layoutManager = new GridLayoutManager(getContext(), 7, LinearLayoutManager.VERTICAL, false);

        recyclerView = findView(R.id.timeline_rv);
        frameLayout = findView(R.id.timeline_content_fragment_fl);
        addFAB = findView(R.id.timeline_add_fab);
    }

    private void initDateList() {
        for (int i = 20; i < 27; i++) {
            dateList.add("" + i);
        }
    }

    @Override
    protected void setView() {
        changeFragment(new TimeLineFragment());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(timelineDateAdapter);
    }

    @Override
    protected void initListener() {

    }

    /**
     * @param fragment
     */
    protected boolean changeFragment(BaseFragment fragment) {
        boolean flag = false;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.timeline_content_fragment_fl, fragment);
        fragmentTransaction.commit();
        return flag;
    }

}
