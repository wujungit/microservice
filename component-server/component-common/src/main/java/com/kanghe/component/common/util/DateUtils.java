package com.kanghe.component.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/10 10:34
 * @Description: 日期
 */
public class DateUtils {

    public static String getCurrentTimeStr(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDateLong(String strDate) {
        if (StringUtils.isNotBlank(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            return formatter.parse(strDate, pos);
        }
        return null;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     */
    public static Date strToDate(String strDate) {
        if (StringUtils.isNotBlank(strDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            return formatter.parse(strDate, pos);
        }
        return null;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     */
    public static String dateToStr(Date date) {
        if (null != date) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(date);
        }
        return null;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStrLong(Date date) {
        if (null != date) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(date);
        }
        return null;
    }

    /**
     * 获取时间 小时:分:秒 HH:mm:ss
     */
    public static String getTimeShort(Date date) {
        if (null != date) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            return formatter.format(date);
        }
        return null;
    }

}
