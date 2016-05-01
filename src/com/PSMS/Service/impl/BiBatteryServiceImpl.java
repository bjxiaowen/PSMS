package com.PSMS.Service.impl;

import com.PSMS.Dao.IBiBatteryDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IBiBatteryService;
import com.PSMS.pojo.PowerStationBase;

public class BiBatteryServiceImpl implements IBiBatteryService {
	
	IBiBatteryDao dao=DAOFactory.getBiBatteryDaoInstance();

	@Override
	public PowerStationBase getBatteryNewestDate(String dateTime, int psId) throws Exception {
		return dao.getBatteryNewestDate(dateTime, psId);
	}

}
