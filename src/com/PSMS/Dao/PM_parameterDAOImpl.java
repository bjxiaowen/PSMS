/** * * 
* 文件名称: PM_parameterDAOImpl.java *
* 
* 电表信息管理，增删改查电表信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-6 下午1:52:33 *
* * @author jiaojiao.wang 
*/
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Adapter.pm_parameter;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.JB_parameter;
import com.PSMS.Hibernate.PM_parameter;

public class PM_parameterDAOImpl implements PM_parameterDAO{
	/** 
	*电表信息管理需要的操作函数*
	* @author jiaojiao.wang 
	* @date 2014-12-6 
	*/ 


	@Override
	public List<PM_parameter> getAllPM() {
		// TODO Auto-generated method stub
		//获取所有电表的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT A.id,PS_id,A.name,type,brand,model,Purchase_time,Max_current,Period_num " +
				" from PM_parameter A,PS_information B where A.PS_id = B.id and B.Delete_flag = 0";
		Query query = session.createSQLQuery(hql).addEntity(PM_parameter.class);
		List<PM_parameter> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	
	}


	@Override
	public boolean checkPMNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		//校验电表是否已存在于电站中 
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from  PM_parameter p where p.name= ? and p.PS_id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		query.setInteger(1, ps_id);
		PM_parameter p = (PM_parameter) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}
	@Override
	public boolean addPM(PM_parameter i_parameter) {
		// TODO Auto-generated method stub
		//新建电表
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(i_parameter);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}
	
	@Override
	public boolean deletePMById(int id) {
		// TODO Auto-generated method stub
		//根据气象站id 删除选中气象站信息 
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="delete from PM_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}
	@Override
	public List<PM_parameter> getPMByIts_name(String PM_name) {
		// TODO Auto-generated method stub
		//根据电表名查询设备
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from PM_parameter where name = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,PM_name);
		List<PM_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}
	@Override
	public List<PM_parameter> getPMByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id查询电表信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from PM_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<PM_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}
			@Override
			public List<Integer> getParameterIDByPs_id(int ps_id) {
				// TODO Auto-generated method stub
				//根据电站id查询电表id
				Session session=HibernateSessionFactory.getHibernateSession();
				HibernateSessionFactory.begainHibernateTransaction();
				String hql=" select id from PM_parameter where PS_id = ? ";
				Query query = session.createQuery(hql);
				query.setInteger(0,ps_id);
				List<Integer> k=query.list();
				HibernateSessionFactory.commitHibernateTransaction();
				HibernateSessionFactory.closeHibernateSession();
				return k;
			}
			@Override
			public String getParameterNameByParameterID(Integer id) {
				// TODO Auto-generated method stub
				//根据设备id获得设备名称
				Session session=HibernateSessionFactory.getHibernateSession();
				HibernateSessionFactory.begainHibernateTransaction();
				String hql=" select name from PM_parameter where id = ? ";
				Query query = session.createQuery(hql);
				query.setInteger(0,id);
				String  k = (String)query.uniqueResult();
				HibernateSessionFactory.commitHibernateTransaction();
				HibernateSessionFactory.closeHibernateSession();
				return k;
			}
			@Override
			public List<PM_parameter> getPMByPsandname(int ps_id, String pM_name) {
				// TODO Auto-generated method stub
				//根据电站名称和设备名称查询电表信息
				Session session=HibernateSessionFactory.getHibernateSession();
				HibernateSessionFactory.begainHibernateTransaction();
				String hql=" from PM_parameter where PS_id = ? and name=?";
				Query query = session.createQuery(hql);
				query.setInteger(0,ps_id);
				query.setString(1,pM_name);
				List<PM_parameter> k=query.list();
				HibernateSessionFactory.commitHibernateTransaction();
				HibernateSessionFactory.closeHibernateSession();
				return k;
			}


			@Override
			public List<PM_parameter> getPMByPsId(int ps_id) {
				// TODO Auto-generated method stub
				//根据电站id获得电表信息
				Session session=HibernateSessionFactory.getHibernateSession();
				HibernateSessionFactory.begainHibernateTransaction();
				String hql=" from PM_parameter where PS_id = ? ";
				Query query = session.createQuery(hql);
				query.setInteger(0,ps_id);
				List<PM_parameter> k=query.list();
				HibernateSessionFactory.commitHibernateTransaction();
				HibernateSessionFactory.closeHibernateSession();
				return k;
			}

}
