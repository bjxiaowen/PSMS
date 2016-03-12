package com.PSMS.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonHelper {
	/* @brief merge Json String for Ext Grid Paging */
	public static String appendCountAndRoot(Long count, String json) {
		StringBuffer sb = new StringBuffer();
		if (null == count) {
			sb.append("{\"rootNode\":");
		} else {
			sb.append("{\"totalCount\":\"" + count + "\",\"rootNode\":");
		}
		sb.append(json);
		sb.append("}");
		return sb.toString();
	}

	/* @brief protect Json cycle by Adding config */
	/* @error there is a cycle in the hierarchy */
	public static String resolveCycle(Object obj) {

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		Object result = null;
		if (obj instanceof List) {
			result = JSONArray.fromObject(obj, jsonConfig);
		} else {
			result = JSONObject.fromObject(obj, jsonConfig);
		}

		return result.toString();
	}

	public static List<Integer> transformJsonToList(String jsonString) {
		if (jsonString == null || jsonString.equals("")) {
			return new ArrayList();
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		List<Integer> list = JSONArray.toList(jsonArray);
		return list;
	}

	public static String createArrayJsonForExtFilter(List<String> list) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			newList.add("'" + list.get(i) + "'");
		}
		return newList.toString();
	}
}
