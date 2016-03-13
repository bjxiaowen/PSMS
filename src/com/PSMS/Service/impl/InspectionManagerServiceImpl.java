package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IInspectionManagerDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IInspectionManagerService;
import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointInspection;
import com.PSMS.util.IDGenerate;

public class InspectionManagerServiceImpl implements IInspectionManagerService {
	
	IInspectionManagerDao dao=DAOFactory.getInspectionManagerDaoInstance();

	@Override
	public boolean addInspectionManager(InspectionManager inspectionManager) throws Exception {
		inspectionManager.setId("IM"+IDGenerate.getId());
		return dao.addInspectionManager(inspectionManager);
	}

	@Override
	public boolean updateInspectionManager(InspectionManager inspectionManager) throws Exception {
		return dao.updateInspectionManager(inspectionManager);
	}

	@Override
	public boolean deleteInspectionManager(InspectionManager inspectionManager) throws Exception {
		return dao.deleteInspectionManager(inspectionManager);
	}

	@Override
	public JointInspection getById(String id) throws Exception {
		return dao.getById(id);
	}

	@Override
	public List<JointInspection> getAll() throws Exception {
		return dao.getAll();
	}

	@Override
	public JointInspection checkById(String areaId, int psId, int userId, int equipmentId) throws Exception {
		return dao.checkById(areaId, psId, userId, equipmentId);
	}

}
