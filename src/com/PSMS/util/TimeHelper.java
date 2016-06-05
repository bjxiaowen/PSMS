package com.PSMS.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeHelper {
	/* @brief getCurrentTimeStamp */
	public static String getCurrentTimeStamp() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date()).toString();
	}

	public static boolean getAllow() {
		String allowDate = "2016-06-08";// 到期时间
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date allTime;
		try {
			allTime = dateFormat.parse(allowDate);
			Date currtTime = new Date();
			int i = allTime.compareTo(currtTime);
			if (i < 0) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
