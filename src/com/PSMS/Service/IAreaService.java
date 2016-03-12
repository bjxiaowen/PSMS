package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.Area;

public interface IAreaService {
	
	boolean addArea(Area area) throws Exception;

	public boolean UpdateArea(Area area) throws Exception;

	public boolean deleteArea(String id) throws Exception;

	public Area getById(String id) throws Exception;

	public List<Area> getParentId(String id) throws Exception;

	public List<Area> getAll() throws Exception;
	
	public List<Area> queryByName(String areaName) throws Exception;
	
	public List<Area> queryByCode(String areaCode) throws Exception;
	
	public List<Area> queryByCodeAndName(String areaCode,String areaName) throws Exception;
	
}
