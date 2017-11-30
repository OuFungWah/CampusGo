package com.example.fungwah.campusgo.module.timeline.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.fungwah.campusgo.R;
import com.example.fungwah.campusgo.common.bean.Events;
import com.example.fungwah.campusgo.common.database.DataTools;
import com.example.fungwah.campusgo.common.database.dao.CampusDao;
import com.example.fungwah.campusgo.common.database.helper.CampusHelper;
import com.example.fungwah.campusgo.module.timeline.OnRefreshListener;
import com.example.fungwah.campusgo.module.timeline.adapter.TimeLineListAdapter;
import com.example.fungwahtools.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by FungWah on 2017/10/19.
 */

public class TimeLineListFragment extends BaseFragment implements AdapterView.OnItemLongClickListener,OnRefreshListener {

    private static final int GET_DATA = 0;

    private RecyclerView recyclerView;

    private List<Map> list;
    private List<Events> eventsList = new ArrayList<>();
    private TimeLineListAdapter timeLineListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Calendar calendar = Calendar.getInstance();
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private int hour;
    private int minute;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case GET_DATA:
                    timeLineListAdapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    protected void initObject() {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.homepage_timeline_fragment;
    }

    @Override
    protected void initView(View parent) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        initCalendar();
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        recyclerView = findView(R.id.homepage_fragment_content_rv);
        list = DataTools.selectEventsByDate(year,month,day,DataTools.calendarDOWToNum(dayOfWeek));
        eventsList = DataTools.changeIntoEvent(list);
        Log.d(TAG, "initView: eventsList.size() = "+(eventsList==null?-1:eventsList.size()));
        timeLineListAdapter = new TimeLineListAdapter(eventsList);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    private void initCalendar() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        Log.d(TAG, "initCalendar: year="+year+" month="+month+" day="+day+" dayOfWeek="+dayOfWeek);
    }

    @Override
    protected void setView() {
        recyclerView.setAdapter(timeLineListAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initListener() {
        timeLineListAdapter.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        createDialog(position);
        return false;
    }
    private void refreshData(){

        initCalendar();
        list = DataTools.selectEventsByDate(year,month,day,DataTools.calendarDOWToNum(dayOfWeek));
        eventsList.clear();
        eventsList.addAll(DataTools.changeIntoEvent(list));
        Log.d(TAG, "onResume: eventsList.size() = "+(eventsList==null?-1:eventsList.size()));
        handler.sendEmptyMessage(GET_DATA);
        List<Map> list = CampusDao.getInstance().select(null,null);
        for(int i  = 0;i<list.size();i++){
            Log.d(TAG, "refreshData: 第"+i+"个    year="+list.get(i).get("year")+"    month="+list.get(i).get("month")+"  day="+list.get(i).get("day"));
        }
    }

    private void createDialog(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请问确定要删除"+eventsList.get(position).getName()+"吗?");
        builder.setTitle("删除提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                DataTools.deleteEventByNum(eventsList.get(position).getNum());
                refreshData();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onRefresh(Date date) {
        calendar.setTime(date);
        refreshData();
    }
}
