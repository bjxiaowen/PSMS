/** * * 
* 文件名称: HistoryOfYear.java *
* 
* 年发电量历史统计数据类，对应数据库中HistoryOfYear表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-02 下午5:40:23 *
* * @author min.li 
*/
package com.PSMS.Hibernate;

public class HistoryOfYear {
	/** 
	* 年发电量历史统计数据类，对应数据库中HistoryOfYear表，其中id是主键，PS_id是（来自PS_information表）的外键*
	* @author min.li 
	* @date 2014-10-02 
	* @param id 
	* @param PS_id 
	* @param total_power
	* @param total_hour
	* @param year
	*/	
	private int id;
	private int PS_id;
    private double total_power;
    private int total_hour;
    private int year;
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
    
    
}
