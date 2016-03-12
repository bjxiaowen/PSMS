/** * * 
* 文件名称: JB_parameterDAOImpl.java *
* 
* 汇流箱信息管理，增删改查汇流箱信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-16 下午1:52:33 *
* * @author liu.yang 
*/ 
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Adapter.jb_parameter;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;

public class JB_parameterDAOImpl implements JB_parameterDAO{

	@Override
	public List<JB_parameter> getAllJunction() {
		// TODO Auto-generated method stub
		//获取所有汇流箱的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT J.id,PS_id,J.name,type,brand,model," +
				"Purchase_time,Max_dc_voltage,Road_num,Period_num " +
				" from JB_parameter J,PS_information B where J.PS_id = B.id and B.Delete_flag = 0";
		Query query = session.createSQLQuery(hql).addEntity(JB_parameter.class);
		List<JB_parameter> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;		
	}

	@Override
	public boolean deleteJunctionById(int id) {
		// TODO Auto-generated method stub
		//根据汇流箱id 删除选中汇流箱信息 
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="delete from JB_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean addJunction(JB_parameter i_parameter) {
		// TODO Auto-generated method stub
		//新建汇流箱
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(i_parameter);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean checkJunctionNameExistById(String name, int ps_id) {
		// TODO Auto-generated method stub
		//校验汇流箱 是否已存在于电站中
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from  JB_parameter p where p.name= ? and p.PS_id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		query.setInteger(1, ps_id);
		JB_parameter p = (JB_parameter) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}

	@Override
	public List<JB_parameter> getJunctionByIts_name(String junction_name) {
		// TODO Auto-generated method stub
		//根据汇流箱名查询设备
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from JB_parameter where name = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,junction_name);
		List<JB_parameter> q=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return q;
	}

	@Override
	public List<JB_parameter> getJunctionByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id查询汇流箱信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from JB_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<JB_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	
	}

	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id查询汇流箱id
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select id from JB_parameter where PS_id = ? ";
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
		String hql=" select name from JB_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		String  k = (String)query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<JB_parameter> getJunctionByPsandname(int ps_id,
			String junction_name) {
		// TODO Auto-generated method stub
		//根据电站名称和设备名称查询汇流箱信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from JB_parameter where PS_id = ? and name=?";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setString(1,junction_name);
		List<JB_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}


	@Override
	public List<JB_parameter> getJBByPsId(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id获得汇流箱信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from JB_parameter where PS_id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<JB_parameter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

}
