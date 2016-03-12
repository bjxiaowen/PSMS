/** * * 
* 文件名称: M_user.java *
* 
* 用户信息类，对应数据库中M_user表*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-05 下午2:30:40 *
* * @author jiaojiao.wang 
*/
package com.PSMS.Hibernate;

/** 
* 用户信息类，对应数据库中M_user表，其中id是主键，Role_id和PS_id是（来自Role表 和PS_information表）的外键*
* @author jiaojiao.wang 
* @date 2014-10-05 
* @param id 
* @param User_name 
* @param password
* @param name
* @param telephone
* @param email
* @param Role_id
* @param PS_id 
*/ 
public class M_user {
	
	private int id;
	private String User_name;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private int Role_id;
	private Integer PS_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole_id() {
		return Role_id;
	}
	public void setRole_id(int role_id) {
		Role_id = role_id;
	}
	public Integer getPS_id() {
		return PS_id;
	}
	public void setPS_id(Integer pS_id) {
		PS_id = pS_id;
	}
	
	
	

}
