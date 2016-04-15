package com.example.songyang.healthmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by JunyiZhou on 2016/4/15.
 */
public class TimeUtil {
    public static final long MINUTE_IN_SECONDS	 = 60;
    public static final long HOUR_IN_SECONDS	 = 60 * MINUTE_IN_SECONDS;
    public static final long DAY_IN_SECONDS		 = 24 * HOUR_IN_SECONDS;
    public static final long WEEK_IN_SECONDS	 = 7 * DAY_IN_SECONDS;

    public static final long SECOND_IN_MILLIS	 = 1000;
    public static final long MINUTE_IN_MILLIS	 = 60 * SECOND_IN_MILLIS;
    public static final long HOUR_IN_MILLIS		 = 60 * MINUTE_IN_MILLIS;
    public static final long DAY_IN_MILLIS		 = 24 * HOUR_IN_MILLIS;
    public static final long WEEK_IN_MILLIS		 = 7 * DAY_IN_MILLIS;

    public static final String TEMPLATE_DATE_TIME_WITH_MILLIS			 = "yyyy-MM-dd HH:mm:ss.S";
    public static final String TEMPLATE_DATE_TIME						 = "yyyy-MM-dd HH:mm:ss";
    public static final String TEMPLATE_DATE_TIME_EXCLUDE_SECOND		 = "yyyy-MM-dd HH:mm";
    public static final String TEMPLATE_DATE_TIME_O_CLOCK_EXCLUDE_YEAR   = "MM月dd日 HH:00";
    public static final String TEMPLATE_DATE_TIME_O_CLOCK_ONLY           = "HH:00";
    public static final String TEMPLATE_DATE							 = "yyyy-MM-dd";
    public static final String TEMPLATE_TIME							 = "HH:mm:ss";
    public static final String TEMPLATE_DATE_TIME_FILENAME_WITH_MILLIS	 = "yyyyMMdd_HHmmssS";
    public static final String TEMPLATE_DATE_TIME_FILENAME 				 = "yyyyMMdd_HHmmss";
    public static final String TEMPLATE_DATE_FILENAME					 = "yyyyMMdd";
    public static final String TEMPLATE_TIME_FILENAME					 = "HHmmss";

    public static String getTime(long timeInMillis, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.CHINA);
        return simpleDateFormat.format(new Date(timeInMillis));
    }

    public static String getCurrentTime(String dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }
}
