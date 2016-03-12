package com.PSMS.pojo;

import java.io.Serializable;

/**
 * 工程师区域电站关系表
 * @author Andy
 * @date 2016-03-08
 */
public class ReEngAreaPowerStation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 区域id
	 */
	private String areaId; 
	
	/**
	 * 工程师id
	 */
	private int userId; 
	
	/**
	 *电厂id 
	 */
	private int  psId; 
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPsId() {
		return psId;
	}

	public void setPsId(int psId) {
		this.psId = psId;
	}

	@Override
	public String toString() {
		return "ReEngineerAreaPowerStation [id=" + id + ", areaId=" + areaId + ", userId=" + userId + ", psId=" + psId
				+ "]";
	}
}
