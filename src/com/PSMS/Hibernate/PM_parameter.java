/** * * 
* 文件名称: PM_parameter.java *
* 
* 电表信息类，对应数据库中PM_parameter表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-6 下午2:22:33 *
* * @author jiaojiao.wang 
*/
package com.PSMS.Hibernate;

import java.util.Date;
/** 
* 电表信息类，对应数据库中PM_parameter表，其中id是主键，type，brand，model和PS_id是（来自Equipment和PS_information表）的外键*
* @author jiaojiao.wang 
* @date 2014-12-6 
* @param id 
* @param PS_id 
* @param name 
* @param brand 
* @param model
* @param type	 
* @param Purchase_time 
* @param Max_current
* @param Period_num		
*/ 
public class PM_parameter {
	
	private int id;
	private int PS_id;
	private String name;
	private String type;
	private String brand;
	private String model;
	private String Purchase_time;
	private String Max_current;
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
	public String getMax_current() {
		return Max_current;
	}
	public void setMax_current(String max_current) {
		Max_current = max_current;
	}

	
	
	
}
