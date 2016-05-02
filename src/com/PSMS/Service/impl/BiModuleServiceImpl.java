package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IBiModuleDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IBiModuleService;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.BiPowerStationTools;

public class BiModuleServiceImpl implements IBiModuleService {
	
	IBiModuleDao dao=DAOFactory.getBiModuleDaoInstance();
	

	@Override
	public PowerStationBase getPowerStationDayByDate(String dateTime, int psId,String type) throws Exception {
		return dao.getPowerStationDayByDate(dateTime, psId,type);
	}

	@Override
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime, int psId) throws Exception {
		List<PowerStationBase> list=dao.getPowerStationHourByDate(dateTime, psId);
		return  BiPowerStationTools.getListSize24(list);
	}

}
