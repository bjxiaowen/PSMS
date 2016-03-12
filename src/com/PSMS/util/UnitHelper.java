package com.PSMS.util;

public class UnitHelper {

	/* @brief fixed KWh */
	public static String fixedKWh(String str) {
		StringBuffer sb = new StringBuffer();
		return sb.append(str + " KWh").toString();
	}

	/* @brief fixed tenThousand */
	public static String fixedTenThousand(String str) {
		StringBuffer sb = new StringBuffer();
		return sb.append(str + " 万元").toString();
	}
}
