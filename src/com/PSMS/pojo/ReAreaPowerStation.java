package com.PSMS.pojo;

import java.io.Serializable;

/**
 * 区域与电站关系
 * 
 * @author Andy
 *
 */
public class ReAreaPowerStation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private int psId;// 电站id

	private String areaId;// 区域id
	
	private String areaName;//区域名称
	
	private String psName;//电站名称


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "ReAreaPowerStation [id=" + id + ", psId=" + psId + ", areaId=" + areaId + ", areaName=" + areaName
				+ ", psName=" + psName + "]";
	}
	
	
}
