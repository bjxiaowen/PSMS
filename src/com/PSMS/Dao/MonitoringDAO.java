package com.PSMS.Dao;

import java.util.List;
import java.util.Map;

import com.PSMS.Hibernate.Failure_alarm;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WeatherStation;

public interface MonitoringDAO {

	public Integer getPsIdByUserName(String userName);

	public List<Integer> getParameterIdsByPsIdAndTermId(String table,
			Integer psId, Integer termId);

	public List<String> getNamesByPsId(String table, Integer psId,
			Integer termId);

	public List<WeatherStation> getWeatherStationSummary(
			List<Integer> parameterIds);

	public List getSummaryChart(List parameterIds, String table);

	public List getResult(int start, int limit, String table,
			List<Integer> parameterIds);

	public long getCount(String table, List<Integer> parameterIds);

	public List getChartByParameterId(String parameterId, String table);

	public Integer getParameterIdByName(List<Integer> parameterIds,
			String name, String table);

	public Map getDeviceCount(String table, Integer psId);

	public Map getDeviceList(String table, Integer psId);

	// stop

	public List<PowerMeter> getPowerMeterSummary();

	public List<Failure_alarm> getFailureAlarmByType(String type);
	
	//2015-05 from lm
	public List<PowerMeter> getPMRealTimeData(int ps_id, int period_num, int id);

}
