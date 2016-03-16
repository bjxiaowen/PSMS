package com.PSMS.Dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.JointFaultMessage;

public class FaultMessageDaoImpl implements IFaultMessageDao {

	@Override
	public boolean addFaultMessage(FaultMessage faultMessage) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(faultMessage);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean updateFaultMessage(FaultMessage faultMessage) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.update(faultMessage);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteFaultMessage(FaultMessage faultMessage) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.delete(faultMessage);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public JointFaultMessage getFaultMessageById(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql="select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,fau.equipmentId,equ.type,fau.userId,mu.User_name,fau.equipmentStatus,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate,fau.failureMeaning,fau.failureType from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps,Equipment equ where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and fau.equipmentId=equ.id and fau.faultMessageId=?";
		Query query = session.createQuery(hql);
		query.setString(0, id);
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list==null||list.size()==0){
			return null;
		}
		JointFaultMessage faultMessage=null;
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			faultMessage=new JointFaultMessage();
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setEquipmentId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setEquipmentName(""+obj[6]);
			if(obj[7]!=null&&!obj[7].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[7]));
			}
			
			faultMessage.setUserName(""+obj[8]);
			if(obj[9]!=null&&!obj[9].equals("0")){
				faultMessage.setEquipmentStatus(Integer.parseInt(""+obj[9]));
			}
			
			if(obj[10]!=null){
				faultMessage.setAlertTime(obj[10]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[11]);
			
			if(obj[12]!=null){
				faultMessage.setPredictTime(obj[12]+"");
			}
			faultMessage.setAlertCause(obj[13]+"");
			faultMessage.setHandleCondition(obj[14]+"");
			if(obj[15]!=null){
				faultMessage.setMaintainDate(obj[15]+"");
			}
			faultMessage.setCheckPerson(obj[16]+"");
			
			if(obj[17]!=null){
				faultMessage.setCheckDate(obj[17]+"");
			}
			
			if(obj[18]!=null&&!obj[18].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[18]));
			}
			
			if(obj[19]!=null){
				faultMessage.setFlagSend(""+obj[19]);
			}
			if(obj[20]!=null){
				faultMessage.setSendDate(""+obj[20]);
			}
			
			if(obj[21]!=null){
				faultMessage.setFailureMeaning(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setFailureType(obj[22]+"");
			}
		}
		return faultMessage;
	}

	public List<JointFaultMessage> getAllJointFaultMessage() throws Exception {

		Session session = HibernateSessionFactory.getHibernateSession();
		String hql="select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,fau.equipmentId,equ.type,fau.userId,mu.User_name,fau.equipmentStatus,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate,fau.failureMeaning,fau.failureType from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps,Equipment equ where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and fau.equipmentId=equ.id";
		Query query = session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list==null||list.size()==0){
			return null;
		}
		List<JointFaultMessage> reList=new ArrayList<JointFaultMessage>();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			JointFaultMessage faultMessage=new JointFaultMessage();
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setEquipmentId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setEquipmentName(""+obj[6]);
			if(obj[7]!=null&&!obj[7].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[7]));
			}
			
			faultMessage.setUserName(""+obj[8]);
			if(obj[9]!=null&&!obj[9].equals("0")){
				faultMessage.setEquipmentStatus(Integer.parseInt(""+obj[9]));
			}
			
			if(obj[10]!=null){
				faultMessage.setAlertTime(obj[10]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[11]);
			
			if(obj[12]!=null){
				faultMessage.setPredictTime(obj[12]+"");
			}
			faultMessage.setAlertCause(obj[13]+"");
			faultMessage.setHandleCondition(obj[14]+"");
			if(obj[15]!=null){
				faultMessage.setMaintainDate(obj[15]+"");
			}
			faultMessage.setCheckPerson(obj[16]+"");
			
			if(obj[17]!=null){
				faultMessage.setCheckDate(obj[17]+"");
			}
			
			if(obj[18]!=null&&!obj[18].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[18]));
			}
			
			//fau.flagSend,fau.sendDate
			if(obj[19]!=null){
				faultMessage.setFlagSend(""+obj[19]);
			}
			if(obj[20]!=null){
				faultMessage.setSendDate(""+obj[20]);
			}
			
			if(obj[21]!=null){
				faultMessage.setFailureMeaning(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setFailureType(obj[22]+"");
			}
			
			reList.add(faultMessage);
		}
		return reList;
	}

	@Override
	public List<JointFaultMessage> getNeedSendMailFaultMessage() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql="select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,fau.equipmentId,equ.type,fau.userId,mu.User_name,fau.equipmentStatus,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate,fau.failureMeaning,fau.failureType,mu.email,mu.telephone from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps,Equipment equ where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and fau.equipmentId=equ.id and fau.flagSend is null and fau.sendDate is null";
		Query query = session.createQuery(hql);
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list==null||list.size()==0){
			return null;
		}
		List<JointFaultMessage> reList=new ArrayList<JointFaultMessage>();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			JointFaultMessage faultMessage=new JointFaultMessage();
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setEquipmentId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setEquipmentName(""+obj[6]);
			if(obj[7]!=null&&!obj[7].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[7]));
			}
			
			faultMessage.setUserName(""+obj[8]);
			if(obj[9]!=null&&!obj[9].equals("0")){
				faultMessage.setEquipmentStatus(Integer.parseInt(""+obj[9]));
			}
			
			if(obj[10]!=null){
				faultMessage.setAlertTime(obj[10]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[11]);
			
			if(obj[12]!=null){
				faultMessage.setPredictTime(obj[12]+"");
			}
			faultMessage.setAlertCause(obj[13]+"");
			faultMessage.setHandleCondition(obj[14]+"");
			if(obj[15]!=null){
				faultMessage.setMaintainDate(obj[15]+"");
			}
			faultMessage.setCheckPerson(obj[16]+"");
			
			if(obj[17]!=null){
				faultMessage.setCheckDate(obj[17]+"");
			}
			
			if(obj[18]!=null&&!obj[18].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[18]));
			}
			
			//fau.flagSend,fau.sendDate
			if(obj[19]!=null){
				faultMessage.setFlagSend(""+obj[19]);
			}
			if(obj[20]!=null){
				faultMessage.setSendDate(""+obj[20]);
			}
			
			if(obj[21]!=null){
				faultMessage.setFailureMeaning(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setFailureType(obj[22]+"");
			}
			
//			mu.email,mu.telephone
			if(obj[23]!=null){
				faultMessage.setEmail(obj[23]+"");
			}
			if(obj[24]!=null){
				faultMessage.setTel(obj[24]+"");
			}
			reList.add(faultMessage);
		}
		return reList;
	}

	@Override
	public boolean updateById(String flagSend, String sendDate,String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "update FaultMessage set flag_send=?,send_date=? where fault_message_id=? ";
		Query query = session.createQuery(hql);
		query.setString(0, flagSend);
		query.setString(1, sendDate);
		query.setString(2, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}
}
