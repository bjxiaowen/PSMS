/** * * 
* 文件名称: HistoryOfFailure.java *
* 
* 设备故障历史数据类，对应数据库中HistoryOfFailure表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-07 下午6:20:06 *
* * @author jie.yang 
*/
package com.PSMS.Hibernate;

public class HistoryOfFailure {
	/** 
	* 设备故障历史数据类，对应数据库中HistoryOfFailure表,其中id是主键*
	* @author min.li 
	* @date 2014-10-07 
	* @param id 
	* @param Ps_name 
	* @param Device_type
	* @param Device_name
	* @param state
	* @param Failure_meaning
	* @param time
	*/
	private int id;
	private String Ps_name;
	private String Device_type;
	private String Device_name;
	private int state;
	private String Failure_meaning;
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPs_name() {
		return Ps_name;
	}
	public void setPs_name(String ps_name) {
		Ps_name = ps_name;
	}
	public String getDevice_type() {
		return Device_type;
	}
	public void setDevice_type(String device_type) {
		Device_type = device_type;
	}
	public String getDevice_name() {
		return Device_name;
	}
	public void setDevice_name(String device_name) {
		Device_name = device_name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getFailure_meaning() {
		return Failure_meaning;
	}
	public void setFailure_meaning(String failure_meaning) {
		Failure_meaning = failure_meaning;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
