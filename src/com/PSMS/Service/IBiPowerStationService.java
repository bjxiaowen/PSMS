package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.PowerStationBase;

public interface IBiPowerStationService {
	
	/**
	 * 通过日期和电站id查询电站状态
	 * @param dateTime
	 * @param psId
	 * @return
	 */
	public PowerStationBase  getPowerStationStatus(String dateTime,int psId);

	
	/**
	 * 组件获取一天的总数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getPowerStationDayByDate(String dateTime,int psId)throws Exception;
	

	/**
	 * 组件通过日期查询获取组件信息
	 * @param dateTime 
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime,int psId)throws Exception;
	

	/**
	 * 蓄电池通过日期查询1天数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getBatteryVoltageDayByDate(String dateTime,int psId)throws Exception;
	
	
	/**
	 * 蓄电池通过日期查询
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getBatteryVoltageHourByDate(String dateTime,int psId)throws Exception;
	
	/**
	 * 控制器一天的数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getControlPhotovoltaicDayByDate(String dateTime,int psId)throws Exception;
	
	
	/**
	 * 控制器每小时的数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getControlPhotovoltaicHourByDate(String dateTime,int psId)throws Exception;
	
	
	
	/**
	 * 控制器输出显示一天的数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getControlOutShowDayByDate(String dateTime,int psId)throws Exception;
	
	/**
	 * 控制器输出显示每小时的数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getControlOutShowHourByDate(String dateTime,int psId)throws Exception;
	
	/**
	 * 一天的发电量
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getElectricDayByDate(String dateTime,int psId)throws Exception;
	
	/**
	 * 按小时来查询
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getElectricHourByDate(String dateTime,int psId)throws Exception;
	
	/**
	 * 每一天的发电量
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase>  getElectricEveryDayByDate(int psId)throws Exception;
	
	
}
