package com.PSMS.Service.impl;
import java.util.List;

import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.BiPowerStationTools;

public class BiPowerStationServiceImpl implements IBiPowerStationService {
	
	IBiPowerStationDao biDao=DAOFactory.getBiPowerStationDaoInstance();

	@Override
	public PowerStationBase getPowerStationDayByDate(String dateTime,int psId) throws Exception {
		return biDao.getPowerStationDayByDate(dateTime,psId);
	}

	@Override
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime,int psId) throws Exception {
		return BiPowerStationTools.getListSize24(biDao.getPowerStationHourByDate(dateTime,psId));
	}

	@Override
	public PowerStationBase getBatteryVoltageDayByDate(String dateTime,int psId) throws Exception {
		return biDao.getBatteryVoltageDayByDate(dateTime, psId);
	}

	@Override
	public List<PowerStationBase> getBatteryVoltageHourByDate(String dateTime,int psId) throws Exception {
		return BiPowerStationTools.getListSize24(biDao.getBatteryVoltageHourByDate(dateTime, psId));
	}

	@Override
	public PowerStationBase getControlPhotovoltaicDayByDate(String dateTime,int psId) throws Exception {
		return biDao.getControlPhotovoltaicDayByDate(dateTime, psId);
	}

	@Override
	public List<PowerStationBase> getControlPhotovoltaicHourByDate(String dateTime,int psId) throws Exception {
		return BiPowerStationTools.getListSize24(biDao.getControlPhotovoltaicHourByDate(dateTime, psId));
	}

	@Override
	public PowerStationBase getControlOutShowDayByDate(String dateTime,int psId) throws Exception {
		return biDao.getControlOutShowDayByDate(dateTime, psId);
	}

	@Override
	public List<PowerStationBase> getControlOutShowHourByDate(String dateTime,int psId) throws Exception {
		return BiPowerStationTools.getListSize24(biDao.getControlOutShowHourByDate(dateTime, psId));
	}

	@Override
	public PowerStationBase getControlInShowDayByDate(String dateTime,int psId) throws Exception {
		return biDao.getControlInShowDayByDate(dateTime, psId);
	}

	@Override
	public List<PowerStationBase> getControlInShowHourByDate(String dateTime,int psId) throws Exception {
		return BiPowerStationTools.getListSize24(biDao.getControlInShowHourByDate(dateTime, psId));
	}

	@Override
	public List<PowerStationBase> getElectricEveryDayByDate(int psId) throws Exception {
		return BiPowerStationTools.getListSize24(biDao.getElectricEveryDayByDate( psId));
	}

	@Override
	public PowerStationBase getPowerStationStatus(String dateTime, int psId) {
		return biDao.getPowerStationStatus(dateTime, psId);
	}

}
