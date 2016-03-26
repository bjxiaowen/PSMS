package com.PSMS.pojo;

import java.math.BigDecimal;

/**
 * 电站信息
 * @author Andy
 *
 */
public class PowerStationBase {
	
	
	private BigDecimal totalCapacity;//电站容量
	
	private BigDecimal totalPower;//总数输出功率
	
	private int currHour;//统计时间
	
	private int groupHour;//分组时间
	
	private BigDecimal mpptTemp;//温度
	
	private BigDecimal batteryVoltage;//电池电压
	
	private int chargeDischarge;//0：电池充电  1：电池放电

	public BigDecimal getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(BigDecimal totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public BigDecimal getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(BigDecimal totalPower) {
		this.totalPower = totalPower;
	}

	public int getCurrHour() {
		return currHour;
	}

	public void setCurrHour(int currHour) {
		this.currHour = currHour;
	}

	public int getGroupHour() {
		return groupHour;
	}

	public void setGroupHour(int groupHour) {
		this.groupHour = groupHour;
	}

	public BigDecimal getMpptTemp() {
		return mpptTemp;
	}

	public void setMpptTemp(BigDecimal mpptTemp) {
		this.mpptTemp = mpptTemp;
	}

	public BigDecimal getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(BigDecimal batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getChargeDischarge() {
		return chargeDischarge;
	}

	public void setChargeDischarge(int chargeDischarge) {
		this.chargeDischarge = chargeDischarge;
	}

	@Override
	public String toString() {
		return "PowerStationBase [totalCapacity=" + totalCapacity + ", totalPower=" + totalPower + ", currHour="
				+ currHour + ", groupHour=" + groupHour + ", mpptTemp=" + mpptTemp + ", batteryVoltage="
				+ batteryVoltage + ", chargeDischarge=" + chargeDischarge + "]";
	}
	
}
