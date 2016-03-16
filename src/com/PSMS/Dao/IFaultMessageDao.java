package com.PSMS.Dao;

import java.util.List;
import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.JointFaultMessage;
public interface IFaultMessageDao {


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
	
	
	/**
	 * 获取需要发短信或邮件的信息
	 * @return
	 * @throws Exception
	 */
	public List<JointFaultMessage> getNeedSendMailFaultMessage() throws Exception;
	
	/**
	 * 发送邮件成功后更新数据发送状态
	 * @param flagSend
	 * @param sendDate
	 * @return
	 * @throws Exception
	 */
	public boolean updateById(String flagSend,String sendDate,String id)throws Exception;
}
