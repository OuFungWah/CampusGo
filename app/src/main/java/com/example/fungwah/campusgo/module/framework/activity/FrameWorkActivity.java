package com.example.fungwah.campusgo.module.framework.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.discover.fragment.DiscoverFragment;
import com.example.fungwah.campusgo.module.framework.adapter.DrawerListAdapter;
import com.example.fungwah.campusgo.command.adapter.FragmentPageAdapter;
import com.example.fungwah.campusgo.module.framework.bean.DrawerItemBean;
import com.example.fungwah.campusgo.module.homepage.fragment.HomepageFragment;
import com.example.fungwah.campusgo.module.setting.activity.SettingActivity;
import com.example.fungwah.campusgo.module.timeline.fragment.TimelineFragment;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtools.util.ToastUtil;
import com.example.fungwahtools.widget.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class FrameWorkActivity extends BaseActivity implements View.OnClickListener, DrawerListAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private TextView title;
    private ImageView nav_img;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerListAdapter drawerAdapter;

    private UnScrollViewPager viewPager;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private FragmentPageAdapter fragmentPageAdapter;

    private List<DrawerItemBean> list = new ArrayList<>();
    private final static int ICON_RES_ARR[] = {R.drawable.home, R.drawable.timeline, R.drawable.discover, R.drawable.group, 0, R.drawable.settings, R.drawable.info};
    private final static String NAME_ARR[] = {"首页", "时间线", "发现", "组", null, "设置", "关于"};
    private final static String TITLE_ARR[] = {"首页", "时间线", "发现", "组", "设置", "关于"};


    @Override
    protected int getLayoutId() {
        return R.layout.framework_activity;
    }

    @Override
    protected void initView() {
        drawerLayout = findView(R.id.drawer_layout);
        toolbar = findView(R.id.toolbar);
        title = findView(R.id.actionbar_title_tv);
        nav_img = findView(R.id.actionbar_left_img);
        drawerRecyclerView = findView(R.id.drawer_rv);
        viewPager = findView(R.id.framework_vp);
        layoutManager = new LinearLayoutManager(FrameWorkActivity.this, LinearLayoutManager.VERTICAL, false);
        initDrawerItemList();
        initFragmentList();

        fragmentPageAdapter = new FragmentPageAdapter(fragmentList, getSupportFragmentManager());
        drawerAdapter = new DrawerListAdapter(list);
    }

    // 初始化Fragment列表
    private void initFragmentList() {
        fragmentList.add(new HomepageFragment());
        fragmentList.add(new TimelineFragment());
        fragmentList.add(new DiscoverFragment());
    }

    //初始化侧拉栏列表
    private void initDrawerItemList() {
        int i = 0;
        while (i < NAME_ARR.length) {
            if (NAME_ARR[i] != null) {
                list.add(new DrawerItemBean(ICON_RES_ARR[i], NAME_ARR[i]));
            } else {
                list.add(null);
            }
            i++;
        }
    }

    @Override
    protected void setView() {
        viewPager.setScrollable(false);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setLayoutManager(layoutManager);

        viewPager.setAdapter(fragmentPageAdapter);
        viewPager.setOffscreenPageLimit(1);

        drawerAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initListener() {
        nav_img.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_left_img:
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
                break;
            default:
                break;
        }
    }

    protected void selectPage(int pageNum) {
        if (pageNum >= 0 && pageNum < fragmentList.size()) {
            viewPager.setCurrentItem(pageNum);
            title.setText(TITLE_ARR[pageNum]);
        } else {
            ToastUtil.showShort("当前模块尚未完成");
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (NAME_ARR[position] != null && NAME_ARR[position].equals("设置")) {
            startActivity(SettingActivity.class);
        } else {
            selectPage(position);
            drawerLayout.closeDrawers();
        }


    }
}
