package com.PSMS.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡检管理
 * @author Andy
 *
 */
public class InspectionManager implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;// 巡检id

	private int psId;//电站id

	private String areaId;// 区域id

	private int equipmentId;// 设备id

	private int userId;// 用户id

	private String currDate;// 当前巡检日期
	
	private int inspectionPeriod;//巡检周期
	
	private String nextDate;//下次巡检日期

	public int getPsId() {
		return psId;
	}

	public void setPsId(int psId) {
		this.psId = psId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public int getInspectionPeriod() {
		return inspectionPeriod;
	}

	public void setInspectionPeriod(int inspectionPeriod) {
		this.inspectionPeriod = inspectionPeriod;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "InspectionManager [id=" + id + ", psId=" + psId + ", areaId=" + areaId + ", equipmentId=" + equipmentId
				+ ", userId=" + userId + ", currDate=" + currDate + ", inspectionPeriod=" + inspectionPeriod
				+ ", nextDate=" + nextDate + "]";
	}
	
	
}
