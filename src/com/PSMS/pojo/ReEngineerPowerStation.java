package com.PSMS.pojo;

import java.io.Serializable;

/**
 * 工程师与电站关系
 * 
 * @author Andy
 *
 */
public class ReEngineerPowerStation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private int userId;

	private int psId;// 电站id

	private String userName;// 用户名

	private String psName;// 电站名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ReEngineerPowerStation [id=" + id + ", userId=" + userId + ", psId=" + psId + ", userName=" + userName
				+ ", psName=" + psName + "]";
	}
}
