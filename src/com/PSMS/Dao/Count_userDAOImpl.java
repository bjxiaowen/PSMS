package com.PSMS.Dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.Count_user;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.M_user;

public class Count_userDAOImpl implements Count_userDAO{

	@Override
	public int getCountByPsId(int ps_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select usercount from Count_user where PS_id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		Integer n =(Integer) query.uniqueResult();
		System.out.println(n);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return n;
	}

	@Override
	public boolean addCount(Count_user count_user) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(count_user);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean updateCountByPsId(int usercount, int ps_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="update Count_user c set c.usercount=? where c.PS_id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, usercount);
		query.setInteger(1, ps_id);
		int ret=query.executeUpdate(); 
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean checkPsIsExitInCount(int ps_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from Count_user c where  c.PS_id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		Count_user p = (Count_user) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}

}
