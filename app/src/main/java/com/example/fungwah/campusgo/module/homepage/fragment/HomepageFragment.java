package com.example.fungwah.campusgo.module.homepage.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.fungwah.campusgo.R;
import com.example.fungwahtools.fragment.BaseFragment;

/**
 * Created by FungWah on 2017/10/19.
 */

public class HomepageFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int setLayoutId() {
        return R.layout.homepage_fragment;
    }

    @Override
    protected void initView(View parent) {
        tabLayout = findView(R.id.homepage_tab_layout);
        viewPager = findView(R.id.homepage_fragment_content_vp);
    }

    @Override
    protected void setView() {
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
        tabLayout.addTab(tabLayout.newTab().setText("时间线"));
        tabLayout.addTab(tabLayout.newTab().setText("动态"));
    }

    @Override
    protected void initListener() {

    }

}
