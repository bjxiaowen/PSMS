package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.HistoryOfFailure;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.Inverter_parameter;

public class HistoryOfFailureDAOImpl implements HistoryOfFailureDAO{

	@Override
	public List<HistoryOfFailure> searchFailureHistoryData(String ps_name,
			String device_type, String fromRangeDate, String toRangeDate) {
		// TODO Auto-generated method stub
		String from[] = fromRangeDate.split("/");
		String to[] = toRangeDate.split("/");
		String fromdate = from[2]+"-"+from[0]+"-"+from[1]+" "+"00:00:00.000";
		String todate = to[2]+"-"+to[0]+"-"+to[1]+" "+"23:59:59.000";	//根据日期值得到数据库中要求的格式	
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from  HistoryOfFailure where  time > ? and time < ? and ps_name = ? and device_type = ? order by time asc";		
		Query query = session.createQuery(hql);
		query.setString(0,fromdate);
		query.setString(1,todate);
		query.setString(2,ps_name);
		query.setString(3,device_type);
		List<HistoryOfFailure> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public boolean getAllFailureInformation() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		// String hql=" from Inverter_parameter i where i.PS_id in (select id from PS_information p where p.Delete_flag = 0)";
		String hql ="insert into PSMS.dbo.HistoryOfFailure (PS_name,Device_type,Device_name,state,Failure_meaning,time) select C.name as PS_name,'逆变器' as Device_type,B.name as Device_name,A.state as State,D.Failure_meaning,A.time from Inverter A,Inverter_parameter B,"+
			    "PS_information C,Failure_alarm D where C.id=B.PS_id and B.id = A.Parameter_id and "+
			    "B.type = D.type and B.brand = D.brand and B.model = D.model and "+
			    "A.state = D.Failure_code and C.delete_flag =0"; 
		Query query = session.createSQLQuery(hql);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return false;
	}
	public boolean getAllFailureInformationJB() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		// String hql=" from Inverter_parameter i where i.PS_id in (select id from PS_information p where p.Delete_flag = 0)";
		String hql ="insert into PSMS.dbo.HistoryOfFailure (PS_name,Device_type,Device_name,state,Failure_meaning,time) select C.name as PS_name,'汇流箱' as Device_type,B.name as Device_name,A.state as State,D.Failure_meaning,A.time from Junction_box A,JB_parameter B,"+
			    "PS_information C,Failure_alarm D where C.id=B.PS_id and B.id = A.Parameter_id and "+
			    "B.type = D.type and B.brand = D.brand and B.model = D.model and "+
			    "A.state = D.Failure_code and C.delete_flag =0"; 
		Query query = session.createSQLQuery(hql);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return false;
	}


	@Override
	public boolean deleteAllData() {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from HistoryOfFailure ";
		query = session.createQuery(hql);
		
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return false;
	}
	
	
}
