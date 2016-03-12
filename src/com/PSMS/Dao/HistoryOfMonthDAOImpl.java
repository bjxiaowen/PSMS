package com.PSMS.Dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.HistoryOfDay;
import com.PSMS.Hibernate.HistoryOfMonth;
import com.PSMS.Hibernate.PM_parameter;

public class HistoryOfMonthDAOImpl implements HistoryOfMonthDAO{

	@Override
	public List<HistoryOfMonth> getDataByPs_idAndYear(int ps_id, int year) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from HistoryOfMonth where ps_id = ? and year = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,ps_id);
		query.setInteger(1,year);
		List<HistoryOfMonth> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Integer> getAllHistoryYear() {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select distinct year from HistoryOfMonth order by year desc";
		Query query = session.createQuery(hql);		
		List<Integer> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public boolean insertMonthData(HistoryOfMonth monthData) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(monthData);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public void clearDataByPsID(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String h = "delete from HistoryOfMonth where PS_id = ?";
		Query query5 = session.createQuery(h);
		query5.setInteger(0, id);
		 query5.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
			
	}

	@Override
	public void createDataByMonthAndPsId(String str, int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		//str格式为yyyy-mm
		int year = Integer.parseInt(str.substring(0, 4));
		int month = Integer.parseInt(str.substring(5, 7));
		Calendar cal = Calendar.getInstance();
		  //取当前月份最后一天
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String end = sdf.format(lastDate);
		String startTime= str+"-01 00:00:00";
		String endTime= end+" 23:59:59";
		String hql1 = "from PM_parameter where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0, id);
		
		List<PM_parameter> l_pm = query1.list();
		double sum_power = 0;
		if(l_pm!=null){
			for(int i=0;i<l_pm.size();i++){
				String hql = "select min( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query = session.createSQLQuery(hql);
				String hql2 = "select max( Acc_power) from Power_meter where time > ? and time < ? and Parameter_id = ?";	
				Query query2 = session.createSQLQuery(hql2);
				query.setString(0, startTime);
				query.setString(1, endTime);
				query.setInteger(2, l_pm.get(i).getId());
				query2.setString(0, startTime);
				query2.setString(1, endTime);
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
		String hql4 = "insert into HistoryOfMonth (PS_id,total_power,total_hour,year,month)" +
		" values ("+id+","+sum_power+",0,"+year+","+month+")";
		
		Query query4 = session.createSQLQuery(hql4);
		query4.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	}

	@Override
	public boolean isEmptyByPsId(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql1 = "from HistoryOfMonth where PS_id = ?";
		Query query1 = session.createQuery(hql1);
		query1.setInteger(0, id);
		List<HistoryOfMonth> l_pm = query1.list();
		
		if(l_pm==null){
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return true;
		}	
		else{
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return false;	
		}
		
	}

}
