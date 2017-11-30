package com.example.fungwah.campusgo.module.homepage.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.common.FabAnimHelper;
import com.example.fungwah.campusgo.common.adapter.FragmentPageAdapter;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwah.campusgo.module.timeline.activity.AddActivitiesActivity;
import com.example.fungwah.campusgo.module.timeline.activity.AddCourseActivity;
import com.example.fungwah.campusgo.module.timeline.fragment.TimeLineListFragment;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FungWah on 2017/10/19.
 */

public class HomepageFragment extends BaseFragment implements View.OnClickListener {

    private static final long ANIM_TIME = 300;

    private static final int REFESH_DATE = 0;

    private Map dayOfWeekMap = new HashMap();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private final Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);
    private int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);
    private String date =  (month+1) + "月" + day + "日";

    private FragmentPagerAdapter pagerAdapter;
    private List<BaseFragment> fragmentList = new ArrayList<>();

    private FabAnimHelper fabAnimHelper;

    private TextView courseTv;
    private TextView activityTv;

    private TextView dayOfWeekTv;
    private TextView courseNumTv;
    private TextView activitiesNumTv;
    private TextView dateTv;

    private int activitiesNum = 0;
    private int courseNum = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case REFESH_DATE:
                    dateTv.setText(date);
                    dayOfWeekTv.setText((String)dayOfWeekMap.get(dayOfWeek));
                    activitiesNumTv.setText(activitiesNum +"");
                    courseNumTv.setText(courseNum+"");
                    break;
            }
            return false;
        }
    });

    @Override
    protected void initObject() {
        dayOfWeekMap.put(Calendar.MONDAY,"星期一");
        dayOfWeekMap.put(Calendar.TUESDAY,"星期二");
        dayOfWeekMap.put(Calendar.WEDNESDAY,"星期三");
        dayOfWeekMap.put(Calendar.THURSDAY,"星期四");
        dayOfWeekMap.put(Calendar.FRIDAY,"星期五");
        dayOfWeekMap.put(Calendar.SATURDAY,"星期六");
        dayOfWeekMap.put(Calendar.SUNDAY,"星期天");
    }

    @Override
    public void onResume() {
        super.onResume();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        activitiesNum = DataTools.getActivitiesNumToday(year,month,day);
        courseNum = DataTools.getCourseNumToday(DataTools.calendarDOWToNum(dayOfWeek));
        handler.sendEmptyMessage(REFESH_DATE);
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
        courseNumTv = findView(R.id.course_num_tv);
        dayOfWeekTv = findView(R.id.homepage_day_of_week_tv);
        activitiesNumTv = findView(R.id.activities_num_tv);
        dateTv = findView(R.id.homepage_date_tv);
        // TODO: 2017/10/23  此处注意，Fragment 嵌套使用时，子 fragment 添加的是 ChildFragmentManager 而非 SupportFragmentManager ,回头了解一下
        pagerAdapter = new FragmentPageAdapter(fragmentList, getChildFragmentManager());
    }

    private void initFragmentList() {
        fragmentList.clear();
        fragmentList.add(new TimeLineListFragment());
//        fragmentList.add(new TimeLineListFragment());
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
            case R.id.add_course_fab:
                startActivity(AddCourseActivity.class);
                fabAnimHelper.startAnimation();
                break;
            case R.id.add_activity_fab:
                startActivity(AddActivitiesActivity.class);
                fabAnimHelper.startAnimation();
                break;
            default:
                break;
        }
    }

}
