package com.PSMS.pojo;

import java.io.Serializable;
import java.util.Date;

public class JointInspection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String manageId;// 管理id

	private String inspectionId;// 巡检id

	private int psId;// 电站id

	private String psName;// 电站名称

	private String areaId;// 区域id

	private String areaName;// 区域名称

	private int equipmentId;// 设备id

	private String equipmentName;// 设备名称

	private int userId;// 用户id

	private String userName;// 用户名

	private String tel;// 电话号码

	private String email;// 邮箱

	private String inspectionDate;// 巡检日期

	private int inspectionPeriod;// 巡检周期

	private String inspectionReport;// 巡检报告

	private String reportDate;// 巡检日期

	private String currDate;// 当前巡检日期

	private String nextDate;// 下次巡检日期

	private String shouldDate;

	private String actualDate;

	private int inspectionStatus;

	public String getShouldDate() {
		return shouldDate;
	}

	public void setShouldDate(String shouldDate) {
		this.shouldDate = shouldDate;
	}

	public String getActualDate() {
		return actualDate;
	}

	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}

	public String getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}

	public int getPsId() {
		return psId;
	}

	public void setPsId(int psId) {
		this.psId = psId;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public int getInspectionPeriod() {
		return inspectionPeriod;
	}

	public void setInspectionPeriod(int inspectionPeriod) {
		this.inspectionPeriod = inspectionPeriod;
	}

	public String getInspectionReport() {
		return inspectionReport;
	}

	public void setInspectionReport(String inspectionReport) {
		this.inspectionReport = inspectionReport;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public int getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(int inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getManageId() {
		return manageId;
	}

	public void setManageId(String manageId) {
		this.manageId = manageId;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	@Override
	public String toString() {
		return "JointInspection [manageId=" + manageId + ", inspectionId=" + inspectionId + ", psId=" + psId
				+ ", psName=" + psName + ", areaId=" + areaId + ", areaName=" + areaName + ", equipmentId="
				+ equipmentId + ", equipmentName=" + equipmentName + ", userId=" + userId + ", userName=" + userName
				+ ", tel=" + tel + ", email=" + email + ", inspectionDate=" + inspectionDate + ", inspectionPeriod="
				+ inspectionPeriod + ", inspectionReport=" + inspectionReport + ", reportDate=" + reportDate
				+ ", currDate=" + currDate + ", nextDate=" + nextDate + ", shouldDate=" + shouldDate + ", actualDate="
				+ actualDate + ", inspectionStatus=" + inspectionStatus + "]";
	}
}