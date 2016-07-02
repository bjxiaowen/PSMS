package com.PSMS.pojo;

import java.io.Serializable;
import java.util.Date;

public class JointFaultMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5127460134513166600L;

	/**
	 * 主键
	 */
	private String faultMessageId;

	/**
	 * 区域id
	 */
	private String areaId;

	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 电站id
	 */
	private int psId;

	/**
	 * 电站名称
	 */
	private String psName;

	/**
	 * 设备id
	 */
	private int equipmentId;

	/**
	 * 设备状态
	 */
	private int equipmentStatus;

	/**
	 * 设备名称
	 */
	private String equipmentName;

	/**
	 * 工程师id
	 */
	private int userId;

	/**
	 * 工程师名称
	 */
	private String userName;

	/**
	 * 报警时间
	 */
	private String alertTime;

	/**
	 * 处理状态
	 */
	private int status;

	/**
	 * 初步诊断
	 */
	private String initialDiagnose;

	/**
	 * 预计完成日期
	 */
	private String predictTime;

	/**
	 * 故障原因
	 */
	private String alertCause;

	/**
	 * 处理状况
	 */
	private String handleCondition;

	/**
	 * 维护日期
	 */
	private String maintainDate;

	/**
	 * 检验人
	 */
	private String checkPerson;

	/**
	 * 检验日期
	 */
	private String checkDate;

	private String checkText;// 检查批语

	private int checkStatus;// 检查状态

	private String tel;// 电话号码

	private String email;// 邮箱
	
	private String flagSend;//是否发过邮件或者短信
	
	private String sendDate;//发送时间
	
	private String failureMeaning;//错误信息
	
	private String failureType;//错误类型
	
	private String initialDate;//初步诊断日期
	
	private String handleDate;//处理日期
	
	

	public String getFaultMessageId() {
		return faultMessageId;
	}

	public void setFaultMessageId(String faultMessageId) {
		this.faultMessageId = faultMessageId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public int getPsId() {
		return psId;
	}

	public void setPsId(int psId) {
		this.psId = psId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(int equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInitialDiagnose() {
		return initialDiagnose;
	}

	public void setInitialDiagnose(String initialDiagnose) {
		this.initialDiagnose = initialDiagnose;
	}

	public String getPredictTime() {
		return predictTime;
	}

	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}

	public String getAlertCause() {
		return alertCause;
	}

	public void setAlertCause(String alertCause) {
		this.alertCause = alertCause;
	}

	public String getHandleCondition() {
		return handleCondition;
	}

	public void setHandleCondition(String handleCondition) {
		this.handleCondition = handleCondition;
	}

	public String getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(String maintainDate) {
		this.maintainDate = maintainDate;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getCheckText() {
		return checkText;
	}

	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getFlagSend() {
		return flagSend;
	}

	public void setFlagSend(String flagSend) {
		this.flagSend = flagSend;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getFailureMeaning() {
		return failureMeaning;
	}

	public void setFailureMeaning(String failureMeaning) {
		this.failureMeaning = failureMeaning;
	}

	public String getFailureType() {
		return failureType;
	}

	public void setFailureType(String failureType) {
		this.failureType = failureType;
	}
	
	

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	@Override
	public String toString() {
		return "JointFaultMessage [faultMessageId=" + faultMessageId + ", areaId=" + areaId + ", areaName=" + areaName
				+ ", psId=" + psId + ", psName=" + psName + ", equipmentId=" + equipmentId + ", equipmentStatus="
				+ equipmentStatus + ", equipmentName=" + equipmentName + ", userId=" + userId + ", userName=" + userName
				+ ", alertTime=" + alertTime + ", status=" + status + ", initialDiagnose=" + initialDiagnose
				+ ", predictTime=" + predictTime + ", alertCause=" + alertCause + ", handleCondition=" + handleCondition
				+ ", maintainDate=" + maintainDate + ", checkPerson=" + checkPerson + ", checkDate=" + checkDate
				+ ", checkText=" + checkText + ", checkStatus=" + checkStatus + ", tel=" + tel + ", email=" + email
				+ ", flagSend=" + flagSend + ", sendDate=" + sendDate + ", failureMeaning=" + failureMeaning
				+ ", failureType=" + failureType + "]";
	}
}
