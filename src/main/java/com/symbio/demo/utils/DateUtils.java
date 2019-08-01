package com.symbio.demo.utils;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtils {


    static {
        TimeZone.setDefault(getDefaultTz());
    }

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_FORMAT_FULLSTRING = "yyyyMMddHHmmssS";
    public static final String DATE_FORMAT_1= "yyyy/MM/dd";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN1 = "M/d/yyyy";
    public static final String DATE_DASH_PATTERN = "dd-MMM-yyyy";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_TIMEZONE_PATTERN = "yyyy-MM-dd HH:mm:ss.S z";

//    private static ConfigRepo configRepo;
//
//    @Autowired
//    public DateUtils(ConfigRepo configRepo) {
//        DateUtils.configRepo = configRepo;
//    }
    
    public DateUtils() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static String formatToDateStr(Date date, String format) {
        if (date == null) {
            return "";
        }
        
        if (format == null || format == "") {
            format = DATE_FORMAT;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(getDefaultTz());
        return dateFormat.format(date);
    }

    public static String formatToDateTzStr(Date date) {
        if (date == null) {
            return "";
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DATE_TIME_TIMEZONE_PATTERN, Locale.US);
        dateFormat.setTimeZone(getDefaultTz());
        return dateFormat.format(date);
    }

    public static TimeZone getDefaultTz() {
        TimeZone timeZoneNY = TimeZone.getTimeZone("America/New_York");
        return timeZoneNY;
    }

    public static Date formatToDate(String date, String format, boolean needsTzConvert, boolean unLenient) {
        if (format == null || format == "") {
            format = DATE_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (needsTzConvert) {
            dateFormat.setTimeZone(getDefaultTz());
        }
        if (unLenient)
            dateFormat.setLenient(false);

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static Date formatToDate(String date, String format,
            boolean needsTzConvert) {
        if (format == null || format == "") {
            format = DATE_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (needsTzConvert) {
            dateFormat.setTimeZone(getDefaultTz());
        }

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static Date formatToDate(String date, String format) {
        return formatToDate(date, format, false);
    }

    public static Date getCurentDate() {

        return new Date(System.currentTimeMillis());
    }

    public static Date get6WeekBeforeDate() {
        Calendar cal = getCurentCalendar();
        if (cal.get(Calendar.WEEK_OF_YEAR) == 2
                && cal.get(Calendar.MONTH) == Calendar.JANUARY) {
            cal.add(Calendar.WEEK_OF_YEAR, -4);
        } else {
            cal.add(Calendar.WEEK_OF_YEAR, -5);
        }

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public static Date get6MonthBeforeDate() {
        Calendar cal = getCurentCalendar();
        cal.add(Calendar.MONTH, -5);
        
        int firstDay = getDayOfFiscalMonth();
        cal.set(Calendar.DATE, firstDay == 1 ? cal.getActualMinimum(Calendar.DATE) : firstDay);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Calendar getCurentCalendar() {
        Calendar calendar = new GregorianCalendar(getDefaultTz());
        return calendar;
    }

    public static Calendar getYesterdayOfCurentCalendar() {
        Calendar calendar = new GregorianCalendar(getDefaultTz());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar;
    }

    public static Calendar getEndOfTodayCalendar() {
        Calendar calendar = new GregorianCalendar(getDefaultTz());

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar;
    }

    public static Calendar getEndOfTodayCalendar(Date date) {
        Calendar calendar = new GregorianCalendar(getDefaultTz());

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar;
    }
    
    public static Calendar getEndOfGivenDate(Date date) {
        Calendar calendar = new GregorianCalendar(getDefaultTz());
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar;
    }

    public static Calendar formatDateToCalendar(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(date);

        return cal;
    }

    public static Timestamp getCurentTs() {
        return new Timestamp(getCurentDate().getTime());
    }

    public static Calendar getFirstDayOfWeek() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    public static Calendar getFirstDayOfLastWeek(Calendar calendar) {
        calendar.add(Calendar.DATE, -7);
        return calendar;
    }

    public static Calendar getLastDayOfWeek(Calendar calendar) {
        calendar.add(Calendar.DATE, +6);
        return calendar;
    }

    public static boolean isFirstDayOfWeek() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public static Calendar getLastDayOfWeek() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getEndDayOfWeek() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getFirstDayOfMonth() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        int firstDay = getDayOfFiscalMonth();
        if (cal.get(Calendar.DATE) < firstDay) {
            cal.add(Calendar.MONTH, -1);
        }
        cal.set(Calendar.DATE, firstDay == 1 ? cal.getActualMinimum(Calendar.DATE) : firstDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getFirstDayOfLastMonth(Calendar calendar) {
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }

    public static Calendar getFirstDayOfLastMonth() {
        Calendar calendar = getFirstDayOfMonth();
        
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }

    public static Calendar getEndDayOfLastMonth() {
        Calendar calendar = getFirstDayOfLastMonth();

        return getEndDayOfMonth(calendar.getTime());
    }

    public static Calendar getFirstDayOfBeforeLastMonth() {
        Calendar calendar = getFirstDayOfMonth();
        
        calendar.add(Calendar.MONTH, -2);
        return calendar;
    }

    public static boolean isFirstDayOfMonth() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        int firstDay = getDayOfFiscalMonth();
        return cal.get(Calendar.DAY_OF_MONTH) == firstDay;
    }

    public static Calendar getFirstDayOfMonth(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(date);

        int firstDay = getDayOfFiscalMonth();
        if (cal.get(Calendar.DATE) < firstDay) {
            cal.add(Calendar.MONTH, -1);
        }
        cal.set(Calendar.DATE, firstDay == 1 ? cal.getActualMinimum(Calendar.DATE) : firstDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getEndDayOfMonth() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        int firstDay = getDayOfFiscalMonth();
        if (firstDay != 1 && cal.get(Calendar.DATE) >= firstDay) {
            cal.add(Calendar.MONTH, 1);
        }
        cal.set(Calendar.DATE, firstDay == 1 ? cal.getActualMaximum(Calendar.DATE) : (firstDay - 1));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal;
    }

    public static Calendar getEndDayOfYear() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        int year = cal.get(Calendar.YEAR);

        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.roll(Calendar.DAY_OF_YEAR, -1);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal;
    }

    public static int getWeekOfYear(Calendar cal) {
        cal.setTimeZone(getDefaultTz());
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        if (week == 1) {
            int month = cal.get(Calendar.MONTH);
            if (Calendar.DECEMBER == month) {
                Calendar cal2 = (Calendar) cal.clone();
                cal2.add(Calendar.WEEK_OF_YEAR, -1);

                return cal2.get(Calendar.WEEK_OF_YEAR);
            } else {
                return week;
            }
        } else {
            return week;
        }

    }

    public static int getWeekOfYear(Date date) {
        Calendar cal = getCalendar(date);
        return getWeekOfYear(cal);
    }

    public static int getWeekOfYearNormal(Date date) {
        Calendar cal = getCalendar(date);
        cal.setTimeZone(getDefaultTz());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date getBeginOfToday() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(getCurentDate());

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    public static Calendar getBeginOfTodayCalendar() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(getCurentDate());

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getBeginOfTodayCalendar(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }
    
    public static Calendar getBeginOfGivenDate(Date date){
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal;
    }

    public static Calendar getBeginOfYesterday() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(getCurentDate());

        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Date getEndOfYesterday() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(getCurentDate());

        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    public static Date getDownloadFileExpiration() {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(DateUtils.getCurentDate());

        cal.add(Calendar.HOUR, 1);

        return cal.getTime();
    }

    public static void resetTz() {
        TimeZone timeZoneNY = TimeZone.getTimeZone("America/New_York");
        TimeZone.setDefault(timeZoneNY);
    }

    public static Calendar getBeginOfYesterday(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getFirstDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(date);

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getEndDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(date);

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public static Calendar getEndDayOfMonth(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(date);

        int firstDay = getDayOfFiscalMonth();
        if (firstDay != 1 && cal.get(Calendar.DATE) >= firstDay) {
            cal.add(Calendar.MONTH, 1);
        }
        cal.set(Calendar.DATE, firstDay == 1 ? cal.getActualMaximum(Calendar.DATE) : (firstDay - 1));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());

        cal.setTime(date);

        return cal;
    }

    public static Calendar getEndDayOfYear(Date start) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.clear();
        cal.setTimeZone(getDefaultTz());
        cal.setTime(start);

        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal;
    }

    public static boolean isDate(String str, String pattern) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }
    
    
    public static Long daysBetween(Date startDate,Date endDate) {    
        Calendar cal = DateUtils.getCurentCalendar();
        cal.setTime(startDate);    
        long time1 = cal.getTimeInMillis();                  
        cal.setTime(endDate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24)+1;  
        return Long.parseLong(String.valueOf(between_days));
    }

    public static int getDayCountOfMonth(Calendar month){
        month.setTimeZone(getDefaultTz());
        return month.getActualMaximum(Calendar.DATE);
    }

//    public static Date getEndDateByReportType(ReportType type) {
//        Date current = getEndDayOfMonth().getTime();
//        switch (type) {
//            case LASTMONTH:
//                current = getEndDayOfLastMonth().getTime();
//                break;
//            case BEFORELASTMONTH:
//                current = getEndDayOfMonth(getFirstDayOfBeforeLastMonth().getTime()).getTime();
//                break;
//            default:
//                break;
//        }
//        return current;
//    }
    

    private static int getDayOfFiscalMonth() {
        return 1;
//        return ConfigTableUtils.getDayOfFiscalMonth();
    }

    public static int getDaysOfMonth(Date date) {
        int firstDay = getDayOfFiscalMonth();
        if (firstDay == 1) {
            Calendar cal = new GregorianCalendar(getDefaultTz());
            cal.setTimeZone(getDefaultTz());
            cal.setTime(date != null ? date : getCurentDate());
            return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            Calendar first = date != null ? getFirstDayOfMonth(date) : getFirstDayOfMonth();
            Calendar end = date != null ? getEndDayOfMonth(date) : getEndDayOfMonth();
            return daysBetween(first.getTime(), end.getTime()).intValue();
        }
    }
    
    public static Calendar getLastDayOfMonth(Date time) {
        Calendar cal = new GregorianCalendar(getDefaultTz());
        cal.setTimeZone(getDefaultTz());
        cal.setTime(time);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal;

    }

}
