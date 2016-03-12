/** * * 
* 文件名称: PowerMeterServiceImpl.java *
* 
* 电表历史数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-17 下午19:06:50 *
* * @author  jiaojiao.wang
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Dao.PowerMeterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PowerMeter;

public class PowerMeterServiceImpl implements PowerMeterService{
	/** 
	*电表设备历史数据管理需要的操作函数*
	* @author jiaojiao.wang
	* @date 2014-12-17 
	* @param dao
	*/ 
	PowerMeterDAO dao = DAOFactory.getPowerMeterDAOInstance();
	@Override
	public List<PowerMeter> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		/** 
		* 获取电表设备历史数据*
		* @author jiaojiao.wang
		* @date 2014-12-17 
		*/
		// TODO Auto-generated method stub
		return dao.searchHistoryDataByDateAndParameterID(parameter_id,fromRangeDate,toRangeDate);
				
	}
	@Override
	public String getMaxDateByPsId(int id) {
		// TODO Auto-generated method stub
		return dao.getMaxDateByPsId( id);
	}
	@Override
	public String getMinDateByPsId(int id) {
		// TODO Auto-generated method stub
		return dao.getMinDateByPsId( id);
	}

}
