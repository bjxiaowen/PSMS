package com.PSMS.Service;
/** * * 
* 文件名称: WeatherStationServiceImpl.java *
* 
* 气象站设备历史数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-17 下午22:14:26 *
* * @author  jie.yang & min.li
*/
import java.util.List;

import com.PSMS.Dao.WeatherStationDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.WeatherStation;

public class WeatherStationServiceImpl implements WeatherStationService{
	/** 
	*气象站设备历史数据管理需要的操作函数*
	* @author jie.yang & min.li
	* @date 2014-12-17 
	* @param dao
	*/ 
	WeatherStationDAO dao = DAOFactory.getWeatherStationDAOInstance();
	@Override
	public List<WeatherStation> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		/** 
		* 获取气象站设备历史数据*
		* @author jie.yang
		* @date 2014-12-15 
		*/
		// TODO Auto-generated method stub
		return dao.searchHistoryDataByDateAndParameterID(parameter_id, fromRangeDate, toRangeDate) ;
				
	}
	@Override
	public String searchTopFZLByParameter_id(int ws_id) {
		/** 
		* 根据parameter_id查询最新的气象站辐射值*
		* @author min.li
		* @date 2014-12-17 
		*/
		// TODO Auto-generated method stub
		return dao.searchTopFZLByParameter_id(ws_id);
	}
	@Override
	public String searchTopTempByParameter_id(int ws_id) {
		/** 
		* 根据parameter_id查询最新的气象站温度*
		* @author min.li
		* @date 2014-12-17 
		*/
		// TODO Auto-generated method stub
		return dao.searchTopTempByParameter_id(ws_id);
	}
	@Override
	public WeatherStation getRealTimeWSDataByParameterID(int p_id) {
		/** 
		* 根据parameter_id查询最新的气象站信息*
		* @author min.li
		* @date 2014-12-17 
		*/
		// TODO Auto-generated method stub
		return dao.getRealTimeWSDataByParameterID(p_id);
	}
	@Override
	public Double getZhengDianAccByParaIdAndHour(int ws_parameter_id, int j) {
		// TODO Auto-generated method stub
		return dao.getZhengDianAccByParaIdAndHour(ws_parameter_id,  j);
	}
	

}
