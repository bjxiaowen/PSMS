package com.PSMS.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IReEngineerAreaDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.ReEngineerArea;
import com.PSMS.util.IDGenerate;

public class ReEngineerAreaDaoImpl implements IReEngineerAreaDao {

	@Override
	public boolean addReEngineerArea(ReEngineerArea reEngineerArea) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String sql = "insert into re_engineer_area(id,area_id,user_id) values(?,?,?)";
		Query query = session.createSQLQuery(sql);
		query.setString(0, "Re" + IDGenerate.getId());
		query.setString(1, reEngineerArea.getAreaId());
		query.setInteger(2, reEngineerArea.getUserId());
		int row = query.executeUpdate();
		System.out.println(row);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean UpdateReEngineerArea(ReEngineerArea reEngineerArea) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "update re_engineer_area set user_id=?,area_id=? where id=? ";
		Query query = session.createSQLQuery(hql);
		query.setInteger(0, reEngineerArea.getUserId());
		query.setString(1, reEngineerArea.getAreaId());
		query.setString(2, reEngineerArea.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteReEngineerArea(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from re_engineer_area where  id = ?";
		query = session.createSQLQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public ReEngineerArea getById(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select re.id,re.area_id as areaId,re.user_id as userId,ar.area_name as areaName,m.name as userName from re_engineer_area re inner join bd_area ar on re.area_id=ar.area_id inner join m_user m on m.id=re.user_id  where re.id=?";
		
		Query query = session.createSQLQuery(hql).addScalar("id", Hibernate.STRING)
				.addScalar("areaId", Hibernate.STRING).addScalar("userId", Hibernate.INTEGER)
				.addScalar("areaName", Hibernate.STRING).addScalar("userName", Hibernate.STRING);
		query.setString(0, id);
		List list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj=(Object[]) list.get(i);
			ReEngineerArea re=new ReEngineerArea();
			re.setId(obj[0]+"");
			re.setAreaId(obj[1]+"");
			re.setUserId(Integer.parseInt(obj[2]+""));
			re.setAreaName(obj[3]+"");
			re.setUserName(obj[4]+"");
			return re;
		}
		return null;
	}

	@Override
	public List<ReEngineerArea> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select re.id,re.area_id as areaId,re.user_id as userId,ar.area_name as areaName,m.name as userName from re_engineer_area re inner join bd_area ar on re.area_id=ar.area_id inner join m_user m on m.id=re.user_id";
		Query query = session.createSQLQuery(hql).addScalar("id", Hibernate.STRING)
				.addScalar("areaId", Hibernate.STRING).addScalar("userId", Hibernate.INTEGER)
				.addScalar("areaName", Hibernate.STRING).addScalar("userName", Hibernate.STRING);
		List list = query.list();
		List<ReEngineerArea> reList=new ArrayList<ReEngineerArea>();
		HibernateSessionFactory.closeHibernateSession();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj=(Object[]) list.get(i);
			ReEngineerArea re=new ReEngineerArea();
			re.setId(obj[0]+"");
			re.setAreaId(obj[1]+"");
			re.setUserId(Integer.parseInt(obj[2]+""));
			re.setAreaName(obj[3]+"");
			re.setUserName(obj[4]+"");
			reList.add(re);
		}
		return reList;
	}

	@Override
	public List<ReEngineerArea> searchByAreaName(String areaName) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select re.id,re.area_id as areaId,re.user_id as userId,ar.area_name as areaName,m.name as userName from re_engineer_area re inner join bd_area ar on re.area_id=ar.area_id inner join m_user m on m.id=re.user_id where ar.area_name like '%"+areaName+"%'";
		Query query = session.createSQLQuery(hql).addScalar("id", Hibernate.STRING)
				.addScalar("areaId", Hibernate.STRING).addScalar("userId", Hibernate.INTEGER)
				.addScalar("areaName", Hibernate.STRING).addScalar("userName", Hibernate.STRING);
		List list = query.list();
		List<ReEngineerArea> reList=new ArrayList<ReEngineerArea>();
		HibernateSessionFactory.closeHibernateSession();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj=(Object[]) list.get(i);
			ReEngineerArea re=new ReEngineerArea();
			re.setId(obj[0]+"");
			re.setAreaId(obj[1]+"");
			re.setUserId(Integer.parseInt(obj[2]+""));
			re.setAreaName(obj[3]+"");
			re.setUserName(obj[4]+"");
			reList.add(re);
		}
		return reList;
	}

	@Override
	public List<ReEngineerArea> searchByUserName(String UserName) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select re.id,re.area_id as areaId,re.user_id as userId,ar.area_name as areaName,m.name as userName from re_engineer_area re inner join bd_area ar on re.area_id=ar.area_id inner join m_user m on m.id=re.user_id where m.name like '%"+UserName+"%'";
		Query query = session.createSQLQuery(hql).addScalar("id", Hibernate.STRING)
				.addScalar("areaId", Hibernate.STRING).addScalar("userId", Hibernate.INTEGER)
				.addScalar("areaName", Hibernate.STRING).addScalar("userName", Hibernate.STRING);
		List list = query.list();
		List<ReEngineerArea> reList=new ArrayList<ReEngineerArea>();
		HibernateSessionFactory.closeHibernateSession();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj=(Object[]) list.get(i);
			ReEngineerArea re=new ReEngineerArea();
			re.setId(obj[0]+"");
			re.setAreaId(obj[1]+"");
			re.setUserId(Integer.parseInt(obj[2]+""));
			re.setAreaName(obj[3]+"");
			re.setUserName(obj[4]+"");
			reList.add(re);
		}
		return reList;
	}

	@Override
	public List<ReEngineerArea> checkByAreaIdAndUserId(String areaId, String userId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select re.id,re.area_id as areaId,re.user_id as userId,ar.area_name as areaName,m.name as userName from re_engineer_area re inner join bd_area ar on re.area_id=ar.area_id inner join m_user m on m.id=re.user_id  where  ar.area_id=? and m.id=?";
		
		Query query = session.createSQLQuery(hql).addScalar("id", Hibernate.STRING)
				.addScalar("areaId", Hibernate.STRING).addScalar("userId", Hibernate.INTEGER)
				.addScalar("areaName", Hibernate.STRING).addScalar("userName", Hibernate.STRING);
		query.setString(0, areaId);
		query.setString(1, userId);
		List list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		List<ReEngineerArea> reList=new ArrayList<ReEngineerArea>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj=(Object[]) list.get(i);
			ReEngineerArea re=new ReEngineerArea();
			re.setId(obj[0]+"");
			re.setAreaId(obj[1]+"");
			re.setUserId(Integer.parseInt(obj[2]+""));
			re.setAreaName(obj[3]+"");
			re.setUserName(obj[4]+"");
			reList.add(re);
		}
		return reList;
	}

	

}
