package com.example.fungwah.campusgo.module.timeline.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.FabAnimHelper;
import com.example.fungwah.campusgo.module.timeline.activity.AddActivitiesActivity;
import com.example.fungwah.campusgo.module.timeline.activity.AddCourseActivity;
import com.example.fungwah.campusgo.module.timeline.adapter.TimelineDateAdapter;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/21.
 */

public class TimelineFragment extends BaseFragment implements View.OnClickListener {

    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private FabAnimHelper fabAnimHelper;

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
        fabAnimHelper = new FabAnimHelper(parent, 300);

        timelineDateAdapter = new TimelineDateAdapter(dateList);
        layoutManager = new GridLayoutManager(getContext(), 7, LinearLayoutManager.VERTICAL, false);

        recyclerView = findView(R.id.timeline_rv);
        frameLayout = findView(R.id.timeline_content_fragment_fl);
    }

    private void initDateList() {
        dateList.clear();
        for (int i = 20; i < 27; i++) {
            dateList.add("" + i);
        }
    }

    @Override
    protected void setView() {
        changeFragment(new TimeLineListFragment());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(timelineDateAdapter);
    }

    @Override
    protected void initListener() {
        fabAnimHelper.initFabClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_fab:
                fabAnimHelper.startAnimation();
                break;
            case R.id.add_course_fab:
                startActivity(AddCourseActivity.class);
                fabAnimHelper.startAnimation();
                break;
            case R.id.add_activity_fab:
                startActivity(AddActivitiesActivity.class);
                fabAnimHelper.startAnimation();
                break;
        }
    }
}
