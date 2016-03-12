/** * * 
* 文件名称: WeatherStationService.java *
* 
* 气象站设备历史数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-17 下午22:14:26 *
* * @author  jie.yang 
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Hibernate.WeatherStation;

public interface WeatherStationService {
	/** 
	*气象站设备历史数据管理需要的操作函数*
	* @author jie.yang
	* @date 2014-12-17 
	*/ 
	List<WeatherStation> searchHistoryDataByDateAndParameterID(Integer parameter_id,
			String fromRangeDate, String toRangeDate);

	String searchTopFZLByParameter_id(int ws_id);

	String searchTopTempByParameter_id(int ws_id);

	WeatherStation getRealTimeWSDataByParameterID(int p_id);

	Double getZhengDianAccByParaIdAndHour(int ws_parameter_id, int j);

	

}
