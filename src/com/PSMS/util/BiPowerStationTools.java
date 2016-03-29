package com.PSMS.util;

import java.util.ArrayList;
import java.util.List;

import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationTools {

	public static  List<PowerStationBase> getListSize24(List<PowerStationBase> list){
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
		return reList;
	}
}
