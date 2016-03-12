package com.PSMS.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.PSMS.Dao.MonitoringDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Failure_alarm;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;
import com.PSMS.Hibernate.JunctionBox;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WS_parameter;
import com.PSMS.Hibernate.WeatherStation;
import com.PSMS.util.JsonHelper;
import com.PSMS.util.SortHelper;
import com.PSMS.util.StringHelper;

public class MonitoringServiceImpl implements MonitoringService {

	MonitoringDAO monitoringDAO = DAOFactory.getMonitoringDaoInstance();

	// !!!
	public WeatherStation getWeatherStationSummary(Integer psId, String table,
			Integer termId) {
		String parameterTable = StringHelper.NaviParameterTable(table);
		List<Integer> parameterIds = monitoringDAO
				.getParameterIdsByPsIdAndTermId(parameterTable, psId, termId);
		List<WeatherStation> list = monitoringDAO
				.getWeatherStationSummary(parameterIds);
		int i = new Random().nextInt(list.size());
		WeatherStation weahterStation = list.get(i);
		return weahterStation;
	}

	// !!!
	public List getSummaryChart(Integer psId, String table, String option,
			Integer termId) {
		String parameterTable = StringHelper.NaviParameterTable(table);
		List<Integer> parameterIds = monitoringDAO
				.getParameterIdsByPsIdAndTermId(parameterTable, psId, termId);
		List list = monitoringDAO.getSummaryChart(parameterIds, table);
		List resultList = null;
		if (table.equals("Inverter") && option.equals("power")) {
			resultList = new ArrayList<Inverter>();
			Inverter inverter = new Inverter();
			inverter.setAcPower(0.0);
			inverter.setDcPower(0.0);
			String time = null;
			time = ((Inverter) list.get(0)).getTime().substring(0, 10);
			inverter.setTime(time);
			for (int i = 1; i < list.size(); i++) {
				if (((Inverter) list.get(i)).getTime().indexOf(time) >= 0) {
					inverter.setAcPower(inverter.getAcPower()
							+ ((Inverter) list.get(i)).getAcPower());
					inverter.setDcPower(inverter.getDcPower()
							+ ((Inverter) list.get(i)).getDcPower());
				} else {
					resultList.add(inverter);
					inverter = new Inverter();
					inverter.setAcPower(0.0);
					inverter.setDcPower(0.0);
					time = ((Inverter) list.get(i)).getTime().substring(0, 10);
					inverter.setTime(time);
				}
			}
		}
		return resultList;
	}

	// !!!
	public String getResultJson(int start, int limit, String table,
			Integer psId, Integer termId) {
		String parameterTable = StringHelper.NaviParameterTable(table);
		List<Integer> parameterIds = monitoringDAO
				.getParameterIdsByPsIdAndTermId(parameterTable, psId, termId);

		List list = monitoringDAO.getResult(start, limit, table, parameterIds);
		Long count = monitoringDAO.getCount(table, parameterIds);

		if (table.equals("Inverter") || table.equals("JunctionBox")) {
			list = this.transformFailureCodeToFailureMeaning(list, table);
		}
		String result = JsonHelper.resolveCycle(list);
		result = JsonHelper.appendCountAndRoot(count, result);
		return result;
	}

	public List transformFailureCodeToFailureMeaning(List list, String table) {

		List<Failure_alarm> failureAlarmList = null;
		if (table.equals("Inverter")) {
			failureAlarmList = monitoringDAO.getFailureAlarmByType("逆变器");
		} else if (table.equals("JunctionBox")) {
			failureAlarmList = monitoringDAO.getFailureAlarmByType("汇流箱");
		}

		Map failureCodeFailureMeaningMap = new HashMap<String, String>();
		for (int i = 0; i < failureAlarmList.size(); i++) {
			failureCodeFailureMeaningMap.put(failureAlarmList.get(i)
					.getFailure_code(), failureAlarmList.get(i)
					.getFailure_meaning());
		}
		if (table.equals("Inverter")) {
			for (int i = 0; i < list.size(); i++) {
				((Inverter) list.get(i)).setState(failureCodeFailureMeaningMap
						.get(((Inverter) list.get(i)).getState()).toString());
			}
		} else if (table.equals("JunctionBox")) {
			for (int i = 0; i < list.size(); i++) {
				((JunctionBox) list.get(i))
						.setState(failureCodeFailureMeaningMap.get(
								((JunctionBox) list.get(i)).getState())
								.toString());
			}
		}
		return list;
	}

	public List getChartByParameterId(String table, Integer parameterId) {
		List list = null;
		list = monitoringDAO.getChartByParameterId(parameterId.toString(),
				table);
		if (null == list) {
			if (table.equals("Inverter")) {
				list = new ArrayList<Inverter>();
			} else if (table.equals("PowerMeter")) {
				list = new ArrayList<PowerMeter>();
			} else if (table.equals("JunctionBox")) {
				list = new ArrayList<JunctionBox>();
			} else if (table.equals("WeatherStation")) {
				list = new ArrayList<WeatherStation>();
			}
		}
		return list;
	}

	public String getMonitorParameterIds(Integer psId, String table,
			Integer termId) {
		String parameterTable = StringHelper.NaviParameterTable(table);
		List<Integer> parameterIds = monitoringDAO
				.getParameterIdsByPsIdAndTermId(parameterTable, psId, termId);
		SortHelper.sortIntegerList(parameterIds);
		List<String> strList = StringHelper
				.transformIntListToStringList(parameterIds);
		System.out.println(StringHelper.spliceSelectMenu(strList, table));
		return StringHelper.spliceSelectMenu(strList, table);
	}

	public String getMonitorNames(Integer psId, String table, Integer termId) {
		String parameterTable = StringHelper.NaviParameterTable(table);
		List<String> namesList = monitoringDAO.getNamesByPsId(parameterTable,
				psId, termId);
		System.out.println(StringHelper.spliceSelectMenu(namesList, table));
		return StringHelper.spliceSelectMenu(namesList, table);
	}

	public Map getDeviceCount(Integer psId) {
		Map inverterCount = monitoringDAO.getDeviceCount("Inverter_parameter",
				psId);
		Map powermeterCount = monitoringDAO
				.getDeviceCount("PM_parameter", psId);
		Map junctionBoxCount = monitoringDAO.getDeviceCount("JB_parameter",
				psId);
		Map weatherStationCount = monitoringDAO.getDeviceCount("WS_parameter",
				psId);
		Map<String, Map> deviceCountMap = new HashMap<String, Map>();
		deviceCountMap.put("inverter", inverterCount);
		deviceCountMap.put("power_meter", powermeterCount);
		deviceCountMap.put("junction_box", junctionBoxCount);
		deviceCountMap.put("weather_station", weatherStationCount);
		return deviceCountMap;
	}

	public List getDeviceList(Integer psId) {
		Map<Integer, Map<Integer, Inverter_parameter>> inverterMap = monitoringDAO
				.getDeviceList("Inverter_parameter", psId);
		Map<Integer, Map<Integer, PM_parameter>> powerMeterMap = monitoringDAO
				.getDeviceList("PM_parameter", psId);
		Map<Integer, Map<Integer, JB_parameter>> junctionBoxMap = monitoringDAO
				.getDeviceList("JB_parameter", psId);
		Map<Integer, Map<Integer, WS_parameter>> weatherStationMap = monitoringDAO
				.getDeviceList("WS_parameter", psId);

		int maxCount = -1;
		if (inverterMap.size() > maxCount) {
			maxCount = inverterMap.size();
		}
		if (powerMeterMap.size() > maxCount) {
			maxCount = powerMeterMap.size();
		}
		if (junctionBoxMap.size() > maxCount) {
			maxCount = junctionBoxMap.size();
		}
		if (weatherStationMap.size() > maxCount) {
			maxCount = weatherStationMap.size();
		}
		List result = new ArrayList<Map<String, Map>>();
		Map<String, Map> typeDevice = null;
		for (int i = 1; i <= maxCount; i++) {
			typeDevice = new HashMap<String, Map>();
			typeDevice.put("inverter",
					(inverterMap.get(i) != null) ? inverterMap.get(i)
							: new TreeMap<Integer, String>());
			typeDevice.put("power_meter",
					(powerMeterMap.get(i) != null) ? powerMeterMap.get(i)
							: new TreeMap<Integer, String>());
			typeDevice.put("junction_box",
					(junctionBoxMap.get(i) != null) ? junctionBoxMap.get(i)
							: new TreeMap<Integer, String>());
			typeDevice.put("weather_station",
					(weatherStationMap.get(i) != null) ? weatherStationMap
							.get(i) : new TreeMap<Integer, String>());
			result.add(typeDevice);
		}

		return result;
	}

	// stop

	/* @brief get summary of InverterList */
	public PowerMeter getPowerMeterSummary() {
		List<PowerMeter> list = monitoringDAO.getPowerMeterSummary();
		PowerMeter powerMeter = new PowerMeter();
		powerMeter.setAccPower(0.0);
		for (int i = 0; i < list.size(); i++) {
			Double accPower = list.get(i).getAccPower()
					+ powerMeter.getAccPower();
			powerMeter.setAccPower(accPower);
		}
		return powerMeter;
	}

	/* @brief getSalary */
	public Double getTotalSalary() {
		Double AccPower = this.getPowerMeterSummary().getAccPower();
		Double UnitPrice = 0.6;
		Double totalSalary = AccPower * 0.6 / 10000;
		return totalSalary;
	}

	public List getFailureMeaning(String table) {
		List<Failure_alarm> list = null;
		if (table.equals("Inverter")) {
			list = monitoringDAO.getFailureAlarmByType("逆变器");
		} else if (table.equals("JunctionBox")) {
			list = monitoringDAO.getFailureAlarmByType("汇流箱");
		}
		List<String> failureMeaningList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			failureMeaningList.add(list.get(i).getFailure_meaning());
		}
		return failureMeaningList;
	}
	//2015-05 from lm
	@Override
	public List<PowerMeter> getPMRealTimeData(int ps_id, int period_num,int id) {
		// TODO Auto-generated method stub
		return monitoringDAO.getPMRealTimeData(ps_id,period_num,id);
	}

}