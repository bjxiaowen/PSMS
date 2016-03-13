package com.PSMS.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTime {

	public static String getYear() {
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getMonth() {
		SimpleDateFormat formattertime = new SimpleDateFormat("MM");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getTimeStr() {
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getSec() {
		SimpleDateFormat formattertime = new SimpleDateFormat("ss");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getHour() {
		SimpleDateFormat formattertime = new SimpleDateFormat("HH");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getMin() {
		SimpleDateFormat formattertime = new SimpleDateFormat("mm");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getCurrentTime() {
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getCurrentTime2() {
		SimpleDateFormat formattertime = new SimpleDateFormat("HH:mm:ss");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getCurrentTime3() {
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy-MM-dd");
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	public static String getBackupTime() {
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy-MM-dd(HH_mm_ss)");

		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
}