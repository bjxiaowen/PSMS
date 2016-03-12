/** * * 
* 文件名称: HistoryOfFailureServiceImpl.java *
* 
* 设备故障数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-16 下午6:10:9 *
* * @author  min.li
*/
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.HistoryOfFailureDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.HistoryOfFailure;

public class HistoryOfFailureServiceImpl implements HistoryOfFailureService{
	/** 
	* 设备故障数据管理*
	* @author min.li
	* @date 2014-1-16 
	* @param dao
	*/ 
	HistoryOfFailureDAO dao = DAOFactory.getHistoryOfFailureDaoInstance();
	@Override
	public List<HistoryOfFailure> searchFailureHistoryData(String ps_name,
			String device_type, String fromRangeDate, String toRangeDate) {
		/** 
		*获取设备故障数据*
		* @author min.li
		* @date 2014-12-18 
		*/
		// TODO Auto-generated method stub
		return dao.searchFailureHistoryData( ps_name,
				 device_type,  fromRangeDate, toRangeDate);
	}
	@Override
	public boolean geAllFailureInformation() {
		/** 
		*获取逆变器设备故障数据*
		* @author min.li
		* @date 2014-12-18 
		*/
		// TODO Auto-generated method stub
		return dao.getAllFailureInformation();
	}
	@Override
	public boolean deleteAllData() {
		/** 
		*删除设备故障数据*
		* @author min.li
		* @date 2014-1-18 
		*/
		// TODO Auto-generated method stub
		return dao.deleteAllData();
	}
	
	@Override
	public boolean geAllFailureInformationJB() {
		/** 
		*获取逆变器设备故障数据*
		* @author min.li
		* @date 2014-12-18 
		*/
		// TODO Auto-generated method stub
		return dao.getAllFailureInformationJB();
	}

}
