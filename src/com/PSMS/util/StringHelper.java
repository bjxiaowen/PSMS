package com.PSMS.util;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

	/* @brief IntegerList to StringList */
	public static List<String> transformIntListToStringList(
			List<Integer> integerList) {
		List<String> stringList = new ArrayList();
		for (int i = 0; i < integerList.size(); i++) {
			stringList.add(integerList.get(i).toString());
		}
		return stringList;
	}

	/* @brief table navi to parameterTable */
	public static String NaviParameterTable(String table) {
		String parameterTable = null;
		if (table.equals("Inverter")) {
			parameterTable = "Inverter_parameter";
		} else if (table.equals("PowerMeter")) {
			parameterTable = "PM_parameter";
		} else if (table.equals("JunctionBox")) {
			parameterTable = "JB_parameter";
		} else if (table.equals("WeatherStation")) {
			parameterTable = "WS_parameter";
		}
		return parameterTable;
	}

	public static String spliceSelectMenu(List<String> list, String table) {

		String selectLabelHead = null;
		if (table.equals("Inverter")) {
			selectLabelHead = "\"<select id='chooseInverter'>";
		} else if (table.equals("JunctionBox")) {
			selectLabelHead = "\"<select id='chooseJunctionBox'>";
		} else if (table.equals("PowerMeter")) {
			selectLabelHead = "\"<select id='choosePowerMeter'>";
		} else if (table.equals("WeatherStation")) {
			selectLabelHead = "\"<select id='chooseWeatherStation'>";
		}

		String selectLabelTail = "</select>\"";
		String optionLabelHead = "<option>";
		String optionLabelTail = "</option>";
		StringBuffer sb = new StringBuffer();
		sb.append(selectLabelHead);
		for (int i = 0; i < list.size(); i++) {
			sb.append(optionLabelHead + list.get(i) + optionLabelTail);
		}
		sb.append(selectLabelTail);
		return sb.toString();
	}
}
