/** * * 
* 文件名称: Failure_alarmDAOImpl.java *
* 
* 故障信息管理，增删改查故障信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-1 下午7:45:33 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.Equipment;
import com.PSMS.Hibernate.Failure_alarm;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.JB_parameter;

public class Failure_alarmDAOImpl implements Failure_alarmDAO{
	/** 
	*故障信息管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-11-1
	*/ 

	@Override
	public List<Failure_alarm> getAllFailureAndAlarmInfo() {
		// TODO Auto-generated method stub
		//获取所有故障的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Failure_alarm";
		Query query=session.createQuery(hql);
		List<Failure_alarm> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean addOrUpdateFA(Failure_alarm failure_alarm) {
		// TODO Auto-generated method stub
		//新建或编辑故障
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(failure_alarm);
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteFA(int id) {
		// TODO Auto-generated method stub
		//删除故障
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="delete from Failure_alarm where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean checkFAIsExist(String brand, String model, String type,
			String failure_code) {
		// TODO Auto-generated method stub
		//判断故障是否已存在
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		
		String hql="from  Failure_alarm f where f.brand= ? and f.model = ? and f.type = ? and f.Failure_code=?";
		Query query = session.createQuery(hql);
		query.setString(0, brand);
		query.setString(1, model);
		query.setString(2, type);
		query.setString(3, failure_code);
		Failure_alarm f=(Failure_alarm)query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (f!=null)
			return true;
		return false;
	}

	@Override
	public List<Failure_alarm> getEquipmentByType(String type) {
		// TODO Auto-generated method stub
		//根骨设备类型查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Failure_alarm where type = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,type);
		List<Failure_alarm> q=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return q;
	}

}
