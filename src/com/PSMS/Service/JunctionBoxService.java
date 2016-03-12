/** * * 
* 文件名称: JunctionBoxService.java *
* 
* 汇流箱设备历史数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-6 上午10:36:44 *
* * @author  min.li
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Hibernate.JunctionBox;

public interface JunctionBoxService {
	/** 
	*汇流箱设备历史数据管理需要的操作函数*
	* @author min.li 
	* @date 2014-1-6 
	*/
	List<JunctionBox> searchHistoryDataByDateAndParameterID(Integer parameter_id,
			String fromRangeDate, String toRangeDate);

}
