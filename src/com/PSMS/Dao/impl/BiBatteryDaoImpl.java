package com.PSMS.Dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Dao.IBiBatteryDao;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.pojo.PowerStationBase;
/**
 * 蓄电池
 * @author Andy
 *
 */
public class BiBatteryDaoImpl implements IBiBatteryDao {

	@Override
	public PowerStationBase getBatteryNewestDate(String dateTime, int psId) throws Exception {
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
}
