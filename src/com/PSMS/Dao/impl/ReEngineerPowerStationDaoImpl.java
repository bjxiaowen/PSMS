package com.PSMS.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IReEngineerPowerStationDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.ReEngineerPowerStation;

public class ReEngineerPowerStationDaoImpl implements IReEngineerPowerStationDao {

	@Override
	public boolean addReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(reEngineerPowerStation);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean UpdateReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "update re_area_power_station set ps_id=?,user_id=? where id=? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, reEngineerPowerStation.getPsId());
		query.setInteger(1, reEngineerPowerStation.getUserId());
		query.setInteger(2, reEngineerPowerStation.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from re_area_power_station where  id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, reEngineerPowerStation.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public ReEngineerPowerStation getById(int id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select re.id,re.area_id,re.ps_id from re_area_power_station re  inner join PS_information ps on re.id=re.ps_id inner join bd_area ar on re.area_id=ar.area_id where re.id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		ReEngineerPowerStation reEnginee = (ReEngineerPowerStation) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return reEnginee;
	}

	@Override
	public List<ReEngineerPowerStation> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select re.id,re.area_id,re.ps_id from re_area_power_station re  inner join PS_information ps on re.id=re.ps_id inner join bd_area ar on re.area_id=ar.area_id ";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ReEngineerPowerStation> list =  query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

}
