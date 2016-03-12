/** * * 
* 文件名称: HistoryOfFailureService.java *
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

import com.PSMS.Hibernate.HistoryOfFailure;

public interface HistoryOfFailureService {
	/** 
	* 设备故障数据管理*
	* @author min.li
	* @date 2014-1-16 
	*/ 
	List<HistoryOfFailure> searchFailureHistoryData(String ps_name,
			String device_type, String fromRangeDate, String toRangeDate);
	boolean geAllFailureInformation();
	boolean deleteAllData();
	boolean geAllFailureInformationJB();
}
