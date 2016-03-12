/** * * 
* 文件名称: Failure_alarmService.java *
* 
* 故障信息管理，增删改查故障信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-1 下午7:45:33 *
* * @author jie.yang 
*/
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Hibernate.Failure_alarm;


public interface Failure_alarmService {
	/** 
	*故障信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-11-1
	*/ 

	List<Failure_alarm> getAllFailureAndAlarmInfo();//获取所有故障的信息

	boolean addOrUpdateFA(Failure_alarm failure_alarm);//新建或编辑故障

	boolean deleteFA(int id);//删除故障

	boolean checkFAIsExist(String brand, String model, String type,
			String failure_code);//判断故障是否已存在

	List<Failure_alarm> getEquipmentByType(String type);//根骨设备类型查询设备信息

}
