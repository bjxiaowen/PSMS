package com.PSMS.pojo;

import java.io.Serializable;

/**
 * 工程师区域关系
 * 
 * @author Andy
 *
 */
public class ReEngineerArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String areaId;

	private int userId;

	private String areaName;

	private String userName;

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

	@Override
	public String toString() {
		return "ReEngineerArea [id=" + id + ", areaId=" + areaId + ", userId=" + userId + ", areaName=" + areaName
				+ ", userName=" + userName + "]";
	}

}
