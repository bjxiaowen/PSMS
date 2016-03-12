package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.JointFaultMessage;

public interface IFaultMessageService {

	/**
	 * 添加异常信息
	 * @param faultMessage
	 * @return
	 * @throws Exception
	 */
	boolean addFaultMessage(FaultMessage faultMessage) throws Exception;
	
	/**
	 * 更新异常信息
	 * @param faultMessage
	 * @return
	 * @throws Exception
	 */
	public boolean updateFaultMessage(FaultMessage faultMessage) throws Exception;

	/**
	 * 删除异常信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFaultMessage(FaultMessage faultMessage) throws Exception;
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JointFaultMessage getFaultMessageById(String id) throws Exception;
	
	/**
	 * 获取所有
	 * @return
	 * @throws Exception
	 */
	public List<JointFaultMessage> getAllJointFaultMessage() throws Exception;


}
