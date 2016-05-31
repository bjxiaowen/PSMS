package com.PSMS.util;

import java.util.ArrayList;
import java.util.List;

import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationTools {

	/**
	 * 一天24小时
	 * @param list
	 * @return
	 */
	public static  List<PowerStationBase> getListSize24(List<PowerStationBase> list){
		List<PowerStationBase> reList=new ArrayList<PowerStationBase>();
		if(list==null||list.size()==0)return null;
		PowerStationBase base=list.get(list.size()-1);
		int ghour=base.getGroupHour();
		for(int i=0;i<24;i++){
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
			if(ghour==i)
				break;
		}
		
		/*List<PowerStationBase> list24=new ArrayList<PowerStationBase>();
		for(PowerStationBase base:reList){
			BigDecimal a=base.getPower();
			BigDecimal b=BigDecimal.valueOf(0);
			if(a==null||!(a.compareTo(b)>0)){
				list24.add(null);
			}else{
				list24.add(base);
			}
		}*/
		return reList;
	}
	
	/**
	 * 每个月的天数
	 * @param list
	 * @return
	 */
	public static List<PowerStationBase> getListMonthOfDays(List<PowerStationBase> list){
		int days=GetTime.getMonthOfDay();
		List<PowerStationBase> reList=new ArrayList<PowerStationBase>();
		for(int i=1;i<=days;i++){
			boolean flag=true;
			for(PowerStationBase power: list){
				int day=power.getGroupDay();
				if(i==day){
					reList.add(power);
					flag=false;
				}
			}
			
			if(flag){//没有相等的时候
				PowerStationBase power=new PowerStationBase();
				power.setGroupDay(i);
				reList.add(power);
			}
			
		}
		return reList;
	}
	
	/**
	 * 本年每月
	 * @param list
	 * @return
	 */
	public static List<PowerStationBase> getListYearOfMonths(List<PowerStationBase> list){
		List<PowerStationBase> reList=new ArrayList<PowerStationBase>();
		for(int i=1;i<=12;i++){
			boolean flag=true;
			
			for(PowerStationBase power: list){
				int month=power.getGroupMonth();
				if(i==month){
					reList.add(power);
					flag=false;
				}
			}
			if(flag){//没有相等的时候
				PowerStationBase power=new PowerStationBase();
				power.setGroupMonth(i);
				reList.add(power);
			}
		}
		return reList;
	}
}
