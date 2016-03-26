package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.PowerStationBase;

public interface IBiPowerStationDao {

	/**
	 * 通过日期查询获取组件信息
	 * @param dateTime 
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getPowerStationByDate(String dateTime)throws Exception;
	
	/**
	 * 通过日期查询
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getBatteryVoltageByDate(String dateTime)throws Exception;
}
