/** * * 
* 文件名称: HistoryOfMonth.java *
* 
* 月发电量历史统计数据类，对应数据库中HistoryOfMonth表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-03 下午6:02:10 *
* * @author jiaojiao.wang 
*/
package com.PSMS.Hibernate;

public class HistoryOfMonth {
	/** 
	* 月发电量历史统计数据类，对应数据库中HistoryOfMonth表，其中id是主键，PS_id是（PS_information表）的外键*
	* @author jiaojiao.wang 
	* @date 2014-10-03 
	* @param id 
	* @param PS_id 
	* @param total_power
	* @param total_hour
	* @param year
	* @param month
	*/		
	private int id;
	private int PS_id;
    private double total_power;
    private int total_hour;
    private int year;
    private int month;
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
	public double getTotal_power() {
		return total_power;
	}
	public void setTotal_power(double total_power) {
		this.total_power = total_power;
	}
	public int getTotal_hour() {
		return total_hour;
	}
	public void setTotal_hour(int total_hour) {
		this.total_hour = total_hour;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
    
}
