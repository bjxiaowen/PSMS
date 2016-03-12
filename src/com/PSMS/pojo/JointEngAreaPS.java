package com.PSMS.pojo;

import java.io.Serializable;

/**
 * 关联出工程师区域电站
 * @author Andy
 * @date 2016-03-08
 */
public class JointEngAreaPS implements Serializable{

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
	
	/**
	 * 区域名称
	 */
	private String areaName;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	
	/**
	 * 电站名称
	 */
	private String psName;


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPsName() {
		return psName;
	}


	public void setPsName(String psName) {
		this.psName = psName;
	}
	

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
		return "JointEngAreaPS [id=" + id + ", areaId=" + areaId + ", userId=" + userId + ", psId=" + psId
				+ ", areaName=" + areaName + ", userName=" + userName + ", psName=" + psName + "]";
	}

	
}
