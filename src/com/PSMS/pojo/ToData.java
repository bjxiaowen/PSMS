package com.PSMS.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据转化
 * 
 * @author Andy
 *
 */
public class ToData implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 518555477335382832L;

	private int InverterDataID;// 采集数据id
	
	private String InverterID;// 设备id
	
	private String ReceiveData;// 接收的数据
	
	private Date OperateDate;// 操作时间
	
	private String Type;// 类型
	
	private BigDecimal InputVoltage;// 输入电压
	
	private BigDecimal BatteryVoltage;// 电池电压
	
	private BigDecimal OutputVoltage;// 输出电压
	
	private BigDecimal OutputCurrent;// 输出电流
	
	private int CityPower;// 市电:1：市电中断,0：市电正常
	
	private int Undervoltage;// 欠压
	
	private int Overvoltage;// 过压
	
	private int MachineState;// 1：机器失效，0：正常
	
	private int ChargeDischarge;// 0：电池充电,1：电池放电
	
	private int MachineObligate1;// 预留1
	
	private int OutputState;// 1：输出过载，0：输出正常
	
	private String LineFrequency;// 电网频率
	
	private int CanMake;// '1:电池充电使能，0:电池充电禁止'
	
	private int InCity   ; // 1:进市电使能，0:进市电禁止
	
	private int ShowObligate5 ;//预留5
	
	private int ShowObligate4 ;//预留4
	
	private int ShowObligate3 ;//预留3
	
	private int ShowObligate2 ;//预留2
	
	private int ShowObligate1 ;//预留1
	
	private int ShowObligate0 ;//预留0
	
	private BigDecimal MpptInVoltage;//mppt输入电压
	
	private BigDecimal MpptOutVoltage;//mppt输出电压
	
	private BigDecimal MpptOutCurrent;//mppt输出电流
	
	private BigDecimal MpptTemp ;//mppt模块温度
	
	private int ShowObligate ;//预留
	
	private BigDecimal CurrHistoryQ;//直流侧历史发电量
	
	private   BigDecimal CurrDayQ;//直流侧日发电量
	
	private   BigDecimal    CurrPower;//直流侧功率
	
	private   BigDecimal    LoadHistoryQ ;//负载历史用电量
	
	private   BigDecimal    LoadDayQ;//负载日用电量
	
	private   BigDecimal   ExchangeOutPower;//交流输出功率
	
	
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
	
	public BigDecimal getInputVoltage() {
		return InputVoltage;
	}
	
	public void setInputVoltage(BigDecimal inputVoltage) {
		InputVoltage = inputVoltage;
	}
	
	public BigDecimal getBatteryVoltage() {
		return BatteryVoltage;
	}
	
	public void setBatteryVoltage(BigDecimal batteryVoltage) {
		BatteryVoltage = batteryVoltage;
	}
	
	public BigDecimal getOutputVoltage() {
		return OutputVoltage;
	}
	
	public void setOutputVoltage(BigDecimal outputVoltage) {
		OutputVoltage = outputVoltage;
	}
	
	public BigDecimal getOutputCurrent() {
		return OutputCurrent;
	}
	
	public void setOutputCurrent(BigDecimal outputCurrent) {
		OutputCurrent = outputCurrent;
	}
	
	public int getCityPower() {
		return CityPower;
	}
	
	public void setCityPower(int cityPower) {
		CityPower = cityPower;
	}
	
	public int getUndervoltage() {
		return Undervoltage;
	}
	
	public void setUndervoltage(int undervoltage) {
		Undervoltage = undervoltage;
	}
	
	public int getOvervoltage() {
		return Overvoltage;
	}
	
	public void setOvervoltage(int overvoltage) {
		Overvoltage = overvoltage;
	}
	
	public int getMachineState() {
		return MachineState;
	}
	
	public void setMachineState(int machineState) {
		MachineState = machineState;
	}
	
	public int getChargeDischarge() {
		return ChargeDischarge;
	}
	
	public void setChargeDischarge(int chargeDischarge) {
		ChargeDischarge = chargeDischarge;
	}
	
	public int getMachineObligate1() {
		return MachineObligate1;
	}
	public void setMachineObligate1(int machineObligate1) {
		MachineObligate1 = machineObligate1;
	}
	public int getOutputState() {
		return OutputState;
	}
	public void setOutputState(int outputState) {
		OutputState = outputState;
	}
	public String getLineFrequency() {
		return LineFrequency;
	}
	public void setLineFrequency(String lineFrequency) {
		LineFrequency = lineFrequency;
	}
	public int getCanMake() {
		return CanMake;
	}
	public void setCanMake(int canMake) {
		CanMake = canMake;
	}
	public int getInCity() {
		return InCity;
	}
	public void setInCity(int inCity) {
		InCity = inCity;
	}
	public int getShowObligate5() {
		return ShowObligate5;
	}
	public void setShowObligate5(int showObligate5) {
		ShowObligate5 = showObligate5;
	}
	public int getShowObligate4() {
		return ShowObligate4;
	}
	public void setShowObligate4(int showObligate4) {
		ShowObligate4 = showObligate4;
	}
	public int getShowObligate3() {
		return ShowObligate3;
	}
	public void setShowObligate3(int showObligate3) {
		ShowObligate3 = showObligate3;
	}
	public int getShowObligate2() {
		return ShowObligate2;
	}
	public void setShowObligate2(int showObligate2) {
		ShowObligate2 = showObligate2;
	}
	public int getShowObligate1() {
		return ShowObligate1;
	}
	public void setShowObligate1(int showObligate1) {
		ShowObligate1 = showObligate1;
	}
	public int getShowObligate0() {
		return ShowObligate0;
	}
	public void setShowObligate0(int showObligate0) {
		ShowObligate0 = showObligate0;
	}
	public BigDecimal getMpptInVoltage() {
		return MpptInVoltage;
	}
	public void setMpptInVoltage(BigDecimal mpptInVoltage) {
		MpptInVoltage = mpptInVoltage;
	}
	public BigDecimal getMpptOutVoltage() {
		return MpptOutVoltage;
	}
	public void setMpptOutVoltage(BigDecimal mpptOutVoltage) {
		MpptOutVoltage = mpptOutVoltage;
	}
	public BigDecimal getMpptOutCurrent() {
		return MpptOutCurrent;
	}
	public void setMpptOutCurrent(BigDecimal mpptOutCurrent) {
		MpptOutCurrent = mpptOutCurrent;
	}
	public BigDecimal getMpptTemp() {
		return MpptTemp;
	}
	public void setMpptTemp(BigDecimal mpptTemp) {
		MpptTemp = mpptTemp;
	}

	public int getShowObligate() {
		return ShowObligate;
	}

	public void setShowObligate(int showObligate) {
		ShowObligate = showObligate;
	}

	public BigDecimal getCurrHistoryQ() {
		return CurrHistoryQ;
	}

	public void setCurrHistoryQ(BigDecimal currHistoryQ) {
		CurrHistoryQ = currHistoryQ;
	}

	public BigDecimal getCurrDayQ() {
		return CurrDayQ;
	}

	public void setCurrDayQ(BigDecimal currDayQ) {
		CurrDayQ = currDayQ;
	}

	public BigDecimal getCurrPower() {
		return CurrPower;
	}

	public void setCurrPower(BigDecimal currPower) {
		CurrPower = currPower;
	}

	public BigDecimal getLoadHistoryQ() {
		return LoadHistoryQ;
	}

	public void setLoadHistoryQ(BigDecimal loadHistoryQ) {
		LoadHistoryQ = loadHistoryQ;
	}

	public BigDecimal getLoadDayQ() {
		return LoadDayQ;
	}

	public void setLoadDayQ(BigDecimal loadDayQ) {
		LoadDayQ = loadDayQ;
	}

	public BigDecimal getExchangeOutPower() {
		return ExchangeOutPower;
	}

	public void setExchangeOutPower(BigDecimal exchangeOutPower) {
		ExchangeOutPower = exchangeOutPower;
	}
}
