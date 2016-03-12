package com.PSMS.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	
	public static String getYear(){
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy");
	
	Date s = new Date();
	String time = formattertime.format(s);
	return time;
}
	public static String getMonth(){
		SimpleDateFormat formattertime = new SimpleDateFormat("MM");
	
	Date s = new Date();
	String time = formattertime.format(s);
	return time;
}
	public static String getTimeStr(){
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	Date s = new Date();
	String time = formattertime.format(s);
	return time;
}
	public static String getSec(){
		SimpleDateFormat formattertime = new SimpleDateFormat("ss");
		
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}
	public static String getHour(){
		SimpleDateFormat formattertime = new SimpleDateFormat("HH");
		
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}
	public static String getMin(){
		SimpleDateFormat formattertime = new SimpleDateFormat("mm");
		
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}
	public static String getCurrentTime(){
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}
	public static String getCurrentTime2(){
		SimpleDateFormat formattertime = new SimpleDateFormat("HH:mm:ss");
		
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}
	public static String getCurrentTime3(){
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy-MM-dd");
		
		Date s = new Date();
		String time = formattertime.format(s);
		return time;
	}
	public static String getBackupTime(){
		SimpleDateFormat formattertime = new SimpleDateFormat("yyyy-MM-dd(HH_mm_ss)");
	
	Date s = new Date();
	String time = formattertime.format(s);
	return time;
	}
}