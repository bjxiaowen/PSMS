package com.PSMS.Service;

import java.util.List;
import java.util.Map;

import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WeatherStation;

public interface MonitoringService {

	// start

	public WeatherStation getWeatherStationSummary(Integer psId, String table,
			Integer termId);

	public List getSummaryChart(Integer psId, String table, String option,
			Integer termId);

	public String getResultJson(int start, int limit, String table,
			Integer psId, Integer termId);

	public List getChartByParameterId(String table, Integer parameterId);

	public String getMonitorParameterIds(Integer psId, String table,
			Integer termId);

	public String getMonitorNames(Integer psId, String table, Integer termId);

	public Map getDeviceCount(Integer psId);

	public List getDeviceList(Integer psId);

	// stop

	public List<String> getFailureMeaning(String table);

	public PowerMeter getPowerMeterSummary();

	public Double getTotalSalary();
	
	// 2015-5 from limin

	public List<PowerMeter> getPMRealTimeData(int ps_id, int period_num, int id);

}
