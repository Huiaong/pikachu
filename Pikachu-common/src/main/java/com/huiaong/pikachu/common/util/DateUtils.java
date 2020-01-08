package com.huiaong.pikachu.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Date withTheTimeAfter(Date time, Long ms) {
        return new Date(time.getTime() + ms);
    }

    public static Date threeMinutesLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 3);
        return calendar.getTime();
    }
}
