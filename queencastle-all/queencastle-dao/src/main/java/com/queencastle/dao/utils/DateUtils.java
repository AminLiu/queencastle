package com.queencastle.dao.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";
    private static DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    public static final String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateFormat ymdhmsFormat = new SimpleDateFormat(YMDHMS_FORMAT);

    public static final String YMD_FORMAT = "yyyy-MM-dd";
    private static DateFormat ymdFormat = new SimpleDateFormat(YMD_FORMAT);

    public static void main(String[] args) {
        System.out.println(getCurrFullTime());
    }


    /**
     * 两个日期的 天数差
     * 
     * @param big
     * @param small
     * @return
     */
    public static int getDayGap(Date big, Date small) {
        return (int) ((big.getTime() - small.getTime()) / 3600 / 1000);
    }

    public static String getCurrFullTime() {
        return dateFormat.format(new Date());
    }

    public static Date getYmdHmsDate(String date) {
        try {
            return ymdhmsFormat.parse(date);
        } catch (ParseException e) {
            logger.error("日期字符串有误，您的输入为：" + date);
            return null;
        }
    }

    public static Date getYmdDate(String date) {
        try {
            return ymdFormat.parse(date);
        } catch (ParseException e) {
            logger.error("日期字符串有误，您的输入为：" + date);
            return null;
        }
    }

    public static String getCurrentYmd() {
        return ymdFormat.format(new Date());
    }
}
