/** * * 
* 文件名称: HistoryOfDayServiceImpl.java *
* 
* 日发电量数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-08 下午12:10:32 *
* * @author  jiaojiao.wang & min.li & jie.yang
*/
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.HistoryOfDayDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.HistoryOfDay;

public class HistoryOfDayServiceImpl implements HistoryOfDayService{
	/** 
	*日发电量数据管理需要的操作函数*
	* @author jiaojiao.wang & min.li & jie.yang
	* @date 2014-12-08 
	* @param dao
	*/
	HistoryOfDayDAO dao = DAOFactory.getHistoryOfDayDaoInstance();
	@Override
	public List<Integer> getAllHistoryYear() {
		/** 
		*获取日历史数据表中所有年份*
		* @author jiaojiao.wang
		* @date 2014-11-18 
		*/
		// TODO Auto-generated method stub
		return dao.getAllHistoryYear();
	}

	@Override
	public List<Integer> getMonthByPsAndYear(int ps_id, int year) {
		/** 
		*获取日历史数据表中所有月份*
		* @author jiaojiao.wang
		* @date 2014-11-08 
		*/
		// TODO Auto-generated method stub
		return dao.getMonthByPsAndYear(ps_id,year);
	}

	@Override
	public List<HistoryOfDay> getDataByMonth(int ps_id, int year, int month) {
		/** 
		*获取日历史数据表中所有记录*
		* @author jiaojiao.wang
		* @date 2014-11-08 
		*/
		// TODO Auto-generated method stub
		return dao.getDataByMonth(ps_id,year,month);
	}

	
	@Override
	public double getTodayAccPowerByPsID(int ps_id) {
		/** 
		*获取当日累计发电量*
		* @author min.li
		* @date 2014-11-24 
		*/		
		// TODO Auto-generated method stub
		return dao.getTodayAccPowerByPsID(ps_id);
	}

	@Override
	public String getTodayOnLineHourByPsID(int ps_id) {
		/** 
		*获取当日累计上网电量*
		* @author min.li
		* @date 2014-11-24 
		*/	
		// TODO Auto-generated method stub
		return dao.getTodayOnLineHourByPsID(ps_id);
	}


	@Override
	public double getSumPowerOfDay() {
		/** 
		*获取当日所有电站累计发电量*
		* @author min.li
		* @date 2014-11-24 
		*/	
		// TODO Auto-generated method stub
		return dao.getSumPowerOfDay();
	}


	@Override
	public double getSumIradOfDay() {
		/** 
		*获取当日所有电站累计辐射量*
		* @author min.li
		* @date 2014-11-24 
		*/	
		// TODO Auto-generated method stub
		return dao.getSumIradOfDay();
	}


	@Override						
	public List<Double> getPowerDataByYearAndPSid(int year, int ps_id) {
		/** 
		*根据电站ID跟年份获得发电量*
		* @author jie.yang
		* @date 2014-11-30 
		*/			
		// TODO Auto-generated method stub
		return dao.getPowerDataByYearAndPSid(year,ps_id);
	}

	
	@Override
	public List<Double> getIrradiationDataByYearAndPSid(int year, int ps_id) {
		/** 
		*根据电站ID跟年份获得辐射量*
		* @author jie.yang
		* @date 2014-11-30 
		*/
		// TODO Auto-generated method stub
		return dao.getIrradiationDataByYearAndPSid(year,ps_id);
	}
	@Override
	public double getTodayAccIradByPsID(int ps_id) {
		/** 
		*获取当日累计辐射量*
		* @author jie.yang
		* @date 2014-12-8 
		*/
		// TODO Auto-generated method stub
		return dao.getTodayAccIradByPsID(ps_id);
	}

	@Override
	public boolean insertDayData(HistoryOfDay dayData) {
		/** 
		*插入日发电量记录*
		* @author jie.yang
		* @date 2014-12-1 
		*/
		// TODO Auto-generated method stub
		return dao.insertDayData(dayData);
	}

	@Override
	public double createDayAccPowerByPSIdAndDate(String date, int id) {
		// TODO Auto-generated method stub
		return dao.createDayAccPowerByPSIdAndDate( date,  id);
	}

	@Override
	public String getMaxDateByPsId(int id) {
		// TODO Auto-generated method stub
		return dao.getMaxDateByPsId(id);
	}

	@Override
	public void clearData(int id) {
		// TODO Auto-generated method stub
		dao.clearData(id);
	}

	@Override
	public String getMaxMonthTimeByPSId(int id) {
		// TODO Auto-generated method stub
		return dao.getMaxMonthTimeByPSId( id);
	}

	@Override
	public String getMinMonthTimeByPSId(int id) {
		// TODO Auto-generated method stub
		return dao.getMinMonthTimeByPSId( id);
	}

	@Override
	public List<Integer> getAllHistoryMonth() {
		// TODO Auto-generated method stub
		return dao.getAllHistoryMonth();
	}
}
