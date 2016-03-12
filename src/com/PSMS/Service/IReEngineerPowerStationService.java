package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.ReEngineerPowerStation;

public interface IReEngineerPowerStationService {


	boolean addReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception;

	public boolean UpdateReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception;

	public boolean deleteReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception;

	public ReEngineerPowerStation getById(int id) throws Exception;

	public List<ReEngineerPowerStation> getAll() throws Exception;


}
