/** * * 
* 文件名称: Connection_bookDAO.java *
* 
* 客户通讯录管理，增删改查客户信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-22 下午6:55:40 *
* @author liu.yang 
*/
package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.Connection_book;

/**
 * 客户通讯录管理需要的操作函数*
 * 
 * @author liu.yang
 * @date 2014-10-22
 */
public interface Connection_bookDAO {

	List<Connection_book> getAllInformation();// 获取所有客户通讯录信息

	boolean addConnectionBook(Connection_book connection_book);// 插入客户数据

	boolean deleteConnectionBookById(int id);// 删除客户数据

	List<Connection_book> getConnectionBookByCompany_name(String company_name);// 根据公司名称查询客户

}
