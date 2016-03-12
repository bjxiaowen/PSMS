package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.JunctionBox;

public class JunctionBoxDAOImpl implements JunctionBoxDAO{

	@Override
	public List<JunctionBox> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		// TODO Auto-generated method stub
		String from[] = fromRangeDate.split("/");
		String to[] = toRangeDate.split("/");
		String fromdate = from[2]+"-"+from[0]+"-"+from[1]+" "+"00:00:00.000";
		String todate = to[2]+"-"+to[0]+"-"+to[1]+" "+"23:59:59.000";	//根据日期值得到数据库中要求的格式	
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from JunctionBox where  time > ? and time < ? and Parameter_id = ?";		
		Query query = session.createQuery(hql);
		query.setString(0,fromdate);
		query.setString(1,todate);
		query.setInteger(2,parameter_id);
		List<JunctionBox> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

}
