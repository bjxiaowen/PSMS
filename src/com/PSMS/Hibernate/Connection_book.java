/** * * 
* 文件名称: Connection_book.java *
* 
* 客户通讯录信息类，对应数据库中Connection_book表 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-22 下午6:55:40 *
* * @author liu.yang 
*/ 
package com.PSMS.Hibernate;

public class Connection_book {
	/** 
	* 客户通讯录信息类，对应数据库中Connection_book表，其中id是主键*
	* @author liu.yang  
	* @date 2014-10-22 
	* @param id 
	* @param Company_name 
	* @param telephone
	* @param email
	* @param QQ
	* @param Home_page
	* @param addr
	* @param more 
	*/ 
	private int id;
	private String Company_name;
	private String telephone;
	private String email;
	private String fax;
	private String QQ;
	private String Home_page;
	private String addr;
	private String more;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_name() {
		return Company_name;
	}
	public void setCompany_name(String company_name) {
		Company_name = company_name;
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
	
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getHome_page() {
		return Home_page;
	}
	public void setHome_page(String home_page) {
		Home_page = home_page;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	
	
	
	

}
