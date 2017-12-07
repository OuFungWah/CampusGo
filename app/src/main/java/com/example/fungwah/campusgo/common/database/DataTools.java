package com.example.fungwah.campusgo.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.fungwah.campusgo.application.Config;
import com.example.fungwah.campusgo.common.TimeComparator;
import com.example.fungwah.campusgo.common.bean.Event;
import com.example.fungwah.campusgo.common.bean.User;
import com.example.fungwah.campusgo.common.database.dao.CampusDao;
import com.example.fungwah.campusgo.common.database.helper.CampusHelper;
import com.example.fungwah.campusgo.module.framework.activity.FrameWorkActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by FungWah on 2017/11/19.
 */

public class DataTools {

    private static Map<Integer, String> dowMap1 = new HashMap<>();
    private static Map<Integer, String> dowMap2 = new HashMap<>();
    private static Map<Integer, Integer> dowMap3 = new HashMap<>();

    public static List<Map> selectEventsByDate(int year, int month, int day, int dayOfWeek) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List<Map> list = CampusDao.getInstance().select("( year = ? AND month = ? AND day = ? )OR dayOfWeek=?", new String[]{"" + year, "" + month, day + "", dayOfWeek + ""});
        return list;
    }

    public static List<Map> selectEventsByDate(String sno,int year, int month, int day, int dayOfWeek) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List<Map> list = CampusDao.getInstance().select("(sno = ? AND year = ? AND month = ? AND day = ? )OR dayOfWeek=?", new String[]{sno,"" + year, "" + month, day + "", dayOfWeek + ""});
        return list;
    }

    public static int getAllEventsCount(){
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List<Map> list = CampusDao.getInstance().select(null,null);
        if(list!=null){
            if(list.size()>=0){
                return list.size();
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    public static boolean insertEvent(Event event) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        ContentValues contentValues = new ContentValues();
        if (event.getType().equals("活动")) {
            contentValues.put("sno", Config.user.getNum());
            contentValues.put("num", event.getNum() + "");
            contentValues.put("name", event.getName());
            contentValues.put("type", event.getType());
            contentValues.put("location", event.getLocation());
            contentValues.put("year", event.getYear());
            contentValues.put("month", event.getMonth());
            contentValues.put("day", event.getDay());
            contentValues.put("hour", event.getHour());
            contentValues.put("minute", event.getMinute());
            contentValues.put("content", event.getContent());
            boolean flag = CampusDao.getInstance().insert(contentValues);
            return flag;
        } else {
            contentValues.put("sno", Config.user.getNum());
            contentValues.put("num", event.getNum() + "");
            contentValues.put("name", event.getName());
            contentValues.put("type", event.getType());
            contentValues.put("location", event.getLocation());
            contentValues.put("startWeek", event.getStartWeek());
            contentValues.put("dayOfWeek", event.getDayOfWeek());
            contentValues.put("endWeek", event.getEndWeek());
            contentValues.put("startTime", event.getStartTime());
            contentValues.put("endTime", event.getEndTime());
            contentValues.put("hour", event.getHour());
            contentValues.put("minute", event.getMinute());
            boolean flag = CampusDao.getInstance().insert(contentValues);
            return flag;
        }

    }

    public static boolean insertUser(User user) {
        boolean flag = false;
        CampusDao.useTable(CampusHelper.TABLE_USERS);
        ContentValues contentValues = new ContentValues();
        contentValues.put("num", user.getNum());
        contentValues.put("name", user.getName());
        contentValues.put("password", user.getPassword());
        contentValues.put("sex", user.getSex());
        contentValues.put("college", user.getCollege());
        contentValues.put("subject", user.getMajorClass().split(" ")[0]);
        contentValues.put("grade", user.getGrade());
        contentValues.put("class", user.getMajorClass().split(" ")[1]);
        flag = CampusDao.getInstance().insert(contentValues);
        return flag;
    }

    public static User selectUserByNum(String sno) {
        CampusDao.useTable(CampusHelper.TABLE_USERS);
        User user;
        List<Map> mapList = CampusDao.getInstance().select("num = ?", new String[]{sno});
        if (mapList != null && mapList.size() > 0) {
            Map map = mapList.get(0);
            user = new User((String) map.get("name"), (String) map.get("num"), (String) map.get("sex"), (String) map.get("grade"), (String) map.get("password"), null, (String) map.get("telephone"), (String) map.get("collage"), (String) map.get("subject") + (String) map.get("class") + "班");
        } else {
            user = null;
        }
        return user;
    }

    public static boolean deleteEventByNum(String num) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        boolean flag = CampusDao.getInstance().delete("num=?", new String[]{num});
        return flag;
    }

    public static List<Event> changeIntoEvent(List<Map> mapList) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Event event = new Event();
            if (mapList.get(i).get("type").equals("活动")) {
                event.setSno(Config.user.getNum());
                event.setNum((String) mapList.get(i).get("num"));
                event.setName((String) mapList.get(i).get("name"));
                event.setType((String) mapList.get(i).get("type"));
                event.setLocation((String) mapList.get(i).get("location"));
                event.setYear((int) mapList.get(i).get("year"));
                event.setMinute((int) mapList.get(i).get("month"));
                event.setDay((int) mapList.get(i).get("day"));
                event.setHour((int) mapList.get(i).get("hour"));
                event.setMinute((int) mapList.get(i).get("minute"));
                event.setContent((String) mapList.get(i).get("content"));
            } else if (mapList.get(i).get("type").equals("课程")) {
                event.setSno(Config.user.getNum());
                event.setNum((String) mapList.get(i).get("num"));
                event.setName((String) mapList.get(i).get("name"));
                event.setType((String) mapList.get(i).get("type"));
                event.setLocation((String) mapList.get(i).get("location"));
                event.setDayOfWeek((int) mapList.get(i).get("dayOfWeek"));
                event.setHour((int) mapList.get(i).get("hour"));
                event.setMinute((int) mapList.get(i).get("minute"));
                event.setStartWeek((String) mapList.get(i).get("startWeek"));
                event.setEndWeek((String) mapList.get(i).get("endWeek"));
                event.setStartTime((String) mapList.get(i).get("startTime"));
                event.setEndTime((String) mapList.get(i).get("endTime"));
            }

            eventList.add(event);
        }
        Collections.sort(eventList,new TimeComparator());
        return eventList;
    }

    /**
     * 若查询失败则返回-1
     *
     * @return
     */
    public static int getActivitiesNumToday(int year, int month, int day) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List list = CampusDao.getInstance().select("sno = ? AND type=? AND year=? AND month=? AND day=?", new String[]{Config.user.getNum(),"活动", year + "", month + "", day + ""});
        return list != null ? list.size() : -1;
    }

    /**
     * 若查询失败则返回-1
     *
     * @return
     */
    public static int getCourseNumToday(int dayOfWeek) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List list = CampusDao.getInstance().select("sno = ? AND type=? AND dayOfWeek=?", new String[]{Config.user.getNum(),"课程", dayOfWeek + ""});
        return list != null ? list.size() : -1;
    }

    public boolean deleteByNum(String num) {
        return CampusDao.getInstance().delete("num=?", new String[]{num});
    }

    /**
     * 把Java的Calendar星期几转成中文的星期几
     *
     * @param dayOfDay
     * @return
     */
    public static String calendarDOWToCN(int dayOfDay) {
        if (dowMap1.size() == 0) {
            dowMap1.put(Calendar.MONDAY, "星期一");
            dowMap1.put(Calendar.TUESDAY, "星期二");
            dowMap1.put(Calendar.WEDNESDAY, "星期三");
            dowMap1.put(Calendar.THURSDAY, "星期四");
            dowMap1.put(Calendar.FRIDAY, "星期五");
            dowMap1.put(Calendar.SATURDAY, "星期六");
            dowMap1.put(Calendar.SUNDAY, "星期日");
        }
        return dowMap1.get(dayOfDay);
    }

    /**
     * 把数字星期几转成中文的星期几
     *
     * @param dayOfDay
     * @return
     */
    public static String numDOWToCN(int dayOfDay) {
        if (dowMap2.size() == 0) {
            dowMap2.put(1, "星期一");
            dowMap2.put(2, "星期二");
            dowMap2.put(3, "星期三");
            dowMap2.put(4, "星期四");
            dowMap2.put(5, "星期五");
            dowMap2.put(6, "星期六");
            dowMap2.put(7, "星期日");
        }
        return dowMap2.get(dayOfDay);
    }

    /**
     * 把Javade Calendar星期几转成数字的星期几
     *
     * @param dayOfDay
     * @return
     */
    public static int calendarDOWToNum(int dayOfDay) {
        if (dowMap3.size() == 0) {
            dowMap3.put(Calendar.MONDAY, 1);
            dowMap3.put(Calendar.TUESDAY, 2);
            dowMap3.put(Calendar.WEDNESDAY, 3);
            dowMap3.put(Calendar.THURSDAY, 4);
            dowMap3.put(Calendar.FRIDAY, 5);
            dowMap3.put(Calendar.SATURDAY, 6);
            dowMap3.put(Calendar.SUNDAY, 7);
        }
        return dowMap3.get(dayOfDay);
    }

    public static void getEventFromNet(){
//        Bmob.initialize(context,Config.APP_KEY);
        BmobQuery<Event> eventBmobQuery = new BmobQuery<>();
        eventBmobQuery.addWhereEqualTo("sno",Config.user.getNum());
        eventBmobQuery.findObjects(new FindListener<Event>() {
            @Override
            public void done(List<Event> list, BmobException e) {
                if(e==null){
                    for(int i =0;i<list.size();i++){
                        DataTools.insertEvent(list.get(i));
                    }
                }
            }
        });
    }

}
