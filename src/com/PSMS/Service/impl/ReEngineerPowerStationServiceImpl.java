package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IReEngineerPowerStationDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IReEngineerPowerStationService;
import com.PSMS.pojo.ReEngineerPowerStation;

public class ReEngineerPowerStationServiceImpl implements IReEngineerPowerStationService {

	 IReEngineerPowerStationDao dao=DAOFactory.getReEngineerPowerStationDaoInstance();
	
	@Override
	public boolean addReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception {
		return dao.addReEngineerPowerStation(reEngineerPowerStation);
	}

	@Override
	public boolean UpdateReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception {
		return dao.UpdateReEngineerPowerStation(reEngineerPowerStation);
	}

	@Override
	public boolean deleteReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception {
		return dao.deleteReEngineerPowerStation(reEngineerPowerStation);
	}

	@Override
	public ReEngineerPowerStation getById(int id) throws Exception {
		return dao.getById(id);
	}

	@Override
	public List<ReEngineerPowerStation> getAll() throws Exception {
		return dao.getAll();
	}

}
