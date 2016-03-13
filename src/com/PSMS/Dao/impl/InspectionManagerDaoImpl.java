package com.PSMS.Dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IInspectionManagerDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointInspection;

public class InspectionManagerDaoImpl implements IInspectionManagerDao {

	@Override
	public boolean addInspectionManager(InspectionManager inspectionManager) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(inspectionManager);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean updateInspectionManager(InspectionManager inspectionManager) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.update(inspectionManager);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteInspectionManager(InspectionManager inspectionManager) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "delete from InspectionManager where  id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, inspectionManager.getId());
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public JointInspection getById(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buff=new StringBuffer(" select ");
		buff.append("inm.id,inm.psId,ps.name,inm.areaId,ar.areaName,inm.equipmentId,equ.type,");
		buff.append("inm.userId,mu.User_name,inm.currDate,inm.inspectionPeriod,mu.telephone,mu.email,inm.nextDate ");
		buff.append(" from InspectionManager as inm, Area as ar,M_user as mu,PS_information as ps,Equipment equ ");
		buff.append(" where inm.areaId=ar.areaId and inm.userId=mu.id and inm.psId=ps.id and inm.equipmentId=equ.id and inm.id=? ");
		Query query = session.createQuery(buff.toString());
		query.setString(0, id);
		List list = query.list();
		JointInspection inspection = new JointInspection();
		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj = (Object[]) list.get(i);
			inspection.setManageId((String) obj[0]);
			inspection.setPsId(Integer.parseInt(obj[1] + ""));
			inspection.setPsName("" + obj[2]);
			inspection.setAreaId("" + obj[3]);
			inspection.setAreaName("" + obj[4]);
			inspection.setEquipmentId(Integer.parseInt(obj[5] + ""));
			inspection.setEquipmentName("" + obj[6]);
			inspection.setUserId(Integer.parseInt(obj[7] + ""));
			inspection.setUserName("" + obj[8]);
			if (obj[9] != null) {
				inspection.setCurrDate("" + obj[9]);
			}
			inspection.setInspectionPeriod(Integer.parseInt(obj[10] + ""));
			inspection.setTel(obj[11]+"");
			inspection.setEmail(obj[12]+"");
			if (obj[13] != null) {
				inspection.setNextDate("" + obj[13]);
			}
		}
		HibernateSessionFactory.closeHibernateSession();
		return inspection;
	}

	@Override
	public List<JointInspection> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "select inm.id,inm.psId,ps.name,inm.areaId,ar.areaName,inm.equipmentId,equ.type,inm.userId,mu.User_name,inm.currDate,inm.inspectionPeriod,mu.telephone,mu.email,inm.nextDate from InspectionManager as inm, Area as ar,M_user as mu,PS_information as ps,Equipment equ where inm.areaId=ar.areaId and inm.userId=mu.id and inm.psId=ps.id and inm.equipmentId=equ.id ";
		Query query = session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		List<JointInspection> reList = new ArrayList<JointInspection>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			JointInspection inspection = new JointInspection();
			Object[] obj = (Object[]) list.get(i);
			inspection.setManageId((String) obj[0]);
			inspection.setPsId(Integer.parseInt(obj[1] + ""));
			inspection.setPsName("" + obj[2]);
			inspection.setAreaId("" + obj[3]);
			inspection.setAreaName("" + obj[4]);
			inspection.setEquipmentId(Integer.parseInt(obj[5] + ""));
			inspection.setEquipmentName("" + obj[6]);
			inspection.setUserId(Integer.parseInt(obj[7] + ""));
			inspection.setUserName("" + obj[8]);
			if (obj[9] != null) {
				inspection.setCurrDate("" + obj[9]);
			}
			inspection.setInspectionPeriod(Integer.parseInt(obj[10] + ""));
			inspection.setTel(obj[11]+"");
			inspection.setEmail(obj[12]+"");
			if (obj[13] != null) {
				inspection.setNextDate("" + obj[13]);
			}
			reList.add(inspection);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	}

	@Override
	public JointInspection checkById(String areaId, int psId, int userId, int equipmentId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buff=new StringBuffer(" select ");
		buff.append("inm.id,inm.psId,ps.name,inm.areaId,ar.areaName,inm.equipmentId,equ.type,");
		buff.append("inm.userId,mu.User_name,inm.currDate,inm.inspectionPeriod,mu.telephone,mu.email,inm.nextDate ");
		buff.append(" from InspectionManager as inm, Area as ar,M_user as mu,PS_information as ps,Equipment equ ");
		buff.append(" where inm.areaId=ar.areaId and inm.userId=mu.id and inm.psId=ps.id and inm.equipmentId=equ.id  ");
		buff.append(" and ar.areaId=? and mu.id=? and ps.id=? and equ.id ");
		Query query = session.createQuery(buff.toString());
		query.setString(0, areaId);
		query.setInteger(1, userId);
		query.setInteger(2, psId);
		query.setInteger(3, equipmentId);
		List list = query.list();
		JointInspection inspection = new JointInspection();
		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj = (Object[]) list.get(i);
			inspection.setManageId((String) obj[0]);
			inspection.setPsId(Integer.parseInt(obj[1] + ""));
			inspection.setPsName("" + obj[2]);
			inspection.setAreaId("" + obj[3]);
			inspection.setAreaName("" + obj[4]);
			inspection.setEquipmentId(Integer.parseInt(obj[5] + ""));
			inspection.setEquipmentName("" + obj[6]);
			inspection.setUserId(Integer.parseInt(obj[7] + ""));
			inspection.setUserName("" + obj[8]);
			if (obj[9] != null) {
				inspection.setCurrDate("" + obj[9]);
			}
			inspection.setInspectionPeriod(Integer.parseInt(obj[10] + ""));
			inspection.setTel(obj[11]+"");
			inspection.setEmail(obj[12]+"");
			if (obj[13] != null) {
				inspection.setNextDate("" + obj[13]);
			}
		}
		HibernateSessionFactory.closeHibernateSession();
		return inspection;
	}

	@Override
	public List<JointInspection> getPsId(int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buff=new StringBuffer(" select ");
		buff.append("inm.id,inm.psId,ps.name,inm.areaId,ar.areaName,inm.equipmentId,equ.type,");
		buff.append("inm.userId,mu.User_name,inm.currDate,inm.inspectionPeriod,mu.telephone,mu.email,inm.nextDate ");
		buff.append(" from InspectionManager as inm, Area as ar,M_user as mu,PS_information as ps,Equipment equ ");
		buff.append(" where inm.areaId=ar.areaId and inm.userId=mu.id and inm.psId=ps.id and inm.equipmentId=equ.id  ");
		buff.append(" and ps.id=? ");
		Query query = session.createQuery(buff.toString());
		query.setInteger(0, psId);
		List list = query.list();
		List<JointInspection> reList = new ArrayList<JointInspection>();
		for (int i = 0; i < list.size(); i++) {
			JointInspection inspection = new JointInspection();
			Object[] obj = (Object[]) list.get(i);
			inspection.setManageId((String) obj[0]);
			inspection.setPsId(Integer.parseInt(obj[1] + ""));
			inspection.setPsName("" + obj[2]);
			inspection.setAreaId("" + obj[3]);
			inspection.setAreaName("" + obj[4]);
			inspection.setEquipmentId(Integer.parseInt(obj[5] + ""));
			inspection.setEquipmentName("" + obj[6]);
			inspection.setUserId(Integer.parseInt(obj[7] + ""));
			inspection.setUserName("" + obj[8]);
			if (obj[9] != null) {
				inspection.setCurrDate("" + obj[9]);
			}
			inspection.setInspectionPeriod(Integer.parseInt(obj[10] + ""));
			inspection.setTel(obj[11]+"");
			inspection.setEmail(obj[12]+"");
			if (obj[13] != null) {
				inspection.setNextDate("" + obj[13]);
			}
			reList.add(inspection);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	}

}
