package com.example.fungwah.campusgo.module.framework.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.module.discover.fragment.DiscoverFragment;
import com.example.fungwah.campusgo.module.framework.adapter.DrawerListAdapter;
import com.example.fungwah.campusgo.module.framework.bean.DrawerItemBean;
import com.example.fungwah.campusgo.module.homepage.fragment.HomepageFragment;
import com.example.fungwah.campusgo.module.setting.activity.SettingActivity;
import com.example.fungwah.campusgo.module.timeline.fragment.TimelineFragment;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtools.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class FrameWorkActivity extends BaseActivity implements View.OnClickListener, DrawerListAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private TextView title;
    private ImageView nav_img;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerRecyclerView;
    private ImageView actionbarDrawDownImg;
    private FrameLayout frameLayout;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerListAdapter drawerAdapter;

    //    private UnScrollViewPager viewPager;
    private List<BaseFragment> fragmentList = new ArrayList<>();
//    private FragmentPageAdapter fragmentPageAdapter;

    private long lastTime = 0L;

    private List<DrawerItemBean> list = new ArrayList<>();
    private final static int ICON_RES_ARR[] = {R.drawable.home, R.drawable.timeline, R.drawable.discover, R.drawable.group, 0, R.drawable.settings, R.drawable.info};
    private final static String NAME_ARR[] = {"首页", "时间线", "发现", "组", null, "设置", "关于"};


    @Override
    protected int getLayoutId() {
        return R.layout.framework_activity;
    }

    @Override
    protected void initView() {
        drawerLayout = findView(R.id.drawer_layout);
        toolbar = findView(R.id.toolbar);
        title = findView(R.id.actionbar_title_tv);
        actionbarDrawDownImg = findView(R.id.action_drawn_down_img);
        nav_img = findView(R.id.actionbar_left_img);
        frameLayout = findView(R.id.framework_fragment_fl);
        drawerRecyclerView = findView(R.id.drawer_rv);
//        viewPager = findView(R.id.framework_vp);
        layoutManager = new LinearLayoutManager(FrameWorkActivity.this, LinearLayoutManager.VERTICAL, false);
        initDrawerItemList();
        initFragmentList();
        addFragment(R.id.framework_fragment_fl, fragmentList.get(0));
//        fragmentPageAdapter = new FragmentPageAdapter(fragmentList, getSupportFragmentManager());
        drawerAdapter = new DrawerListAdapter(list);
    }

    // 初始化Fragment列表
    private void initFragmentList() {
        fragmentList.add(new HomepageFragment());
        fragmentList.add(new TimelineFragment());
//        fragmentList.add(new DiscoverFragment());
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
//        viewPager.setScrollable(false);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.setLayoutManager(layoutManager);

//        viewPager.setAdapter(fragmentPageAdapter);
//        viewPager.setOffscreenPageLimit(0);

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

    //
    protected void selectPage(int pageNum) {
        if (pageNum >= 0 && pageNum < fragmentList.size()) {
            replaceFragment(R.id.framework_fragment_fl, fragmentList.get(pageNum));
            title.setText(NAME_ARR[pageNum]);
            if (NAME_ARR[pageNum].equals("时间线")) {
                actionbarDrawDownImg.setVisibility(View.VISIBLE);
            } else {
                actionbarDrawDownImg.setVisibility(View.INVISIBLE);
            }
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

    @Override
    public void onBackPressed() {
        long nowTime = System.currentTimeMillis();
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
        } else {
            if (lastTime == 0 || (nowTime - lastTime) >= 2000) {
                ToastUtil.showShort("再次点击退出程序");
                lastTime = nowTime;
            } else {
                finish();
            }
        }
    }

    public View getContentView() {
        View view = findViewById(android.R.id.content);
        return view;
    }

}
