/** * * 
* 文件名称: PS_periodService.java *
* 
* 电站分期管理，增删改电站分期信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-12 下午9:46:33 *
* * @author jie.yang 
*/
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Hibernate.PS_period;

public interface PS_periodService {
	/** 
	* 电站分期管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-12-12 
	*/ 

	boolean addPS_period(PS_period ps_period);//新建电站分期

	List<PS_period> getPSPeriodDataByPSID(int ps_id);//根据电站获取电站分期数据

	List<PS_period> getPsPeriodInformation();//获取电站分期信息

	boolean editPeriodPartByID(int pID, String period_capacity, Integer unit_num,
			String period_time);//编辑电站分期

	boolean deletePeriodByPid(int p_id);//删除电站分期

	int getPeriodNumByPsId(int ps_id);//根据电站id获取电站分期数目

	Double getTotalCapacityByPsID(int ps_id);//根据电站id获取电站分期总容量



}
