package com.PSMS.Dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IBiModuleDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.PowerStationBase;
/**
 * 组件数据查询
 * @author Andy
 *
 */
public class BiModuleDaoImpl implements IBiModuleDao {
	
	/**
	 * 组件一天数据的总和
	 */
	@Override
	public PowerStationBase getPowerStationDayByDate(String dateTime, int psId,String type) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" sum(distinct psi.capacity) totalCapacity, ");//组件容量
		buffer.append(" sum(tod.MpptOutVoltage * tod.MpptOutCurrent) as totalPower, ");//输出功率
		buffer.append(" sum(tod.MpptOutVoltage)  totalVoltage ,");//组件电压
		buffer.append(" sum(tod.MpptOutCurrent)  totalCurrent , ");//组件电流
		buffer.append(" DateName(hour,GetDate()) as currHour ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)=? and inp.PS_id=? ");
		buffer.append(" and inp.type=? ");//组件
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
	
	
	@Override//组件一天的数据
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime, int psId) throws Exception {
		Session session = HibernateSessionFactory.getHibernateSession();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		buffer.append(" (tod.MpptOutVoltage * tod.MpptOutCurrent) as power, ");//输出功率
		buffer.append(" tod.MpptOutVoltage  voltage ,");//组件电压
		buffer.append(" tod.MpptOutCurrent  curr , ");//组件电流
		buffer.append(" DateName(hour,GetDate()) as groupHour ");
		buffer.append(" from  Inverter_parameter inp   inner join bd_to_data tod on inp.name=tod.InverterID ");
		buffer.append(" inner join PS_information psi on inp.PS_id=psi.id ");
		buffer.append(" where CONVERT(varchar(100),OperateDate, 23)=? and inp.PS_id=? ");
		buffer.append(" and inp.type='组件' ");//组件
		buffer.append(" group by DateName(hour,tod.OperateDate) ");
		Query query = session.createSQLQuery(buffer.toString());
		query.setString(0, dateTime);
		query.setInteger(1, psId);
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
