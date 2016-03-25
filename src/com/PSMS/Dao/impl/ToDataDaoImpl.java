package com.PSMS.Dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IToDataDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.ToData;

public class ToDataDaoImpl implements IToDataDao {

	@Override
	public boolean addToData(ToData toData) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(toData);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public int getMax() throws Exception {
		int row=0;
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select max(t.InverterDataID) from ToData t";
		Query query = session.createQuery(hql);
		Object obj=query.uniqueResult();
		if(null!=obj){
			row=(int)obj;
		}
		return row;
	}

}
