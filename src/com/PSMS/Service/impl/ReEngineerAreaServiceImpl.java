package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IReEngineerAreaDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IReEngineerAreaService;
import com.PSMS.pojo.ReEngineerArea;

public class ReEngineerAreaServiceImpl implements IReEngineerAreaService {

	IReEngineerAreaDao dao=DAOFactory.getReEngineerAreaDaoInstance();
	
	@Override
	public boolean addReEngineerArea(ReEngineerArea reEngineerArea) throws Exception {
		return dao.addReEngineerArea(reEngineerArea);
	}

	@Override
	public boolean UpdateReEngineerArea(ReEngineerArea reEngineerArea) throws Exception {
		return dao.UpdateReEngineerArea(reEngineerArea);
	}

	@Override
	public boolean deleteReEngineerArea(String id) throws Exception {
		return dao.deleteReEngineerArea(id);
	}

	@Override
	public ReEngineerArea getById(String id) throws Exception {
		return dao.getById(id);
	}

	@Override
	public List<ReEngineerArea> getAll() throws Exception {
		return dao.getAll();
	}

	@Override
	public List<ReEngineerArea> searchByAreaName(String areaName) throws Exception {
		return dao.searchByAreaName(areaName);
	}

	@Override
	public List<ReEngineerArea> searchByUserName(String UserName) throws Exception {
		return dao.searchByUserName(UserName);
	}

	@Override
	public List<ReEngineerArea> checkByAreaIdAndUserId(String areaId, String userId) throws Exception {
		return dao.checkByAreaIdAndUserId(areaId, userId);
	}

}
