/** * * 
* 文件名称: WS_parameterDAO.java *
* 
* 气象站信息管理，增删改查气象站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-1 下午2:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.WS_parameter;

public interface WS_parameterDAO {
	/** 
	* 气象站信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-12-1 
	*/ 

	List<WS_parameter> getAllWS();	//获取所有气象站的信息
	boolean checkWSNameExistById(String name, int ps_id);//校验气象站 是否已存在于电站中

	boolean addWS(WS_parameter w_parameter);//新建气象站

	boolean deleteWSById(int id);//根据气象站id 删除选中气象站信息 

	List<WS_parameter> getWSByIts_name(String ws_name);//根据气象站名查询设备

	List<WS_parameter> getWSByPs_name(int ps_id);//根据电站id查询气象站信息

	List<Integer> getParameterIDByPs_id(int ps_id);//根据电站id查询气象站id

	String getNameById(int ws_id);//根据设备id获得设备名称

	String getParameterNameByParameterID(Integer integer);//根据设备id获得设备信息

	List<WS_parameter> getWSByPsandname(int ps_id, String wS_name);//根据电站名称和设备名称查询气象站信息

	List<WS_parameter> getWSByPsId(int ps_id);//根据电站id获得气象站信息

	List<WS_parameter> getWSByPsIdAndPeriod(int ps_id, int period);//根据电站名称和分期获得气象站

}
