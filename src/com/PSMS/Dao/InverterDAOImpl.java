package com.PSMS.Dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Adapter.inverter_parameter;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Hibernate.PowerMeter;

public class InverterDAOImpl implements InverterDAO{

	@Override
	public List<Inverter> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		// TODO Auto-generated method stub
		String from[] = fromRangeDate.split("/");
		String to[] = toRangeDate.split("/");
		String fromdate = from[2]+"-"+from[0]+"-"+from[1]+" "+"00:00:00.000";
		String todate = to[2]+"-"+to[0]+"-"+to[1]+" "+"23:59:59.000";	//根据日期值得到数据库中要求的格式	
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Inverter where  time > ? and time < ? and Parameter_id = ?";		
		Query query = session.createQuery(hql);
		query.setString(0,fromdate);
		query.setString(1,todate);
		query.setInteger(2,parameter_id);
		List<Inverter> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
		
		
	}

	@Override
	public String searchTopPowerByParameter_id(int parameter_id) {//---------------------------lm
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select top 1 AC_power from Inverter  where Parameter_id = ? order by id desc";			
		Query query = session.createSQLQuery(hql);
		query.setInteger(0,parameter_id);
		List<BigDecimal> result = query.list();
		if(result.size()==0)
		{
			result.add(new BigDecimal("0.00"));
		}		
		return result.get(0).toString();
	}

	@Override
	public String searchTopStateByParameter_id(int parameter_id) {
		// TODO Auto-generated method stub				
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select top 1 state from Inverter  where Parameter_id = ? order by id desc";					
		Query query = session.createSQLQuery(hql);
		query.setInteger(0,parameter_id);
		String result = query.uniqueResult().toString();
		return result;
	}

	@Override
	public List<Inverter> getTopRealTimeData(Integer parameter_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select top 10 * from Inverter  where Parameter_id = ? order by time desc";			
		Query query = session.createSQLQuery(hql).addEntity(Inverter.class);
		query.setInteger(0,parameter_id);
		List<Inverter> result = query.list();
		
		return result;
	}

	@Override
	public Double getZhengDianPowerByParaIdAndHour(Integer parameter_id, int h) {
		// TODO Auto-generated method stub
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date);
		if(h==0)return 0.0;
		String maxTime = "";
		String minTime = "";
		if(h==10){
			maxTime = time+" 10:00:00.000";
			minTime = time+" 09:00:00.000";
		}
		else if(h>0&&h<10){
			int minhour=h-1;
			maxTime = time+" 0"+h+":00:00.000";
			minTime = time+" 0"+minhour+":00:00.000";
		}
		else if(h>10&&h<24){
			int minhour=h-1;
			maxTime = time+" "+h+":00:00.000";
			minTime = time+" "+minhour+":00:00.000";
		}
		
		   Session session=HibernateSessionFactory.getHibernateSession();
			HibernateSessionFactory.begainHibernateTransaction();
			String hql=" select top 1 AC_power from Inverter  where AC_power in (select AC_power from Inverter " +
					" where time > ? and time < ? and Parameter_id = ? ) order by time desc";			
			Query query = session.createSQLQuery(hql);
			query.setString(0,minTime);
			query.setString(1,maxTime);
			query.setInteger(2,parameter_id);
			 List<BigDecimal> result = query.list();
			if(result.size()>0){
				
				String s = result.get(0).toString();
				return  Double.parseDouble(s)/1000;
				
	}
			else return 0.0;
	
	}
	
}
