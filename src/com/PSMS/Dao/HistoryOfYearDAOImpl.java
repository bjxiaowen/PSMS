package com.PSMS.Dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.HistoryOfYear;
import com.PSMS.Hibernate.PM_parameter;


public class HistoryOfYearDAOImpl implements HistoryOfYearDAO {

	@Override
	public List<HistoryOfYear> getDataByYear(int year) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from HistoryOfYear where year = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,year);
		List<HistoryOfYear> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Integer> getAllHistoryYear() {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select distinct year from HistoryOfYear order by year desc";
		Query query = session.createQuery(hql);		
		List<Integer> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public double getSumPowerOfYear() {
		// TODO Auto-generated method stub
		int y;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		
			String maxTime = y+"-12-31 23:59:59.000";
			String minTime = y+"-01-01 00:00:00.000";
			Session session = HibernateSessionFactory.getHibernateSession();
			HibernateSessionFactory.begainHibernateTransaction();	
			String hql1 = "from PM_parameter";
			Query query1 = session.createQuery(hql1);
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
				return sum_power;
			}
			else return 0;
	}
	@Override
	public double getPowerByPsIDAndYear(int ps_id, int year) {
		// TODO Auto-generated method stub
		String maxTime = year+"-12-31 23:59:59.000";
		String minTime = year+"-01-01 00:00:00.000";
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
			return sum_power;
		}
		else return 0;
	}

	@Override
	public List<HistoryOfYear> getDataByYearAndPs_id(int year, int ps_id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from HistoryOfYear where year = ?  and PS_id=? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,year);
		query.setInteger(1, ps_id);
		List<HistoryOfYear> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public boolean insertYearData(HistoryOfYear yearData) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(yearData);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean isEmptyByPsId(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String h = " from HistoryOfYear where PS_id = ?";
		Query query5 = session.createQuery(h);
		query5.setInteger(0, id);
		if(query5.list()==null){
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

	@Override
	public void clearDataByPsID(int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String h = "delete from HistoryOfYear where PS_id = ?";
		Query query5 = session.createQuery(h);
		query5.setInteger(0, id);
		 query5.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	}

	@Override
	public void createDataByYearAndPsId(int year, int id) {
		// TODO Auto-generated method stub
		String maxTime = year+"-12-31 23:59:59.000";
		String minTime = year+"-01-01 00:00:00.000";
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	
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
			String hql4 = "insert into HistoryOfYear (PS_id,total_power,total_hour,year)" +
			" values ("+id+","+sum_power+",0,"+year+")";
			Query query4 = session.createSQLQuery(hql4);
			query4.executeUpdate();
		}
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	}
	
}
