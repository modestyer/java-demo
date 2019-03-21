package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/18
 **/
public class DateTools {
    /**
     * 日期转化为指定日期格式类型
     * @param date 日期
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     */
    public static String dateToString(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String date_str = sdf.format(date);//日期转化为指定格式
        return date_str;
    }

    /**
     * 日期字符串转化为指定日期格式类型
     * @param date_str 日期字符串
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date_str,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        Date date = sdf.parse(date_str);//日期转化为指定格式
        return date;
    }

    /**
     * 获得今天日期
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     */
    public static String getToday(String pattern){
        Date date = new Date();//今天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String today = sdf.format(date);//日期转化为指定格式
        return today;
    }

    /**
     * 获得明天日期
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     */
    public static String getTomorrow(String pattern){
        Date date = new Date();//今天日期
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);//设置时间
        cal.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date = cal.getTime();//这个时间就是日期往后推一天的日期结果，即明天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String tommorrow = sdf.format(date);//明天日期
        return tommorrow;
    }

    /**
     * 获得昨天日期
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     */
    public static String getYesterday(String pattern){
        Date date = new Date();//今天日期
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);//设置时间
        cal.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        date = cal.getTime();//这个时间就是日期往前推一天的日期结果，即昨天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String yesterday = sdf.format(date);//昨天日期
        return yesterday;
    }

    /**
     * 获得指定日期的后一天
     * @param date 指定日期
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date getAfterDay(Date date,String pattern) throws ParseException {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);//设置时间
        cal.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date = cal.getTime();//这个时间就是日期往后推一天的日期结果，即后一天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String next_date_str = sdf.format(date);//后一天日期
        //将日期字符串转化为指定日期格式类型
        Date after_date =  stringToDate(next_date_str,pattern);
        return after_date;
    }

    /**
     * 获得指定日期的后一天
     * @param date_str 指定日期字符串
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getAfterDay(String date_str,String pattern) throws ParseException {
        //将日期字符串转化为指定日期格式类型
        Date date = stringToDate(date_str,pattern);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);//设置时间
        cal.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
        date = cal.getTime();//这个时间就是日期往后推一天的日期结果，即后一天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String after_date_str = sdf.format(date);//后一天日期
        return after_date_str;
    }

    /**
     * 获得指定日期的前一天
     * @param date 指定日期
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date getBeforetDay(Date date,String pattern) throws ParseException {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);//设置时间
        cal.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        date = cal.getTime();//这个时间就是日期往后推一天的日期结果，即前一天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String before_date_str = sdf.format(date);//前一天日期
        //将日期字符串转化为指定日期格式类型
        Date before_date =  stringToDate(before_date_str,pattern);
        return before_date;
    }

    /**
     * 获得指定日期的前一天
     * @param date_str 指定日期字符串
     * @param pattern 日期格式类型，如yyyy-MM-dd，yyyy年MM月dd日 HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getBreforeDay(String date_str,String pattern) throws ParseException {
        //将日期字符串转化为指定日期格式类型
        Date date = stringToDate(date_str,pattern);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);//设置时间
        cal.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
        date = cal.getTime();//这个时间就是日期往后推一天的日期结果，即前一天日期
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//日期格式
        String before_date_str = sdf.format(date);//前一天日期
        return before_date_str;
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }
    /**
     * 日期格式字符串转换成时间戳
     * @param date 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /**
     * 获取当月第一天
     * @return
     */
    public static String getMonthFirstDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //获取前月的第一天
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());

        return firstDay;
    }

}
