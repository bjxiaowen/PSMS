package com.PSMS.Dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationDaoImpl implements IBiPowerStationDao {
	
	/**
	 * 查询电站状态
	 */
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
			if(obj[0]!=null){
				power.setMachineState(Integer.parseInt(obj[0] + ""));
			}
			if(obj[1]!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
			    try {
					Date date = sdf.parse(obj[1] + "");
					power.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				
			}
		}
		return power;
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
			if(obj[1]!=null){
				power.setTotalPower(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				// currHour
				power.setCurrHour(Integer.parseInt(obj[2] + ""));
			}
			if(obj[3] !=null){
				// mpptTemp
				power.setMpptTemp(new BigDecimal(obj[3] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[4]!=null){
				//totalVoltage ,totalCurrent,chargeDischarge
				power.setTotalVoltage(new BigDecimal(obj[4] + ""));
			}
			if(obj[5]!=null){
				power.setTotalCurrent(new BigDecimal(obj[5] + ""));
			}
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
			if(obj[0]!=null){
				power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[3]!=null){
				power.setCurrHour(Integer.parseInt(obj[3] + ""));
			}
			if(obj[4]!=null){
				power.setGroupHour(Integer.parseInt(obj[4] + ""));
			}
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
			if(obj[0]!=null){
				power.setTotalVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setTotalCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setCurrHour(Integer.parseInt(obj[2] + ""));
			}
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
			if(obj[0]!=null){
				power.setVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setPower(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[3]!=null){
				power.setCurrHour(Integer.parseInt(obj[3] + ""));
			}
			if(obj[4]!=null){
				power.setGroupHour(Integer.parseInt(obj[4] + ""));
			}
			
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
			if(obj[0]!=null){
				power.setTotalVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setTotalCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setCurrHour(Integer.parseInt(obj[3] + ""));
			}
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
			if(obj[0]!=null){
				power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[3]!=null){
				power.setCurrHour(Integer.parseInt(obj[3] + ""));
			}
			if(obj[4]!=null){
				power.setGroupHour(Integer.parseInt(obj[4] + ""));
			}
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
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)= ? and inp.PS_id= ?  ");
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
			if(obj[0]!=null){
				power.setTotalPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setTotalVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setTotalCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[3]!=null){
				power.setCurrHour(Integer.parseInt(obj[3] + ""));
			}
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
			if(obj[0]!=null){
				power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[3]!=null){
				power.setCurrHour(Integer.parseInt(obj[3] + ""));
			}
			if(obj[4]!=null){
				power.setGroupHour(Integer.parseInt(obj[4] + ""));
			}
			reList.add(power);
		}
		return reList;
	}

	@SuppressWarnings("deprecation")
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
			if(obj[0]!=null){
				power.setPower(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[1]!=null){
				power.setVoltage(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[2]!=null){
				power.setCurrent(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[3]!=null){
				power.setDate(new Date(obj[3] + ""));
			}
			reList.add(power);
		}
		return reList;
	}

	/**
	 * 查询电站最新输出状态
	 */
	@Override
	public PowerStationBase getOutputStatus(String dateTime, int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" top 1 tod.OutputState,tod.OperateDate,psi.id ,tod.MachineState,tod.Undervoltage,tod.ChargeDischarge ");
		buffer.append(" from  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)= ? ");
		buffer.append(" and  inp.PS_id=? ");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase rePs = new PowerStationBase();
		if(list==null||list.size()==0){
			return rePs;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			if(obj[0]!=null){
				rePs.setOutputState(Integer.parseInt(obj[0] + ""));
			}
			if(obj[2]!=null){
				rePs.setPsId(Integer.parseInt(obj[2] + ""));
			}
			if(obj[3]!=null){
				rePs.setMachineState(Integer.parseInt(obj[3] + ""));
			}
			//Undervoltage
			if(obj[4]!=null){
				rePs.setUndervoltage(Integer.parseInt(obj[4] + ""));
			}
			
			if(obj[5]!=null){
				rePs.setChargeDischarge(Integer.parseInt(obj[5] + ""));
			}
		}
		return rePs;
	}

	@Override
	public PowerStationBase getNewestStatus(String dateTime, int psId, String type) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select top 1 ");
		buffer.append(" tod.MpptOutVoltage Voltage, ");//电压
		buffer.append(" tod.MpptOutCurrent curr, ");//电流
		buffer.append(" (tod.MpptOutVoltage * tod.MpptOutCurrent) power, ");//功率
		buffer.append(" tod.Undervoltage , ");//欠压
		buffer.append(" tod.ChargeDischarge  chargeDischarge,");//0：电池充电,1：电池放电
		buffer.append(" tod.MpptTemp mpptTemp,  ");//MpptTemp 温度
		buffer.append(" DateName(hour,GetDate()) as currHour ");
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),tod.OperateDate, 23)= ? and inp.PS_id=?");
		buffer.append(" and inp.type=?");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		query.setString(2, type);
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
				power.setVoltage(new BigDecimal(obj[0] + "").setScale(2, BigDecimal.ROUND_HALF_UP));//电压
			}
			if(obj[1]!=null){
				power.setCurrent(new BigDecimal(obj[1] + "").setScale(2, BigDecimal.ROUND_HALF_UP));//电流
			}
			if(obj[2] !=null){
				power.setPower(new BigDecimal(obj[2] + "").setScale(2, BigDecimal.ROUND_HALF_UP));//功率
			}
			if(obj[3]!=null){//欠压
				power.setUndervoltage(Integer.parseInt(obj[3] + ""));
			}
			if(obj[4]!=null){//温度
				power.setMpptTemp(new BigDecimal(obj[4] + "").setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if(obj[5]!=null){//时间
				power.setCurrHour(Integer.parseInt(obj[5] + ""));
			}
		}
		return power;
	
	}

	@Override
	public PowerStationBase getPSOutOneData(String dateTime, int psId, String type) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(distinct psi.capacity) totalCapacity, ");//容量
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent) as totalPower, ");//输出功率
		buffer.append(" sum(tod.MpptOutVoltage)  totalVoltage ,");//电压
		buffer.append(" sum(tod.MpptOutCurrent)  totalCurrent , ");//电流
		buffer.append(" DateName(hour,GetDate()) as currHour ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)=? and inp.PS_id=? ");
		buffer.append(" and inp.type=? ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		query.setString(2, type);
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
				power.setTotalCapacity(new BigDecimal(obj[0] + ""));
			}
			
			if(obj[1]!=null){
				power.setTotalPower(new BigDecimal(obj[1] + ""));
			}
			
			if(obj[2]!=null){
				power.setTotalVoltage(new BigDecimal(obj[2] + ""));
			}
			
			if(obj[3]!=null){
				power.setTotalCurrent(new BigDecimal(obj[3] + ""));
			}
			
			if(obj[4]!=null){
				power.setCurrHour(Integer.parseInt(obj[4] + ""));
			}
		}
		return power;
	}

	@Override
	public List<Inverter_parameter> getParameter(int ps_id, String type) {
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = " from Inverter_parameter where PS_id = ? and type=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		query.setString(1, type);
		List<Inverter_parameter> parame = query.list();
		HibernateSessionFactory.closeHibernateSession();
		return parame;
	}

	@Override
	public List<PowerStationBase> getPSHourlyData(String dateTime, int psId, String type) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" (tod.MpptOutVoltage * tod.MpptOutCurrent) as power, ");//功率
		buffer.append(" tod.MpptOutVoltage  voltage ,");//电压
		buffer.append(" tod.MpptOutCurrent  curr , ");//电流
		buffer.append(" DateName(hour,GetDate()) as groupHour ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)=? and inp.PS_id=? ");
		buffer.append(" and inp.type=? ");//组件
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
		query.setString(2, type);
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			PowerStationBase power = new PowerStationBase();
			
			if(obj[0]!=null){
				power.setPower(new BigDecimal(obj[0] + ""));
			}
			
			if(obj[1]!=null){
				power.setVoltage(new BigDecimal(obj[1] + ""));
			}
			
			if(obj[2]!=null){
				power.setCurrent(new BigDecimal(obj[2] + ""));
			}
			
			if(obj[3]!=null){
				power.setGroupHour(Integer.parseInt(obj[3] + ""));
			}
			reList.add(power);
		}
		return reList;
	}

}
