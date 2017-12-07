package com.example.fungwah.campusgo.module.timeline.activity;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.application.Config;
import com.example.fungwah.campusgo.common.bean.Event;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.ToastUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by FungWah on 2017/10/25.
 */

public class AddCourseActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "AddCourseActivity";

    private TextView actionbarTitle;
    private ImageView actionbarLeft;

    private Map<String, Integer> dayOfWeekMap = new HashMap();

    private EditText courseNameEt;
    private EditText courseTeacherEt;
    private Spinner placeSpin;
    private Spinner dayOfWeekSpin;
    private Spinner startWeekSpin;
    private Spinner endWeekSpin;
    private Spinner startTimeSpin;
    private Spinner endTimeSpin;
    private FloatingActionButton commitFAB;

    private final Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);
    private int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);
    private String date = month + "月" + day + "日";

    private String courseNameStr;
    private String courseTeacherStr;
    private String courseLocationStr;
    private int courseDayOfWeek;
    private String startWeek;
    private String endWeek;
    private String startTime;
    private String endTime;

    private ArrayAdapter<CharSequence> placeAdapter;
    private ArrayAdapter<CharSequence> dayOfWeekAdapter;
    private ArrayAdapter<CharSequence> startWeekAdapter;
    private ArrayAdapter<CharSequence> endWeekAdapter;
    private ArrayAdapter<CharSequence> startTimeAdapter;
    private ArrayAdapter<CharSequence> endTimeAdapter;

    private String[] classRoomStrArr;
    private String[] dayOfWeekStrArr;
    private String[] startWeekStrArr;
    private String[] endWeekStrArr;
    private String[] startTimeStrArr;
    private String[] endTimeStrArr;

    @Override
    protected int getLayoutId() {
        return R.layout.timeline_add_course_activity;
    }

    @Override
    protected void initView() {
        Bmob.initialize(this, Config.APP_KEY);
        placeAdapter = ArrayAdapter.createFromResource(this, R.array.classroom_arr, android.R.layout.simple_spinner_item);
        dayOfWeekAdapter = ArrayAdapter.createFromResource(this, R.array.day_arr, android.R.layout.simple_spinner_item);
        startWeekAdapter = ArrayAdapter.createFromResource(this, R.array.week_arr, android.R.layout.simple_spinner_item);
        endWeekAdapter = ArrayAdapter.createFromResource(this, R.array.week_arr, android.R.layout.simple_spinner_item);
        startTimeAdapter = ArrayAdapter.createFromResource(this, R.array.course_start_time_arr, android.R.layout.simple_spinner_item);
        endTimeAdapter = ArrayAdapter.createFromResource(this, R.array.course_end_time_arr, android.R.layout.simple_spinner_item);
        actionbarTitle = findView(R.id.actionbar_title_tv);
        actionbarLeft = findView(R.id.actionbar_left_img);
        courseNameEt = findView(R.id.course_name_et);
        courseTeacherEt = findView(R.id.course_teacher_name_et);
        placeSpin = findView(R.id.course_place_spin);
        dayOfWeekSpin = findView(R.id.course_day_of_week_spin);
        startWeekSpin = findView(R.id.course_start_week_spin);
        endWeekSpin = findView(R.id.course_end_week_spin);
        startTimeSpin = findView(R.id.course_start_time_spin);
        endTimeSpin = findView(R.id.course_end_time_spin);
        commitFAB = findView(R.id.course_commit_fab);

        classRoomStrArr = getResources().getStringArray(R.array.classroom_arr);
        dayOfWeekStrArr = getResources().getStringArray(R.array.day_arr);
        startWeekStrArr = getResources().getStringArray(R.array.week_arr);
        endWeekStrArr = getResources().getStringArray(R.array.week_arr);
        startTimeStrArr = getResources().getStringArray(R.array.course_start_time_arr);
        endTimeStrArr = getResources().getStringArray(R.array.course_end_time_arr);

        dayOfWeekMap.put("星期一", 1);
        dayOfWeekMap.put("星期二", 2);
        dayOfWeekMap.put("星期三", 3);
        dayOfWeekMap.put("星期四", 4);
        dayOfWeekMap.put("星期五", 5);
        dayOfWeekMap.put("星期六", 6);
        dayOfWeekMap.put("星期日", 7);

    }

    @Override
    protected void setView() {
        placeSpin.setAdapter(placeAdapter);
        dayOfWeekSpin.setAdapter(dayOfWeekAdapter);
        startWeekSpin.setAdapter(startWeekAdapter);
        endWeekSpin.setAdapter(endWeekAdapter);
        startTimeSpin.setAdapter(startTimeAdapter);
        endTimeSpin.setAdapter(endTimeAdapter);
        actionbarTitle.setText("添加课程");
        actionbarLeft.setImageResource(R.drawable.ic_arrow_back_white_36dp);
    }

    @Override
    protected void initListener() {
        commitFAB.setOnClickListener(this);
        actionbarLeft.setOnClickListener(this);
        placeSpin.setOnItemSelectedListener(this);
        dayOfWeekSpin.setOnItemSelectedListener(this);
        startWeekSpin.setOnItemSelectedListener(this);
        endWeekSpin.setOnItemSelectedListener(this);
        startTimeSpin.setOnItemSelectedListener(this);
        endTimeSpin.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.course_commit_fab:
                courseNameStr = courseNameEt.getText().toString();
                courseTeacherStr = courseTeacherEt.getText().toString();
                commit();
                break;
            case R.id.actionbar_left_img:
                onBackPressed();
                break;
        }
    }

    private void commit() {
        String tempHour;
        String tempMin;
        String tempArr[] = startTime.split(":");
        tempHour = tempArr[0];
        tempMin = tempArr[1];
        Log.d(TAG, "commit: tempHour " + tempHour);
        Log.d(TAG, "commit: tempMin " + tempMin);
        if (courseNameStr == null || courseNameStr.equals("")) {
            ToastUtil.showShort("课程名不可为空");
        } else if (courseTeacherStr == null || courseTeacherStr.equals("")) {
            ToastUtil.showShort("老师名不可为空");
        } else if (!checkWeek(startWeek, endWeek)) {
            ToastUtil.showShort("结束周不能在开始周之前");
        } else if (!checkTime(startTime, endTime)) {
            ToastUtil.showShort("下课时间不可在上课之前");
        } else {
            final Event event = new Event(Config.user.getNum(), System.currentTimeMillis() + "", courseNameStr, "课程", Integer.parseInt(tempHour), Integer.parseInt(tempMin), courseDayOfWeek, courseLocationStr, startWeek, endWeek, startTime, endTime);
            event.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Log.d(TAG, "done: 上传事件成功: " + s);
                        boolean flag = DataTools.insertEvent(event);
                        if (flag) {
                            ToastUtil.showShort("添加成功");
                            onBackPressed();
                        } else {
                            ToastUtil.showShort("添加本地失败");
                        }
                    } else {
                        ToastUtil.showShort("上传服务器失败");
                        Log.d(TAG, "done: 上传事件失败: " + e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(dayOfWeekSpin)) {
            courseDayOfWeek = dayOfWeekMap.get(dayOfWeekStrArr[position]);
        } else if (parent.equals(placeSpin)) {
            courseLocationStr = classRoomStrArr[position];
        } else if (parent.equals(startWeekSpin)) {
            startWeek = startWeekStrArr[position];
        } else if (parent.equals(endWeekSpin)) {
            endWeek = endWeekStrArr[position];
        } else if (parent.equals(startTimeSpin)) {
            startTime = startTimeStrArr[position];
        } else if (parent.equals(endTimeSpin)) {
            endTime = endTimeStrArr[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.equals(dayOfWeekSpin)) {
            courseDayOfWeek = dayOfWeekMap.get(dayOfWeekStrArr[0]);
        } else if (parent.equals(placeSpin)) {
            courseLocationStr = classRoomStrArr[0];
        } else if (parent.equals(startWeekSpin)) {
            startWeek = startWeekStrArr[0];
        } else if (parent.equals(endWeekSpin)) {
            endWeek = endWeekStrArr[0];
        } else if (parent.equals(startTimeSpin)) {
            startTime = startTimeStrArr[0];
        } else if (parent.equals(endTimeSpin)) {
            endTime = endTimeStrArr[0];
        }
    }

    /**
     * 检查开始周是否在结束周以前
     *
     * @param startWeek
     * @param endWeek
     * @return
     */
    private boolean checkWeek(String startWeek, String endWeek) {
        int startInt = Integer.parseInt(startWeek.substring(1, startWeek.length() - 1));
        int endInt = Integer.parseInt(endWeek.substring(1, endWeek.length() - 1));
        return !(startInt > endInt);
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    private boolean checkTime(String startTime, String endTime) {
        boolean flag = false;
        int startHour;
        int startMin;
        int endHour;
        int endMin;
        String[] startArr = startTime.split(":");
        String[] endArr = endTime.split(":");
        startHour = Integer.parseInt(startArr[0]);
        startMin = Integer.parseInt(startArr[1]);
        endHour = Integer.parseInt(endArr[0]);
        endMin = Integer.parseInt(endArr[1]);
        if (startHour > endHour) {
            flag = false;
        } else if (startHour == endHour) {
            if (startMin > endMin) {
                flag = false;
            } else {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

}
