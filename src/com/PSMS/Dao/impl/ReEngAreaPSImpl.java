package com.PSMS.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IReEngAreaPS;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.ReEngAreaPowerStation;

public class ReEngAreaPSImpl implements IReEngAreaPS {

	@Override
	public boolean addReEngAreaPS(ReEngAreaPowerStation reEngAreaPS) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(reEngAreaPS);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean UpdateReEngAreaPS(ReEngAreaPowerStation reEngAreaPS) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "update ReEngAreaPowerStation set area_id=?,user_id=?,ps_id=? where id=? ";
		Query query = session.createQuery(hql);
		query.setString(0, reEngAreaPS.getAreaId());
		query.setInteger(1, reEngAreaPS.getUserId());
		query.setInteger(2, reEngAreaPS.getPsId());
		query.setString(3,reEngAreaPS.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteReEngAreaPS(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from ReEngAreaPowerStation where  id = ?";
		query = session.createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public JointEngAreaPS getById(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select reg.id,ar.areaId,ps.id,mu.id ,mu.User_name,ar.areaName,ps.name from ReEngAreaPowerStation as reg, Area as ar,M_user as mu,PS_information as ps where reg.areaId=ar.areaId and mu.id=reg.userId and reg.psId=ps.id and reg.id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, id);
		List list = query.list();
		JointEngAreaPS join=new JointEngAreaPS();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			join.setId((String)obj[0]);
			join.setAreaId((String)obj[1]);
			System.out.println(obj[2]+"  "+obj[3]);
			join.setPsId(Integer.parseInt(obj[2]+""));
			join.setUserId(Integer.parseInt(obj[3]+""));
			join.setUserName((String)obj[4]);
			join.setAreaName((String)obj[5]);
			join.setPsName((String)obj[6]);
		}
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return join;
	}

	@Override
	public List<JointEngAreaPS> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select reg.id,ar.areaId,ps.id,mu.id ,mu.User_name,ar.areaName,ps.name from ReEngAreaPowerStation as reg, Area as ar,M_user as mu,PS_information as ps where reg.areaId=ar.areaId and mu.id=reg.userId and reg.psId=ps.id";
		Query query = session.createQuery(hql);
		List<JointEngAreaPS> reList=new ArrayList<JointEngAreaPS>();
		List list = query.list();
		if(list==null||list.size()==0){
			return reList;
		}
		for(int i=0;i<list.size();i++){
			JointEngAreaPS join=new JointEngAreaPS();
			Object[] obj=(Object[]) list.get(i);
			join.setId((String)obj[0]);
			join.setAreaId((String)obj[1]);
			System.out.println(obj[2]+"  "+obj[3]);
			join.setPsId(Integer.parseInt(obj[2]+""));
			join.setUserId(Integer.parseInt(obj[3]+""));
			join.setUserName((String)obj[4]);
			join.setAreaName((String)obj[5]);
			join.setPsName((String)obj[6]);
			reList.add(join);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	
	}

	@Override
	public List<JointEngAreaPS> searchByAreaId(String areaId) throws Exception {

		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select reg.id,ar.areaId,ps.id,mu.id ,mu.User_name,ar.areaName,ps.name from ReEngAreaPowerStation as reg, Area as ar,M_user as mu,PS_information as ps where reg.areaId=ar.areaId and mu.id=reg.userId and reg.psId=ps.id and reg.areaId=?";
		Query query = session.createQuery(hql);
		query.setString(0, areaId);
		List list = query.list();
		List<JointEngAreaPS> reList=new ArrayList<JointEngAreaPS>();
		if(list==null||list.size()==0){
			return reList;
		}
		for(int i=0;i<list.size();i++){
			JointEngAreaPS join=new JointEngAreaPS();
			Object[] obj=(Object[]) list.get(i);
			join.setId((String)obj[0]);
			join.setAreaId((String)obj[1]);
			System.out.println(obj[2]+"  "+obj[3]);
			join.setPsId(Integer.parseInt(obj[2]+""));
			join.setUserId(Integer.parseInt(obj[3]+""));
			join.setUserName((String)obj[4]);
			join.setAreaName((String)obj[5]);
			join.setPsName((String)obj[6]);
			reList.add(join);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	
	
	}

	@Override
	public List<JointEngAreaPS> searchByUserId(int userId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select reg.id,ar.areaId,ps.id,mu.id ,mu.User_name,ar.areaName,ps.name from ReEngAreaPowerStation as reg, Area as ar,M_user as mu,PS_information as ps where reg.areaId=ar.areaId and mu.id=reg.userId and reg.psId=ps.id and reg.userId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		List list = query.list();
		List<JointEngAreaPS> reList=new ArrayList<JointEngAreaPS>();
		if(list==null||list.size()==0){
			return reList;
		}
		
		for(int i=0;i<list.size();i++){
			JointEngAreaPS join=new JointEngAreaPS();
			Object[] obj=(Object[]) list.get(i);
			join.setId((String)obj[0]);
			join.setAreaId((String)obj[1]);
			join.setPsId(Integer.parseInt(obj[2]+""));
			join.setUserId(Integer.parseInt(obj[3]+""));
			join.setUserName((String)obj[4]);
			join.setAreaName((String)obj[5]);
			join.setPsName((String)obj[6]);
			reList.add(join);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	
	
	}

	@Override
	public List<JointEngAreaPS> searchByPSId(int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select reg.id,ar.areaId,ps.id,mu.id ,mu.User_name,ar.areaName,ps.name from ReEngAreaPowerStation as reg, Area as ar,M_user as mu,PS_information as ps where reg.areaId=ar.areaId and mu.id=reg.userId and reg.psId=ps.id and reg.psId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, psId);
		List list = query.list();
		List<JointEngAreaPS> reList=new ArrayList<JointEngAreaPS>();
		if(list==null||list.size()==0){
			return reList;
		}
		for(int i=0;i<list.size();i++){
			JointEngAreaPS join=new JointEngAreaPS();
			Object[] obj=(Object[]) list.get(i);
			join.setId((String)obj[0]);
			join.setAreaId((String)obj[1]);
			join.setPsId(Integer.parseInt(obj[2]+""));
			join.setUserId(Integer.parseInt(obj[3]+""));
			join.setUserName((String)obj[4]);
			join.setAreaName((String)obj[5]);
			join.setPsName((String)obj[6]);
			reList.add(join);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	
	
	}

	@Override
	public List<JointEngAreaPS> checkById(String areaId, int userId, int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select reg.id,ar.areaId,ps.id,mu.id ,mu.User_name,ar.areaName,ps.name from ReEngAreaPowerStation as reg, Area as ar,M_user as mu,PS_information as ps where reg.areaId=ar.areaId and mu.id=reg.userId and reg.psId=ps.id and ar.areaId=? and ps.id=? and mu.id=?";
		Query query = session.createQuery(hql);
		query.setString(0, areaId);
		query.setInteger(1, psId);
		query.setInteger(2, userId);
		List list = query.list();
		if(list==null||list.size()==0){
			return null;
		}
		List<JointEngAreaPS> reList=new ArrayList<JointEngAreaPS>();
		for(int i=0;i<list.size();i++){
			JointEngAreaPS join=new JointEngAreaPS();
			Object[] obj=(Object[]) list.get(i);
			join.setId((String)obj[0]);
			join.setAreaId((String)obj[1]);
			join.setPsId(Integer.parseInt(obj[2]+""));
			join.setUserId(Integer.parseInt(obj[3]+""));
			join.setUserName((String)obj[4]);
			join.setAreaName((String)obj[5]);
			join.setPsName((String)obj[6]);
			reList.add(join);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	}

}
