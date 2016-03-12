package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;


public class RoleDAOImpl implements RoleDAO {

	@Override
	public List<String> getAllRoleName() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select Role_name from Role";
		Query query=session.createQuery(hql);
		List<String> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public String getRoleNameById(int role_id) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select Role_name from Role c where c.id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, role_id);
		String p = (String) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public int getRoleIdByName(String role_name) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select id from Role c where c.Role_name = ?";
		Query query = session.createQuery(hql);
		query.setString(0, role_name);
		Integer p = (Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}
	

}
