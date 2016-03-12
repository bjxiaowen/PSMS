package com.PSMS.Adapter;

public class stationMonitorAdapter {
	private String inverterPower;//逆变器功率
	private String irradiationValue;//辐照度
	private String temperature;//当前温度
	private String accPower;//发电量
	private String activePower;//当日上网电量
	private String state;//逆变器状态
	public String getInverterPower() {
		return inverterPower;
	}
	public void setInverterPower(String inverterPower) {
		this.inverterPower = inverterPower;
	}
	public String getIrradiationValue() {
		return irradiationValue;
	}
	public void setIrradiationValue(String irradiationValue) {
		this.irradiationValue = irradiationValue;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getAccPower() {
		return accPower;
	}
	public void setAccPower(String accPower) {
		this.accPower = accPower;
	}
	public String getActivePower() {
		return activePower;
	}
	public void setActivePower(String activePower) {
		this.activePower = activePower;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
