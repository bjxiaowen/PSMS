package com.PSMS.util;

public class Paging {

	public static final int PAGING_LIMIT = 20;// paging record limit

	/* @brief Judge start */
	public static int getStart(String start) {
		int startInt = start != null ? Integer.parseInt(start) : 0;
		return startInt;
	}
}
