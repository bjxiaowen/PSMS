package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.PowerStationBase;

/**
 * 首页bi接口
 * @author Andy
 *
 */
public interface IBiIndexDao {

	/**
	 * 当天日发电量
	 * @param currTime  当前时间
	 * @param psId		电站id
	 * @return
	 */
	public List<PowerStationBase>  getCurrDayQ(String currTime,int psId);
	
	/**
	 * 当天累计发电量
	 * @param currTime  当前时间
	 * @param psId		电站id
	 * @return
	 */
	public PowerStationBase  getCurrDayCountQ(String currTime,int psId);
	
	/**
	 * 当月发电量
	 * @param currMonth 当前月
	 * @param psId 电站id
	 * @return
	 */
	public List<PowerStationBase> getCurrMonthQ(String currMonth,int psId);
	
	/**
	 * 当月累计发电量
	 * @param currMonth 当前月
	 * @param psId 电站id
	 * @return
	 */
	public PowerStationBase getCurrMonthCountQ(String currMonth,int psId);
	
	/**
	 * 当年每月发电量
	 * @param currYear 当前年
	 * @param psId 电站id
	 * @return
	 */
	public List<PowerStationBase> getCurrYearQ(String currYear,int psId);
	
	/**
	 * 当年总发电量
	 * @param currYear 当前年
	 * @param psId 电站id
	 * @return
	 */
	public PowerStationBase getCurrYearCountQ(String currYear,int psId);
	
	/**
	 * 查询电站仪表盘实时数据（最新数据）
	 * @param psId 电站id
	 * @return
	 */
	public PowerStationBase getCurrDashboard(int psId);
	
	
	/**
	 * 历史发电量  减排二氧化碳
	 * @param psId 电站id
	 * @return
	 */
	public PowerStationBase getHistoryQAndObligate(int psId);
}
