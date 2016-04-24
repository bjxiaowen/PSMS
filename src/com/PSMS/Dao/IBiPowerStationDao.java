package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.PowerStationBase;

public interface IBiPowerStationDao {
	
	
	/**
	 * 通过日期和电站id查询电站状态
	 * @param dateTime
	 * @param psId
	 * @return
	 */
	public PowerStationBase  getPowerStationStatus(String dateTime,int psId);
	
	


	/**
	 * 蓄电池通过日期查询1天数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getBatteryVoltageDayByDate(String dateTime,int psId)throws Exception;
	
	
	/**
	 * 蓄电池通过日期查询24小时
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
	 * 控制器一天的发电量
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getControlInShowDayByDate(String dateTime,int psId)throws Exception;
	
	/**
	 *控制器按小时来查询
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getControlInShowHourByDate(String dateTime,int psId)throws Exception;
	
	/**
	 * 每一天的发电量
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase>  getElectricEveryDayByDate(int psId)throws Exception;
	
	/**
	 * 查询电站最新输出状态
	 * @param dateTime
	 * @param psId
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getOutputStatus(String dateTime,int psId)throws Exception;
	
	
	
}
