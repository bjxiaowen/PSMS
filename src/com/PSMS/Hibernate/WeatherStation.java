package com.PSMS.Hibernate;

import java.sql.Timestamp;

/**
 * WeatherStation1 entity. @author MyEclipse Persistence Tools
 */

public class WeatherStation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parameterId;
	private Double pvTemperature;
	private Double irraditationValue;
	private Double temperature;
	private Double windSpeed;
	private Integer windDirection;
	private Double humidity;
	private String time;

	// Constructors

	/** default constructor */
	public WeatherStation() {
	}

	/** minimal constructor */
	public WeatherStation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WeatherStation(Integer id, Integer parameterId,
			Double pvTemperature, Double irraditationValue, Double temperature,
			Double windSpeed, Integer windDirection, Double humidity,
			String time) {
		this.id = id;
		this.parameterId = parameterId;
		this.pvTemperature = pvTemperature;
		this.irraditationValue = irraditationValue;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.humidity = humidity;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}

	public Double getPvTemperature() {
		return this.pvTemperature;
	}

	public void setPvTemperature(Double pvTemperature) {
		this.pvTemperature = pvTemperature;
	}

	public Double getIrraditationValue() {
		return this.irraditationValue;
	}

	public void setIrraditationValue(Double irraditationValue) {
		this.irraditationValue = irraditationValue;
	}

	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getWindSpeed() {
		return this.windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Integer getWindDirection() {
		return this.windDirection;
	}

	public void setWindDirection(Integer windDirection) {
		this.windDirection = windDirection;
	}

	public Double getHumidity() {
		return this.humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}