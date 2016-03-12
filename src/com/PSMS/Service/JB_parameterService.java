/** * * 
* 文件名称: JB_parameterService.java *
* 
* 汇流箱信息管理，增删改查汇流箱信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-16 下午1:52:33 *
* * @author liu.yang 
*/ 
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Adapter.jb_parameter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;

public interface JB_parameterService {
	/** 
	*汇流箱信息管理需要的操作函数*
	* @author liu.yang 
	* @date 2014-12-16 
	*/ 
	List<JB_parameter> getAllJunction();//获取所有汇流箱的信息

	boolean deleteJunctionById(int id);//根据汇流箱id 删除选中汇流箱信息 
	
	boolean addJunction(JB_parameter i_parameter);//新建汇流箱

	boolean checkJunctionNameExistById(String name, int ps_id);//校验汇流箱 是否已存在于电站中

	List<JB_parameter> getJunctionByIts_name(String junction_name);//根据汇流箱名查询设备
	
	List<JB_parameter> getJunctionByPs_name(int ps_id);//根据电站id查询汇流箱信息

	List<Integer> getParameterIDByPs_id(int ps_id);//根据电站id查询汇流箱id

	String getParameterNameByParameterID(Integer id);//根据设备id获得设备名称

	List<JB_parameter> getJunctionByPsandname(int ps_id, String junction_name);//根据电站名称和设备名称查询汇流箱信息

	List<JB_parameter> getJBByPsId(int ps_id);//根据电站id获得汇流箱信息
}
