/** * * 
* 文件名称: Failure_alarmServiceImpl.java *
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

import com.PSMS.Dao.Failure_alarmDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Failure_alarm;

public class Failure_alarmServiceImpl implements Failure_alarmService{
	/** 
	*故障信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-11-1
	*/ 
	Failure_alarmDAO dao = DAOFactory.getFailure_alarmDAOInstance();
	@Override
	public List<Failure_alarm> getAllFailureAndAlarmInfo() {
		// TODO Auto-generated method stub
		/** 
		*获取所有故障的信息*
		* @author jie.yang 
		* @date 2014-11-1
		*/ 
		return dao.getAllFailureAndAlarmInfo();
	}
	@Override
	public boolean addOrUpdateFA(Failure_alarm failure_alarm) {
		// TODO Auto-generated method stub
		/** 
		*新建或编辑故障*
		* @author jie.yang 
		* @date 2014-11-1
		*/ 
		return dao.addOrUpdateFA(failure_alarm);
	}
	@Override
	public boolean deleteFA(int id) {
		// TODO Auto-generated method stub
		/** 
		*删除故障*
		* @author jie.yang 
		* @date 2014-11-1
		*/ 
		return dao.deleteFA(id);
	}
	@Override
	public boolean checkFAIsExist(String brand, String model, String type,
			String failure_code) {
		// TODO Auto-generated method stub
		/** 
		*判断故障是否已存在*
		* @author jie.yang 
		* @date 2014-11-1
		*/ 
		return dao.checkFAIsExist( brand,model, type,failure_code);
	}
	@Override
	public List<Failure_alarm> getEquipmentByType(String type) {
		// TODO Auto-generated method stub
		/** 
		*根骨设备类型查询设备信息*
		* @author jie.yang 
		* @date 2014-11-1
		*/ 
		return dao.getEquipmentByType(type);
	}

}
