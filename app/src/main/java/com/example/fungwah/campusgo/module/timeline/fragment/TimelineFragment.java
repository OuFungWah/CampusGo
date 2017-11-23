package com.example.fungwah.campusgo.module.timeline.fragment;

import android.app.DatePickerDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.DateHelper;
import com.example.fungwah.campusgo.command.FabAnimHelper;
import com.example.fungwah.campusgo.command.database.DataTools;
import com.example.fungwah.campusgo.command.picker.DatePickerFragment;
import com.example.fungwah.campusgo.module.framework.activity.FrameWorkActivity;
import com.example.fungwah.campusgo.module.timeline.OnRefreshListener;
import com.example.fungwah.campusgo.module.timeline.activity.AddActivitiesActivity;
import com.example.fungwah.campusgo.module.timeline.activity.AddCourseActivity;
import com.example.fungwah.campusgo.module.timeline.adapter.TimelineDateAdapter;
import com.example.fungwah.campusgo.module.timeline.bean.TagDate;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by FungWah on 2017/10/21.
 */

public class TimelineFragment extends BaseFragment implements View.OnClickListener, DatePickerFragment.MySetListener, TimelineDateAdapter.OnItemClickListener {

    private static final String TAG = "TimelineFragment";

    private OnRefreshListener onRefreshListener = null;

    private static final int MONtH1[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int MONtH2[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private String dateTitleArr[] = new String[]{"SUN", "MON", "TUE", "WEN", "THU", "FIR", "STA"};

    private static final int REFRESH = 0;

    private FrameLayout frameLayout;
    private RecyclerView recyclerView;
    private FabAnimHelper fabAnimHelper;

    private TimelineDateAdapter timelineDateAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<TagDate> dateList = new ArrayList<>();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TimeLineListFragment fragment;

    private final Calendar calendar = Calendar.getInstance();
    private int year;
    private int month;
    private int dayOfMonth;
    private int dayOfWeek;
    private int dayOfYear;
    private int hour;
    private int minute;
    private int weekofYear;
    private int selectedDay;

    private int tagYear;
    private int tagMonth;
    private int tagDay;

    private TextView weekTv;
    private TextView monthTv;
    private TextView yearTv;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH:
                    setDate();
                    timelineDateAdapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void initObject() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        initCalendar();
        selectedDay = dayOfMonth;
        initDateList();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.timeline_fragment;
    }

    @Override
    protected void initView(View parent) {

        weekTv = findView(R.id.timeline_week_tv);
        monthTv = findView(R.id.timeline_month_tv);
        yearTv = findView(R.id.timeline_year_tv);
        fragmentManager = getActivity().getSupportFragmentManager();
        fabAnimHelper = new FabAnimHelper(parent, 300);
        fragment = new TimeLineListFragment();

        timelineDateAdapter = new TimelineDateAdapter(dateList);
        layoutManager = new GridLayoutManager(getContext(), 7, LinearLayoutManager.VERTICAL, false);

        recyclerView = findView(R.id.timeline_rv);
        frameLayout = findView(R.id.timeline_content_fragment_fl);
    }

    private void initCalendar() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        weekofYear = calendar.get(Calendar.WEEK_OF_YEAR);
        dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void initDateList() {
        dateList.clear();
        for (int i = 0; i < dateTitleArr.length; i++) {
            dateList.add(new TagDate(dateTitleArr[i]));
        }
        initStartTagDateOfWeek();
        initDateOfThisWeek();
    }

    @Override
    protected void setView() {
        setDate();
        setOnRefreshListener(fragment);
        changeFragment(fragment);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(timelineDateAdapter);
    }

    private void setDate() {
        weekTv.setText("第" + weekofYear + "周");
        monthTv.setText((month + 1) + "月");
        yearTv.setText(year + "");
    }

    @Override
    protected void initListener() {
        timelineDateAdapter.setOnItemClickListener(this);
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.selectedDay = dayOfMonth;
        Date date = new Date(year - 1900, month, dayOfMonth, 0, 0);
        calendar.setTime(date);
        initCalendar();
        initDateList();
        onRefreshListener.onRefresh(date);
        handler.sendEmptyMessage(REFRESH);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if(position>=7){
            TagDate selectedDate = dateList.get(position);
            selectedDay = selectedDate.getDay();
            Date date = new Date(selectedDate.getYear() - 1900, selectedDate.getMonth(), selectedDate.getDay(), 0, 0);
            ((FrameWorkActivity)getActivity()).setDate(date);
            calendar.setTime(date);
            initCalendar();
            initDateList();
            onRefreshListener.onRefresh(date);
            handler.sendEmptyMessage(REFRESH);
        }
    }

    /**
     * 计算当前天所在周的第一天
     */
    private void initStartTagDateOfWeek() {
        if (dayOfYear - dayOfWeek < 0) {
            tagYear = year - 1;
            tagMonth = Calendar.DECEMBER;
            tagDay = 31 - (dayOfWeek - dayOfYear)+1;
        } else if (dayOfYear - dayOfWeek == 0) {
            tagYear = year;
            tagMonth = Calendar.JANUARY;
            tagDay = 1;
        } else {
            tagYear = year;
            if (dayOfMonth - dayOfWeek < 0) {
                tagMonth = month - 1;
                tagDay = (tagYear % 4 == 0 ? MONtH2[tagMonth] : MONtH1[tagMonth]) - (dayOfWeek - dayOfMonth) + 1;
            } else {
                tagMonth = month;
                tagDay = dayOfMonth - dayOfWeek + 1;
            }
        }
        Log.d(TAG, "initStartTagDateOfWeek: tagYear=" + tagYear + " tagMonth=" + tagMonth + " tagDay=" + tagDay);
    }

    /**
     * 初始化当前周的日期列表
     */
    private void initDateOfThisWeek() {
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                dateList.add(new TagDate(tagYear, tagMonth, tagDay, i, tagDay == selectedDay ? true : false));
            } else {
                if (tagMonth != Calendar.DECEMBER) {
                    if (tagDay + 6 <= MONtH1[tagMonth]) {
                        dateList.add(new TagDate(tagYear, tagMonth, (tagDay + i), i, (tagDay + i) == selectedDay ? true : false));
                    } else {
                        if (tagYear % 4 == 0) {
                            if (tagDay + i <= MONtH2[tagMonth]) {
                                dateList.add(new TagDate(tagYear, tagMonth, (tagDay + i), i, (tagDay + i) == selectedDay ? true : false));
                            } else {
                                dateList.add(new TagDate(tagYear, tagMonth + 1, i - (MONtH2[tagMonth] - tagDay), i, (i - (MONtH2[tagMonth] - tagDay)) == selectedDay ? true : false));
                            }
                        } else {
                            if (tagDay + i <= MONtH1[tagMonth]) {
                                dateList.add(new TagDate(tagYear, tagMonth, (tagDay + i), i, (tagDay + i) == selectedDay ? true : false));
                            } else {
                                dateList.add(new TagDate(tagYear, tagMonth + 1, i - (MONtH1[tagMonth] - tagDay), i, (i - (MONtH1[tagMonth] - tagDay)) == selectedDay ? true : false));
                            }
                        }
                    }
                } else {
                    if (tagDay + 6 <= MONtH1[Calendar.DECEMBER]) {
                        dateList.add(new TagDate(tagYear, tagMonth, (tagDay + i), i, (tagDay + i) == selectedDay ? true : false));
                    } else {
                        if (tagDay + i <= MONtH1[tagMonth]) {
                            dateList.add(new TagDate(tagYear, tagMonth, (tagDay + i), i, (tagDay + i) == selectedDay ? true : false));
                        } else {
                            dateList.add(new TagDate(tagYear + 1, Calendar.JANUARY, i - (MONtH1[tagMonth] - tagDay), i, (i - (MONtH1[tagMonth] - tagDay)) == selectedDay ? true : false));
                        }
                    }
                }
            }
        }
    }
}
