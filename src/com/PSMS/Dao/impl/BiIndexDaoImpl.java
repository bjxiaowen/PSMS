package com.PSMS.Dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.PSMS.Dao.IBiIndexDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.DataUtils;

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
			power.setGroupHour(DataUtils.getInteger(obj[0]));
			power.setCurrDayQ(DataUtils.getDecimal(obj[1]));
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
			power.setCurrDayCountQ(DataUtils.getDecimal(obj));
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
			power.setGroupDay(DataUtils.getInteger(obj[0]));
			power.setCurrMonthQ(DataUtils.getDecimal(obj[1]));
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
			power.setCurrMonthCountQ(DataUtils.getDecimal(obj));
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
			power.setGroupMonth(DataUtils.getInteger(obj[0]));
			power.setCurrYearQ(DataUtils.getDecimal(obj[1]));
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
			power.setCurrYearCountQ(DataUtils.getDecimal(obj));
		}
		return power;
	}

	@Override//实时数据仪表盘
	public PowerStationBase getCurrDashboard(int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" top 1 tod.OutputVoltage voltage,tod.OutputCurrent curr, ");
		buffer.append(" (tod.OutputVoltage* tod.OutputCurrent)/1000 as power, ");
		buffer.append(" CONVERT(varchar(100), tod.OperateDate, 20) OperateDate "); // CONVERT(varchar(100), GETDATE(), 20)
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
			power.setVoltage(DataUtils.getDecimal(obj[0]));
			power.setCurrent(DataUtils.getDecimal(obj[1]));
			power.setPower(DataUtils.getDecimal(obj[2]));
			power.setOperateDate(DataUtils.getString(obj[3]));
		}
		return power;
	}

	@Override//历史发电量  减排二氧化碳
	public PowerStationBase getHistoryQAndObligate(int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(tod.CurrHistoryQ) CurrHistoryQ,sum(tod.Carbon) Carbon, ");
		buffer.append(" sum(DISTINCT (DATEDIFF (day , psi.Build_time , convert(varchar(10),getdate(),120) ))) dayCount ");
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
			power.setHistoryCountQ(DataUtils.getDecimal(obj[0]));
			power.setCountCarbon(DataUtils.getDecimal(obj[1]));
			power.setDayCount(DataUtils.getInteger(obj[2]));
		}
		return power;
	}
}
