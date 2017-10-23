package com.example.fungwah.campusgo.module.homepage.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.adapter.FragmentPageAdapter;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class HomepageFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected void initObject() {
        initFragmentList();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.homepage_fragment;
    }

    @Override
    protected void initView(View parent) {
        tabLayout = findView(R.id.homepage_tab_layout);
        viewPager = findView(R.id.homepage_fragment_content_vp);
        pagerAdapter = new FragmentPageAdapter(fragmentList,((BaseActivity)getActivity()).getSupportFragmentManager());
    }

    private void initFragmentList() {
        fragmentList.clear();
        fragmentList.add(new TimeLineFragment());
        fragmentList.add(new TimeLineFragment());
    }

    @Override
    protected void setView() {

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
//        tabLayout.addTab(tabLayout.newTab().setText("时间线"));
//        tabLayout.addTab(tabLayout.newTab().setText("动态"));
        tabLayout.getTabAt(0).setText("时间线");
        tabLayout.getTabAt(1).setText("动态");

    }

    @Override
    protected void initListener() {

    }

}
