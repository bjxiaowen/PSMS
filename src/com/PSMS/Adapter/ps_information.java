package com.PSMS.Adapter;

public class ps_information {

	private int id;
	private String name;
	private String capacity;
	private String area;
	private Integer Part_num;
	private String owner;
	private String investor;
	private String province;
	private String longitude;
	private String latitude;
	private String Build_time;
	private String Station_manage;
	private String User_manage;
	private String Device_manage;
	private Integer Station_num;
	private Integer User_num;
	private Integer MachineState;//机器状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getPart_num() {
		return Part_num;
	}
	public void setPart_num(Integer part_num) {
		Part_num = part_num;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getBuild_time() {
		return Build_time;
	}
	public void setBuild_time(String build_time) {
		Build_time = build_time;
	}
	
	public String getStation_manage() {
		return Station_manage;
	}
	public void setStation_manage(String station_manage) {
		Station_manage = station_manage;
	}
	public String getUser_manage() {
		return User_manage;
	}
	public void setUser_manage(String user_manage) {
		User_manage = user_manage;
	}
	public String getDevice_manage() {
		return Device_manage;
	}
	public void setDevice_manage(String device_manage) {
		Device_manage = device_manage;
	}
	
	public Integer getStation_num() {
		return Station_num;
	}
	public void setStation_num(Integer station_num) {
		Station_num = station_num;
	}
	public Integer getUser_num() {
		return User_num;
	}
	public void setUser_num(Integer user_num) {
		User_num = user_num;
	}
	public Integer getMachineState() {
		return MachineState;
	}
	public void setMachineState(Integer machineState) {
		MachineState = machineState;
	}

}
