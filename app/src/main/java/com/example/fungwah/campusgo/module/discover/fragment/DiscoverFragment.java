package com.example.fungwah.campusgo.module.discover.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.adapter.FragmentPageAdapter;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/21.
 */

public class DiscoverFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void initView(View parent) {
        tabLayout = findView(R.id.discover_tab_layout);
        viewPager = findView(R.id.discover_vp);
        initFragmentList();
        fragmentPagerAdapter = new FragmentPageAdapter(fragmentList, getActivity().getSupportFragmentManager());
    }

    private void initFragmentList() {
        fragmentList.add(new HotFragment());
        fragmentList.add(new NewFragment());
        fragmentList.add(new FocusFragment());
        fragmentList.add(new ClubFragment());
    }

    @Override
    protected void setView() {
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("热门");
        tabLayout.getTabAt(1).setText("最新");
        tabLayout.getTabAt(2).setText("关注");
        tabLayout.getTabAt(3).setText("社团");
    }

    @Override
    protected void initListener() {

    }

}
