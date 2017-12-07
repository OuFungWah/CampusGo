package com.example.fungwah.campusgo.common;

import com.example.fungwah.campusgo.common.bean.Event;

import java.util.Comparator;

/**
 * 事件按时间排序用的 Comparator
 * Created by FungWah on 2017/12/7.
 */

public class TimeComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        int e1hour;
        int e2hour;
        int e1minute;
        int e2minute;
        if(o1!=null&&o2!=null){
            e1hour = o1.getHour();
            e1minute = o1.getMinute();
            e2hour = o2.getHour();
            e2minute = o2.getMinute();
            if(e1hour>e2hour){
                return 1;
            }else if(e1hour == e2hour){
                if(e1minute>e2minute){
                    return 1;
                }else if (e1minute==e2minute){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }
        return 0;
    }
}
