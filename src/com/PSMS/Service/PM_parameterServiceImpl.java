/** * * 
* 文件名称: PM_parameterServiceImpl.java *
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
import com.PSMS.Dao.PM_parameterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PM_parameter;

public class PM_parameterServiceImpl implements PM_parameterService{
	/** 
	*电表信息管理需要的操作函数*
	* @author jiaojiao.wang 
	* @date 2014-12-6 
	*/ 

	PM_parameterDAO dao = DAOFactory.getPMDAOInstance();
	
	public List<PM_parameter> getAllPM(){
		// TODO Auto-generated method stub
		/** 
		*获取所有电表的信息*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 
		return dao.getAllPM();
	}

	@Override
	public boolean checkPMNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*校验电表是否已存在于电站中 *
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.checkPMNameExistById(name, ps_id);
	}

	@Override
	public boolean addPM(PM_parameter i_parameter) {
		// TODO Auto-generated method stub
		/** 
		*新建电表*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.addPM(i_parameter);
	}

	@Override
	public boolean deletePMById(int id) {
		// TODO Auto-generated method stub
		/** 
		*根据气象站id 删除选中气象站信息 *
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.deletePMById(id);
	}

	@Override
	public List<PM_parameter> getPMByIts_name(String PM_name) {
		// TODO Auto-generated method stub
		/** 
		*根据电表名查询设备*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.getPMByIts_name(PM_name);
	}

	@Override
	public List<PM_parameter> getPMByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id查询电表信息*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.getPMByPs_name(ps_id);
	}

	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id查询电表id*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.getParameterIDByPs_id( ps_id);
	}

	@Override
	public String getParameterNameByParameterID(Integer integer) {
		// TODO Auto-generated method stub
		/** 
		*根据设备id获得设备名称*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.getParameterNameByParameterID( integer);
	}

	@Override
	public List<PM_parameter> getPMByPsandname(int ps_id, String pM_name) {
		// TODO Auto-generated method stub
		/** 
		*根据电站名称和设备名称查询电表信息*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao.getPMByPsandname(ps_id,pM_name);
	}

	@Override
	public List<PM_parameter> getPMByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		*根据电站id获得电表信息*
		* @author jiaojiao.wang 
		* @date 2014-12-6 
		*/ 

		return dao. getPMByPsId(ps_id);
	}

	
}
