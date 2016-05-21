package com.PSMS.pojo;
/**
 * 电站设备
 * @author Andy
 *
 */
public class PSEquipment {

	private int psId;//电站id
	
	private String psName;//电站名称
	
	private String type;//电站设备

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
