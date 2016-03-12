/** * * 
* 文件名称: PS_informationServiceImpl.java *
* 
* 电站信息管理，增删改查电站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-11 下午3:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Service;

import java.util.List;
import java.util.Date;

import com.PSMS.Dao.PS_informationDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PS_information;

public class PS_informationServiceImpl implements PS_informationService {
	/** 
	*电站信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-10-11 
	*/ 
	PS_informationDAO dao = DAOFactory.getPS_informationDaoInstance();

	@Override
	public List<String> getAllStationLongitude() {
		// TODO Auto-generated method stub
		/** 
		*获取所有电站的经度*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getAllStationLongitude();
	}

	@Override
	public List<String> getAllStationLatitude() {
		// TODO Auto-generated method stub
		/** 
		*获取所有电站的纬度*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getAllStationLatitude();
	}

	@Override
	public List<String> getAllStationName() {
		// TODO Auto-generated method stub
		/** 
		*获取所有电站的名称*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getAllStationName();
	}

	@Override
	public String getStationNameById(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*通过电站id获取电站名称*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getStationNameById(ps_id);
	}

	@Override
	public int getPS_idByName(String ps_name) {
		// TODO Auto-generated method stub
		/** 
		*通过电站名称获取id*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getPS_idByName(ps_name);
	}

	@Override
	public List<PS_information> getAllStation() {
		// TODO Auto-generated method stub
		/** 
		*获取所有有效电站*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getAllStation();
	}

	@Override
	public boolean addPs(PS_information ps) {
		// TODO Auto-generated method stub
		/** 
		*增加电站*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.addPs(ps);
	}

	@Override
	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub
		/** 
		*通过电站id删除电站*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.deleteUserById(id);
	}

	@Override
	public boolean updatePS(int ps_id, String ps_name, String ps_capacity,
			String ps_area, Integer ps_part_num, String ps_owner,
			String ps_investor, String ps_province, String ps_longitude,
			String ps_latitude, String ps_build_time) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id更新电站信息*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.updatePS( ps_id, ps_name,ps_capacity,ps_area,  ps_part_num,  ps_owner,ps_investor,  ps_province, ps_longitude,ps_latitude,  ps_build_time);
	}

	@Override
	public List<PS_information> getStationByName(String ps_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站名称获得电站列表*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getStationByName(ps_name);
	}

	@Override
	public List<PS_information> getStationByProvince(String ps_province) {
		// TODO Auto-generated method stub
		/** 
		*根据电站省份获得电站信息*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao. getStationByProvince(ps_province);
	}

	@Override
	public List<PS_information> getStationByTime(String date1, String date2) {
		// TODO Auto-generated method stub
		/** 
		*根据电站建站时间获得电站信息*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getStationByTime(date1, date2) ;
	}

	@Override
	public boolean updatePs(PS_information ps) {
		// TODO Auto-generated method stub
		/** 
		*编辑电站后保存信息*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.updatePs(ps);
	}

	@Override
	public boolean checkPsNameIsExist(String name) {
		// TODO Auto-generated method stub
		/** 
		*判断电站是否已存在*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.checkPsNameIsExist( name);
	}

	@Override
	public int getPSNum() {
		// TODO Auto-generated method stub
		/** 
		*获取电站数量*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getPSNum();
	}

	@Override            
	public String getCapacityById(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据ID获得电站的装机容量，即理论发电量 *
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getCapacityById(ps_id);
	}

	@Override
	public List<PS_information> getStationByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id获得电站*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getStationByPsId(ps_id);
	}

	@Override
	public int getUser_numByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id获得用户数目*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getUser_numByPsId(ps_id);
	}

	@Override
	public PS_information getPSByID(int id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id获得电站*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getPSByID(id);
	}

	@Override
	public int getDeleteFlagByPSName(String name) {
		// TODO Auto-generated method stub
		/** 
		*查询电站是否删除*
		* @author jie.yang 
		* @date 2014-10-11 
		*/ 
		return dao.getDeleteFlagByPSName(name);
	}
	
}
