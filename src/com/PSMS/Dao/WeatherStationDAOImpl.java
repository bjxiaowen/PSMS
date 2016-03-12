package com.PSMS.Dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.WeatherStation;

public class WeatherStationDAOImpl implements WeatherStationDAO{

	@Override
	public List<WeatherStation> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		// TODO Auto-generated method stub
		String from[] = fromRangeDate.split("/");
		String to[] = toRangeDate.split("/");
		String fromdate = from[2]+"-"+from[0]+"-"+from[1]+" "+"00:00:00.000";
		String todate = to[2]+"-"+to[0]+"-"+to[1]+" "+"23:59:59.000";	//根据日期值得到数据库中要求的格式	
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from WeatherStation where  time > ? and time < ? and Parameter_id = ?";		
		Query query = session.createQuery(hql);
		query.setString(0,fromdate);
		query.setString(1,todate);
		query.setInteger(2,parameter_id);
		List<WeatherStation> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public String searchTopFZLByParameter_id(int ws_id) {//-------------LM
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		System.out.println(ws_id);
		String hql=" select top 1 Irraditation_value from Weather_station  where Parameter_id = ? order by id desc";			
		Query query = session.createSQLQuery(hql);
		query.setInteger(0,ws_id);
		String result = query.uniqueResult().toString();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return result;
	}

	@Override
	public String searchTopTempByParameter_id(int ws_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select top 1 temperature from Weather_station  where Parameter_id = ? order by id desc";			
		Query query = session.createSQLQuery(hql);
		query.setInteger(0,ws_id);
		String result = query.uniqueResult().toString();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return result;
	}

	@Override
	public WeatherStation getRealTimeWSDataByParameterID(int p_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select top 1 * from Weather_station  where Parameter_id = ? order by time desc";			
		Query query = session.createSQLQuery(hql).addEntity(WeatherStation.class);
		query.setInteger(0,p_id);
		WeatherStation result =(WeatherStation) query.uniqueResult();
		
		return result;
	}

	@Override
	public Double getZhengDianAccByParaIdAndHour(int ws_parameter_id, int h) {
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
			String hql=" select top 1 Irraditation_value from Weather_station  where Irraditation_value in (select Irraditation_value from Weather_station " +
					" where time > ? and time < ? and Parameter_id = ? ) order by time desc";			
			Query query = session.createSQLQuery(hql);
			query.setString(0,minTime);
			query.setString(1,maxTime);
			query.setInteger(2,ws_parameter_id);
			 List<BigDecimal> result = query.list();
			if(result.size()>0){
				
				String s = result.get(0).toString();
				
				return  Double.parseDouble(s);
				
	}
			else return 0.0;
	
	}

	
}
