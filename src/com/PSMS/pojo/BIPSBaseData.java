package com.PSMS.pojo;

import java.math.BigDecimal;


public class BIPSBaseData {
	
	private BigDecimal    X_TPV_Voltage;								//组件电压：  
	private BigDecimal    X_TPV_Current;                                //组件电流：  
	private BigDecimal    BatteryVoltage;                               //蓄电池电压  
	private BigDecimal    X_Battery_Current;                            //蓄电池电流  
	private BigDecimal    ExchangeOutPower;                             //功率：      
	private BigDecimal    OutputVoltage;                                //电压：      
	private BigDecimal    OutputCurrent;                                //电流：      
	private BigDecimal    X_AC_Frequency;                               //频率：    
	private BigDecimal    X_TPV_Power;                                  //光伏阵列功率
	private BigDecimal    BatteryPower;       							//蓄电池功率  
	private BigDecimal    MpptTemp;                                     //mppt温度    
	private int    X_Run_Status ;                                //运行状态    
	private int    ChargeDischarge;                              //充放电      
	private BigDecimal 	  X_Battery_tem;								//温度
	private int 	  X_Failcode_1;									//故障代码第9位，1则显示蓄电池欠压，0则正常
	private int    X_Battery_Capacity;							//剩余容量
	
	private BigDecimal X_Coutpout_Voltage;//直流电压
	private BigDecimal X_Coutpout_Current;//直流电流
	private BigDecimal X_Coutpout_Power;//直流功率
	private BigDecimal X_Inerin_tem;//逆变器内部温度
	private BigDecimal MachineState;//1：机器失效，0：正常
	private String 		OperateDate;//最新操作时间
	
	
	
	public BigDecimal getX_TPV_Voltage() {
		return X_TPV_Voltage;
	}
	public void setX_TPV_Voltage(BigDecimal x_TPV_Voltage) {
		X_TPV_Voltage = x_TPV_Voltage;
	}
	public BigDecimal getX_TPV_Current() {
		return X_TPV_Current;
	}
	public void setX_TPV_Current(BigDecimal x_TPV_Current) {
		X_TPV_Current = x_TPV_Current;
	}
	public BigDecimal getBatteryVoltage() {
		return BatteryVoltage;
	}
	public void setBatteryVoltage(BigDecimal batteryVoltage) {
		BatteryVoltage = batteryVoltage;
	}
	public BigDecimal getX_Battery_Current() {
		return X_Battery_Current;
	}
	public void setX_Battery_Current(BigDecimal x_Battery_Current) {
		X_Battery_Current = x_Battery_Current;
	}
	public BigDecimal getExchangeOutPower() {
		return ExchangeOutPower;
	}
	public void setExchangeOutPower(BigDecimal exchangeOutPower) {
		ExchangeOutPower = exchangeOutPower;
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
	public BigDecimal getX_AC_Frequency() {
		return X_AC_Frequency;
	}
	public void setX_AC_Frequency(BigDecimal x_AC_Frequency) {
		X_AC_Frequency = x_AC_Frequency;
	}
	public BigDecimal getX_TPV_Power() {
		return X_TPV_Power;
	}
	public void setX_TPV_Power(BigDecimal x_TPV_Power) {
		X_TPV_Power = x_TPV_Power;
	}
	public BigDecimal getBatteryPower() {
		return BatteryPower;
	}
	public void setBatteryPower(BigDecimal batteryPower) {
		BatteryPower = batteryPower;
	}
	public BigDecimal getMpptTemp() {
		return MpptTemp;
	}
	public void setMpptTemp(BigDecimal mpptTemp) {
		MpptTemp = mpptTemp;
	}
	public int getX_Run_Status() {
		return X_Run_Status;
	}
	public void setX_Run_Status(int x_Run_Status) {
		X_Run_Status = x_Run_Status;
	}
	public int getX_Battery_Capacity() {
		return X_Battery_Capacity;
	}
	public void setX_Battery_Capacity(int x_Battery_Capacity) {
		X_Battery_Capacity = x_Battery_Capacity;
	}
	public int getChargeDischarge() {
		return ChargeDischarge;
	}
	public void setChargeDischarge(int chargeDischarge) {
		ChargeDischarge = chargeDischarge;
	}
	public BigDecimal getX_Battery_tem() {
		return X_Battery_tem;
	}
	public void setX_Battery_tem(BigDecimal x_Battery_tem) {
		X_Battery_tem = x_Battery_tem;
	}
	public int getX_Failcode_1() {
		return X_Failcode_1;
	}
	public void setX_Failcode_1(int x_Failcode_1) {
		X_Failcode_1 = x_Failcode_1;
	}
	public BigDecimal getX_Coutpout_Voltage() {
		return X_Coutpout_Voltage;
	}
	public void setX_Coutpout_Voltage(BigDecimal x_Coutpout_Voltage) {
		X_Coutpout_Voltage = x_Coutpout_Voltage;
	}
	public BigDecimal getX_Coutpout_Current() {
		return X_Coutpout_Current;
	}
	public void setX_Coutpout_Current(BigDecimal x_Coutpout_Current) {
		X_Coutpout_Current = x_Coutpout_Current;
	}
	public BigDecimal getX_Coutpout_Power() {
		return X_Coutpout_Power;
	}
	public void setX_Coutpout_Power(BigDecimal x_Coutpout_Power) {
		X_Coutpout_Power = x_Coutpout_Power;
	}
	public BigDecimal getX_Inerin_tem() {
		return X_Inerin_tem;
	}
	public void setX_Inerin_tem(BigDecimal x_Inerin_tem) {
		X_Inerin_tem = x_Inerin_tem;
	}
	public BigDecimal getMachineState() {
		return MachineState;
	}
	public void setMachineState(BigDecimal machineState) {
		MachineState = machineState;
	}
	public String getOperateDate() {
		return OperateDate;
	}
	public void setOperateDate(String operateDate) {
		OperateDate = operateDate;
	}
	
}
