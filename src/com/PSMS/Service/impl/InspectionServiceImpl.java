package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IInspectionDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IInspectionService;
import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.JointInspection;
import com.PSMS.util.IDGenerate;

public class InspectionServiceImpl implements IInspectionService {
	
	IInspectionDao dao=DAOFactory.getInspectionDaolInstance();

	@Override
	public boolean addInspection(Inspection inspection) throws Exception {
		inspection.setId("bd"+IDGenerate.getId());
		return dao.addInspection(inspection);
	}

	@Override
	public boolean UpdateInspection(Inspection inspection) throws Exception {
		inspection.setInspectionStatus(1);
		return dao.UpdateInspection(inspection);
	}

	@Override
	public boolean deleteInspection(Inspection inspection) throws Exception {
		return dao.deleteInspection(inspection);
	}

	@Override
	public JointInspection getInspectionById(String id) throws Exception {
		return dao.getById(id);
	}

	@Override
	public List<JointInspection> getInspectionAll() throws Exception {
		return dao.getAll();
	}

	@Override
	public List<JointInspection> getPsId(int psId) throws Exception {
		return dao.getPsId(psId);
	}

}
