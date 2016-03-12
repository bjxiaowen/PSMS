/** * * 
* 文件名称: PS_periodServiceImpl.java *
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

import com.PSMS.Dao.PS_periodDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PS_period;

public class PS_periodServiceImpl implements PS_periodService {
	PS_periodDAO dao = DAOFactory.getPS_periodDaoInstance();
	@Override
	public boolean addPS_period(PS_period ps_period) {
		// TODO Auto-generated method stub
		/** 
		*新建电站分期*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.addPS_period(ps_period);
	}
	@Override
	public List<PS_period> getPSPeriodDataByPSID(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站获取电站分期数据*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.getPSPeriodDataByPSID(ps_id);
	}
	@Override
	public List<PS_period> getPsPeriodInformation() {
		// TODO Auto-generated method stub
		/** 
		* 获取电站分期信息*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.getPsPeriodInformation();
	}
	@Override
	public boolean editPeriodPartByID(int pID,String period_capacity, Integer unit_num,
			String period_time) {
		// TODO Auto-generated method stub
		/** 
		* 编辑电站分期*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.editPeriodPartByID(pID, period_capacity,unit_num,period_time);
	}
	@Override
	public boolean deletePeriodByPid(int p_id) {
		// TODO Auto-generated method stub
		/** 
		* 删除电站分期*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.deletePeriodByPid(p_id);
	}
	@Override
	public int getPeriodNumByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id获取电站分期数目*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.getPeriodNumByPsId(ps_id);
	}
	@Override
	public Double getTotalCapacityByPsID(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id获取电站分期总容量*
		* @author jie.yang 
		* @date 2014-12-12 
		*/ 
		return dao.getTotalCapacityByPsID(ps_id);
	}

}
