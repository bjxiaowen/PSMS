/** * * 
* 文件名称: WS_parameterServiceImpl.java *
* 
* 气象站信息管理，增删改查气象站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-1 下午2:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.WS_parameterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.WS_parameter;

public class WS_parameterServiceImpl implements WS_parameterService{
	/** 
	* 气象站信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-12-1 
	*/ 
	WS_parameterDAO dao = DAOFactory.getWSDAOInstance();
	@Override
	public List<WS_parameter> getAllWS() {
		// TODO Auto-generated method stub
		/** 
		* 获取所有气象站的信息*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getAllWS();
	}
	@Override
	public boolean checkWSNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 校验气象站 是否已存在于电站中*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.checkWSNameExistById( name, ps_id);
	}
	@Override
	public boolean addWS(WS_parameter w_parameter) {
		// TODO Auto-generated method stub
		/** 
		* 新建气象站*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.addWS(w_parameter);
	}
	@Override
	public boolean deleteWSById(int id) {
		// TODO Auto-generated method stub
		/** 
		* 根据气象站id 删除选中气象站信息 *
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.deleteWSById(id);
	}
	@Override
	public List<WS_parameter> getWSByIts_name(String ws_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据气象站名查询设备*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getWSByIts_name(ws_name);
	}
	@Override
	public List<WS_parameter> getWSByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id查询气象站信息*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getWSByPs_name(ps_id);
	}
	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id查询气象站id*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getParameterIDByPs_id(ps_id);
	}
	@Override
	public String getNameById(int ws_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备id获得设备名称*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getNameById(ws_id);
	}
	@Override
	public String getParameterNameByParameterID(Integer integer) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备id获得设备信息*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getParameterNameByParameterID( integer);
	}
	@Override
	public List<WS_parameter> getWSByPsandname(int ps_id, String wS_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站名称和设备名称查询气象站信息*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getWSByPsandname(ps_id, wS_name);
	}
	@Override
	public List<WS_parameter> getWSByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id获得气象站信息*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getWSByPsId(ps_id);
	}
	@Override
	public List<WS_parameter> getWSByPsIdAndPeriod(int ps_id, int period) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站名称和分期获得气象站*
		* @author jie.yang 
		* @date 2014-12-1 
		*/ 
		return dao.getWSByPsIdAndPeriod(ps_id,period);
	}

}
