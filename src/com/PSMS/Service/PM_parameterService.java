/** * * 
* 文件名称: PM_parameterService.java *
* 
* 电表信息管理，增删改查电表信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-6 下午1:52:33 *
* * @author jiaojiao.wang 
*/
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Adapter.pm_parameter;
import com.PSMS.Hibernate.PM_parameter;

public interface PM_parameterService {
	/** 
	*电表信息管理需要的操作函数*
	* @author jiaojiao.wang 
	* @date 2014-12-6 
	*/ 

	List<PM_parameter> getAllPM();//获取所有电表的信息

	boolean checkPMNameExistById(String name, int ps_id);//校验电表是否已存在于电站中 

	boolean addPM(PM_parameter i_parameter);//新建电表

	boolean deletePMById(int id);//根据电表id 删除选中电表信息 

	List<PM_parameter> getPMByIts_name(String PM_name);//根据电表名查询设备

	List<PM_parameter> getPMByPs_name(int ps_id);//根据电站id查询电表信息

	List<Integer> getParameterIDByPs_id(int ps_id);//根据电站id查询电表id

	String getParameterNameByParameterID(Integer integer);//根据设备id获得设备名称

	List<PM_parameter> getPMByPsandname(int ps_id, String pM_name);//根据电站名称和设备名称查询电表信息

	List<PM_parameter> getPMByPsId(int ps_id);//根据电站id获得电表信息

	
}
