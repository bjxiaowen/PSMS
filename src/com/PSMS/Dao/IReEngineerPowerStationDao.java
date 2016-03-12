package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.ReEngineerPowerStation;
/**
 * 工程师与电站关系
 * @author Andy
 *
 */
public interface IReEngineerPowerStationDao {

	boolean addReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception;

	public boolean UpdateReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception;

	public boolean deleteReEngineerPowerStation(ReEngineerPowerStation reEngineerPowerStation) throws Exception;

	public ReEngineerPowerStation getById(int id) throws Exception;

	public List<ReEngineerPowerStation> getAll() throws Exception;
}
