/** * * 
* 文件名称: HistoryOfMonthServiceImpl.java *
* 
* 月发电量数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-10 下午11:09:32 *
* * @author  min.li & jiaojiao.wang
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Dao.HistoryOfMonthDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.HistoryOfMonth;

public class HistoryOfMonthServiceImpl implements HistoryOfMonthService{
	/** 
	*月发电量数据管理需要的操作函数*
	* @author  min.li & jiaojiao.wang
	* @date 2014-12-10 
	* @param dao
	*/ 
	HistoryOfMonthDAO dao = DAOFactory.getHistoryOfMonthDaoInstance();
	@Override
	public List<HistoryOfMonth> getDataByPs_idAndYear(int ps_id, int year) {
		/** 
		* 获取发电量示例数据月份表中的数据*
		* @author min.li
		* @date 2014-12-1 
		*/
		// TODO Auto-generated method stub
		return dao.getDataByPs_idAndYear(ps_id, year);
	}
	@Override
	public List<Integer> getAllHistoryYear() {
		/** 
		* 获取月份历史数据表中所有年份*
		* @author jiaojiao.wang
		* @date 2014-12-1 
		*/
		// TODO Auto-generated method stub
		return dao.getAllHistoryYear();
	}
	@Override
	public boolean insertMonthData(HistoryOfMonth monthData) {
		/** 
		* 插入发电量示例数据月份表中的数据*
		* @author jiaojiao.wang
		* @date 2014-12-10
		*/
		// TODO Auto-generated method stub
		return dao.insertMonthData(monthData);
	}
	@Override
	public void clearDataByPsID(int id) {
		// TODO Auto-generated method stub
		dao.clearDataByPsID(id);
	}
	@Override
	public void createDataByMonthAndPsId(String str, int id) {
		// TODO Auto-generated method stub
		dao.createDataByMonthAndPsId(str, id);
	}
	@Override
	public boolean isEmptyByPsId(int id) {
		// TODO Auto-generated method stub
		return dao.isEmptyByPsId(id);
	}

}
