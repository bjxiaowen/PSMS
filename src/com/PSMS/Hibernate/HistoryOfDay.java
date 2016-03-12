/** * * 
* 文件名称: HistoryOfDay.java *
* 
* 日发电量历史统计数据类，对应数据库中HistoryOfDay表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-04 下午9:56:23 *
* * @author liu.yang 
*/
package com.PSMS.Hibernate;

public class HistoryOfDay {
	/** 
	* 日发电量历史统计数据类，对应数据库中HistoryOfDay表，其中id是主键，PS_id是（来自PS_information表）的外键*
	* @author liu.yang 
	* @date 2014-10-04 
	* @param id 
	* @param PS_id 
	* @param total_power
	* @param grid_connection_power
	* @param power_consumption
	* @param total_irradiation
	* @param direct_radiation
	* @param diffuse_radiation 
	* @param max_irradiation
	* @param year
	* @param month
	* @param day
	*/
	private int id;
	private int PS_id;
    private double total_power;
    private double grid_connection_power;
    private double power_consumption;
    private double total_irradiation;
    private double direct_radiation;
    private double diffuse_radiation;
    private double max_irradiation;
    private int year;
    private int month;
    private int day;
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
	public double getGrid_connection_power() {
		return grid_connection_power;
	}
	public void setGrid_connection_power(double grid_connection_power) {
		this.grid_connection_power = grid_connection_power;
	}
	public double getPower_consumption() {
		return power_consumption;
	}
	public void setPower_consumption(double power_consumption) {
		this.power_consumption = power_consumption;
	}
	public double getTotal_irradiation() {
		return total_irradiation;
	}
	public void setTotal_irradiation(double total_irradiation) {
		this.total_irradiation = total_irradiation;
	}
	public double getDirect_radiation() {
		return direct_radiation;
	}
	public void setDirect_radiation(double direct_radiation) {
		this.direct_radiation = direct_radiation;
	}
	public double getDiffuse_radiation() {
		return diffuse_radiation;
	}
	public void setDiffuse_radiation(double diffuse_radiation) {
		this.diffuse_radiation = diffuse_radiation;
	}
	public double getMax_irradiation() {
		return max_irradiation;
	}
	public void setMax_irradiation(double max_irradiation) {
		this.max_irradiation = max_irradiation;
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
    
    
}
