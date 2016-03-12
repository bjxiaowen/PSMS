package com.PSMS.Hibernate;

public class Device_period {
	
	private int id;
	private int PS_id;
	private int Period_num;
	private String Device_type;
	private int Parameter_id;
	private String name;
	private int Unit_num;
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
	public int getPeriod_num() {
		return Period_num;
	}
	public void setPeriod_num(int period_num) {
		Period_num = period_num;
	}
	public String getDevice_type() {
		return Device_type;
	}
	public void setDevice_type(String device_type) {
		Device_type = device_type;
	}
	public int getParameter_id() {
		return Parameter_id;
	}
	public void setParameter_id(int parameter_id) {
		Parameter_id = parameter_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnit_num() {
		return Unit_num;
	}
	public void setUnit_num(int unit_num) {
		Unit_num = unit_num;
	}
	
	

}
