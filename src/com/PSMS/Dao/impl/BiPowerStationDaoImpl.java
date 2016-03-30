package com.PSMS.Dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationDaoImpl implements IBiPowerStationDao {
	
	@Override
	public PowerStationBase getPowerStationStatus(String dateTime, int psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" top 1 tod.MachineState,tod.OperateDate ");
		buffer.append(" from bd_to_data tod   ");
		buffer.append(" left join Inverter_parameter inp  on inp.name=tod.InverterID ");
		buffer.append(" left join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where CONVERT(varchar(100),tod.OperateDate, 23)=? ");
		buffer.append(" and inp.PS_id=? ");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			power.setStatus(Integer.parseInt(obj[0] + ""));
			power.setDate(new Date(obj[1] + ""));
		}
		return power;
	}
	
	
	@Override
	public PowerStationBase getPowerStationDayByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(psi.capacity) totalCapacity, ");
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent) as totalPower, ");
		buffer.append(" DateName(hour,GetDate()) as currHour ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)=? and inp.PS_id=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			power.setTotalCapacity(new BigDecimal(obj[0] + ""));
			power.setTotalPower(new BigDecimal(obj[1] + ""));
			power.setCurrHour(Integer.parseInt(obj[2] + ""));
		}
		return power;
	}

	@Override
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(psi.capacity) totalCapacity, ");
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent) as totalPower, ");
		buffer.append(" DateName(hour,GetDate()) as currHour, ");
		buffer.append(" DateName(hour,tod.OperateDate) as groupHour , ");
		buffer.append(" sum(tod.MpptOutCurrent) curr ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)=? ");
		buffer.append(" and inp.PS_id=? ");
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}

		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			power.setTotalCapacity(new BigDecimal(obj[0] + ""));
			power.setPower(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			// currHour, groupHour
			power.setCurrHour(Integer.parseInt(obj[2] + ""));
			power.setGroupHour(Integer.parseInt(obj[3] + ""));
			power.setCurrent(new BigDecimal(obj[4] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			reList.add(power);
		}
		return reList;
	}

	@Override
	public PowerStationBase getBatteryVoltageDayByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append("  sum(psi.capacity) totalCapacity, ");
		buffer.append(" sum(tod.MpptOutVoltage) * sum(tod.MpptOutCurrent) as totalPower, ");
		buffer.append(" DateName(hour,GetDate()) as currHour, ");
		buffer.append(" avg(tod.MpptTemp) mpptTemp,  ");
		buffer.append(" max(tod.BatteryVoltage) totalVoltage, ");
		buffer.append(" max(tod.MpptOutCurrent)totalCurrent ");
		//buffer.append(" sum(distinct tod.ChargeDischarge) chargeDischarge ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)= ? and inp.PS_id=?");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {//.setScale(2, BigDecimal.ROUND_HALF_UP)
			Object[] obj = (Object[]) list.get(i);
//			power.setTotalCapacity(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setTotalPower(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			// currHour
			power.setCurrHour(Integer.parseInt(obj[2] + ""));
			// mpptTemp
			power.setMpptTemp(new BigDecimal(obj[3] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			//totalVoltage ,totalCurrent,chargeDischarge
			power.setTotalVoltage(new BigDecimal(obj[4] + ""));
			power.setTotalCurrent(new BigDecimal(obj[5] + ""));
			//power.setChargeDischarge(Integer.parseInt(obj[6] + ""));
		}
		return power;
	}

	@Override
	public List<PowerStationBase> getBatteryVoltageHourByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select sum(tod.BatteryVoltage * tod.MpptOutCurrent) as power, ");
		buffer.append(" sum(tod.BatteryVoltage) batteryVoltage, ");
		buffer.append(" sum(tod.MpptOutCurrent) curr, ");
		buffer.append(" DateName(hour,GetDate()) as currHour, ");
		buffer.append(" DateName(hour,tod.OperateDate) as groupHour ");
		buffer.append(" from  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)= ? and inp.PS_id=? ");
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}

		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			//power,batteryVoltage,curr,currHour,groupHour
			power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrHour(Integer.parseInt(obj[3] + ""));
			power.setGroupHour(Integer.parseInt(obj[4] + ""));
			reList.add(power);
		}
		return reList;
	}

	@Override
	public PowerStationBase getControlPhotovoltaicDayByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(tod.MpptInVoltage) as totalPower,  ");
		buffer.append(" sum((tod.MpptOutVoltage*tod.MpptOutCurrent)/MpptInVoltage) as totalCurrent, ");
		buffer.append(" DateName(hour,GetDate()) as currHour  ");
		buffer.append(" from  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)= ? ");
		buffer.append(" and inp.PS_id =? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}

		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj = (Object[]) list.get(i);
			// totalPower,totalCurrent,currHour
			power.setTotalVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setTotalCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrHour(Integer.parseInt(obj[2] + ""));
			
		}
		return power;
	}

	//组件的输出
	@Override
	public List<PowerStationBase> getControlPhotovoltaicHourByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select ");
		buffer.append(" max(tod.MpptInVoltage) as voltage, ");
		buffer.append(" (sum((tod.MpptOutVoltage*tod.MpptOutCurrent)/tod.MpptInVoltage)) as curr, ");
		buffer.append(" (sum(((tod.MpptOutVoltage*tod.MpptOutCurrent)/tod.MpptInVoltage)*tod.MpptInVoltage)) as power, ");
		buffer.append(" DateName(hour,GetDate()) as currHour,  ");
		buffer.append(" DateName(hour,tod.OperateDate) as groupHour ");
		buffer.append(" from  Inverter_parameter inp  inner join bd_to_data tod on inp.name=tod.InverterID inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)=? and inp.PS_id=? ");
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			// voltage,curr,currHour,power,currHour,groupHour
			power.setVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setPower(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrHour(Integer.parseInt(obj[3] + ""));
			power.setGroupHour(Integer.parseInt(obj[4] + ""));
			reList.add(power);
		}
		return reList;
	}
	
	//控制器输出基本信息
	@Override
	public PowerStationBase getControlOutShowDayByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select sum(tod.OutputVoltage) totalVoltage, ");
		buffer.append(" sum(tod.OutputCurrent) totalCurrent, ");
		buffer.append(" sum(distinct tod.OutputState)status, ");//输出过载或者正常
		buffer.append(" DateName(hour,GetDate()) as currHour  ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)= ? and inp.PS_id= ? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			
			Object[] obj = (Object[]) list.get(i);
			// totalVoltage,totalCurrent,status,currHour
			power.setTotalVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setTotalCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setStatus(Integer.parseInt(obj[2] + ""));
			power.setCurrHour(Integer.parseInt(obj[3] + ""));
		}
		return power;
	}

	//控制器输出图表信息
	@Override
	public List<PowerStationBase> getControlOutShowHourByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select ");
		buffer.append(" sum(tod.OutputVoltage*tod.OutputCurrent)power, ");
		buffer.append(" max(tod.OutputVoltage) voltage, ");
		buffer.append(" max(tod.OutputCurrent)  curr , ");
		buffer.append(" DateName(hour,GetDate()) as currHour, ");
		buffer.append(" DateName(hour,tod.OperateDate) as groupHour ");
		buffer.append(" from  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)=? and  inp.PS_id= ? ");
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			// power,voltage,curr,currHour,groupHour
			power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrHour(Integer.parseInt(obj[3] + ""));
			power.setGroupHour(Integer.parseInt(obj[4] + ""));
			reList.add(power);
		}
		return reList;
	}

	//电池的输入
	@Override
	public PowerStationBase getControlInShowDayByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select ");
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent*DateName(hour,GetDate())) as totalPower,  ");
		buffer.append(" max(tod.MpptOutVoltage) totalVoltage,  ");
		buffer.append(" max(tod.MpptOutCurrent) totalCurrent,  ");
		buffer.append(" DateName(hour,GetDate()) as currHour  ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)= ? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			// totalPower,totalVoltage,totalCurrent,currHour
			power.setTotalPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setTotalVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setTotalCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrHour(Integer.parseInt(obj[3] + ""));
		}
		return power;
	}

	@Override
	public List<PowerStationBase> getControlInShowHourByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select sum(tod.MpptOutVoltage * tod.MpptOutCurrent) as power,  ");
		buffer.append("  sum(tod.MpptOutVoltage) voltage,  ");
		buffer.append("  sum(tod.MpptOutCurrent) curr,  ");
		buffer.append("  DateName(hour,GetDate()) as currHour,  ");
		buffer.append("  DateName(hour,tod.OperateDate) as groupHour   ");
		buffer.append(" from  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)=? and inp.PS_id=?");
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			// power,voltage,curr,currHour,groupHour
			power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrHour(Integer.parseInt(obj[3] + ""));
			power.setGroupHour(Integer.parseInt(obj[4] + ""));
			reList.add(power);
		}
		return reList;
	}

	@Override
	public List<PowerStationBase> getElectricEveryDayByDate(int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("   select sum(tod.MpptOutVoltage*tod.MpptOutCurrent*24)power,  ");
		buffer.append("   sum(tod.MpptOutVoltage) voltage,   ");
		buffer.append("  sum(tod.MpptOutCurrent)  curr ,   ");
		buffer.append("   CONVERT(varchar(100), OperateDate, 23) date  ");
		buffer.append("  from  Inverter_parameter inp    ");
		buffer.append("  inner join bd_to_data tod on inp.name=tod.InverterID   ");
		buffer.append("  inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append("  group by CONVERT(varchar(100), OperateDate, 23)  and  inp.PS_id=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			PowerStationBase power = new PowerStationBase();
			Object[] obj = (Object[]) list.get(i);
			// power,voltage,curr,date
			power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			power.setDate(new Date(obj[3] + ""));
			reList.add(power);
		}
		return reList;
	}

}
