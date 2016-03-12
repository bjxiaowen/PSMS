/** * * 
* 文件名称: InverterService.java *
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

import com.PSMS.Hibernate.Inverter;

public interface InverterService {
	/** 
	*逆变器设备历史数据管理需要的操作函数*
	* @author min.li
	* @date 2014-12-15 
	*/ 
	List<Inverter> searchHistoryDataByDateAndParameterID(Integer parameter_id,String fromRangeDate, String toRangeDate);
	String searchTopPowerByParameter_id(int parameter_id);//20141120根据parameter_id查询最新的逆变器数据
	String searchTopStateByParameter_id(int parameter_id);
	List<Inverter> getTopRealTimeData(Integer parameter_id);
	Double getZhengDianPowerByParaIdAndHour(Integer parameter_id, int h);	
}
