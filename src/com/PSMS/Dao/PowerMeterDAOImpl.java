package com.PSMS.Dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.PowerMeter;

public class PowerMeterDAOImpl implements PowerMeterDAO{

	@Override
	public List<PowerMeter> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		// TODO Auto-generated method stub
		String from[] = fromRangeDate.split("/");
		String to[] = toRangeDate.split("/");
		String fromdate = from[2]+"-"+from[0]+"-"+from[1]+" "+"00:00:00.000";
		String todate = to[2]+"-"+to[0]+"-"+to[1]+" "+"23:59:59.000";	//根据日期值得到数据库中要求的格式	
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from PowerMeter where  time > ? and time < ? and parameterId = ?";		
		Query query = session.createQuery(hql);
		query.setString(0,fromdate);
		query.setString(1,todate);
		query.setInteger(2,parameter_id);
		List<PowerMeter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public String getMaxDateByPsId(int id) {//获取某电站数据的最大日期
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from PM_parameter where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0,id);
		List<PM_parameter> l_pm = query1.list();
		String s = null;
		if(l_pm!=null){
			String hql = "select distinct max(time) from Power_meter where Parameter_id = ?";	
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, l_pm.get(0).getId());
			if(query.uniqueResult()!=null){
				s =  query.uniqueResult().toString();
			}
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return s;
	}

	@Override
	public String getMinDateByPsId(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from PM_parameter where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0,id);
		List<PM_parameter> l_pm = query1.list();
		String s = null;
		if(l_pm!=null){
			String hql = "select distinct min(time) from Power_meter where Parameter_id = ?";	
			Query query = session.createSQLQuery(hql);
			query.setInteger(0, l_pm.get(0).getId());
			if(query.uniqueResult()!=null){
				s =  query.uniqueResult().toString();
			}
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return s;
	}

}
