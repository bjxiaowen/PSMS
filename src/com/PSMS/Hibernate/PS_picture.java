/** * * 
* 文件名称: PS_picture.java *
* 
* 电站分期类，对应数据库中PS_picture表 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-11 下午2:34:33 *
* * @author jie.yang 
*/
package com.PSMS.Hibernate;

public class PS_picture {
	/** 
	* 电站分期类，对应数据库中PS_picture表，其中id是主键，PS_id是（来自PS_information表）的外键*
	* @author jie.yang 
	* @date 2014-11-11
	* @param id 
	* @param PS_id 
	* @param name 
	* @param Picture_url 	 	
	*/ 
	private int id;
	private int PS_id;
	private String name;
	private String Picture_url;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture_url() {
		return Picture_url;
	}
	public void setPicture_url(String picture_url) {
		Picture_url = picture_url;
	}


	
}
