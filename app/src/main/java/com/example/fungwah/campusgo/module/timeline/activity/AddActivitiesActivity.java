package com.example.fungwah.campusgo.module.timeline.activity;

import android.content.ContentValues;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.command.bean.Events;
import com.example.fungwah.campusgo.command.database.DataTools;
import com.example.fungwah.campusgo.command.database.dao.CampusDao;
import com.example.fungwah.campusgo.command.database.helper.CampusHelper;
import com.example.fungwah.campusgo.command.picker.DatePickerFragment;
import com.example.fungwah.campusgo.command.picker.TimePickerFragment;
import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.util.ToastUtil;

import java.util.Calendar;

/**
 * Created by FungWah on 2017/11/13.
 */

public class AddActivitiesActivity extends BaseActivity implements View.OnClickListener, DatePickerFragment.MySetListener, TimePickerFragment.MySetListener {


    private static final int SET_DATE = 0;
    private static final int SET_TIME = 1;

    private TextView actionbarTitleTv;
    private ImageView actionBarLeft;

    private FloatingActionButton commitFAB;

    private TimePickerFragment timePickerFragment;
    private DatePickerFragment datePickerFragment;

    private EditText activityNameEt;
    private EditText activityPlaceEt;
    private Button datePickerBtn;
    private Button timePickerBtn;
    private EditText remarkEt;

    private final Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);
    private String date = year + "." + month + "." + day;
    private String time;

    private String activityNameStr;
    private String activityPlaceStr;
    private String activityRemarkStr;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SET_DATE:
                    date = year + "." + month + "." + day + "";
                    datePickerBtn.setText(date);
                    break;
                case SET_TIME:
                    formatTime(hour,minute);
                    timePickerBtn.setText(time);
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.timeline_add_activities_activity;
    }

    @Override
    protected void initView() {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        actionbarTitleTv = findView(R.id.actionbar_title_tv);
        actionBarLeft = findView(R.id.actionbar_left_img);
        activityNameEt = findView(R.id.add_activity_name_et);
        activityPlaceEt = findView(R.id.add_activity_place_et);
        datePickerBtn = findView(R.id.add_activity_date_pick_btn);
        timePickerBtn = findView(R.id.add_activity_time_pick_btn);
        remarkEt = findView(R.id.add_activity_remark_et);
        commitFAB = findView(R.id.add_commit_fab);
        timePickerFragment = new TimePickerFragment();
        datePickerFragment = new DatePickerFragment();
    }

    @Override
    protected void setView() {
        actionbarTitleTv.setText("添加活动");
        datePickerBtn.setText(date);
        formatTime(hour,minute);
        timePickerBtn.setText(time);
        actionBarLeft.setImageResource(R.drawable.ic_arrow_back_white_36dp);
    }

    @Override
    protected void initListener() {
        commitFAB.setOnClickListener(this);
        actionBarLeft.setOnClickListener(this);
        datePickerBtn.setOnClickListener(this);
        timePickerBtn.setOnClickListener(this);
        datePickerFragment.setMySetListener(this);
        timePickerFragment.setMySetListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_commit_fab:
                activityNameStr = activityNameEt.getText().toString();
                activityPlaceStr = activityPlaceEt.getText().toString();
                activityRemarkStr = remarkEt.getText().toString();
                commit();
                break;
            case R.id.add_activity_date_pick_btn:
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");

                break;
            case R.id.add_activity_time_pick_btn:
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");

                break;
            case R.id.actionbar_left_img:
                onBackPressed();
                break;
        }
    }

    private void commit() {
        if(activityNameStr==null||activityNameStr.equals("")){
            ToastUtil.showShort("请填写活动名称");
        }else if (activityPlaceStr==null||activityPlaceStr.equals("")){
            ToastUtil.showShort("请填写活动地址");
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put("num",System.currentTimeMillis()+"");
            contentValues.put("name",activityNameStr);
            contentValues.put("type","活动");
            contentValues.put("location",activityPlaceStr);
            contentValues.put("year",year);
            contentValues.put("month",month);
            contentValues.put("day",day);
            contentValues.put("hour",hour);
            contentValues.put("minute",minute);
            contentValues.put("content",activityRemarkStr);
            Events event = new Events(System.currentTimeMillis()+"",activityNameStr,"活动",year,month,day,hour,minute,activityPlaceStr,activityRemarkStr);
            boolean flag = DataTools.insertEvent(event);
            if (flag){
                ToastUtil.showShort("添加成功");
                onBackPressed();
            }else{
                ToastUtil.showShort("添加失败");
            }
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        handler.sendEmptyMessage(SET_DATE);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
        String tempHour = hourOfDay + "";
        String tempMin = minute + "";
        handler.sendEmptyMessage(SET_TIME);
    }

    private void formatTime(int hour,int minute){
        if (hour < 10) {
            time = "0" + hour;
        } else {
            time = "" + hour;
        }
        time+=":";
        if (minute < 10) {
            time += "0" + minute;
        } else {
            time += "" + minute;
        }
    }

}
