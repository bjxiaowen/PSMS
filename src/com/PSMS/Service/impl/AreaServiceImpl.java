package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IAreaDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IAreaService;
import com.PSMS.pojo.Area;

public class AreaServiceImpl implements IAreaService {

	IAreaDao dao=DAOFactory.getAreaDaoInstance();
			
	@Override
	public boolean addArea(Area area) throws Exception {
		return dao.addArea(area);
	}

	@Override
	public boolean UpdateArea(Area area) throws Exception {
		return dao.UpdateArea(area);
	}

	@Override
	public boolean deleteArea(String id) throws Exception {
		return dao.deleteArea(id);
	}

	@Override
	public Area getById(String id) throws Exception {
		return dao.getById(id);
	}

	@Override
	public List<Area> getParentId(String id) throws Exception {
		return dao.getParentId(id);
	}

	@Override
	public List<Area> getAll() throws Exception {
		return dao.getAll();
	}

	@Override
	public List<Area> queryByName(String areaName) throws Exception {
		return dao.queryByName(areaName);
	}

	@Override
	public List<Area> queryByCode(String areaCode) throws Exception {
		return dao.queryByCode(areaCode);
	}

	@Override
	public List<Area> queryByCodeAndName(String areaCode, String areaName) throws Exception {
		return dao.queryByCodeAndName(areaCode, areaName);
	}

}
