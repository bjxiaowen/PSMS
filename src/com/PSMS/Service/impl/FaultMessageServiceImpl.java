package com.PSMS.Service.impl;

import java.util.List;

import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IFaultMessageService;
import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.JointFaultMessage;
import com.PSMS.util.IDGenerate;

public class FaultMessageServiceImpl implements IFaultMessageService {

	IFaultMessageDao dao=DAOFactory.getFaultMessageInstance();
	@Override
	public boolean addFaultMessage(FaultMessage faultMessage) throws Exception {
		faultMessage.setFaultMessageId("Fa"+IDGenerate.getId());
		return dao.addFaultMessage(faultMessage);
	}

	@Override
	public boolean updateFaultMessage(FaultMessage faultMessage) throws Exception {
		return dao.updateFaultMessage(faultMessage);
	}

	@Override
	public boolean deleteFaultMessage(FaultMessage faultMessage) throws Exception {
		return dao.deleteFaultMessage(faultMessage);
	}

	@Override
	public JointFaultMessage getFaultMessageById(String id) throws Exception {
		return dao.getFaultMessageById(id);
	}

	@Override
	public List<JointFaultMessage> getAllJointFaultMessage() throws Exception {
		return dao.getAllJointFaultMessage();
	}

	@Override
	public List<JointFaultMessage> getFaultMessageByPsId(String psId) throws Exception {
		return  dao.getFaultMessageByPsId(psId);
	}

	@Override
	public List<JointFaultMessage> getFaultMessageByUserId(int userId) throws Exception {
		return dao.getFaultMessageByUserId(userId);
	}

}
