/** * * 
* 文件名称: JB_parameter.java *
* 
* 汇流箱信息类，对应数据库中JB_parameter表 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-16 下午1:52:33 *
* * @author liu.yang 
*/ 
package com.PSMS.Hibernate;

public class JB_parameter {
	/** 
	* 汇流箱信息类，对应数据库中JB_parameter表，其中id是主键，type，brand，model和PS_id是（来自Equipment和PS_information表）的外键*
	* @author liu.yang 
	* @date 2014-12-16 
	* @param id 
	* @param PS_id 
	* @param name 
	* @param brand 
	* @param model
	* @param type	 
	* @param Purchase_time 
	* @param Max_dc_voltage
	* @param Road_num	
	* @param Period_num 	
	*/ 
	
	private int id;
	private int PS_id;
	private String name;
	private String type;
	private String brand;
	private String model;
	private String Purchase_time;
	private String Max_dc_voltage;
	private String Road_num;
	private int Period_num;
	
	public int getPeriod_num() {
		return Period_num;
	}
	public void setPeriod_num(int period_num) {
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
	public String getMax_dc_voltage() {
		return Max_dc_voltage;
	}
	public void setMax_dc_voltage(String max_dc_voltage) {
		Max_dc_voltage = max_dc_voltage;
	}
	public String getRoad_num() {
		return Road_num;
	}
	public void setRoad_num(String road_num) {
		Road_num = road_num;
	}
	
	
}
