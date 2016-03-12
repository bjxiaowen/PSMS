/** * * 
* 文件名称: Equipment.java *
* 
* 设备信息类，对应数据库中Equipment表 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-6 下午5:33:40 *
* * @author liu.yang 
*/ 
package com.PSMS.Hibernate;


/** 
* 设备信息类，对应数据库中Equipment表，其中id是主键*
* @author liu.yang 
* @date 2014-11-6 
* @param id 
* @param brand 
* @param model
* @param type	
*/ 
public class Equipment {
	
	private int id;
	private String brand;
	private String model;
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
