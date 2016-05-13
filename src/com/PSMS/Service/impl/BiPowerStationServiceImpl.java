package com.PSMS.Service.impl;
import java.util.List;

import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.pojo.BIPSBaseData;
import com.PSMS.pojo.InParameter;
import com.PSMS.pojo.PSEquipment;
import com.PSMS.pojo.PSTotal;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.BiPowerStationTools;

public class BiPowerStationServiceImpl implements IBiPowerStationService {
	
	IBiPowerStationDao biDao=DAOFactory.getBiPowerStationDaoInstance();

	@Override
	public PowerStationBase getPowerStationDayByDate(String dateTime,int psId) throws Exception {
		return null;
//		return biDao.getPowerStationDayByDate(dateTime,psId);
	}

	@Override
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime,int psId) throws Exception {
		return null;
//		return BiPowerStationTools.getListSize24(biDao.getPowerStationHourByDate(dateTime,psId));
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

	/**
	 * 电站状态
	 */
	@Override
	public PowerStationBase getPowerStationStatus(String dateTime, int psId) {
		return biDao.getPowerStationStatus(dateTime, psId);
	}

	/**
	 * 电站机器输出状态
	 */
	@Override
	public PowerStationBase getOutputStatus(String dateTime, int psId) throws Exception {
		return biDao.getOutputStatus(dateTime, psId);
	}

	@Override
	public PowerStationBase getNewestStatus(String dateTime, int psId, String type) throws Exception {
		return biDao.getNewestStatus(dateTime, psId, type);
	}

	@Override
	public PowerStationBase getPSOutOneData(String dateTime, int psId, String type) throws Exception {
		return biDao.getPSOutOneData(dateTime, psId, type);
	}

	@Override
	public List<Inverter_parameter> getParameter(int ps_id, String type) {
		return biDao.getParameter(ps_id, type);
	}

	@Override
	public List<PowerStationBase> getPSHourlyData(String dateTime, int psId) throws Exception {
		List<PowerStationBase> list=biDao.getPSHourlyData(dateTime, psId);
		return  BiPowerStationTools.getListSize24(list);
	}

	@Override
	public BIPSBaseData getNewesData(String dateTime, int psId) throws Exception {
		return biDao.getNewesData(dateTime, psId);
	}

	@Override
	public PSTotal getPSTotalData() throws Exception {
		return biDao.getPSTotalData();
	}

	@Override
	public InParameter getInParameter(String dateTime, int psId) throws Exception {
		return biDao.getInParameter(dateTime, psId);
	}

	@Override
	public List<PowerStationBase> getPSHourlyData(String dateTime, int psId, String type) throws Exception {
		List<PowerStationBase> list=biDao.getPSHourlyData(dateTime, psId,type);
		return BiPowerStationTools.getListSize24(list);
	}

	@Override
	public List<PSEquipment> getPSEquipment(int psId) throws Exception {
		return biDao.getPSEquipment(psId);
	}

	
}
