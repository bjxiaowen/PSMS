package com.PSMS.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 电站信息
 * @author Andy
 *
 */
public class PowerStationBase {
	
	
	private BigDecimal totalCapacity;//电站容量
	
	private BigDecimal totalPower;//总数输出功率
	
	private BigDecimal totalVoltage;//总电压
	
	private BigDecimal  totalCurrent;//总电流
	
	private int currHour;//统计时间
	
	private int groupHour;//分组时间
	
	private BigDecimal mpptTemp;//温度
	
	private int chargeDischarge;//0：电池充电  1：电池放电
	
	private BigDecimal voltage;//电压
	
	private BigDecimal current;//电流
	
	private BigDecimal power;//功率
	
	private int status;//状态
	
	private Date date;//日期
	
	private int psId;//电站id
	
	private String psName;//电站名称
	
	private int partNum;//光伏板数量
	
	private BigDecimal area;//电站占地面积
	
	

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


	public int getChargeDischarge() {
		return chargeDischarge;
	}

	public void setChargeDischarge(int chargeDischarge) {
		this.chargeDischarge = chargeDischarge;
	}

	public BigDecimal getVoltage() {
		return voltage;
	}

	public void setVoltage(BigDecimal voltage) {
		this.voltage = voltage;
	}

	public BigDecimal getCurrent() {
		return current;
	}

	public void setCurrent(BigDecimal current) {
		this.current = current;
	}

	public BigDecimal getPower() {
		return power;
	}

	public void setPower(BigDecimal power) {
		this.power = power;
	}

	public BigDecimal getTotalVoltage() {
		return totalVoltage;
	}

	public void setTotalVoltage(BigDecimal totalVoltage) {
		this.totalVoltage = totalVoltage;
	}

	public BigDecimal getTotalCurrent() {
		return totalCurrent;
	}

	public void setTotalCurrent(BigDecimal totalCurrent) {
		this.totalCurrent = totalCurrent;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PowerStationBase [totalCapacity=" + totalCapacity + ", totalPower=" + totalPower + ", totalVoltage="
				+ totalVoltage + ", totalCurrent=" + totalCurrent + ", currHour=" + currHour + ", groupHour="
				+ groupHour + ", mpptTemp=" + mpptTemp + ", chargeDischarge=" + chargeDischarge + ", voltage=" + voltage
				+ ", current=" + current + ", power=" + power + ", status=" + status + ", date=" + date + "]";
	}

}
