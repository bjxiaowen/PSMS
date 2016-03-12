package com.PSMS.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
	/* @brief getCurrentTimeStamp */
	public static String getCurrentTimeStamp() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date()).toString();
	}
}
