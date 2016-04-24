package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IBiIndexDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IBiIndexService;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.BiPowerStationTools;

/**
 * bi首页
 * @author Andy
 *
 */
public class BiIndexServiceImpl implements IBiIndexService {
	
	IBiIndexDao dao=DAOFactory.getBiIndexDaoInstance();

	@Override
	public List<PowerStationBase> getCurrDayQ(String currTime, int psId) {//当日24小时
		List<PowerStationBase> list=dao.getCurrDayQ(currTime, psId);
		return BiPowerStationTools.getListSize24(list);
	}

	@Override
	public PowerStationBase getCurrDayCountQ(String currTime, int psId) {
		return dao.getCurrDayCountQ(currTime, psId);
	}

	@Override
	public List<PowerStationBase> getCurrMonthQ(String currMonth, int psId) {
		List<PowerStationBase> list=dao.getCurrMonthQ(currMonth, psId);
		return BiPowerStationTools.getListMonthOfDays(list);
	}

	@Override
	public PowerStationBase getCurrMonthCountQ(String currMonth, int psId) {
		return dao.getCurrMonthCountQ(currMonth, psId);
	}

	@Override
	public List<PowerStationBase> getCurrYearQ(String currYear, int psId) {
		List<PowerStationBase> list=dao.getCurrYearQ(currYear, psId);
		return BiPowerStationTools.getListYearOfMonths(list);
	}

	@Override
	public PowerStationBase getCurrYearCountQ(String currYear, int psId) {
		return dao.getCurrYearCountQ(currYear, psId);
	}

	@Override
	public PowerStationBase getCurrDashboard(int psId) {
		return dao.getCurrDashboard(psId);
	}

	@Override
	public PowerStationBase getHistoryQAndObligate(int psId) {
		return dao.getHistoryQAndObligate(psId);
	}

}
