/** * * 
* 文件名称: Inverter_parameterDAO.java *
* 
* 逆变器信息管理，增删改查逆变器信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-18 下午2:27:40 *
* * @author jiaojiao.wang 
*/ 
package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Adapter.inverter_parameter;
import com.PSMS.Hibernate.Inverter_parameter;

/**
 * 逆变器信息管理需要的操作函数*
 * 
 * @author andy
 * @date 2016-05-01
 */
public interface Inverter_parameterDAO {

	List<Inverter_parameter> getInverterByItName(String name);// 根据设备id查询设备
																// 因为名称中存贮的是设备id

	List<Inverter_parameter> getAllInverter();// 获取所有逆变器的信息

	boolean checkInverterNameExistById(String name, int ps_id);// 校验
																// 逆变器是否已存在于电站中

	boolean addInverter(Inverter_parameter i_parameter);// 新建逆变器

	boolean deleteInverterById(int id);// 根据汇流箱id 删除选中逆变器信息

	List<Inverter_parameter> getInverterByIts_name(String inverter_name);// 根据逆变器名查询设备

	List<Inverter_parameter> getInverterByPs_name(int ps_id);// 根据电站id查询逆变器信息

	List<Integer> getParameterIDByPs_id(int ps_id);// 根据电站id查询逆变器id

	String getParameterNameByParameterID(Integer id);// 根据设备id获得设备名称

	List<Inverter_parameter> getInverterByPsandname(int ps_id, String inverter_name);// 根据电站名称和设备名称查询逆变器信息

	List<Inverter_parameter> getInverterByPsId(int ps_id);// 根据电站id获得逆变器信息

	List<Integer> getPeriodIndexByPsId(int ps_id);// 根据电站id分期

	List<String> getInverterNamesByPsId(int ps_id, int period_num);// 根据电站id和电站期数查询所有逆变器名称

	Integer getParameterIdByPsId(int ps_id, int period_num, String inverter_name);// 根据电站和逆变器名称查询对应的parameterid

	public List<Inverter_parameter> getParameter(int ps_id,String type);//通过电站和类型查询设备信息
}
