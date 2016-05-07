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
	
	private int groupHour;//按小时分组时间
	
	private int groupMonth;//按月分组
	
	private int groupDay;//按天分组
	
	private BigDecimal mpptTemp;//温度
	
	private int chargeDischarge;//0：电池充电  1：电池放电
	
	private BigDecimal voltage;//电压
	
	private BigDecimal current;//电流
	
	private BigDecimal power;//功率
	
	private int outputState;//输出状态  1：输出过载，0：输出正常
	
	private Date date;//日期
	
	private int psId;//电站id
	
	private String psName;//电站名称
	
	private int partNum;//光伏板数量
	
	private BigDecimal area;//电站占地面积
	
	private int machineState;// 1：机器失效，0：正常
	
	private int undervoltage;//1：电池欠压  0：电池正常
	
	private BigDecimal currDayQ;//当天日发电量
	
	private BigDecimal currDayCountQ;// 当天累计发电量
	
	private BigDecimal currMonthQ;//当月发电量
	
	private BigDecimal currMonthCountQ;//当月累计发电量
	
	private BigDecimal currYearQ;//当年每月发电量
	
	private BigDecimal currYearCountQ;//当年总发电量
	
	private BigDecimal historyCountQ;//历史发电量
	
	private BigDecimal countCarbon;//二氧化碳
	
	private String batteryCapacity;//蓄电池容量
	
	private int dayCount;//运行天数
	
	
	
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

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

	public int getPartNum() {
		return partNum;
	}

	public void setPartNum(int partNum) {
		this.partNum = partNum;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public int getOutputState() {
		return outputState;
	}

	public void setOutputState(int outputState) {
		this.outputState = outputState;
	}

	public int getMachineState() {
		return machineState;
	}

	public void setMachineState(int machineState) {
		this.machineState = machineState;
	}



	public int getUndervoltage() {
		return undervoltage;
	}

	public void setUndervoltage(int undervoltage) {
		this.undervoltage = undervoltage;
	}

	public BigDecimal getCurrDayQ() {
		return currDayQ;
	}

	public void setCurrDayQ(BigDecimal currDayQ) {
		this.currDayQ = currDayQ;
	}

	public BigDecimal getCurrDayCountQ() {
		return currDayCountQ;
	}

	public void setCurrDayCountQ(BigDecimal currDayCountQ) {
		this.currDayCountQ = currDayCountQ;
	}

	public BigDecimal getCurrMonthQ() {
		return currMonthQ;
	}

	public void setCurrMonthQ(BigDecimal currMonthQ) {
		this.currMonthQ = currMonthQ;
	}

	public BigDecimal getCurrMonthCountQ() {
		return currMonthCountQ;
	}

	public void setCurrMonthCountQ(BigDecimal currMonthCountQ) {
		this.currMonthCountQ = currMonthCountQ;
	}

	public BigDecimal getCurrYearQ() {
		return currYearQ;
	}

	public void setCurrYearQ(BigDecimal currYearQ) {
		this.currYearQ = currYearQ;
	}

	public BigDecimal getCurrYearCountQ() {
		return currYearCountQ;
	}

	public void setCurrYearCountQ(BigDecimal currYearCountQ) {
		this.currYearCountQ = currYearCountQ;
	}

	public int getGroupMonth() {
		return groupMonth;
	}

	public void setGroupMonth(int groupMonth) {
		this.groupMonth = groupMonth;
	}

	public int getGroupDay() {
		return groupDay;
	}

	public void setGroupDay(int groupDay) {
		this.groupDay = groupDay;
	}

	public BigDecimal getHistoryCountQ() {
		return historyCountQ;
	}

	public void setHistoryCountQ(BigDecimal historyCountQ) {
		this.historyCountQ = historyCountQ;
	}

	public BigDecimal getCountCarbon() {
		return countCarbon;
	}

	public void setCountCarbon(BigDecimal countCarbon) {
		this.countCarbon = countCarbon;
	}

	public String getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(String batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
}
