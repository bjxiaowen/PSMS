package com.PSMS.pojo;

import java.math.BigDecimal;

public class InParameter {

	private BigDecimal modelInCurrent;//组件输入电流
	
	private BigDecimal modelInVoltage;//组件输入电压
	
	private BigDecimal batteryInVoltage;//蓄电池输入电压
	
	private BigDecimal batteryInCurrent;//蓄电池输入电流
	
	private BigDecimal CurrPower;//直流侧功率
	
	private BigDecimal Power_factor;//功率因数

	public BigDecimal getModelInCurrent() {
		return modelInCurrent;
	}

	public void setModelInCurrent(BigDecimal modelInCurrent) {
		this.modelInCurrent = modelInCurrent;
	}

	public BigDecimal getModelInVoltage() {
		return modelInVoltage;
	}

	public void setModelInVoltage(BigDecimal modelInVoltage) {
		this.modelInVoltage = modelInVoltage;
	}

	public BigDecimal getBatteryInVoltage() {
		return batteryInVoltage;
	}

	public void setBatteryInVoltage(BigDecimal batteryInVoltage) {
		this.batteryInVoltage = batteryInVoltage;
	}

	public BigDecimal getBatteryInCurrent() {
		return batteryInCurrent;
	}

	public void setBatteryInCurrent(BigDecimal batteryInCurrent) {
		this.batteryInCurrent = batteryInCurrent;
	}

	public BigDecimal getCurrPower() {
		return CurrPower;
	}

	public void setCurrPower(BigDecimal currPower) {
		CurrPower = currPower;
	}

	public BigDecimal getPower_factor() {
		return Power_factor;
	}

	public void setPower_factor(BigDecimal power_factor) {
		Power_factor = power_factor;
	}
	
}
