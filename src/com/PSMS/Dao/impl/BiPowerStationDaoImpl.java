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
import com.PSMS.pojo.BIPSBaseData;
import com.PSMS.pojo.InParameter;
import com.PSMS.pojo.PSEquipment;
import com.PSMS.pojo.PSTotal;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.DataUtils;

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
//		buffer.append("  where CONVERT(varchar(100),tod.OperateDate, 23)=? ");
		buffer.append("  where  ");
		buffer.append("  inp.PS_id=? ");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
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
		buffer.append(" sum(psi.capacity) totalCapacity, ");
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
			power.setPower(DataUtils.getDecimal(obj[0]));
			power.setVoltage(DataUtils.getDecimal(obj[1]));
			power.setCurrent(DataUtils.getDecimal(obj[2]));
			power.setCurrHour(DataUtils.getInteger(obj[3]));
			power.setGroupHour(DataUtils.getInteger(obj[4]));
			
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
			power.setTotalVoltage(DataUtils.getDecimal(obj[0]));
			power.setTotalCurrent(DataUtils.getDecimal(obj[1]));
			power.setCurrHour(DataUtils.getInteger(obj[2] ));
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
			power.setVoltage(DataUtils.getDecimal(obj[0]));
			power.setCurrent(DataUtils.getDecimal(obj[1]));
			power.setPower(DataUtils.getDecimal(obj[2]));
			power.setCurrHour(DataUtils.getInteger(obj[3]));
			power.setGroupHour(DataUtils.getInteger(obj[4]));
			
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
			// totalVoltage,totalCurrent,status,currHour DataUtils
			power.setTotalVoltage(DataUtils.getDecimal(obj[0]));
			power.setTotalCurrent(DataUtils.getDecimal(obj[1]));
			power.setCurrHour(DataUtils.getInteger(obj[2]));
		}
		return power;
	}

	//控制器输出图表信息
	@Override
	public List<PowerStationBase> getControlOutShowHourByDate(String dateTime,int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select ");
		buffer.append(" sum(tod.OutputVoltage*tod.OutputCurrent)/1000   power, ");
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
			power.setPower(DataUtils.getDecimal(obj[0]));
			power.setVoltage(DataUtils.getDecimal(obj[1]));
			power.setCurrent(DataUtils.getDecimal(obj[2]));
			power.setCurrHour(DataUtils.getInteger(obj[3]));
			power.setGroupHour(DataUtils.getInteger(obj[4]));
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
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent*DateName(hour,GetDate()))/1000 as totalPower,  ");
		buffer.append(" max(tod.MpptOutVoltage) totalVoltage,  ");
		buffer.append(" max(tod.MpptOutCurrent) totalCurrent,  ");
		buffer.append(" DateName(hour,GetDate()) as currHour  ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" where  inp.PS_id= ?  ");//CONVERT(varchar(100),OperateDate, 23)= ? and
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
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
			// totalPower,totalVoltage,totalCurrent,currHour 
			power.setTotalPower(DataUtils.getDecimal(obj[0]));
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
		buffer.append("  select sum(tod.MpptOutVoltage * tod.MpptOutCurrent)/1000 as power,  ");
		buffer.append("  sum(tod.MpptOutVoltage) voltage,  ");
		buffer.append("  sum(tod.MpptOutCurrent) curr,  ");
		buffer.append("  DateName(hour,GetDate()) as currHour,  ");
		buffer.append("  DateName(hour,tod.OperateDate) as groupHour   ");
		buffer.append(" from  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where  inp.PS_id=?");//CONVERT(varchar(100),tod.OperateDate, 23)=? and
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
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
			power.setPower(DataUtils.getDecimal(obj[0]));
			power.setVoltage(DataUtils.getDecimal(obj[1]));
			power.setCurrent(DataUtils.getDecimal(obj[2]));
			power.setCurrHour(DataUtils.getInteger(obj[3]));
			power.setGroupHour(DataUtils.getInteger(obj[4]));
			reList.add(power);
		}
		return reList;
	}

	@Override
	public List<PowerStationBase> getElectricEveryDayByDate(int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("   select sum(tod.MpptOutVoltage*tod.MpptOutCurrent*24)/1000 power,  ");
		buffer.append("   sum(tod.MpptOutVoltage) voltage,   ");
		buffer.append("   sum(tod.MpptOutCurrent)  curr ,   ");
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
			power.setPower(DataUtils.getDecimal(obj[0]));
			power.setVoltage(DataUtils.getDecimal(obj[1]));
			power.setCurrent(DataUtils.getDecimal(obj[2]));
			power.setDate(DataUtils.getDate(obj[3]));
			
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
		buffer.append(" where  ");//CONVERT(varchar(100),tod.OperateDate, 23)= ? and
		buffer.append("   inp.PS_id=? ");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase rePs = new PowerStationBase();
		if(list==null||list.size()==0){
			return rePs;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			rePs.setOutputState(DataUtils.getInteger(obj[0]));
			rePs.setPsId(DataUtils.getInteger(obj[2]));
			rePs.setMachineState(DataUtils.getInteger(obj[3]));
			rePs.setUndervoltage(DataUtils.getInteger(obj[4]));
			rePs.setChargeDischarge(DataUtils.getInteger(obj[5]));
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
		buffer.append(" (tod.MpptOutVoltage * tod.MpptOutCurrent)/1000 power, ");//功率
		buffer.append(" tod.Undervoltage , ");//欠压
		buffer.append(" tod.ChargeDischarge  chargeDischarge,");//0：电池充电,1：电池放电
		buffer.append(" tod.MpptTemp mpptTemp,  ");//MpptTemp 温度
		buffer.append(" DateName(hour,GetDate()) as currHour, ");
		buffer.append(" tod.MachineState ");//机器状态
		
		buffer.append(" from Inverter_parameter inp  ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where  inp.PS_id=?");//CONVERT(varchar(100),tod.OperateDate, 23)= ? and
		buffer.append(" and inp.type=?");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		query.setString(1, type);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			power.setVoltage(DataUtils.getDecimal(obj[0]));//电压
			power.setCurrent(DataUtils.getDecimal(obj[1]));//电流
			power.setPower(DataUtils.getDecimal(obj[2]));//功率
			power.setUndervoltage(DataUtils.getInteger(obj[3]));
			power.setChargeDischarge(DataUtils.getInteger(obj[4]));
			power.setMpptTemp(DataUtils.getDecimal(obj[5]));
			power.setCurrHour(DataUtils.getInteger(obj[6]));
			power.setMachineState(DataUtils.getInteger(obj[7]));
		}
		return power;
	
	}
	

	@Override
	public PowerStationBase getPSOutOneData(String dateTime, int psId, String type) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(distinct psi.capacity) totalCapacity, ");//容量
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent)/1000 as totalPower, ");//输出功率
		buffer.append(" sum(tod.MpptOutVoltage)  totalVoltage ,");//电压
		buffer.append(" sum(tod.MpptOutCurrent)  totalCurrent , ");//电流
		buffer.append(" DateName(hour,GetDate()) as currHour ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where inp.PS_id=? ");//CONVERT(varchar(100),OperateDate, 23)=? and 
		buffer.append(" and inp.type=? ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		query.setString(1, type);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PowerStationBase power = new PowerStationBase();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			power.setTotalCapacity(DataUtils.getDecimal(obj[0]));
			power.setTotalPower(DataUtils.getDecimal(obj[1]));
			power.setTotalVoltage(DataUtils.getDecimal(obj[2]));
			power.setTotalCurrent(DataUtils.getDecimal(obj[3]));
			power.setCurrHour(DataUtils.getInteger(obj[4]));
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
		@SuppressWarnings("unchecked")
		List<Inverter_parameter> parame = query.list();
		HibernateSessionFactory.closeHibernateSession();
		return parame;
	}

	@Override
	public List<PowerStationBase> getPSHourlyData(String dateTime, int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		
		/**20160531由于要查询最新数据所以sql比较复杂只考虑实现没有考虑性能**/
		/*buffer.append(" select ");
		buffer.append(" b.ExchangeOutPower as power, ");
		buffer.append(" b.X_TPV_Voltage as voltage, ");
		buffer.append(" b.X_TPV_Current as curr, ");
		buffer.append(" DateName(hour,b.operatedate) as groupHour  ");
		buffer.append(" from bd_to_data b right join  ");
		buffer.append(" (SELECT ");
		buffer.append(" max(tod.operateDate) as operatedate ");
		buffer.append(" FROM ");
		buffer.append(" Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" WHERE ");
//		buffer.append(" convert(varchar(30),tod.operateDate,23) = ? and ");
		buffer.append("  inp.PS_id=? ");
		buffer.append(" GROUP BY ");
		buffer.append(" DATEPART(hh,tod.operateDate)) c on b.OperateDate = c.operatedate ");*/
		
		buffer.append(" select atmp.power,atmp.voltage,atmp.curr,DateName(hour,atmp.operatedate) as groupHour,atmp.X_TPV_Power ");
		buffer.append(" from ( ");
		buffer.append(" select distinct ");
		buffer.append(" b.ExchangeOutPower as power,  b.X_TPV_Voltage as voltage,  b.X_TPV_Current as curr,  ");
		buffer.append(" b.operatedate ,inp.PS_id,b.X_TPV_Power  ");
		buffer.append(" from bd_to_data b ");
		buffer.append(" inner join Inverter_parameter inp on inp.name=b.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" WHERE   inp.PS_id=?  ");
		buffer.append(" ) atmp inner join ( ");
		buffer.append(" select max(b.OperateDate)  as odate,max(inp.PS_id) psId ");
		buffer.append(" from bd_to_data b ");
		buffer.append(" inner join Inverter_parameter inp on inp.name=b.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id   ");
		buffer.append(" WHERE   inp.PS_id=?  ");
		buffer.append(" and CONVERT(varchar(100),b.operateDate, 23)=( ");
		buffer.append(" SELECT  CONVERT(varchar(100),max(tod.operateDate), 23) as operatedate ");
		buffer.append(" FROM  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID  ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" WHERE   inp.PS_id=? ");
		buffer.append(" ) ");
		buffer.append(" GROUP BY  DATEPART(hh,b.operateDate) ");
		buffer.append(" ) btmp on btmp.odate=atmp.operatedate and btmp.psId=atmp.PS_id ");
		
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		query.setInteger(1, psId);
		query.setInteger(2, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			PowerStationBase power = new PowerStationBase();
			power.setPower(DataUtils.getDecimal(obj[0]));
			power.setVoltage(DataUtils.getDecimal(obj[1]));
			power.setCurrent(DataUtils.getDecimal(obj[2]));
			power.setGroupHour(DataUtils.getInteger(obj[3]));
			power.setX_TPV_Power(DataUtils.getDecimal(obj[4]));
			reList.add(power);
		}
		return reList;
	}

	@Override
	public BIPSBaseData getNewesData(String dateTime, int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" top 1 ");
		buffer.append(" tod.X_TPV_Voltage,");	
		buffer.append(" tod.X_TPV_Current,  ");
		buffer.append(" tod.BatteryVoltage, ");
		buffer.append(" tod.X_Battery_Current ,");
		buffer.append(" tod.ExchangeOutPower ,");
		buffer.append(" tod.OutputVoltage,  ");
		buffer.append(" tod.OutputCurrent,  ");
		buffer.append(" tod.X_AC_Frequency, ");
		buffer.append(" tod.X_TPV_Power,    ");
		buffer.append(" (tod.X_Battery_Current*tod.BatteryVoltage)/1000 as  BatteryPower,");
		buffer.append(" tod.MpptTemp,       ");
		buffer.append(" tod.X_Run_Status ,  ");
		buffer.append(" tod.ChargeDischarge ,");
		buffer.append(" tod.X_Battery_tem, ");
		buffer.append(" tod.X_Failcode_1, ");
		buffer.append(" tod.X_Battery_Capacity, ");
		buffer.append(" tod.X_Coutpout_Voltage, ");
		buffer.append(" tod.X_Coutpout_Current,  ");
		buffer.append(" tod.X_Coutpout_Power, ");
		buffer.append(" tod.X_Inerin_tem, ");
		buffer.append(" tod.MachineState, ");
		buffer.append("  CONVERT(varchar(100), tod.OperateDate, 20) OperateDate, ");
		buffer.append(" (( tod.OutputVoltage * tod.OutputCurrent)/1000) as OutputPower ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where ");
//		buffer.append(" CONVERT(varchar(100),OperateDate, 23)=? and ");
		buffer.append("  inp.PS_id=? ");
		buffer.append(" order by tod.OperateDate desc ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		BIPSBaseData power = new BIPSBaseData();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			power.setX_TPV_Voltage(DataUtils.getDecimal(obj[0]));
			power.setX_TPV_Current(DataUtils.getDecimal(obj[1]));
			power.setBatteryVoltage(DataUtils.getDecimal(obj[2]));
			power.setX_Battery_Current(DataUtils.getDecimal(obj[3]));
			power.setExchangeOutPower(DataUtils.getDecimal(obj[4]));
			power.setOutputVoltage(DataUtils.getDecimal(obj[5]));
			power.setOutputCurrent(DataUtils.getDecimal(obj[6]));
			power.setX_AC_Frequency(DataUtils.getDecimal(obj[7]));
			power.setX_TPV_Power(DataUtils.getDecimal(obj[8]));
			power.setBatteryPower(DataUtils.getDecimal(obj[9]));
			power.setMpptTemp(DataUtils.getDecimal(obj[10]));
			power.setX_Run_Status(DataUtils.getInteger(obj[11]));
			power.setChargeDischarge(DataUtils.getInteger(obj[12]));
			power.setX_Battery_tem(DataUtils.getDecimal(obj[13]));
			power.setX_Failcode_1(DataUtils.getInteger(obj[14]));
			power.setX_Battery_Capacity(DataUtils.getInteger(obj[15]));
			power.setX_Coutpout_Voltage(DataUtils.getDecimal(obj[16]));
			power.setX_Coutpout_Current(DataUtils.getDecimal(obj[17]));
			power.setX_Coutpout_Power(DataUtils.getDecimal(obj[18]));
			power.setX_Inerin_tem(DataUtils.getDecimal(obj[19]));
			power.setMachineState(DataUtils.getDecimal(obj[20]));
			power.setOperateDate(DataUtils.getString(obj[21]));
			power.setOutputPower(DataUtils.getDecimal(obj[22]));
		}
		return power;
	}

	@Override
	public PSTotal getPSTotalData() throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" count(distinct psi.id) totalPS, ");
		buffer.append(" sum(psi.capacity) totalCapacity, ");
		buffer.append(" sum(tod.LoadHistoryQ) totalHistoryQ  ");
		buffer.append(" from  PS_information psi ");
		buffer.append(" left join Inverter_parameter inp on inp.PS_id=psi.id ");
		buffer.append(" left join bd_to_data tod   on inp.name=tod.InverterID ");
		
		Query query = session.createSQLQuery(buffer.toString());
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		PSTotal total=new PSTotal();
		if (list == null || list.size() == 0) {
			return total;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			total.setTotalPS(DataUtils.getInteger(obj[0]));
			total.setTotalCapacity(DataUtils.getDecimal(obj[1]));
			total.setTotalHistoryQ(DataUtils.getDecimal(obj[2]));
		}
		return total;
	}

	@Override
	public InParameter getInParameter(String dateTime, int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select ");
		buffer.append(" sum(tod.InputVoltage) modelInVoltage,  ");
		buffer.append(" sum(tod.ShowObligate) modelInCurrent,  ");
		buffer.append(" sum(tod.BatteryVoltage) batteryVoltage,  ");
		buffer.append(" sum(tod.CurrPower) CurrPower,");
		buffer.append(" DateName(hour,GetDate()) as currHour  ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" where ");
//		buffer.append(" CONVERT(varchar(100),OperateDate, 23)= ? and inp.PS_id= ?   ");
		buffer.append(" inp.PS_id= ? ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		InParameter power = new InParameter();
		if (list == null || list.size() == 0) {
			return power;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			power.setModelInVoltage(DataUtils.getDecimal(obj[0]));
			power.setModelInCurrent(DataUtils.getDecimal(obj[1]));
			power.setBatteryInVoltage(DataUtils.getDecimal(obj[2]));
			power.setCurrPower(DataUtils.getDecimal(obj[2]));
		}
		return power;
	}

	@Override
	public List<PowerStationBase> getPSHourlyData(String dateTime, int psId, String type) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		/*buffer.append(" select ");
		buffer.append(" b.X_Coutpout_Power as power, ");
		buffer.append(" b.X_TPV_Voltage as voltage, ");
		buffer.append(" b.X_TPV_Current as curr, ");
		buffer.append(" DateName(hour,b.operatedate) as groupHour  ");
		buffer.append(" from bd_to_data b right join  ");
		buffer.append(" (SELECT ");
		buffer.append(" max(tod.operateDate) as operatedate ");
		buffer.append(" FROM ");
		buffer.append(" Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" WHERE ");
//		buffer.append(" convert(varchar(30),tod.operateDate,23) = ?  and");
		buffer.append("  inp.PS_id=? ");
		buffer.append(" GROUP BY ");
		buffer.append(" DATEPART(hh,tod.operateDate)) c on b.OperateDate = c.operatedate ");*/
		
		buffer.append(" select atmp.power,atmp.voltage,atmp.curr,DateName(hour,atmp.operatedate) as groupHour, ");
		buffer.append(" atmp.OutputVoltage, atmp.OutputCurrent,atmp.OutputPower");
		buffer.append(" from ( ");
		buffer.append(" select distinct ");
		buffer.append(" b.X_Coutpout_Power as power,  b.X_Coutpout_Voltage as voltage,  b.X_Coutpout_Current as curr,  ");
		buffer.append(" b.operatedate ,inp.PS_id , ");
		buffer.append("  b.OutputVoltage,  b.OutputCurrent,");
		buffer.append(" (( b.OutputVoltage * b.OutputCurrent)/1000) as OutputPower ");
		buffer.append(" from bd_to_data b ");
		buffer.append(" inner join Inverter_parameter inp on inp.name=b.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" WHERE   inp.PS_id=?  ");
		buffer.append(" ) atmp inner join ( ");
		buffer.append(" select max(b.OperateDate)  as odate,max(inp.PS_id) psId ");
		buffer.append(" from bd_to_data b ");
		buffer.append(" inner join Inverter_parameter inp on inp.name=b.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id   ");
		buffer.append(" WHERE   inp.PS_id=?  ");
		buffer.append(" and CONVERT(varchar(100),b.operateDate, 23)=( ");
		buffer.append(" SELECT  CONVERT(varchar(100),max(tod.operateDate), 23) as operatedate ");
		buffer.append(" FROM  Inverter_parameter inp ");
		buffer.append(" inner join bd_to_data tod on inp.name=tod.InverterID  ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id  ");
		buffer.append(" WHERE   inp.PS_id=? ");
		buffer.append(" ) ");
		buffer.append(" GROUP BY  DATEPART(hh,b.operateDate) ");
		buffer.append(" ) btmp on btmp.odate=atmp.operatedate and btmp.psId=atmp.PS_id ");
		Query query = session.createSQLQuery(buffer.toString());
//		query.setString(0, dateTime);
		query.setInteger(0, psId);
		query.setInteger(1, psId);
		query.setInteger(2, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PowerStationBase> reList = new ArrayList<PowerStationBase>();
		if (list == null || list.size() == 0) {
			return reList;
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			PowerStationBase power = new PowerStationBase();
			power.setPower(DataUtils.getDecimal(obj[0]));
			power.setVoltage(DataUtils.getDecimal(obj[1]));
			power.setCurrent(DataUtils.getDecimal(obj[2]));
			power.setGroupHour(DataUtils.getInteger(obj[3]));
//			buffer.append(" atmp.OutputVoltage, atmp.OutputCurrent,atmp.OutputPower");
			power.setOutputVoltage(DataUtils.getDecimal(obj[4]));
			power.setOutputCurrent(DataUtils.getDecimal(obj[5]));
			power.setOutputPower(DataUtils.getDecimal(obj[6]));
			reList.add(power);
		}
		return reList;
	
	}

	@Override
	public List<PSEquipment> getPSEquipment(int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append("  select ");
		buffer.append("  psi.id,psi.name,inp.type  ");
		buffer.append("  from  Inverter_parameter inp    ");
		buffer.append("  inner join bd_to_data tod on inp.name=tod.InverterID  ");
		buffer.append("  inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append("  where  inp.PS_id=?   ");
		buffer.append("  group by inp.type,psi.id,psi.name ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setInteger(0, psId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		HibernateSessionFactory.closeHibernateSession();
		List<PSEquipment> reList = new ArrayList<PSEquipment>();
		if (list == null || list.size() == 0) {
			return reList;
		}
	
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			PSEquipment power = new PSEquipment();
			power.setPsId(DataUtils.getInteger(obj[0]));
			power.setPsName(DataUtils.getString(obj[1]));
			power.setType(DataUtils.getString(obj[2]));
			reList.add(power);
		}
		return reList;
	}

}
