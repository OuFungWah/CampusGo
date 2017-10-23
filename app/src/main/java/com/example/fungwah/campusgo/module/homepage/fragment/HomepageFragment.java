package com.example.fungwah.campusgo.module.homepage.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.adapter.FragmentPageAdapter;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class HomepageFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton addFAB;

    private FragmentPagerAdapter pagerAdapter;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    private Dialog dialog;
    private View dialogView;

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

        addFAB = findView(R.id.homepage_add_fab);
        tabLayout = findView(R.id.homepage_tab_layout);
        viewPager = findView(R.id.homepage_fragment_content_vp);
        // TODO: 2017/10/23  此处注意，Fragment 嵌套使用时，子 fragment 添加的是 ChildFragmentManager 而非 SupportFragmentManager ,回头了解一下
        pagerAdapter = new FragmentPageAdapter(fragmentList, getChildFragmentManager());

        dialog = new Dialog(getContext(), R.style.MyDialog);
        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.include_add_fab_layout, null);
    }

    private void initFragmentList() {
        fragmentList.clear();
        fragmentList.add(new TimeLineFragment());
        fragmentList.add(new TimeLineFragment());
    }

    @Override
    protected void setView() {
        dialogView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.setContentView(dialogView);

        //全屏显示Dialog
        WindowManager windowManager = dialog.getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = display.getWidth();

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
        tabLayout.getTabAt(0).setText("时间线");
        tabLayout.getTabAt(1).setText("动态");

    }

    @Override
    protected void initListener() {
        addFAB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage_add_fab:
                dialog.show();
                break;
            default:
                break;
        }
    }
}
