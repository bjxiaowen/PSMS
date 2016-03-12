/** * * 
* 文件名称: PowerMeterService.java *
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

import com.PSMS.Hibernate.PowerMeter;

public interface PowerMeterService {
	/** 
	*电表设备历史数据管理需要的操作函数*
	* @author jiaojiao.wang
	* @date 2014-12-17 
	*/ 
	List<PowerMeter> searchHistoryDataByDateAndParameterID(Integer parameter_id,
			String fromRangeDate, String toRangeDate);

	String getMaxDateByPsId(int id);

	String getMinDateByPsId(int id);

}
