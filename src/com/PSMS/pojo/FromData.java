package com.PSMS.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 来源数据
 * @author Andy
 *
 */
public class FromData implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2227477163506693491L;
	
	private int InverterDataID;//设备采集数据ID
	
	private String InverterID;//设备ID
	
	private String ReceiveData;//设备接收数据(16进制)

	private Date OperateDate;//操作时间
	
	private String Type;//类型

	public int getInverterDataID() {
		return InverterDataID;
	}

	public void setInverterDataID(int inverterDataID) {
		InverterDataID = inverterDataID;
	}

	public String getInverterID() {
		return InverterID;
	}

	public void setInverterID(String inverterID) {
		InverterID = inverterID;
	}

	public String getReceiveData() {
		return ReceiveData;
	}

	public void setReceiveData(String receiveData) {
		ReceiveData = receiveData;
	}

	public Date getOperateDate() {
		return OperateDate;
	}

	public void setOperateDate(Date operateDate) {
		OperateDate = operateDate;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	
	@Override
	public String toString() {
		return "FromData [InverterDataID=" + InverterDataID + ", InverterID=" + InverterID + ", ReceiveData="
				+ ReceiveData + ", OperateDate=" + OperateDate + ", Type=" + Type + "]";
	}
}
