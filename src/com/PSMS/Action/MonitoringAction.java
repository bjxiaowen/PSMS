package com.PSMS.Action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.PSMS.Dao.MonitoringDAOImpl;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WeatherStation;
import com.PSMS.Service.MonitoringService;
import com.PSMS.Service.MonitoringServiceImpl;
import com.PSMS.util.HttpHelper;
import com.PSMS.util.JsonHelper;
import com.PSMS.util.Paging;
import com.PSMS.util.UnitHelper;
import com.opensymphony.xwork2.Action;

public class MonitoringAction implements Action {

	MonitoringService monitoringService;

	public MonitoringService getMonitoringService() {
		return monitoringService;
	}

	public void setMonitoringService(MonitoringService monitoringService) {
		this.monitoringService = monitoringService;
	}

	/* @brief getWeatherStationSummary */
	public void getWeatherStationSummary() {
		HttpServletRequest request = HttpHelper.requestFactory();
		String psId = request.getParameter("ps_id");
		String termId = request.getParameter("term_id");
		WeatherStation weatherStation = monitoringService
				.getWeatherStationSummary(Integer.parseInt(psId),
						"WeatherStation", Integer.parseInt(termId));
		String result = JsonHelper.resolveCycle(weatherStation);
		result = JsonHelper.appendCountAndRoot(1L, result);
		HttpHelper.print(result);
	}

	/* @brief getSummaryChart */
	public void getSummaryChart() {
		HttpServletRequest request = HttpHelper.requestFactory();
		String deviceName = request.getParameter("device_name");
		String psId = request.getParameter("ps_id");
		String option = request.getParameter("option");
		String termId = request.getParameter("term_id");
		List list = monitoringService.getSummaryChart(Integer.parseInt(psId),
				deviceName, option, Integer.parseInt(termId));
		String result = JsonHelper.resolveCycle(list);
		HttpHelper.print(result);
	}

	/* @brief getMonitorGrid for Ext Grid-paging */
	public void getMonitorGrid() {

		HttpServletRequest request = HttpHelper.requestFactory();
		/* @brief the index of the first record in current page */
		/* @return auto return */
		int start = Paging.getStart(request.getParameter("start"));
		String psId = request.getParameter("ps_id");
		String deviceName = request.getParameter("device_name");
		String termId = request.getParameter("term_id");

		MonitoringServiceImpl monitoringServiceImpl = new MonitoringServiceImpl();
		String result = monitoringServiceImpl.getResultJson(start,
				Paging.PAGING_LIMIT, deviceName, Integer.parseInt(psId),
				Integer.parseInt(termId));
		HttpHelper.print(result);
	}

	/* @brief setBatchDelete */
	public void setBatchDelete() {

		HttpServletRequest request = HttpHelper.requestFactory();
		String idsString = request.getParameter("ids");
		System.out.println(idsString);
		// set batch delete when empty grid,prevent DAO IN(empty)
		if (idsString.equals("[]")) {
			return;
		}
		String table = request.getParameter("table");

		List idsList = JsonHelper.transformJsonToList(idsString);

		MonitoringDAOImpl eadi = (MonitoringDAOImpl) DAOFactory
				.getMonitoringDaoInstance();
		eadi.setBatchDelete(idsList, table);
	}

	/* @brief setAllDelete */
	public void setAllDeleteOrRecover() {

		HttpServletRequest request = HttpHelper.requestFactory();
		String table = request.getParameter("table");
		Integer option = Integer.parseInt(request.getParameter("option"));

		MonitoringDAOImpl eadi = (MonitoringDAOImpl) DAOFactory
				.getMonitoringDaoInstance();
		eadi.setAllDeleteOrRecover(table, option);
	}

	/* @brief getMonitorChart */
	public void getMonitorChart() {

		HttpServletRequest request = HttpHelper.requestFactory();
		String parameterId = request.getParameter("parameter_id");
		String deviceName = request.getParameter("device_name");

		List list = monitoringService.getChartByParameterId(deviceName, Integer
				.parseInt(parameterId));

		String result = JsonHelper.resolveCycle(list);
		HttpHelper.print(result);
	}

	/* @brief getMonitorParameterIds */
	public void getMonitorParameterIds() {

		HttpServletRequest request = HttpHelper.requestFactory();
		String psId = request.getParameter("ps_id");
		String deviceName = request.getParameter("device_name");
		String termId = request.getParameter("term_id");

		String result = monitoringService.getMonitorParameterIds(Integer
				.parseInt(psId), deviceName, Integer.parseInt(termId));

		HttpHelper.print(result);
	}

	/* @brief getMonitorNames */
	public void getMonitorNames() {

		HttpServletRequest request = HttpHelper.requestFactory();
		String psId = request.getParameter("ps_id");
		String deviceName = request.getParameter("device_name");
		String termId = request.getParameter("term_id");
		String result = monitoringService.getMonitorNames(Integer
				.parseInt(psId), deviceName, Integer.parseInt(termId));

		HttpHelper.print(result);
	}

	public void getDeviceList() {
		HttpServletRequest request = HttpHelper.requestFactory();
		String psId = request.getParameter("ps_id");
		List list = monitoringService.getDeviceList(Integer.parseInt(psId));
		String result = JsonHelper.resolveCycle(list);
		HttpHelper.print(result);
	}

	/* @brief getAccPwer */
	public void getAccPowerSummary() {
		PowerMeter powerMeter = monitoringService.getPowerMeterSummary();
		String result = UnitHelper
				.fixedKWh(powerMeter.getAccPower().toString());
		HttpHelper.print(result);
	}

	/* @brief getTotalSalary */
	public void getTotalSalary() {
		Double totalSalary = monitoringService.getTotalSalary();
		String result = UnitHelper.fixedTenThousand(totalSalary.toString());
		HttpHelper.print(result);
	}

	public void getFailureMeaning() {
		HttpServletRequest request = HttpHelper.requestFactory();
		String deviceName = request.getParameter("device_name");
		List<String> list = monitoringService.getFailureMeaning(deviceName);
		String arrayJson = JsonHelper.createArrayJsonForExtFilter(list);
		HttpHelper.print(arrayJson.toString());
	}

	/* @brief when you doesn't choose a method */
	public String execute() throws Exception {
		return null;
	}
}
