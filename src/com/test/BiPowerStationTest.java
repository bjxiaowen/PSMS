package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationTest {

	public static void main(String[] args) {
		IBiPowerStationDao dao=DAOFactory.getBiPowerStationDaoInstance();
		try {
			//getPowerStationDayByDate(dao);
			//getPowerStationHourByDate(dao);
			//getBatteryVoltageDayByDate(dao);
			//getBatteryVoltageHourByDate(dao);
			//getControlDayByDate(dao);
			//getControlHourByDate(dao);
			//getControlOutShowDayByDate(dao);
			getControlOutShowHourByDate(dao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public static void getPowerStationDayByDate(IBiPowerStationDao dao){
		PowerStationBase power = null;
		try {
			power = dao.getPowerStationDayByDate("2016-03-26",92);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(power.toString());
	}*/
	
/*	public static void getPowerStationHourByDate(IBiPowerStationDao dao){
		try {
			List<PowerStationBase> list=dao.getPowerStationHourByDate("2016-03-26",92);
			for(PowerStationBase power:list){
				System.out.println(power.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void getBatteryVoltageDayByDate(IBiPowerStationDao dao){
		try {
			PowerStationBase  power=dao.getBatteryVoltageDayByDate("2016-03-26",92);
			System.out.println(power.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getBatteryVoltageHourByDate(IBiPowerStationDao dao){
		List<PowerStationBase> list = null;
		try {
			list = dao.getBatteryVoltageHourByDate("2016-03-26",92);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(PowerStationBase power:list){
			System.out.println(power.toString());
		}
	}
	
	public static void getControlDayByDate(IBiPowerStationDao dao){
		try {
			PowerStationBase  power=dao.getControlPhotovoltaicDayByDate("2016-03-26",92);
			System.out.println(power.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getControlHourByDate(IBiPowerStationDao dao){
		try {
			List<PowerStationBase> list=dao.getControlPhotovoltaicHourByDate("2016-03-26",92);
			for(PowerStationBase power:list){
				System.out.println(power.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getControlOutShowDayByDate(IBiPowerStationDao dao){
		try {
			PowerStationBase  power=dao.getControlOutShowDayByDate("2016-03-26",92);
			System.out.println(power.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getControlOutShowHourByDate(IBiPowerStationDao dao){
		try {
			List<PowerStationBase> list=dao.getControlOutShowHourByDate("2016-03-26",92);
			for(PowerStationBase power:list){
				System.out.println(power.toString());
			}
			List<PowerStationBase> reList=new ArrayList<PowerStationBase>();
			for(int i=1;i<25;i++){
				boolean flag=true;
				for(PowerStationBase power: list){
					int hour=power.getGroupHour();
					if(i==hour){
						reList.add(power);
						flag=false;
					}
				}
				if(flag){//没有相等的时候
					PowerStationBase power=new PowerStationBase();
					power.setGroupHour(i);
					reList.add(power);
				}
			}
			for(PowerStationBase power:reList){
				System.err.println(power.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
