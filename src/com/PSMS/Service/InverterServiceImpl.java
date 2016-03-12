/** * * 
* 文件名称: InverterServiceImpl.java *
* 
* 逆变器设备历史数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-15 下午20:06:30 *
* * @author  min.li 
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Dao.InverterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Inverter;

public class InverterServiceImpl implements InverterService{
	/** 
	*逆变器设备历史数据管理需要的操作函数*
	* @author min.li
	* @date 2014-12-15 
	* @param dao
	*/ 
	InverterDAO dao = DAOFactory.getInverterDAOInstance();

	@Override
	public List<Inverter> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		/** 
		* 获取逆变器设备历史数据*
		* @author min.li
		* @date 2014-12-15 
		*/
		// TODO Auto-generated method stub
		return dao.searchHistoryDataByDateAndParameterID(
				 parameter_id,  fromRangeDate,  toRangeDate) ;
	}

	@Override
	public String searchTopPowerByParameter_id(int parameter_id) {
		/** 
		* 根据parameter_id查询最新的逆变器数据*
		* @author min.li
		* @date 2014-11-20 
		*/
		// TODO Auto-generated method stub
		return dao.searchTopPowerByParameter_id(parameter_id);
	}

	@Override
	public String searchTopStateByParameter_id(int parameter_id) {
		/** 
		* 根据parameter_id查询最新的逆变器状态*
		* @author min.li
		* @date 2014-11-20 
		*/
		// TODO Auto-generated method stub
		return dao.searchTopStateByParameter_id(parameter_id);
	}

	@Override
	public List<Inverter> getTopRealTimeData(Integer parameter_id) {
		// TODO Auto-generated method stub
		return dao.getTopRealTimeData( parameter_id);
	}

	@Override
	public Double getZhengDianPowerByParaIdAndHour(Integer parameter_id, int h) {
		// TODO Auto-generated method stub
		return dao.getZhengDianPowerByParaIdAndHour(parameter_id,  h);
	}
	
	

}
