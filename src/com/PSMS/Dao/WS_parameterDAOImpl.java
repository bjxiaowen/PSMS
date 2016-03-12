/** * * 
* 文件名称: WS_parameterDAOImpl.java *
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

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;
import com.PSMS.Hibernate.WS_parameter;

public class WS_parameterDAOImpl implements WS_parameterDAO{
	/** 
	* 气象站信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-12-1 
	*/ 

	@Override
	public List<WS_parameter> getAllWS() {
		// TODO Auto-generated method stub
		//获取所有气象站的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT A.id,PS_id,A.name,type,brand,model,Purchase_time,Max_temperature,Period_num" +
				" from WS_parameter A,PS_information B where A.PS_id = B.id and B.Delete_flag = 0";
		Query query = session.createSQLQuery(hql).addEntity(WS_parameter.class);
		List<WS_parameter> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}


	@Override
	public boolean checkWSNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		//校验气象站 是否已存在于电站中
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from  WS_parameter p where p.name= ? and p.PS_id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		query.setInteger(1, ps_id);
		WS_parameter p =  (WS_parameter) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}

	@Override
	public boolean addWS(WS_parameter w_parameter) {
		// TODO Auto-generated method stub
		//新建气象站
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(w_parameter);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteWSById(int id) {
		// TODO Auto-generated method stub
		//根据气象站id 删除选中气象站信息 
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="delete from WS_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<WS_parameter> getWSByIts_name(String ws_name) {
		// TODO Auto-generated method stub
		//根据气象站名查询设备
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from WS_parameter where name = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,ws_name);
		List<WS_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<WS_parameter> getWSByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id查询气象站信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from WS_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<WS_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id查询气象站id
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select id from WS_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<Integer> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public String getNameById(int ws_id) {
		// TODO Auto-generated method stub
		//根据设备id获得设备名称
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select name from WS_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ws_id);
		String  k = (String)query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public String getParameterNameByParameterID(Integer id) {
		// TODO Auto-generated method stub
		//根据设备id获得设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select name from WS_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		String  k = (String)query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<WS_parameter> getWSByPsandname(int ps_id, String wS_name) {
		// TODO Auto-generated method stub
		//根据电站名称和设备名称查询气象站信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from WS_parameter where PS_id = ? and name=?";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setString(1,wS_name);
		List<WS_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}


	@Override
	public List<WS_parameter> getWSByPsId(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id获得气象站信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from WS_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<WS_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}


	@Override
	public List<WS_parameter> getWSByPsIdAndPeriod(int ps_id, int period) {
		// TODO Auto-generated method stub
		//根据电站名称和分期获得气象站
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from WS_parameter where PS_id = ? and Period_num = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setInteger(1,period);
		List<WS_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}


}
