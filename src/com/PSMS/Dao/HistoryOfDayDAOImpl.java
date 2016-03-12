package com.PSMS.Dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.HistoryOfDay;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.WS_parameter;

public class HistoryOfDayDAOImpl implements HistoryOfDayDAO{

	@Override
	public List<Integer> getAllHistoryYear() {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select distinct year from HistoryOfDay ";
		Query query = session.createQuery(hql);		
		List<Integer> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Integer> getMonthByPsAndYear(int ps_id, int year) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select distinct month from HistoryOfDay where ps_id = ? and year = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setInteger(1,year);
		List<Integer> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<HistoryOfDay> getDataByMonth(int ps_id, int year, int month) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
//		String hql="select from HistoryOfDay u where u.PS_id = ? and u.year = ? and u.month = ?";
		String hql="select * from HistoryOfDay where PS_id ="+ps_id+" and year ="+year+" and month ="+month+ "order by day asc";
//		Query query = session.createQuery(hql);
//		query.setInteger(0,ps_id);
//		query.setInteger(1,year);
//		query.setInteger(2,month);
		Query query = session.createSQLQuery(hql).addEntity(HistoryOfDay.class);
		List<HistoryOfDay> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}


	@Override
	public double getTodayAccPowerByPsID(int ps_id) {
		// TODO Auto-generated method stub
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date);
		String maxTime = time+" 23:59:59.000";
		String minTime = time+" 00:00:00.000";
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from PM_parameter where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0, ps_id);
		List<PM_parameter> l_pm = query1.list();
		double sum_power = 0;
		if(l_pm!=null){
			for(int i=0;i<l_pm.size();i++){
				String hql = "select min( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query = session.createSQLQuery(hql);
				String hql2 = "select max( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query2 = session.createSQLQuery(hql2);
				query.setString(0, minTime);
				query.setString(1, maxTime);
				query.setInteger(2, l_pm.get(i).getId());
				query2.setString(0, minTime);
				query2.setString(1, maxTime);
				query2.setInteger(2, l_pm.get(i).getId());
				if(query.uniqueResult()!=null){
				String minAccPower =  query.uniqueResult().toString();
				String maxAccPower =  query2.uniqueResult().toString();
				double min = Double.parseDouble(minAccPower);
				double max = Double.parseDouble(maxAccPower);
				sum_power =  sum_power + max - min;
				}
			}
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return sum_power;
	}

	@Override
	public String getTodayOnLineHourByPsID(int ps_id) {
		// TODO Auto-generated method stub
		int y,m,d;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH)+1;    
		d=cal.get(Calendar.DATE);    
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select grid_connection_power from HistoryOfDay p where p.PS_id= ? and p.year = ? and p.month = ? and p.day = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		query.setInteger(1, y);
		query.setInteger(2, m);
		query.setInteger(3, d);
		query.uniqueResult();
		
		if(query.uniqueResult()==null){
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return null;
			
		}
		else {String p =  query.uniqueResult().toString();
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;}
	}

	@Override
	public double getSumPowerOfDay() {//-----------------------LM20141126
		// TODO Auto-generated method stub
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date);
		String maxTime = time+" 23:59:59.000";
		String minTime = time+" 00:00:00.000";
		System.out.println(maxTime+""+minTime+"------------");
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from PM_parameter";
		Query query1 = session.createQuery(hql1);
		List<PM_parameter> l_pm = query1.list();
		double sum_power = 0;
		if(l_pm!=null){
			for(int i=0;i<l_pm.size();i++){
				System.out.println(l_pm.get(i).getId());
				String hql = "select min( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query = session.createSQLQuery(hql);
				String hql2 = "select max( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query2 = session.createSQLQuery(hql2);
				query.setString(0, minTime);
				query.setString(1, maxTime);
				query.setInteger(2, l_pm.get(i).getId());
				query2.setString(0, minTime);
				query2.setString(1, maxTime);
				query2.setInteger(2, l_pm.get(i).getId());
				if(query.uniqueResult()!=null){
				String minAccPower =  query.uniqueResult().toString();
				String maxAccPower =  query2.uniqueResult().toString();
				double min = Double.parseDouble(minAccPower);
				double max = Double.parseDouble(maxAccPower);
				sum_power =  sum_power + max - min;
				}
			}
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return sum_power;
		
	}

	@Override
	public double getSumIradOfDay() {//-------------------------LM20141126
		// TODO Auto-generated method stub
		
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from WS_parameter";
		Query query1 = session.createQuery(hql1);
		List<WS_parameter> l_pm = query1.list();
		double sum_irrad = 0;
		if(l_pm!=null){
			for(int i=0;i<l_pm.size();i++){
				String hql=" select top 1 Irraditation_value from Weather_station where Parameter_id = ? order by id desc";			
				Query query = session.createSQLQuery(hql);
				query.setInteger(0, l_pm.get(i).getId());
				if(query.uniqueResult()!=null){
					String result = query.uniqueResult().toString();
					double rs = Double.parseDouble(result);
					sum_irrad = sum_irrad + rs;
				}
			}
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return sum_irrad;
		
		
	}

	@Override
	public List<Double> getPowerDataByYearAndPSid(int year, int ps_id) {
		// TODO Auto-generated method stub
		
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select total_power from HistoryOfDay p where p.PS_id= ? and p.year = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setInteger(1,year);
		///////////?????????????????????????
		List<Double> p=query.list();		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<Double> getIrradiationDataByYearAndPSid(int year, int ps_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select total_irradiation from HistoryOfDay p where p.PS_id= ? and p.year = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setInteger(1,year);
		List<Double> p=query.list();		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}	
	@Override
	public double getTodayAccIradByPsID(int ps_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from WS_parameter where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0, ps_id);
		List<WS_parameter> l_pm = query1.list();
		double sum_irrad = 0;
		if(l_pm!=null){
			for(int i=0;i<l_pm.size();i++){
				String hql=" select top 1 Irraditation_value from Weather_station where Parameter_id = ? order by id desc";			
				Query query = session.createSQLQuery(hql);
				query.setInteger(0, l_pm.get(i).getId());
				if(query.uniqueResult()!=null){
					String result = query.uniqueResult().toString();
					double rs = Double.parseDouble(result);
					sum_irrad = sum_irrad + rs;
				}
			}
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return sum_irrad;
		
	}

	@Override
	public boolean insertDayData(HistoryOfDay dayData) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(dayData);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public double createDayAccPowerByPSIdAndDate(String date, int id) {
		// TODO Auto-generated method stub
		
		String maxTime = date+" 23:59:59.000";
		String minTime = date+" 00:00:00.000";
		int y = Integer.parseInt(date.substring(0, 4));
		int m = Integer.parseInt(date.substring(5, 7));
		int d = Integer.parseInt(date.substring(8, 10));
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "from PM_parameter where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0, id);
		
		String hql6 = "from WS_parameter where PS_id = ?";
		Query query6 = session.createQuery(hql6);
		query6.setInteger(0, id);
		
		List<PM_parameter> l_pm = query1.list();
		List<WS_parameter> l_ws = query6.list();
		double sum_power = 0;
		double Irraditation_value = 0;
		
		
		if(l_ws!=null){
			String hql3 = "select max( Irraditation_value) from Weather_station where time > ? and time < ? and Parameter_id = ?";	
			Query query3 = session.createSQLQuery(hql3);
			query3.setString(0, minTime);
			query3.setString(1, maxTime);
			query3.setInteger(2, l_ws.get(0).getId());
			if(query3.uniqueResult()!=null){
				String irr = query3.uniqueResult().toString();
				Irraditation_value = Double.parseDouble(irr);
			}
		}
		
		
		if(l_pm!=null){
			for(int i=0;i<l_pm.size();i++){
				String hql = "select min( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query = session.createSQLQuery(hql);
				String hql2 = "select max( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query2 = session.createSQLQuery(hql2);
				query.setString(0, minTime);
				query.setString(1, maxTime);
				query.setInteger(2, l_pm.get(i).getId());
				query2.setString(0, minTime);
				query2.setString(1, maxTime);
				query2.setInteger(2, l_pm.get(i).getId());
				if(query.uniqueResult()!=null){
					String minAccPower =  query.uniqueResult().toString();
					String maxAccPower =  query2.uniqueResult().toString();
					double min = Double.parseDouble(minAccPower);
					double max = Double.parseDouble(maxAccPower);
					sum_power =  sum_power + max - min;
				}
			}
		}
		
		String hql4 = "insert into HistoryOfDay (PS_id,total_power,max_irradiation," +
				"year,month,day,grid_connection_power,power_consumption,total_irradiation,direct_radiation,diffuse_radiation) values ("+id+","+sum_power+","+Irraditation_value+","+
				y+","+m+","+d+",0,0,0,0,0)";
		Query query4 = session.createSQLQuery(hql4);
		query4.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return sum_power;
	}

	@Override
	public String getMaxDateByPsId(int id) {//获取该表中最大的时间
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
		String hql1 = "select distinct max(year) from HistoryOfDay where PS_id = ?";	
		String hql2 = "select distinct max(month) from HistoryOfDay where PS_id = ?";
		String hql3 = "select distinct max(day) from HistoryOfDay where PS_id = ?";
		Query query1 = session.createSQLQuery(hql1);
		Query query2 = session.createSQLQuery(hql2);
		Query query3 = session.createSQLQuery(hql3);
		query1.setInteger(0, id);
		query2.setInteger(0, id);
		query3.setInteger(0, id);
		String date=null;
		if(query1.uniqueResult()!=null){
			int year = (Integer)query1.uniqueResult();
			int month = (Integer)query2.uniqueResult();
			int day = (Integer)query3.uniqueResult();			
			if(month<10&&day<10)date=year+"-0"+month+"-0"+day;
			else if(month<10&&day>=10)date=year+"-0"+month+"-"+day;
			else if(month>10&&day<10)date=year+"-"+month+"-0"+day;
			else date = year+"-"+month+"-"+day;
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return date;
	}

	@Override
	public void clearData(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String h = "delete from HistoryOfDay where PS_id = ?";
		Query query5 = session.createQuery(h);
		query5.setInteger(0, id);
		 query5.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
			
		
	}

	@Override
	public String getMaxMonthTimeByPSId(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql1 = "select distinct max(year) from HistoryOfDay where PS_id = ?";
		String hql2 = "select distinct max(month) from HistoryOfDay where PS_id = ?";
		Query query1 = session.createSQLQuery(hql1);
		Query query2 = session.createSQLQuery(hql2);
		query1.setInteger(0, id);
		query2.setInteger(0, id);
		int year =(Integer) query1.uniqueResult();
		int month =(Integer) query2.uniqueResult();
		if(month==12){
			month=1;year=year+1;
		}
		else month=month+1;
		String result = year+"-"+month;
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return 	result;
	}

	@Override
	public String getMinMonthTimeByPSId(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql1 = "select distinct min(year) from HistoryOfDay where PS_id = ?";
		String hql2 = "select distinct min(month) from HistoryOfDay where PS_id = ?";
		Query query1 = session.createSQLQuery(hql1);
		Query query2 = session.createSQLQuery(hql2);
		query1.setInteger(0, id);
		query2.setInteger(0, id);
		int year =(Integer) query1.uniqueResult();
		int month =(Integer) query2.uniqueResult();
		
		String result = year+"-"+month;
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return 	result;
	}

	@Override
	public List<Integer> getAllHistoryMonth() {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select distinct month from HistoryOfDay order by month desc";
		Query query = session.createQuery(hql);		
		List<Integer> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}
}
