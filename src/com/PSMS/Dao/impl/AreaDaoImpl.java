package com.PSMS.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IAreaDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.Area;
/**
 * 
 * @author Andy
 * @date 2016-03-05
 */
public class AreaDaoImpl implements IAreaDao {

	@Override
	public boolean addArea(Area area) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(area);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean UpdateArea(Area area) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "update Area set area_code=?,area_name=?,area_level=?,parent_id=?,area_flag=? where area_id=? ";
		Query query = session.createQuery(hql);
		query.setString(0, area.getAreaCode());
		query.setString(1, area.getAreaName());
		query.setInteger(2, area.getAreaLevel());
		query.setInteger(3, area.getParentId());
		query.setInteger(4, area.getAreaFlag());
		query.setString(5, area.getAreaId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteArea(String id) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from Area where  area_id = ?";
		query = session.createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public Area getById(String id) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from Area where  area_id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, id);
		Area area = (Area) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return area;
	}

	@Override
	public List<Area> getParentId(String id) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from Area where  parent_id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, id);
		@SuppressWarnings("unchecked")
		List<Area> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	@Override
	public List<Area> getAll() {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from Area";
		Query query = session.createQuery(hql);
		List<Area> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	@Override
	public List<Area> queryByName(String areaName) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from Area where  area_name = ?";
		Query query = session.createQuery(hql);
		query.setString(0, areaName);
		@SuppressWarnings("unchecked")
		List<Area> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	@Override
	public List<Area> queryByCode(String areaCode) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from Area where  area_code= ?";
		Query query = session.createQuery(hql);
		query.setString(0, areaCode);
		@SuppressWarnings("unchecked")
		List<Area> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	@Override
	public List<Area> queryByCodeAndName(String areaCode,String areaName) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from Area where  area_code= ? and area_name=?";
		Query query = session.createQuery(hql);
		query.setString(0, areaCode);
		query.setString(1, areaName);
		@SuppressWarnings("unchecked")
		List<Area> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	

}
