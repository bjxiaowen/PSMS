/** * * 
* 文件名称: PS_informationDAO.java *
* 
* 电站信息管理，增删改查电站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-11 下午3:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.PS_information;

import java.util.Date;
public interface PS_informationDAO {
	/** 
	*电站信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-10-11 
	*/ 
	public PS_information getInformation() ;//获取所有电站
	
	public List<PS_information> getAllInformation();//获取所有电站信息
	
	public boolean findPsByName(String psName);
	
	public List<String> getAllStationLongitude();//获取所有电站的经度
	
	public List<String> getAllStationLatitude();//获取所有电站的纬度
	
	public List<String> getAllStationName();//获取所有电站的名称
	
	public String getStationNameById(int ps_id);//通过电站id获取电站名称
	
	public PS_information getById(int ps_id);//通过电站id获取电站名称
	
	public int getPS_idByName(String ps_name);//通过电站名称获取id
	
	
	public List<PS_information> getAllStation();//获取所有有效电站
	
	public boolean addPs(PS_information ps);//增加电站
	
	public boolean deleteUserById(int id);//通过电站id删除电站
	
	public boolean updatePS(int ps_id, String ps_name, String ps_capacity,
			String ps_area, Integer ps_part_num, String ps_owner,
			String ps_investor, String ps_province, String ps_longitude,
			String ps_latitude, String ps_build_time);//根据电站id更新电站信息
	
	public List<PS_information> getStationByName(String ps_name); //根据电站名称获得电站列表
	
	public List<PS_information> getStationByProvince(String ps_province);//根据电站省份获得电站信息
	
	public List<PS_information> getStationByTime(String date1, String date2);//根据电站建站时间获得电站信息
	
	public boolean updatePs(PS_information ps);//编辑电站后保存信息
	
	public boolean checkPsNameIsExist(String name);//判断电站是否已存在
	
	public int getPSNum();//获取电站数量	
	
	public String getCapacityById(int ps_id);//根据ID获得电站的装机容量，即理论发电量 
	
	public List<PS_information> getStationByPsId(int ps_id);//根据电站id获得电站
	
	public int getUser_numByPsId(int ps_id);//根据电站id获得用户数目
	
	public PS_information getPSByID(int id);//根据电站id获得电站
	
	public int getDeleteFlagByPSName(String name);//查询电站是否删除

}
