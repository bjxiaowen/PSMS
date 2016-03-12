package com.PSMS.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡检
 * 
 * @author Andy
 *
 */
public class Inspection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3864475733627371496L;

	private String id;
	
	private String managerId;
	
	private String shouldDate;
	
	private String actualDate;
	
	private String inspectionReport;
	
	private int inspectionStatus;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

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

	public String getInspectionReport() {
		return inspectionReport;
	}

	public void setInspectionReport(String inspectionReport) {
		this.inspectionReport = inspectionReport;
	}

	public int getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(int inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	@Override
	public String toString() {
		return "Inspection [id=" + id + ", managerId=" + managerId + ", shouldDate=" + shouldDate + ", actualDate="
				+ actualDate + ", inspectionReport=" + inspectionReport + ", inspectionStatus=" + inspectionStatus
				+ "]";
	}
}
