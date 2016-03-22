package com.PSMS.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.ISendRecordDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.SendRecord;

public class SendRecordDaoImpl implements ISendRecordDao {

	@Override
	public boolean add(SendRecord sendRecord) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(sendRecord);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<SendRecord> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from SendRecord";
		Query query = session.createQuery(hql);
		List<SendRecord> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

}
