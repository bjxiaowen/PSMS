package com.PSMS.Hibernate;

/**
 * JunctionBox1 entity. @author MyEclipse Persistence Tools
 */

public class JunctionBox implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parameterId;
	private Integer pvUnit;
	private Double pvCurrent;
	private Double pvVoltage;
	private String time;
	private String state;	
	private Integer flag;

	



	// Constructors

	/** default constructor */
	public JunctionBox() {
	}

	/** minimal constructor */
	public JunctionBox(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JunctionBox(Integer id, Integer parameterId, Integer pvUnit,
			Double pvCurrent, Double pvVoltage, String time) {
		this.id = id;
		this.parameterId = parameterId;
		this.pvUnit = pvUnit;
		this.pvCurrent = pvCurrent;
		this.pvVoltage = pvVoltage;
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

	public Integer getPvUnit() {
		return this.pvUnit;
	}

	public void setPvUnit(Integer pvUnit) {
		this.pvUnit = pvUnit;
	}

	public Double getPvCurrent() {
		return this.pvCurrent;
	}

	public void setPvCurrent(Double pvCurrent) {
		this.pvCurrent = pvCurrent;
	}

	public Double getPvVoltage() {
		return this.pvVoltage;
	}

	public void setPvVoltage(Double pvVoltage) {
		this.pvVoltage = pvVoltage;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	

}