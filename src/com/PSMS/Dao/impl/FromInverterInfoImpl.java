package com.PSMS.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IFromInverterInfo;
import com.PSMS.Hibernate.BaseSessionFactory;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.FromData;

public class FromInverterInfoImpl implements IFromInverterInfo {

	@Override
	public boolean add(FromData fromData) {
		Session session = BaseSessionFactory.getHibernateSession();
		BaseSessionFactory.begainHibernateTransaction();
		session.save(fromData);
		BaseSessionFactory.commitHibernateTransaction();
		BaseSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<FromData> getById(int id) {
		Session session = BaseSessionFactory.getHibernateSession();
		String hql = "from FromData where  InverterDataID > ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		@SuppressWarnings("unchecked")
		List<FromData> list = query.list();
		BaseSessionFactory.closeHibernateSession();
		return list;
	}

}
