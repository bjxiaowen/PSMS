package com.PSMS.Dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IInspectionDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.JointInspection;

public class InspectionDaoImpl implements IInspectionDao {

	@Override
	public boolean addInspection(Inspection inspection) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(inspection);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean UpdateInspection(Inspection inspection) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.update(inspection);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteInspection(Inspection inspection) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.delete(inspection);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	public JointInspection getById(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buff=new StringBuffer("select ");
		buff.append(" inm.id,inm.psId,inm.areaId,inm.equipmentId,inm.userId, ");
		buff.append(" ps.name,ar.areaName,equ.type,mu.User_name,mu.telephone,mu.email, ");
		buff.append(" ins.id,ins.shouldDate,ins.actualDate,ins.inspectionReport,ins.inspectionStatus ");
		buff.append(" from InspectionManager as inm ,Inspection as ins ,");
		buff.append(" Area as ar,M_user as mu,PS_information as ps,Equipment equ");
		buff.append(" where inm.id=ins.managerId and inm.psId= ps.id");
		buff.append(" and inm.areaId=ar.areaId and inm.equipmentId=equ.id ");
		buff.append(" and inm.userId=mu.id and ins.id=?");
		Query query = session.createQuery(buff.toString());
		query.setString(0, id);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		JointInspection inspection = new JointInspection();
		if (list == null || list.size() == 0) {
			return inspection;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			//inm.id,inm.psId,inm.areaId,inm.equipmentId,inm.userId,
			inspection.setManageId(obj[0]+"");
			if (obj[1] != null) {
				inspection.setPsId(Integer.parseInt(obj[1] + ""));
			}
			if (obj[2] != null) {
				inspection.setAreaId(obj[2] + "");
			}
			if (obj[3] != null) {
				inspection.setEquipmentId(Integer.parseInt(obj[3] + ""));
			}
			if (obj[4] != null) {
				inspection.setUserId(Integer.parseInt(obj[4] + ""));
			}
			
			//ps.name,ar.areaName,equ.type,mu.User_name,mu.telephone,mu.email, 
			inspection.setPsName(obj[5] + "");
			inspection.setAreaName(obj[6] + "");
			inspection.setEquipmentName(obj[7] + "");
			inspection.setUserName(obj[8] + "");
			inspection.setTel(obj[9] + "");
			inspection.setEmail(obj[10] + "");
			//ins.id,ins.shouldDate,ins.actualDate,ins.inspectionReport,ins.inspectionStatus
			inspection.setInspectionId(obj[11] + "");
			if (obj[12] != null) {
				inspection.setShouldDate(obj[12]+"");
			}
			if (obj[13] != null) {
				inspection.setActualDate(obj[13]+"");
			}
			inspection.setInspectionReport(obj[14]+"");
			if (obj[15] != null) {
				inspection.setInspectionStatus(Integer.parseInt(obj[15]+""));
			}
		}
		HibernateSessionFactory.closeHibernateSession();
		return inspection;
	}

	@Override
	public List<JointInspection> getAll() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buff=new StringBuffer("select ");
		buff.append(" inm.id,inm.psId,inm.areaId,inm.equipmentId,inm.userId, ");
		buff.append(" ps.name,ar.areaName,equ.type,mu.User_name,mu.telephone,mu.email, ");
		buff.append(" ins.id,ins.shouldDate,ins.actualDate,ins.inspectionReport,ins.inspectionStatus ");
		buff.append(" from InspectionManager as inm ,Inspection as ins ,");
		buff.append(" Area as ar,M_user as mu,PS_information as ps,Equipment equ");
		buff.append(" where inm.id=ins.managerId and inm.psId= ps.id");
		buff.append(" and inm.areaId=ar.areaId and inm.equipmentId=equ.id ");
		buff.append(" and inm.userId=mu.id");
		Query query = session.createQuery(buff.toString());
		List list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		List<JointInspection> reList = new ArrayList<JointInspection>();
		
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			JointInspection inspection = new JointInspection();
			Object[] obj = (Object[]) list.get(i);
			inspection.setManageId(obj[0]+"");
			if (obj[1] != null) {
				inspection.setPsId(Integer.parseInt(obj[1] + ""));
			}
			if (obj[2] != null) {
				inspection.setAreaId(obj[2] + "");
			}
			if (obj[3] != null) {
				inspection.setEquipmentId(Integer.parseInt(obj[3] + ""));
			}
			if (obj[4] != null) {
				inspection.setUserId(Integer.parseInt(obj[4] + ""));
			}
			inspection.setPsName(obj[5] + "");
			inspection.setAreaName(obj[6] + "");
			inspection.setEquipmentName(obj[7] + "");
			inspection.setUserName(obj[8] + "");
			inspection.setTel(obj[9] + "");
			inspection.setEmail(obj[10] + "");
			
			inspection.setInspectionId(obj[11] + "");
			if (obj[12] != null) {
				inspection.setShouldDate(obj[12]+"");
			}
			if (obj[13] != null) {
				inspection.setActualDate(obj[13]+"");
			}
			inspection.setInspectionReport(obj[14]+"");
			if (obj[15] != null) {
				inspection.setInspectionStatus(Integer.parseInt(obj[15]+""));
			}
			reList.add(inspection);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	}
	
	public List<JointInspection> getPsId(int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buff=new StringBuffer("select ");
		buff.append(" inm.id,inm.psId,inm.areaId,inm.equipmentId,inm.userId, ");
		buff.append(" ps.name,ar.areaName,equ.type,mu.User_name,mu.telephone,mu.email, ");
		buff.append(" ins.id,ins.shouldDate,ins.actualDate,ins.inspectionReport,ins.inspectionStatus ");
		buff.append(" from InspectionManager as inm ,Inspection as ins ,");
		buff.append(" Area as ar,M_user as mu,PS_information as ps,Equipment equ");
		buff.append(" where inm.id=ins.managerId and inm.psId= ps.id");
		buff.append(" and inm.areaId=ar.areaId and inm.equipmentId=equ.id and ins.inspectionStatus=0");
		buff.append(" and inm.userId=mu.id and inm.psId=?");
		Query query = session.createQuery(buff.toString());
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		List<JointInspection> reList=new ArrayList<JointInspection>();
		
		for (int i = 0; i < list.size(); i++) {
			JointInspection inspection = new JointInspection();
			Object[] obj = (Object[]) list.get(i);
			//inm.id,inm.psId,inm.areaId,inm.equipmentId,inm.userId,
			inspection.setManageId(obj[0]+"");
			if (obj[1] != null) {
				inspection.setPsId(Integer.parseInt(obj[1] + ""));
			}
			if (obj[2] != null) {
				inspection.setAreaId(obj[2] + "");
			}
			if (obj[3] != null) {
				inspection.setEquipmentId(Integer.parseInt(obj[3] + ""));
			}
			if (obj[4] != null) {
				inspection.setUserId(Integer.parseInt(obj[4] + ""));
			}
			
			//ps.name,ar.areaName,equ.type,mu.User_name,mu.telephone,mu.email, 
			inspection.setPsName(obj[5] + "");
			inspection.setAreaName(obj[6] + "");
			inspection.setEquipmentName(obj[7] + "");
			inspection.setUserName(obj[8] + "");
			inspection.setTel(obj[9] + "");
			inspection.setEmail(obj[10] + "");
			//ins.id,ins.shouldDate,ins.actualDate,ins.inspectionReport,ins.inspectionStatus
			inspection.setInspectionId(obj[11] + "");
			if (obj[12] != null) {
				inspection.setShouldDate(obj[12]+"");
			}
			if (obj[13] != null) {
				inspection.setActualDate(obj[13]+"");
			}
			inspection.setInspectionReport(obj[14]+"");
			if (obj[15] != null) {
				inspection.setInspectionStatus(Integer.parseInt(obj[15]+""));
			}
			reList.add(inspection);
		}
		HibernateSessionFactory.closeHibernateSession();
		return reList;
	}

}
