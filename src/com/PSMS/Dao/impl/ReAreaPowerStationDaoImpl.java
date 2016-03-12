package com.PSMS.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.PSMS.Dao.IReAreaPowerStationDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.ReAreaPowerStation;
import com.PSMS.util.IDGenerate;

public class ReAreaPowerStationDaoImpl implements IReAreaPowerStationDao {

	@Override
	public boolean addReAreaPowerStationn(ReAreaPowerStation reAreaPowerStation) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String sql="insert into re_area_power_station(id,area_id,ps_id)values(?,?,?)";
		Query query = session.createSQLQuery(sql);
		query.setString(0, "Re" + IDGenerate.getId());
		query.setString(1, reAreaPowerStation.getAreaId());
		query.setInteger(2,reAreaPowerStation.getPsId());
		int row = query.executeUpdate();
		System.out.println(row);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean UpdateReAreaPowerStation(ReAreaPowerStation reAreaPowerStation) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "update re_area_power_station set ps_id=?,area_id=? where id=? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, reAreaPowerStation.getPsId());
		query.setString(1, reAreaPowerStation.getAreaId());
		query.setString(2, reAreaPowerStation.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteReAreaPowerStation(ReAreaPowerStation reAreaPowerStation) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from re_area_power_station where  id = ?";
		query = session.createQuery(hql);
		query.setString(0, reAreaPowerStation.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public ReAreaPowerStation getById(int id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from re_area_power_station where re.id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		ReAreaPowerStation reArea = (ReAreaPowerStation) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return reArea;
	}

	@Override
	public List<ReAreaPowerStation> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from ReAreaPowerStation";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ReAreaPowerStation> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	@Override
	public List<ReAreaPowerStation> searchByAreaName(String areaName) throws Exception {
		return null;
	}

	@Override
	public List<ReAreaPowerStation> searchByUserName(String UserName) throws Exception {
		return null;
	}

	@Override
	public List<ReAreaPowerStation> checkByAreaIdAndUserId(String areaId, String psId) throws Exception {
		return null;
	}

}
