package com.example.fungwah.campusgo.module.homepage.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.FabAnimHelper;
import com.example.fungwah.campusgo.command.adapter.FragmentPageAdapter;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class HomepageFragment extends BaseFragment implements View.OnClickListener {

    private static final long ANIM_TIME = 300;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    private FabAnimHelper fabAnimHelper;

    private TextView courseTv;
    private TextView activityTv;

    @Override
    protected void initObject() {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.homepage_fragment;
    }

    @Override
    protected void initView(View parent) {
        initFragmentList();
        //初始化添加按钮工具类
        fabAnimHelper = new FabAnimHelper(parent);

        activityTv = findView(R.id.activity_fab_tv);
        courseTv = findView(R.id.course_fab_tv);
        tabLayout = findView(R.id.homepage_tab_layout);
        viewPager = findView(R.id.homepage_fragment_content_vp);
        // TODO: 2017/10/23  此处注意，Fragment 嵌套使用时，子 fragment 添加的是 ChildFragmentManager 而非 SupportFragmentManager ,回头了解一下
        pagerAdapter = new FragmentPageAdapter(fragmentList, getChildFragmentManager());
    }

    private void initFragmentList() {
        fragmentList.clear();
        fragmentList.add(new TimeLineFragment());
//        fragmentList.add(new TimeLineFragment());
    }

    @Override
    protected void setView() {

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
        tabLayout.getTabAt(0).setText("时间线");
        //屏蔽“动态”的入口
//        tabLayout.getTabAt(1).setText("动态");

    }

    @Override
    protected void initListener() {
        fabAnimHelper.initFabClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fab:
                fabAnimHelper.startAnimation();
                break;
            default:
                break;
        }
    }

}
