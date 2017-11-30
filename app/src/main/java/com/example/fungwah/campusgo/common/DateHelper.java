package com.example.fungwah.campusgo.common;

import java.util.Calendar;

/**
 * Created by FungWah on 2017/11/21.
 */

public class DateHelper {

    private static final int[] MONTHS = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    public static void resetCalendar(){
        Calendar.getInstance().setTimeInMillis(System.currentTimeMillis());
    }

}
