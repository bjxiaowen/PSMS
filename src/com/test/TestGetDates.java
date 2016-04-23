package com.test;

import java.util.Calendar;
import java.util.Locale;

public class TestGetDates {

	public static void main(String[] args) {
		System.out.println(getDayOfMonth());
	}
	
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}

}
