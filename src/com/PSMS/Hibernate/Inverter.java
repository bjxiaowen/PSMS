package com.PSMS.Hibernate;

/**
 * Inverter1 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Inverter implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parameterId;
	private Double dcCurrent;
	private Double dcVoltage;
	private Double dcPower;
	private Double acCurrent;
	private Double acVoltage;
	private Double acPower;
	private Double frequency;
	private Double temperature;
	private String time;
	private String state;
	private Integer flag;

	/** full constructor */
	public Inverter(Integer id, Integer parameterId, Double dcCurrent,
			Double dcVoltage, Double dcPower, Double acCurrent,
			Double acVoltage, Double acPower, Double frequency,
			Double temperature, String time, String state, Integer flag) {
		this.id = id;
		this.parameterId = parameterId;
		this.dcCurrent = dcCurrent;
		this.dcVoltage = dcVoltage;
		this.dcPower = dcPower;
		this.acCurrent = acCurrent;
		this.acVoltage = acVoltage;
		this.acPower = acPower;
		this.frequency = frequency;
		this.temperature = temperature;
		this.time = time;
		this.state = state;
		this.flag = flag;
	}

	public Inverter() {
	}

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

	public Double getDcCurrent() {
		return this.dcCurrent;
	}

	public void setDcCurrent(Double dcCurrent) {
		this.dcCurrent = dcCurrent;
	}

	public Double getDcVoltage() {
		return this.dcVoltage;
	}

	public void setDcVoltage(Double dcVoltage) {
		this.dcVoltage = dcVoltage;
	}

	public Double getDcPower() {
		return this.dcPower;
	}

	public void setDcPower(Double dcPower) {
		this.dcPower = dcPower;
	}

	public Double getAcCurrent() {
		return this.acCurrent;
	}

	public void setAcCurrent(Double acCurrent) {
		this.acCurrent = acCurrent;
	}

	public Double getAcVoltage() {
		return this.acVoltage;
	}

	public void setAcVoltage(Double acVoltage) {
		this.acVoltage = acVoltage;
	}

	public Double getAcPower() {
		return this.acPower;
	}

	public void setAcPower(Double acPower) {
		this.acPower = acPower;
	}

	public Double getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}