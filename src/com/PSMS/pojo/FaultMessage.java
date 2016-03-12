package com.PSMS.pojo;

import java.io.Serializable;
/**
 * 错误信息
 * @author Andy
 * @date 2016-03-08
 *
 */
public class FaultMessage implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String faultMessageId;

	/**
	 * 区域id
	 */
	private String areaId;

	/**
	 * 电站id
	 */
	private int psId;

	/**
	 * 设备id
	 */
	private int equipmentId;

	/**
	 * 设备状态
	 */
	private int equipmentStatus;

	/**
	 * 工程师id
	 */
	private int userId;

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
	
	private String checkText;//检查批语
	
	private int checkStatus;//检查状态
	

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

	@Override
	public String toString() {
		return "FaultMessage [faultMessageId=" + faultMessageId + ", areaId=" + areaId + ", psId=" + psId
				+ ", equipmentId=" + equipmentId + ", equipmentStatus=" + equipmentStatus + ", userId=" + userId
				+ ", alertTime=" + alertTime + ", status=" + status + ", initialDiagnose=" + initialDiagnose
				+ ", predictTime=" + predictTime + ", alertCause=" + alertCause + ", handleCondition=" + handleCondition
				+ ", maintainDate=" + maintainDate + ", checkPerson=" + checkPerson + ", checkDate=" + checkDate
				+ ", checkText=" + checkText + ", checkStatus=" + checkStatus + "]";
	}
}
