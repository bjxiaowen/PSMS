package com.PSMS.Adapter;

import java.util.Date;

public class inverter_parameter {
	private int id;
	private String ps_name;
	private String name;
	private String type;
	private String brand;
	private String model;
	private String Purchase_time;
	private String Rate_power;
	private String Rated_voltage;
	private String Max_power;
	private String Power_factor;
	
	private String BatteryCapacity;//电池容量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPs_name() {
		return ps_name;
	}
	public void setPs_name(String ps_name) {
		this.ps_name = ps_name;
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
	public String getBatteryCapacity() {
		return BatteryCapacity;
	}
	public void setBatteryCapacity(String batteryCapacity) {
		BatteryCapacity = batteryCapacity;
	}
	
	
	
}
