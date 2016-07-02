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

//	@Override
//	public JointFaultMessage getFaultMessageById(String id) throws Exception {
//		Session session = HibernateSessionFactory.getHibernateSession();
//		String hql="select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,fau.equipmentId,equ.type,fau.userId,mu.User_name,fau.equipmentStatus,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate,fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps,Equipment equ where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and fau.equipmentId=equ.id and fau.faultMessageId=?";
//
//		Query query = session.createQuery(hql);
//		query.setString(0, id);
//		@SuppressWarnings("rawtypes")
//		List list=query.list();
//		if(list==null||list.size()==0){
//			return null;
//		}
//		JointFaultMessage faultMessage=null;
//		for(int i=0;i<list.size();i++){
//			Object[] obj=(Object[]) list.get(i);
//			faultMessage=new JointFaultMessage();
//			faultMessage.setFaultMessageId(""+obj[0]);
//			if(obj[1]!=null&&!obj[1].equals("0")){
//				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
//			}
//			faultMessage.setPsName(""+obj[2]);
//			faultMessage.setAreaId(""+obj[3]);
//			faultMessage.setAreaName(""+obj[4]);
//			if(obj[5]!=null&&!obj[5].equals("0")){
//				faultMessage.setEquipmentId(Integer.parseInt(""+obj[5]));
//			}
//			faultMessage.setEquipmentName(""+obj[6]);
//			if(obj[7]!=null&&!obj[7].equals("0")){
//				faultMessage.setUserId(Integer.parseInt(""+obj[7]));
//			}
//			
//			faultMessage.setUserName(""+obj[8]);
//			if(obj[9]!=null&&!obj[9].equals("0")){
//				faultMessage.setEquipmentStatus(Integer.parseInt(""+obj[9]));
//			}
//			
//			if(obj[10]!=null){
//				faultMessage.setAlertTime(obj[10]+"");
//			}
//			faultMessage.setInitialDiagnose(""+obj[11]);
//			
//			if(obj[12]!=null){
//				faultMessage.setPredictTime(obj[12]+"");
//			}
//			faultMessage.setAlertCause(obj[13]+"");
//			faultMessage.setHandleCondition(obj[14]+"");
//			if(obj[15]!=null){
//				faultMessage.setMaintainDate(obj[15]+"");
//			}
//			faultMessage.setCheckPerson(obj[16]+"");
//			
//			if(obj[17]!=null){
//				faultMessage.setCheckDate(obj[17]+"");
//			}
//			
//			if(obj[18]!=null&&!obj[18].equals("0")){
//				faultMessage.setStatus(Integer.parseInt(""+obj[18]));
//			}
//			
//			if(obj[19]!=null){
//				faultMessage.setFlagSend(""+obj[19]);
//			}
//			if(obj[20]!=null){
//				faultMessage.setSendDate(""+obj[20]);
//			}
//			
//			if(obj[21]!=null){
//				faultMessage.setFailureMeaning(obj[21]+"");
//			}
//			
//			if(obj[22]!=null){
//				faultMessage.setFailureType(obj[22]+"");
//			}
//			
////			mu.email,mu.telephone
//			if(obj[23]!=null){
//				faultMessage.setEmail(obj[23]+"");
//			}
//			if(obj[24]!=null){
//				faultMessage.setTel(obj[24]+"");
//			}
//			
//			if(obj[25]!=null){
//				faultMessage.setCheckText(obj[25]+"");
//			}
//			
//			
//		}
//		return faultMessage;
//	}
	
	@Override
	public JointFaultMessage getFaultMessageById(String id) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer sql=new StringBuffer();
		sql.append("select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,");
		sql.append(" fau.userId,mu.User_name,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition, ");
		sql.append(" fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, ");
		sql.append(" fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText ");
		sql.append(" from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps ");
		sql.append(" where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and ");
		sql.append(" fau.faultMessageId=? ");
		Query query = session.createQuery(sql.toString());
		query.setString(0, id);
		List list=query.list();
		JointFaultMessage faultMessage=new JointFaultMessage();
		if(list==null||list.size()==0){
			return faultMessage;
		}
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			//fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			//fau.userId,mu.User_name,
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setUserName(""+obj[6]);
			
			//fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,
			if(obj[7]!=null){
				faultMessage.setAlertTime(obj[7]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[8]);
			
			if(obj[9]!=null){
				faultMessage.setPredictTime(obj[9]+"");
			}
			faultMessage.setAlertCause(obj[10]+"");
			faultMessage.setHandleCondition(obj[11]+"");
			
			//fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, 
			if(obj[12]!=null){
				faultMessage.setMaintainDate(obj[12]+"");
			}
			faultMessage.setCheckPerson(obj[13]+"");
			
			if(obj[14]!=null){
				faultMessage.setCheckDate(obj[14]+"");
			}
			
			if(obj[15]!=null&&!obj[15].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[15]));
			}
			
			if(obj[16]!=null){
				faultMessage.setFlagSend(""+obj[16]);
			}
			
			
			if(obj[17]!=null){
				faultMessage.setSendDate(""+obj[17]);
			}
			//fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText
			if(obj[18]!=null){
				faultMessage.setFailureMeaning(obj[18]+"");
			}
			
			if(obj[19]!=null){
				faultMessage.setFailureType(obj[19]+"");
			}
			
			if(obj[20]!=null){
				faultMessage.setEmail(obj[20]+"");
			}
			if(obj[21]!=null){
				faultMessage.setTel(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setCheckText(obj[22]+"");
			}
		}
		return faultMessage;
	}

	public List<JointFaultMessage> getAllJointFaultMessage() throws Exception {

		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer sql=new StringBuffer();
		sql.append("select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,");
		sql.append(" fau.userId,mu.User_name,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition, ");
		sql.append(" fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, ");
		sql.append(" fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText ,fau.initialDate,fau.handleDate");
		sql.append(" from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps ");
		sql.append(" where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id  ");
		
		Query query = session.createQuery(sql.toString());
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list==null||list.size()==0){
			return null;
		}
		List<JointFaultMessage> reList=new ArrayList<JointFaultMessage>();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			JointFaultMessage faultMessage=new JointFaultMessage();
			//fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			//fau.userId,mu.User_name,
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setUserName(""+obj[6]);
			
			//fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,
			if(obj[7]!=null){
				faultMessage.setAlertTime(obj[7]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[8]);
			
			if(obj[9]!=null){
				faultMessage.setPredictTime(obj[9]+"");
			}
			faultMessage.setAlertCause(obj[10]+"");
			faultMessage.setHandleCondition(obj[11]+"");
			
			//fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, 
			if(obj[12]!=null){
				faultMessage.setMaintainDate(obj[12]+"");
			}
			faultMessage.setCheckPerson(obj[13]+"");
			
			if(obj[14]!=null){
				faultMessage.setCheckDate(obj[14]+"");
			}
			
			if(obj[15]!=null&&!obj[15].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[15]));
			}
			
			if(obj[16]!=null){
				faultMessage.setFlagSend(""+obj[16]);
			}
			
			
			if(obj[17]!=null){
				faultMessage.setSendDate(""+obj[17]);
			}
			//fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText
			if(obj[18]!=null){
				faultMessage.setFailureMeaning(obj[18]+"");
			}
			
			if(obj[19]!=null){
				faultMessage.setFailureType(obj[19]+"");
			}
			
			if(obj[20]!=null){
				faultMessage.setEmail(obj[20]+"");
			}
			if(obj[21]!=null){
				faultMessage.setTel(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setCheckText(obj[22]+"");
			}
			if(obj[23]!=null){
				faultMessage.setInitialDate(obj[23]+"");
			}
			
			if(obj[24]!=null){
				faultMessage.setHandleDate(obj[24]+"");
			}
			reList.add(faultMessage);
		}
		return reList;
	}

	@Override
	public List<JointFaultMessage> getNeedSendMailFaultMessage() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer sql=new StringBuffer();
		sql.append("select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,");
		sql.append(" fau.userId,mu.User_name,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition, ");
		sql.append(" fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, ");
		sql.append(" fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText ");
		sql.append(" from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps ");
		sql.append(" where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id ");
		sql.append(" and fau.flagSend is null and fau.sendDate is null ");
		Query query = session.createQuery(sql.toString());
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list==null||list.size()==0){
			return null;
		}
		List<JointFaultMessage> reList=new ArrayList<JointFaultMessage>();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[]) list.get(i);
			JointFaultMessage faultMessage=new JointFaultMessage();
			//fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			//fau.userId,mu.User_name,
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setUserName(""+obj[6]);
			
			//fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,
			if(obj[7]!=null){
				faultMessage.setAlertTime(obj[7]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[8]);
			
			if(obj[9]!=null){
				faultMessage.setPredictTime(obj[9]+"");
			}
			faultMessage.setAlertCause(obj[10]+"");
			faultMessage.setHandleCondition(obj[11]+"");
			
			//fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, 
			if(obj[12]!=null){
				faultMessage.setMaintainDate(obj[12]+"");
			}
			faultMessage.setCheckPerson(obj[13]+"");
			
			if(obj[14]!=null){
				faultMessage.setCheckDate(obj[14]+"");
			}
			
			if(obj[15]!=null&&!obj[15].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[15]));
			}
			
			if(obj[16]!=null){
				faultMessage.setFlagSend(""+obj[16]);
			}
			
			
			if(obj[17]!=null){
				faultMessage.setSendDate(""+obj[17]);
			}
			//fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText
			if(obj[18]!=null){
				faultMessage.setFailureMeaning(obj[18]+"");
			}
			
			if(obj[19]!=null){
				faultMessage.setFailureType(obj[19]+"");
			}
			
			if(obj[20]!=null){
				faultMessage.setEmail(obj[20]+"");
			}
			if(obj[21]!=null){
				faultMessage.setTel(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setCheckText(obj[22]+"");
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

	@Override
	public List<JointFaultMessage> getFaultMessageByPsId(String psId) throws Exception {

		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer sql=new StringBuffer();
		sql.append("select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,");
		sql.append(" fau.userId,mu.User_name,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition, ");
		sql.append(" fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, ");
		sql.append(" fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText ");
		sql.append(" from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps ");
		sql.append(" where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and ");
		sql.append(" fau.psId=? ");
		Query query = session.createQuery(sql.toString());
		query.setString(0, psId);
		List list=query.list();
		List<JointFaultMessage> reList=new ArrayList<JointFaultMessage>();
		if(list==null||list.size()==0){
			return reList;
		}
		for(int i=0;i<list.size();i++){
			JointFaultMessage faultMessage=new JointFaultMessage();
			Object[] obj=(Object[]) list.get(i);
			//fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			//fau.userId,mu.User_name,
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setUserName(""+obj[6]);
			
			//fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,
			if(obj[7]!=null){
				faultMessage.setAlertTime(obj[7]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[8]);
			
			if(obj[9]!=null){
				faultMessage.setPredictTime(obj[9]+"");
			}
			faultMessage.setAlertCause(obj[10]+"");
			faultMessage.setHandleCondition(obj[11]+"");
			
			//fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, 
			if(obj[12]!=null){
				faultMessage.setMaintainDate(obj[12]+"");
			}
			faultMessage.setCheckPerson(obj[13]+"");
			
			if(obj[14]!=null){
				faultMessage.setCheckDate(obj[14]+"");
			}
			
			if(obj[15]!=null&&!obj[15].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[15]));
			}
			
			if(obj[16]!=null){
				faultMessage.setFlagSend(""+obj[16]);
			}
			
			
			if(obj[17]!=null){
				faultMessage.setSendDate(""+obj[17]);
			}
			//fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText
			if(obj[18]!=null){
				faultMessage.setFailureMeaning(obj[18]+"");
			}
			
			if(obj[19]!=null){
				faultMessage.setFailureType(obj[19]+"");
			}
			
			if(obj[20]!=null){
				faultMessage.setEmail(obj[20]+"");
			}
			if(obj[21]!=null){
				faultMessage.setTel(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setCheckText(obj[22]+"");
			}
			
			reList.add(faultMessage);
		}
		return reList;
	
	}

	@Override
	public List<JointFaultMessage> getFaultMessageByUserId(int userId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer sql=new StringBuffer();
		sql.append("select fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,");
		sql.append(" fau.userId,mu.User_name,fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition, ");
		sql.append(" fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, ");
		sql.append(" fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText,fau.initialDate,fau.handleDate ");
		sql.append(" from FaultMessage as fau, Area as ar,M_user as mu,PS_information as ps ");
		sql.append(" where fau.areaId=ar.areaId and fau.userId=mu.id and fau.psId=ps.id and ");
		sql.append(" mu.id=? ");
		Query query = session.createQuery(sql.toString());
		query.setInteger(0, userId);
		List list=query.list();
		List<JointFaultMessage> reList=new ArrayList<JointFaultMessage>();
		if(list==null||list.size()==0){
			return reList;
		}
		for(int i=0;i<list.size();i++){
			JointFaultMessage faultMessage=new JointFaultMessage();
			Object[] obj=(Object[]) list.get(i);
			//fau.faultMessageId,fau.psId,ps.name,fau.areaId,ar.areaName,
			faultMessage.setFaultMessageId(""+obj[0]);
			if(obj[1]!=null&&!obj[1].equals("0")){
				faultMessage.setPsId(Integer.parseInt(obj[1]+""));
			}
			faultMessage.setPsName(""+obj[2]);
			faultMessage.setAreaId(""+obj[3]);
			faultMessage.setAreaName(""+obj[4]);
			//fau.userId,mu.User_name,
			if(obj[5]!=null&&!obj[5].equals("0")){
				faultMessage.setUserId(Integer.parseInt(""+obj[5]));
			}
			faultMessage.setUserName(""+obj[6]);
			
			//fau.alertTime ,fau.initialDiagnose,fau.predictTime,fau.alertCause,fau.handleCondition,
			if(obj[7]!=null){
				faultMessage.setAlertTime(obj[7]+"");
			}
			faultMessage.setInitialDiagnose(""+obj[8]);
			
			if(obj[9]!=null){
				faultMessage.setPredictTime(obj[9]+"");
			}
			faultMessage.setAlertCause(obj[10]+"");
			faultMessage.setHandleCondition(obj[11]+"");
			
			//fau.maintainDate,fau.checkPerson,fau.checkDate,fau.status,fau.flagSend,fau.sendDate, 
			if(obj[12]!=null){
				faultMessage.setMaintainDate(obj[12]+"");
			}
			faultMessage.setCheckPerson(obj[13]+"");
			
			if(obj[14]!=null){
				faultMessage.setCheckDate(obj[14]+"");
			}
			
			if(obj[15]!=null&&!obj[15].equals("0")){
				faultMessage.setStatus(Integer.parseInt(""+obj[15]));
			}
			
			if(obj[16]!=null){
				faultMessage.setFlagSend(""+obj[16]);
			}
			
			
			if(obj[17]!=null){
				faultMessage.setSendDate(""+obj[17]);
			}
			//fau.failureMeaning,fau.failureType,mu.email,mu.telephone,fau.checkText
			if(obj[18]!=null){
				faultMessage.setFailureMeaning(obj[18]+"");
			}
			
			if(obj[19]!=null){
				faultMessage.setFailureType(obj[19]+"");
			}
			
			if(obj[20]!=null){
				faultMessage.setEmail(obj[20]+"");
			}
			if(obj[21]!=null){
				faultMessage.setTel(obj[21]+"");
			}
			
			if(obj[22]!=null){
				faultMessage.setCheckText(obj[22]+"");
			}
			
			if(obj[23]!=null){
				faultMessage.setInitialDate(obj[23]+"");
			}
			
			if(obj[24]!=null){
				faultMessage.setHandleDate(obj[24]+"");
			}
			
			reList.add(faultMessage);
		}
		return reList;
	
	
	}
}
