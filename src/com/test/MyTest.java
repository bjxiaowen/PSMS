package com.test;

import java.util.List;

import com.PSMS.Dao.IAreaDao;
import com.PSMS.Dao.IReAreaPowerStationDao;
import com.PSMS.Dao.PS_informationDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.ReAreaPowerStation;
import com.PSMS.util.IDGenerate;

public class MyTest {

	public static void main(String[] args) throws Exception{
		//addReAreaPowerStationn();
		getAll();
	}
	
	public static void addReAreaPowerStationn() throws Exception{
		IReAreaPowerStationDao rAPS=DAOFactory.getReAreaPowerStationDaolInstance();
		ReAreaPowerStation reAreaPowerStation=new ReAreaPowerStation();
		reAreaPowerStation.setAreaId("14571890456820001");
		reAreaPowerStation.setId("Re"+IDGenerate.getId());
		reAreaPowerStation.setPsId(80);
		rAPS.addReAreaPowerStationn(reAreaPowerStation);
	}
	
	public static void getAll() throws Exception{/*
		IReAreaPowerStationDao rAPS=DAOFactory.getReAreaPowerStationDaolInstance();
		AreaPowerStationAgg agg=new AreaPowerStationAgg();
		List<ReAreaPowerStation> list=rAPS.getAll();
		for(ReAreaPowerStation re:list){
			agg.setId(re.getId());
			agg.setPsId(re.getPsId());
			agg.setAreaId(re.getAreaId());
			PS_informationDAO psDao=DAOFactory.getPS_informationDaoInstance();
			PS_information powerStation=psDao.getById(re.getPsId());
			System.out.println(powerStation);
			agg.setPowerStation(powerStation);
			IAreaDao aDao=DAOFactory.getAreaDaolInstance();
			Area area=aDao.getById(re.getAreaId());
			agg.setArea(area);
		}
		
		System.out.println(agg.toString());
	*/}
}
