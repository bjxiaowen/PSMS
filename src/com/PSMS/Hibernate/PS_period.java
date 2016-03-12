/** * * 
* 文件名称: PS_period.java *
* 
* 电站分期类，对应数据库中PS_period表 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-12 下午9:46:33 *
* * @author jie.yang 
*/
package com.PSMS.Hibernate;

public class PS_period {
	/** 
	* 电站分期类，对应数据库中PS_period表，其中id是主键，PS_id是（来自PS_information表）的外键*
	* @author jie.yang 
	* @date 2014-12-12
	* @param id 
	* @param PS_id 
	* @param Period_num 
	* @param Period_capacity 
	* @param Period_unit_num
	* @param Period_time	 	
	*/ 
	private int id;
	private int PS_id;
	private Integer Period_num;
	private String Period_capacity;
	private Integer Period_unit_num;
	private String Period_time;
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
	public Integer getPeriod_num() {
		return Period_num;
	}
	public void setPeriod_num(Integer period_num) {
		Period_num = period_num;
	}
	public String getPeriod_capacity() {
		return Period_capacity;
	}
	public void setPeriod_capacity(String period_capacity) {
		Period_capacity = period_capacity;
	}
	public Integer getPeriod_unit_num() {
		return Period_unit_num;
	}
	public void setPeriod_unit_num(Integer period_unit_num) {
		Period_unit_num = period_unit_num;
	}
	public String getPeriod_time() {
		return Period_time;
	}
	public void setPeriod_time(String period_time) {
		Period_time = period_time;
	}
	
}
