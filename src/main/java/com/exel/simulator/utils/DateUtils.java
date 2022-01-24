package com.exel.simulator.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by brixzen on 3/24/17.
 */
public class DateUtils {

    static SimpleDateFormat simpleDateFormatNowTimeMillisecond = new SimpleDateFormat("HH:mm:ss.SSS");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getNowLogDate() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("ddMMyy");
        return fmt.print(new DateTime());
    }

    public static String getNowTimeMs(){
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormatNowTimeMillisecond.format(calendar.getTime());
    }

    public String getUnixTime(){
        long unixTime = Instant.now().getEpochSecond();
        return String.valueOf(unixTime);
    }

    public static String getDateTime() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return fmt.print(new DateTime());
    }

    public static String getNowDate() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return fmt.print(new DateTime());
    }

    public static String getNowDateTdr() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
        return fmt.print(new DateTime());
    }

    public static String getNowTime() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
        return fmt.print(new DateTime());
    }

    public static String getNowTimeMinutes() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
        return fmt.print(new DateTime());
    }

    public static String getNowDateHour() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd-HH");
        return fmt.print(new DateTime());
    }

    public static String getNowMillisecond() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmmssSSS");
        return fmt.print(new DateTime());
    }


    public static String plusYear(String sDate, int nYear) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(sDate);
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return fmt.print(dateTime.plusYears(nYear));
    }

    public static Date stringToDate(String sDate) throws ParseException {
        String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        Date myDate = formatter.parse(sDate);
        return myDate;
    };

    public static String dateToString(Date date) {
        String DEFAULT_PATTERN = "yyyy-MM-dd";

        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        String myDate = formatter.format(date);
        return myDate;
    }

    public static Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static LocalDateTime parseLocaDateTime(String timestamp) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
        return dateTime;
    }

    public static boolean isCalSame(Calendar cal1, Calendar cal2){
        if(cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH)==cal2.get(Calendar.DAY_OF_MONTH)){
            return true;
        }
        return false;
    }

    public static String getYesterdayLogDate() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return fmt.print(new DateTime().minusDays(1));
    }

    public static String getLasNDate(int minusDays) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return fmt.print(new DateTime().minusDays(minusDays));
    }

}
