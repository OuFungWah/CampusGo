package com.example.fungwah.campusgo.command.database;

import android.content.ContentValues;

import com.example.fungwah.campusgo.command.bean.Events;
import com.example.fungwah.campusgo.command.database.dao.CampusDao;
import com.example.fungwah.campusgo.command.database.helper.CampusHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FungWah on 2017/11/19.
 */

public class DataTools {

    private static Map<Integer,String> dowMap1 = new HashMap<>();
    private static Map<Integer,String> dowMap2 = new HashMap<>();
    private static Map<Integer,Integer> dowMap3 = new HashMap<>();

    public static List<Map> selectEventsByDate(int year, int month, int day,int dayOfWeek) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List<Map> list = CampusDao.getInstance().select("( year = ? AND month = ? AND day = ? )OR dayOfWeek=?", new String[]{"" + year, "" + month, day + "",dayOfWeek+""});
        return list;
    }

    public static boolean insertEvent(Events event) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        ContentValues contentValues = new ContentValues();
        if (event.getType().equals("活动")){
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
        }else{
            contentValues.put("num", event.getNum() + "");
            contentValues.put("name", event.getName());
            contentValues.put("type", event.getType());
            contentValues.put("location", event.getLocation());
            contentValues.put("startWeek",event.getStartWeek());
            contentValues.put("dayOfWeek",event.getDayOfWeek());
            contentValues.put("endWeek",event.getEndWeek());
            contentValues.put("startTime",event.getStartTime());
            contentValues.put("endTime",event.getEndTime());
            contentValues.put("hour", event.getHour());
            contentValues.put("minute", event.getMinute());
            boolean flag = CampusDao.getInstance().insert(contentValues);
            return flag;
        }

    }

    public static boolean deleteEventByNum(String num) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        boolean flag = CampusDao.getInstance().delete("num=?", new String[]{num});
        return flag;
    }

    public static List<Events> changeIntoEvent(List<Map> mapList) {
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List<Events> eventsList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Events events = new Events();
            if(mapList.get(i).get("type").equals("活动")){
                events.setNum((String) mapList.get(i).get("num"));
                events.setName((String) mapList.get(i).get("name"));
                events.setType((String) mapList.get(i).get("type"));
                events.setLocation((String) mapList.get(i).get("location"));
                events.setYear((int) mapList.get(i).get("year"));
                events.setMinute((int) mapList.get(i).get("month"));
                events.setDay((int) mapList.get(i).get("day"));
                events.setHour((int) mapList.get(i).get("hour"));
                events.setMinute((int) mapList.get(i).get("minute"));
                events.setContent((String) mapList.get(i).get("content"));
            }else if(mapList.get(i).get("type").equals("课程")){
                events.setNum((String) mapList.get(i).get("num"));
                events.setName((String) mapList.get(i).get("name"));
                events.setType((String) mapList.get(i).get("type"));
                events.setLocation((String) mapList.get(i).get("location"));
                events.setDayOfWeek((int)mapList.get(i).get("dayOfWeek"));
                events.setHour((int) mapList.get(i).get("hour"));
                events.setMinute((int) mapList.get(i).get("minute"));
                events.setStartWeek((String) mapList.get(i).get("startWeek"));
                events.setEndWeek((String) mapList.get(i).get("endWeek"));
                events.setStartTime((String) mapList.get(i).get("startTime"));
                events.setEndTime((String) mapList.get(i).get("endTime"));
            }

            eventsList.add(events);
        }
        return eventsList;
    }

    /**
     * 若查询失败则返回-1
     * @return
     */
    public static int getActivitiesNumToday(int year,int month,int day){
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List list = CampusDao.getInstance().select("type=? AND year=? AND month=? AND day=?",new String[]{"活动",year+"",month+"",day+""});
        return list!=null?list.size():-1;
    }

    /**
     * 若查询失败则返回-1
     * @return
     */
    public static int getCourseNumToday(int dayOfWeek){
        CampusDao.useTable(CampusHelper.TABLE_EVENTS);
        List list = CampusDao.getInstance().select("type=? AND dayOfWeek=?",new String[]{"课程",dayOfWeek+""});
        return list!=null?list.size():-1;
    }

    public boolean deleteByNum(String num){
        return CampusDao.getInstance().delete("num=?",new String[]{num});
    }

    /**
     * 把Java的Calendar星期几转成中文的星期几
     * @param dayOfDay
     * @return
     */
    public static String calendarDOWToCN(int dayOfDay){
        if(dowMap1.size()==0){
            dowMap1.put(Calendar.MONDAY,"星期一");
            dowMap1.put(Calendar.TUESDAY,"星期二");
            dowMap1.put(Calendar.WEDNESDAY,"星期三");
            dowMap1.put(Calendar.THURSDAY,"星期四");
            dowMap1.put(Calendar.FRIDAY,"星期五");
            dowMap1.put(Calendar.SATURDAY,"星期六");
            dowMap1.put(Calendar.SUNDAY,"星期日");
        }
        return dowMap1.get(dayOfDay);
    }

    /**
     * 把数字星期几转成中文的星期几
     * @param dayOfDay
     * @return
     */
    public static String numDOWToCN(int dayOfDay){
        if(dowMap2.size()==0){
            dowMap2.put(1,"星期一");
            dowMap2.put(2,"星期二");
            dowMap2.put(3,"星期三");
            dowMap2.put(4,"星期四");
            dowMap2.put(5,"星期五");
            dowMap2.put(6,"星期六");
            dowMap2.put(7,"星期日");
        }
        return dowMap2.get(dayOfDay);
    }

    /**
     * 把Javade Calendar星期几转成数字的星期几
     * @param dayOfDay
     * @return
     */
    public static int calendarDOWToNum(int dayOfDay){
        if(dowMap3.size()==0){
            dowMap3.put(Calendar.MONDAY,1);
            dowMap3.put(Calendar.TUESDAY,2);
            dowMap3.put(Calendar.WEDNESDAY,3);
            dowMap3.put(Calendar.THURSDAY,4);
            dowMap3.put(Calendar.FRIDAY,5);
            dowMap3.put(Calendar.SATURDAY,6);
            dowMap3.put(Calendar.SUNDAY,7);
        }
        return dowMap3.get(dayOfDay);
    }

}
