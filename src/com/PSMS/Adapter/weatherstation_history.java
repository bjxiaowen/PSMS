package com.PSMS.Adapter;

public class weatherstation_history {
	private int id;
	private String weatherstation_name;
	private double pv_temperature;
	private double irraditation_value;
	private double temperature;
	private double wind_speed;
	private int wind_direction;
	private double humidity;
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWeatherstation_name() {
		return weatherstation_name;
	}
	public void setWeatherstation_name(String weatherstation_name) {
		this.weatherstation_name = weatherstation_name;
	}
	public double getPv_temperature() {
		return pv_temperature;
	}
	public void setPv_temperature(double pv_temperature) {
		this.pv_temperature = pv_temperature;
	}
	public double getIrraditation_value() {
		return irraditation_value;
	}
	public void setIrraditation_value(double irraditation_value) {
		this.irraditation_value = irraditation_value;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}
	
	public int getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(int wind_direction) {
		this.wind_direction = wind_direction;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
