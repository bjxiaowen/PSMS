package com.PSMS.Hibernate;

public class Failure_alarm {
	private int id;
	private String type;
	private String brand;
	private String model;
	private String Failure_code;
	private String Failure_meaning;
	private String Failure_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getFailure_code() {
		return Failure_code;
	}
	public void setFailure_code(String failure_code) {
		Failure_code = failure_code;
	}
	public String getFailure_meaning() {
		return Failure_meaning;
	}
	public void setFailure_meaning(String failure_meaning) {
		Failure_meaning = failure_meaning;
	}
	public String getFailure_type() {
		return Failure_type;
	}
	public void setFailure_type(String failure_type) {
		Failure_type = failure_type;
	}
	
	
}
