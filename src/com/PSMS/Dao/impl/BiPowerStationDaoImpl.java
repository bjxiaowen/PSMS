package com.PSMS.Dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationDaoImpl implements IBiPowerStationDao {

	@Override
	public List<PowerStationBase> getPowerStationByDate(String dateTime) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String sql="select count(psi.capacity) totalCapacity, count(tod.MpptOutVoltage) * count(tod.MpptOutCurrent) as totalPower,	  DateName(hour,GetDate()) as currHour,	  DateName(hour,tod.OperateDate) as groupHour  from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID  inner join PS_information psi on inp.PS_id=psi.id  where CONVERT(varchar(100),OperateDate, 23)=? group by DateName(hour,tod.OperateDate)";
		Query query = session.createSQLQuery(sql);
		query.setString(0, dateTime);
		List list = query.list();
		List<PowerStationBase> reList=new ArrayList<PowerStationBase>();
		if(list==null||list.size()==0){
			return reList;
		}
		
		for(int i=0;i<list.size();i++){
			PowerStationBase power=new PowerStationBase();
			Object[] obj=(Object[]) list.get(i);
			power.setTotalCapacity(new BigDecimal(obj[0]+""));
			power.setTotalPower(new BigDecimal(obj[1]+""));
			//currHour,	 groupHour
			power.setCurrHour(Integer.parseInt(obj[2]+""));
			power.setGroupHour(Integer.parseInt(obj[3]+""));
			reList.add(power);
		}
		return reList;
	}

	@Override
	public List<PowerStationBase> getBatteryVoltageByDate(String dateTime) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		String sql="select count(psi.capacity) totalCapacity, count(tod.MpptOutVoltage) * count(tod.MpptOutCurrent) as totalPower,	  DateName(hour,GetDate()) as currHour,	  DateName(hour,tod.OperateDate) as groupHour, avg(tod.MpptTemp) mpptTemp, count(tod.BatteryVoltage) batteryVoltage,sum(distinct tod.ChargeDischarge) chargeDischarge  from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID  inner join PS_information psi on inp.PS_id=psi.id  where CONVERT(varchar(100),OperateDate, 23)=? group by DateName(hour,tod.OperateDate)";
		Query query = session.createSQLQuery(sql);
		query.setString(0, dateTime);
		List list = query.list();
		List<PowerStationBase> reList=new ArrayList<PowerStationBase>();
		if(list==null||list.size()==0){
			return reList;
		}
		
		for(int i=0;i<list.size();i++){
			PowerStationBase power=new PowerStationBase();
			Object[] obj=(Object[]) list.get(i);
			power.setTotalCapacity(new BigDecimal(obj[0]+""));
			power.setTotalPower(new BigDecimal(obj[1]+""));
			//currHour,	 groupHour
			power.setCurrHour(Integer.parseInt(obj[2]+""));
			power.setGroupHour(Integer.parseInt(obj[3]+""));
			
			//mpptTemp, batteryVoltage,chargeDischarge
			power.setMpptTemp(new BigDecimal(obj[4]+""));
			power.setBatteryVoltage(new BigDecimal(obj[5]+""));
			power.setChargeDischarge(Integer.parseInt(obj[6]+""));
			reList.add(power);
		}
		return reList;
	}

}
