package com.test;

import java.util.List;

import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.PowerStationBase;

public class BiPowerStationTest {

	public static void main(String[] args) {
		IBiPowerStationDao dao=DAOFactory.getBiPowerStationDaoInstance();
		try {
			List<PowerStationBase> list=dao.getBatteryVoltageByDate("2016-03-26");
			//dao.getPowerStationByDate("2016-03-26");
			for(PowerStationBase power:list){
				System.err.println(power.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
