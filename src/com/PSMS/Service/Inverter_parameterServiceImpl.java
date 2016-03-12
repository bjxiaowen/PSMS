/** * * 
* 文件名称: Inverter_parameterServiceImpl.java *
* 
* 逆变器信息管理，增删改查逆变器信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-18 下午2:27:40 *
* * @author jiaojiao.wang 
*/ 
package com.PSMS.Service;

import java.util.List;


import com.PSMS.Adapter.inverter_parameter;
import com.PSMS.Dao.Inverter_parameterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Inverter_parameter;

public class Inverter_parameterServiceImpl implements Inverter_parameterService{
	/** 
	*逆变器信息管理需要的操作函数*
	* @author jiaojiao.wang  
	* @date 2014-11-18 
	*/ 
	Inverter_parameterDAO dao = DAOFactory.getInverter_parameterDAOInstance();
	
	public List<Inverter_parameter> getAllInverter(){
		// TODO Auto-generated method stub
		/** 
		*获取所有逆变器的信息*
		* @author jiaojiao.wang  
		* @date 2014-11-18 
		*/ 
		return dao.getAllInverter();
	}

	@Override
	public boolean checkInverterNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*校验 逆变器是否已存在于电站中*
		* @author jiaojiao.wang  
		* @date 2014-11-18 
		*/ 
		return dao.checkInverterNameExistById(name, ps_id);
	}

	@Override
	public boolean addInverter(Inverter_parameter i_parameter) {
		// TODO Auto-generated method stub
		/** 
		*新建逆变器*
		* @author jiaojiao.wang  
		* @date 2014-11-18 
		*/ 
		return dao.addInverter(i_parameter);
	}

	@Override
	public boolean deleteInverterById(int id) {
		// TODO Auto-generated method stub
		/** 
		*根据汇流箱id 删除选中逆变器信息*
		* @author jiaojiao.wang  
		* @date 2014-11-18 
		*/ 
		return dao.deleteInverterById(id);
	}

	@Override
	public List<Inverter_parameter> getInverterByIts_name(String inverter_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据逆变器名查询设备*
		* @author jiaojiao.wang  
		* @date 2014-11-18 
		*/ 
		return dao.getInverterByIts_name(inverter_name);
	}

	@Override
	public List<Inverter_parameter> getInverterByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id查询逆变器信息*
		* @author jiaojiao.wang  
		* @date 2014-11-18 
		*/ 
		return dao.getInverterByPs_name(ps_id);
	}

		@Override
		public List<Integer> getParameterIDByPs_id(int ps_id) {
			// TODO Auto-generated method stub
			/** 
			* 根据电站id查询逆变器id*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/ 
			return dao.getParameterIDByPs_id( ps_id);
		}

		@Override
		public String getParameterNameByParameterID(Integer id) {
			// TODO Auto-generated method stub
			/** 
			* 根据设备id获得设备名称*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/ 
			return dao.getParameterNameByParameterID( id);
		}

		@Override
		public List<Inverter_parameter> getInverterByPsandname(int ps_id,
				String inverter_name) {
			// TODO Auto-generated method stub
			/** 
			* 根据电站名称和设备名称查询逆变器信息*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/ 
			return dao.getInverterByPsandname(ps_id,
					inverter_name);
		}

		@Override
		public List<Inverter_parameter> getInverterByPsId(int ps_id) {
			// TODO Auto-generated method stub
			/** 
			*根据电站id获得逆变器信息*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/ 
			return dao.getInverterByPsId(ps_id);
		}

		@Override
		public List<Integer> getPeriodIndexByPsId(int ps_id) {
			// TODO Auto-generated method stub
			/** 
			* 根据电站id分期*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/ 
			return dao.getPeriodIndexByPsId(ps_id);
		}

		@Override
		public List<String> getInverterNamesByPsId(int ps_id, int period_num) {
			// TODO Auto-generated method stub
			/** 
			* 根据电站id和电站期数查询所有逆变器名称*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/
			return dao. getInverterNamesByPsId(ps_id, period_num);
		}

		@Override
		public Integer getParameterIdByPsId(int ps_id, int period_num,
				String inverter_name) {
			// TODO Auto-generated method stub
			
			/** 
			* 根据电站id和逆变器名称查询对应的parameter_id*
			* @author jiaojiao.wang  
			* @date 2014-11-18 
			*/
			return dao.getParameterIdByPsId( ps_id,  period_num,
					inverter_name);
		}
}
