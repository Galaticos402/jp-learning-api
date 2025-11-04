package com.self.jplearning.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final int DAY = 0;
    public static final int MINUTE = 1;
    public static Date add(Date originalDate, int values, int type){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalDate);
        switch (type){
            case DAY:
                calendar.add(Calendar.DATE, values);
                break;
            case MINUTE:
                calendar.add(Calendar.MINUTE, values);
                break;
            default:
                return originalDate;
        }
        return calendar.getTime();
    }
}
