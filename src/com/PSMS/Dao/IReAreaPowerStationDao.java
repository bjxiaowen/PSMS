package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.ReAreaPowerStation;
/**
 * 区域与电站关系
 * @author Andy
 *
 */
public interface IReAreaPowerStationDao {

	boolean addReAreaPowerStationn(ReAreaPowerStation reAreaPowerStation) throws Exception;

	public boolean UpdateReAreaPowerStation(ReAreaPowerStation reAreaPowerStation) throws Exception;

	public boolean deleteReAreaPowerStation(ReAreaPowerStation reAreaPowerStation) throws Exception;

	public ReAreaPowerStation getById(int id) throws Exception;

	public List<ReAreaPowerStation> getAll() throws Exception;
	
	public List<ReAreaPowerStation> searchByAreaName(String areaName) throws Exception;
	
	public List<ReAreaPowerStation> searchByUserName(String psName) throws Exception;
	
	public List<ReAreaPowerStation> checkByAreaIdAndUserId(String areaId,String psId) throws Exception;
}
