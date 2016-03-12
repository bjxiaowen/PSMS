package com.PSMS.pojo;

import java.io.Serializable;

/**
 * 
 * @author Andy 区域
 */
public class Area implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String areaId;

	private String areaCode;

	private String areaName;

	private int areaLevel;

	private int parentId;

	private int areaFlag;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(int areaLevel) {
		this.areaLevel = areaLevel;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getAreaFlag() {
		return areaFlag;
	}

	public void setAreaFlag(int areaFlag) {
		this.areaFlag = areaFlag;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaCode=" + areaCode + ", areaName=" + areaName + ", areaLevel="
				+ areaLevel + ", parentId=" + parentId + ", areaFlag=" + areaFlag + "]";
	}

}
