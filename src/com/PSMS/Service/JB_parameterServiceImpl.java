/** * * 
* 文件名称: JB_parameterServiceImpl.java *
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
import com.PSMS.Dao.Inverter_parameterDAO;
import com.PSMS.Dao.JB_parameterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.JB_parameter;

public class JB_parameterServiceImpl implements JB_parameterService{
	JB_parameterDAO dao = DAOFactory.getJBDAOInstance();
	@Override
	public List<JB_parameter> getAllJunction() {
		// TODO Auto-generated method stub
		/** 
		*获取所有汇流箱的信息*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getAllJunction();
	}
	@Override
	public boolean deleteJunctionById(int id) {
		// TODO Auto-generated method stub
		/** 
		* 根据汇流箱id 删除选中汇流箱信息 *
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.deleteJunctionById(id);
	}
	@Override
	public boolean addJunction(JB_parameter i_parameter) {
		// TODO Auto-generated method stub
		/** 
		* 新建汇流箱*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.addJunction(i_parameter);
	}
	@Override
	public boolean checkJunctionNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*校验汇流箱 是否已存在于电站中*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.checkJunctionNameExistById(name, ps_id);
	}
	@Override
	public List<JB_parameter> getJunctionByIts_name(String junction_name) {
		// TODO Auto-generated method stub
		/** 
		*根据汇流箱名查询设备*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getJunctionByIts_name(junction_name);
	}
	@Override
	public List<JB_parameter> getJunctionByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id查询汇流箱信息*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getJunctionByPs_name(ps_id);
	}
	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id查询汇流箱id*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getParameterIDByPs_id(ps_id);
	}
	@Override
	public String getParameterNameByParameterID(Integer id) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备id获得设备名称*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getParameterNameByParameterID(id);
	}
	@Override
	public List<JB_parameter> getJunctionByPsandname(int ps_id,
			String junction_name) {
		// TODO Auto-generated method stub
		/** 
		*根据电站名称和设备名称查询汇流箱信息*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getJunctionByPsandname(ps_id,
				junction_name);
	}
	@Override
	public List<JB_parameter> getJBByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id获得汇流箱信息*
		* @author liu.yang 
		* @date 2014-12-16 
		*/ 
		return dao.getJBByPsId(ps_id);
	}
}
