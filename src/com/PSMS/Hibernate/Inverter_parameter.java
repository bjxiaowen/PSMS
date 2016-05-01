/** * * 
* 文件名称: toInverterManageAction.java *
* 
* 逆变器信息类，对应数据库中Inverter_parameter表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-18 下午2:27:40 *
* * @author jiaojiao.wang 
*/ 
package com.PSMS.Hibernate;

import java.util.Date;
/** 
* 设备信息类，对应数据库中Inverter_parameter表，其中id是主键，type，brand，model和PS_id是（来自Equipment和PS_information表）的外键*
* @author jiaojiao.wang 
* @date 2014-11-18 
* @param id 
* @param PS_id 
* @param name 
* @param brand 
* @param model
* @param type	 
* @param Purchase_time 
* @param Rate_power
* @param Rated_voltage	
* @param Max_power 
* @param Power_factor
* @param Period_num	
*/ 
public class Inverter_parameter {

	private int id;
	private int PS_id;
	private String name;
	private String type;
	private String brand;
	private String model;
	private String Purchase_time;
	private String Rate_power;
	private String Rated_voltage;
	private String Max_power;
	private String Power_factor;
	private Integer Period_num;
	
	public Integer getPeriod_num() {
		return Period_num;
	}
	public void setPeriod_num(Integer period_num) {
		Period_num = period_num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPS_id() {
		return PS_id;
	}
	public void setPS_id(int pS_id) {
		PS_id = pS_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getPurchase_time() {
		return Purchase_time;
	}
	public void setPurchase_time(String purchase_time) {
		Purchase_time = purchase_time;
	}
	public String getRate_power() {
		return Rate_power;
	}
	public void setRate_power(String rate_power) {
		Rate_power = rate_power;
	}
	public String getRated_voltage() {
		return Rated_voltage;
	}
	public void setRated_voltage(String rated_voltage) {
		Rated_voltage = rated_voltage;
	}
	public String getMax_power() {
		return Max_power;
	}
	public void setMax_power(String max_power) {
		Max_power = max_power;
	}
	public String getPower_factor() {
		return Power_factor;
	}
	public void setPower_factor(String power_factor) {
		Power_factor = power_factor;
	}
	
	
	
}
