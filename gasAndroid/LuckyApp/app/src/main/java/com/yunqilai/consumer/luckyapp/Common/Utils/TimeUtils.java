package com.yunqilai.consumer.luckyapp.Common.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TimeUtils {

	// private static Calendar mCalendar = Calendar.getInstance();
	private static DateFormat dayDf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 获取系统时间-年
	 * 
	 * @return
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取系统时间-月
	 *
	 * @return
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取系统时间-周
	 *
	 * @return
	 */
	public static int getWeek() {
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取系统时间-日
	 * 
	 * @return
	 */
	public static int getDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取系统的时间-小时
	 * @return
	 */
	public static int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取系统时间-分钟
	 * @return
	 */
	public static int getMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/**
	 * 获取系统时间-秒
	 * @return
	 */
	public static int getSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	/**
	 * 由字符串解析得到时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateFromString(String date) {
		Date tDate = null;
		try {
			tDate = dayDf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tDate;
	}

	/**
	 * 由字符串解析得到时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateFromString(DateFormat format,String date) {
		Date tDate = null;
		try {
			tDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tDate;
	}
	/**
	 * 获得年月日
	 *
	 * @return
	 */
	public static String getYMD() {
		return getYear()+"-"+getMonth()+"-"+getDay();
	}

	/**
	 * 获得年月日时分秒
	 * @return
     */
	public static String getYMDHms() {
		return getYear()+"-"+getMonth()+"-"+getDay()+" "+getHour()+":"+getMinute()+":"+getSecond();
	}

	/**
	 * 比较时间早晚
	 * 
	 * @param date1
	 * @param date2
	 * @return 如果date2比date1早，返回true
	 */
	public static boolean isEarly(Date date1, Date date2) {
		if (date1.getTime() - date2.getTime() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 比较两个日期字符串的早晚
	 * @param dateStr1
	 * @param dateStr2
     * @return 如果dateStr2比dateStr1早，返回true
     */
	public static boolean isDateEarly(String dateStr1,String dateStr2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(dateStr1);
			date2 = df.parse(dateStr2);
			return isEarly(date1,date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 比较两个时间字符串的早晚
	 * @param timeStr1
	 * @param timeStr2
     * @return 如果dateStr2比dateStr1早，返回true
     */
	public static boolean isTimeEarly(String timeStr1,String timeStr2){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(timeStr1);
			date2 = df.parse(timeStr2);
			return isEarly(date1,date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 比较两个日期时间字符串的早晚
	 * @param DateTimeStr1
	 * @param DateTimeStr2
     * @return 如果dateStr2比dateStr1早，返回true
     */
	public static boolean isDateTimeEarly(String DateTimeStr1,String DateTimeStr2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(DateTimeStr1);
			date2 = df.parse(DateTimeStr2);
			return isEarly(date1,date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 比较两个时间字符串的间隔早晚
	 * @param timeStr1
	 * @param timeStr2
	 * @param seconds
     * @return 如果 dateStr2 比 dateStr1+间隔 早，返回true
     */
	public static boolean isTimeIntervalEarly(String timeStr1,String timeStr2,int seconds){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(timeStr1);
			date2 = df.parse(timeStr2);
			if (date1.getTime()+seconds*1000 - date2.getTime() > 0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 得到每个月的天数
	 * 
	 * @param year
	 *            查询的年
	 * @param month
	 *            查询的月
	 * @return
	 */
	public static int getDayCountByYearAndMonth(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}

	/**
	 * 根据年月日得到前一天
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return 数组，0-年，1-月，2-日
	 */
	public static int[] getPreviousDay(int year, int month, int day) {
		int[] previousDay = new int[3];
		if (day == 1) {
			if (month == 1) {
				year = year - 1;
				month = 12;
				day = TimeUtils.getDayCountByYearAndMonth(year, month);
			} else {
				month = month - 1;
				day = TimeUtils.getDayCountByYearAndMonth(year, month);
			}

		} else {
			day = day - 1;
		}
		previousDay[0] = year;
		previousDay[1] = month;
		previousDay[2] = day;
		return previousDay;
	}

	/**
	 * 根据年月日得到下一天
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return 数组，0-年，1-月，2-日
	 */
	public static int[] getNextDay(int year, int month, int day) {
		int[] nextDay = new int[3];

		int mDayMaxByMonth = TimeUtils.getDayCountByYearAndMonth(year, month);

		if (day < mDayMaxByMonth) {
			day++;
		} else {
			if (month < 12) {
				month++;
			} else {
				year++;
				month = 1;
			}
			day = 1;
		}

		nextDay[0] = year;
		nextDay[1] = month;
		nextDay[2] = day;
		return nextDay;
	}

	/**
	 * 根据年月日得到该月最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}
	/**
	 * 取得指定日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime ();
	}
	/**
	 * 取得指定日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	/**
	 * 得到某年某周的第一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE , week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	// 获取当前时间所在年的最大周数
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

		return getWeekOfYear(c.getTime());
	}
	// 获取当前时间所在年的周数
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(1);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获得两个日子之间的所有日期
	 * @param startDay
	 * @param endDay
	 */
	public static List<String> getDaysBetweenTwo(Date startDay, Date endDay) {
		Calendar startDayC = Calendar.getInstance();
		Calendar endDayC = Calendar.getInstance();
		startDayC.setTime(startDay);
		endDayC.setTime(endDay);

		List<String> dates = new ArrayList<String>();
		if (startDayC.compareTo(endDayC) >= 0) {
			return dates;
		}
		Calendar currentPrintDay = startDayC;
		while (true) {
			// 日期加一
			currentPrintDay.add(Calendar.DATE, 1);
			if (currentPrintDay.compareTo(endDayC) == 0) {
				break;
			}
			dates.add(dayDf.format(currentPrintDay.getTime()));
		}
		return dates;
	}

	public static String getPeriodOfWeek(int year, int week) {
		Date first = getFirstDayOfWeek(year, week);
		Date second = getLastDayOfWeek(year, week);

		String result = new SimpleDateFormat("MM/dd").format(first)+"-"+new SimpleDateFormat("MM/dd").format(second);

		return result;
	}

	/**
	 * 将形式如:"00:00:00"的时间加一秒
	 * @param time
	 * @return
	 */
	public static String getNextSecondTime(String time) {
		String[] times = time.split(":");
		int len = times.length;
		int second = Integer.valueOf(times[len - 1]);
		int minute = Integer.valueOf(times[len - 2]);
		int hour = len > 2 ? Integer.valueOf(times[len-3]) : 0;
		second++;
		if (second > 59) {
			minute++;
			second = second % 60;

			if (minute > 59) {
				hour++;
				minute = minute % 60;
			}
		}

		String sHour = hour > 9 ? String.valueOf(hour) : "0" + hour;
		String sMinute = minute > 9 ? String.valueOf(minute) : "0" + minute;
		String sSecond = second > 9 ? String.valueOf(second) : "0" + second;

		return len > 2 ? sHour + ":" + sMinute + ":" + sSecond : sMinute + ":" + sSecond;
	}


	public static String COURSE_CLICK_HOUR = "";
	public static String COURSE_CLICK_TIME = "";
	public static String PUSH_CLICK_TIME = "";

	public static String getTimeDiff(String start,String end){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date now = null;
		Date date = null;
		long hour;
		long min;
		try {
			now = df.parse(end);
			date=df.parse(start);
			long l=now.getTime()-date.getTime();
			long day=l/(24*60*60*1000);
			hour=(l/(60*60*1000)-day*24);
			min=((l/(60*1000))-day*24*60-hour*60);
			System.out.println(""+day+"天"+hour+"小时"+min+"分");
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		if(hour == 0){
			return min+"分钟";
		}else{
			return hour+"小时"+min+"分钟";
		}
	}

	/***
	 * 格式化时间
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatStr =formatter.format(new Date());
		return formatStr;
	}

	public static String getStringTimeToStringTime(String time){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format1.parse(time);
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
			return format2.format(date);
		} catch (ParseException e) {
			return time;
		}

	}

	/***
	 * 获得时期时间
	 * @return
	 */
	public static String getDateTimeFromMills(String mills){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long lt = new Long(mills);
			Date date = new Date(lt);
			String formatStr =formatter.format(date);
			return formatStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 通过日期时间获得毫秒
	 * @param dateTime
	 * @return
     */
	public static long getMillsFromDateTime(String dateTime){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = formatter.parse(dateTime);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
