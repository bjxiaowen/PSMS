package com.PSMS.Hibernate;

/**
 * PowerMeter1 entity. @author MyEclipse Persistence Tools
 */

public class PowerMeter implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parameterId;
	private Double accPower;
	private Double activePower;
	private Double reactivePower;
	private Double powerFactor;
	private Double acCurrent;
	private Double acVoltage;
	private Double acPower;
	private String time;

	// Constructors

	/** default constructor */
	public PowerMeter() {
	}

	/** minimal constructor */
	public PowerMeter(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public PowerMeter(Integer id, Integer parameterId, Double accPower,
			Double activePower, Double reactivePower, Double powerFactor,
			Double acCurrent, Double acVoltage, Double acPower, String time) {
		this.id = id;
		this.parameterId = parameterId;
		this.accPower = accPower;
		this.activePower = activePower;
		this.reactivePower = reactivePower;
		this.powerFactor = powerFactor;
		this.acCurrent = acCurrent;
		this.acVoltage = acVoltage;
		this.acPower = acPower;
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

	public Double getAccPower() {
		return this.accPower;
	}

	public void setAccPower(Double accPower) {
		this.accPower = accPower;
	}

	public Double getActivePower() {
		return this.activePower;
	}

	public void setActivePower(Double activePower) {
		this.activePower = activePower;
	}

	public Double getReactivePower() {
		return this.reactivePower;
	}

	public void setReactivePower(Double reactivePower) {
		this.reactivePower = reactivePower;
	}

	public Double getPowerFactor() {
		return this.powerFactor;
	}

	public void setPowerFactor(Double powerFactor) {
		this.powerFactor = powerFactor;
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

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}