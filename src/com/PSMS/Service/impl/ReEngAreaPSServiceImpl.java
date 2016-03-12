package com.PSMS.Service.impl;

import java.util.List;
import com.PSMS.Dao.IReEngAreaPS;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IReEngAreaPSService;
import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.ReEngAreaPowerStation;
import com.PSMS.util.IDGenerate;

public class ReEngAreaPSServiceImpl implements IReEngAreaPSService {
	
	IReEngAreaPS dao =DAOFactory.getReEngAreaPSInstance();

	@Override
	public boolean addReEngAreaPS(ReEngAreaPowerStation reEngAreaPS) throws Exception {
		reEngAreaPS.setId("bd"+IDGenerate.getId());
		return dao.addReEngAreaPS(reEngAreaPS);
	}

	@Override
	public boolean UpdateReEngAreaPS(ReEngAreaPowerStation reEngAreaPS) throws Exception {
		return dao.UpdateReEngAreaPS(reEngAreaPS);
	}

	@Override
	public boolean deleteReEngAreaPS(String id) throws Exception {
		return dao.deleteReEngAreaPS(id);
	}

	@Override
	public JointEngAreaPS getById(String id) throws Exception {
		return dao.getById(id);
	}

	@Override
	public List<JointEngAreaPS> getAll() throws Exception {
		return dao.getAll();
	}

	@Override
	public List<JointEngAreaPS> searchByAreaName(String areaName) throws Exception {
		return dao.searchByAreaName(areaName);
	}

	@Override
	public List<JointEngAreaPS> searchByUserName(String UserName) throws Exception {
		return dao.searchByUserName(UserName);
	}

	@Override
	public List<JointEngAreaPS> searchByPSName(String psName) throws Exception {
		return dao.searchByPSName(psName);
	}

	@Override
	public List<JointEngAreaPS> checkById(String areaId, int userId, int psId) throws Exception {
		return dao.checkById(areaId, userId, psId);
	}

}
