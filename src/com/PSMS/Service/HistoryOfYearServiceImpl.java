/** * * 
* 文件名称: HistoryOfYearServiceImpl.java *
* 
* 年发电量数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-10 下午10:11:32 *
* * @author  min.li & jie.yang
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Dao.HistoryOfYearDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.HistoryOfYear;

public  class HistoryOfYearServiceImpl implements HistoryOfYearService {
	/** 
	*年发电量数据管理需要的操作函数*
	* @author min.li & jie.yang
	* @date 2014-12-10 
	* @param dao
	*/
	HistoryOfYearDAO dao = DAOFactory.getHistoryOfYearDaoInstance();

	@Override
	public List<HistoryOfYear> getDataByYear(int year) {
		/** 
		* 根据年份获取发电量示例数据年份表中的数据*
		* @author min.li
		* @date 2014-11-17 
		*/
		// TODO Auto-generated method stub
		return dao.getDataByYear(year);
	}

	@Override
	public List<Integer> getAllHistoryYear() {
		/** 
		* 获取年份历史数据表中所有年份*
		* @author min.li
		* @date 2014-11-17 
		*/
		// TODO Auto-generated method stub
		return dao.getAllHistoryYear();
	}

	@Override
	public double getSumPowerOfYear() {
		/** 
		* 统计一年所有电站的发电量*
		* @author jie.yang
		* @date 2014-11-17 
		*/
		// TODO Auto-generated method stub
		return dao.getSumPowerOfYear();
	
	}
	@Override
	public double getPowerByPsIDAndYear(int ps_id, int year) {
		/** 
		* 根据电站ID与年份获得年发电量*
		* @author jie.yang
		* @date 2014-11-27 
		*/
		// TODO Auto-generated method stub
		return dao.getPowerByPsIDAndYear(ps_id,year);
	}

	@Override
	public List<HistoryOfYear> getDataByYearAndPs_id(int year, int ps_id) {
		/** 
		* 获取发电量示例数据年份表中的数据*
		* @author min.li
		* @date 2014-11-27 
		*/
		// TODO Auto-generated method stub
		return dao.getDataByYearAndPs_id(year,ps_id);
	}

	@Override
	public boolean insertYearData(HistoryOfYear yearData) {
		/** 
		* 插入发电量示例数据年份表中的数据*
		* @author jie.yang
		* @date 2014-12-10 
		*/
		// TODO Auto-generated method stub
		return dao.insertYearData(yearData);
	}

	@Override
	public boolean isEmptyByPsId(int id) {
		// TODO Auto-generated method stub
		return dao.isEmptyByPsId( id);
	}

	@Override
	public void clearDataByPsID(int id) {
		// TODO Auto-generated method stub
		dao.clearDataByPsID( id);
	}

	@Override
	public void createDataByYearAndPsId(int year, int id) {
		// TODO Auto-generated method stub
		dao.createDataByYearAndPsId( year,  id);
	}

}
