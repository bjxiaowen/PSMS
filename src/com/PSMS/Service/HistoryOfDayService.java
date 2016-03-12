/** * * 
* 文件名称: HistoryOfDayService.java *
* 
* 日发电量数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-08 下午12:10:32 *
* * @author  jiaojiao.wang & jie.yang
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Hibernate.HistoryOfDay;

public interface HistoryOfDayService {
	/** 
	*日发电量数据管理需要的操作函数*
	* @author jiaojiao.wang & jie.yang
	* @date 2014-12-08 
	*/ 
	List<Integer> getAllHistoryYear();//获取日历史数据表中所有年份

	List<Integer> getMonthByPsAndYear(int ps_id, int year);

	List<HistoryOfDay> getDataByMonth(int ps_id, int year, int month);
			
	double getTodayAccPowerByPsID(int ps_id);//获取当日累计发电量

	String getTodayOnLineHourByPsID(int ps_id);//获取当日累计上网电量

	double getSumPowerOfDay();//获取当日所有电站累计发电量

	double getSumIradOfDay();//获取当日所有电站累计辐射量

	List<Double> getPowerDataByYearAndPSid(int year, int ps_id);// YJ根据电站ID跟年份获得发电量，便于电量统计 

	List<Double> getIrradiationDataByYearAndPSid(int year, int ps_id);// YJ根据电站ID跟年份获得辐射量，便于辐射量天数统计 	

	double getTodayAccIradByPsID(int ps_id);//获取当日累计辐射量

	boolean insertDayData(HistoryOfDay dayData);

	double createDayAccPowerByPSIdAndDate(String date, int id);

	String getMaxDateByPsId(int id);

	void clearData(int id);

	String getMaxMonthTimeByPSId(int id);

	String getMinMonthTimeByPSId(int id);

	List<Integer> getAllHistoryMonth();
}
