package com.PSMS.Dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.PSMS.Dao.IBiIndexDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.PowerStationBase;

/**
 * 首页bi数据
 * @author Andy
 *
 */
public class BiIndexDaoImpl implements IBiIndexDao{

	@Override//当天日发电量
	public List<PowerStationBase> getCurrDayQ(String currTime, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" DateName(hour,tod.OperateDate) as groupHour, ");
		buffer.append(" sum(tod.CurrDayQ) CurrDayQ ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(100),tod.OperateDate, 23)=? ");
		buffer.append("  and inp.PS_id=? ");
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, currTime);
		query.setInteger(1, psId);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			
			if(obj[0]!=null){
				power.setGroupHour(Integer.parseInt(obj[0] + ""));
			}
			
			if(obj[1]!=null){
				power.setCurrDayQ(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			reList.add(power);
		}
		return reList;
	}

	
	@Override
	public PowerStationBase getCurrDayCountQ(String currTime, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(tod.CurrDayQ) currDayCountQ ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(100),tod.OperateDate, 23)=? ");
		buffer.append("  and inp.PS_id=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, currTime);
		query.setInteger(1, psId);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object obj=list.get(i);
			if(obj!=null){
				power.setCurrDayCountQ(new BigDecimal(obj + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		return power;
	}
	
	/**
	 * 某个月每天的数据
	 */
	@Override
	public List<PowerStationBase> getCurrMonthQ(String currMonth, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" DateName(day,tod.OperateDate) as groupDay, ");
		buffer.append(" sum(tod.CurrDayQ) currMonthQ ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(7),tod.OperateDate, 20)=? ");
		buffer.append(" and inp.PS_id=? ");
		buffer.append(" group by DateName(day,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, currMonth);
		query.setInteger(1, psId);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			
			if(obj[0]!=null){
				power.setGroupDay(Integer.parseInt(obj[0] + ""));
			}
			
			if(obj[1]!=null){
				power.setCurrMonthQ(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			reList.add(power);
		}
		return reList;
	}
	
	@Override
	public PowerStationBase getCurrMonthCountQ(String currMonth, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(tod.CurrDayQ) currMonthCountQ ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(7),tod.OperateDate, 20)=? ");
		buffer.append(" and inp.PS_id=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, currMonth);
		query.setInteger(1, psId);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if(obj!=null){
				power.setCurrMonthCountQ(new BigDecimal(obj + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		return power;
	}


	@Override
	public List<PowerStationBase> getCurrYearQ(String currYear, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" DateName(month,tod.OperateDate) as groupMonth, ");
		buffer.append(" sum(tod.CurrDayQ) currYearQ ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(4),tod.OperateDate, 20)=? ");
		buffer.append(" and inp.PS_id=? ");
		buffer.append(" group by DateName(month,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, currYear);
		query.setInteger(1, psId);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			if(obj[0]!=null){
				power.setGroupMonth(Integer.parseInt(obj[0] + ""));
			}
			if(obj[1]!=null){
				power.setCurrYearQ(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			reList.add(power);
		}
		return reList;
	}
	

	@Override
	public PowerStationBase getCurrYearCountQ(String currYear, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(tod.CurrDayQ) currYearCountQ ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(4),tod.OperateDate, 20)=? ");
		buffer.append(" and inp.PS_id=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, currYear);
		query.setInteger(1, psId);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object obj = (Object) list.get(i);
			if(obj!=null){
				power.setCurrYearCountQ(new BigDecimal(obj + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		return power;
	}

	@Override//实时数据仪表盘
	public PowerStationBase getCurrDashboard(int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" top 1 tod.OutputVoltage voltage,tod.OutputCurrent curr, ");
		buffer.append(" tod.OutputVoltage* tod.OutputCurrent as power ");
		buffer.append(" from bd_to_data tod   ");
		buffer.append(" inner join Inverter_parameter inp  on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where inp.PS_id=? ");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			if(obj[0]!=null){
				power.setVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			
			if(obj[2]!=null){
				power.setPower(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		return power;
	}

	@Override//历史发电量  减排二氧化碳
	public PowerStationBase getHistoryQAndObligate(int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(tod.CurrHistoryQ) CurrHistoryQ,sum(tod.Carbon) Carbon ");
		buffer.append(" from bd_to_data tod   ");
		buffer.append(" inner join Inverter_parameter inp  on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where inp.PS_id=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			if(obj[0]!=null){
				power.setHistoryCountQ(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setCountCarbon(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		return power;
	}
}
